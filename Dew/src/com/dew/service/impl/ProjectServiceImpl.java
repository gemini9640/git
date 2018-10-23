package com.dew.service.impl;



import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dew.db.Project;
import com.dew.mapper.ImageInfoMapper;
import com.dew.mapper.ProjectMapper;
import com.dew.service.ProjectService;
import com.dew.util.Const;
import com.dew.util.DateUtils;
import com.dew.util.ImageUtil;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	private ProjectMapper projectMapper;
	@Autowired
	private ImageInfoMapper imageInfoMapper;
	
	public List<Project> selectAll() {
		return projectMapper.selectAll();
	}
	
	public Project getProjectById(Integer id) {
		return projectMapper.selectByPrimaryKey(id);
	}
	
	public Project putFrontImg(Integer projectId, String imgName) {
		Project project = projectMapper.selectByPrimaryKey(projectId);
		if (project == null) 
			return null;
		project.setImgPath(imgName);
		projectMapper.updateByPrimaryKeySelective(project);
		return project;
	}
	
	public String deleteById(Integer id) {
		if(projectMapper.deleteByPrimaryKey(id)>0) {
			if(imageInfoMapper.deleteByProjectId(id)>0) {
				ImageUtil.deleteDirectory(Const.IMGPATH+id);
				return null;
			}
			return "删除图片失败";
		}
		return "删除失败";
	}
	
	public void add(String project, String title, String caption, String content) {
		Project pj = assembleProject(project, title, caption, content, DateUtils.convertTimestamp(new Date()));
		projectMapper.insert(pj);
	}
	
	public String edit(Integer id, String project, String title, String caption, String content) {
		int resultCount = projectMapper.updateByPrimaryKeySelective(assembleProject(id, project, title, caption, content));
		if(resultCount < 1)
			return "更新失败";
		return null;
	}
	
	private Project assembleProject(String project, String title, String caption, String content, Timestamp createTime) {
		Project pj = new Project();
		pj.setProject(project);
		pj.setTitle(title);
		pj.setCaption(caption);
		pj.setContent(content);
		pj.setCreateTime(createTime);
		return pj;
	}
	
	private Project assembleProject(Integer id, String project, String title, String caption, String content) {
		Project pj = new Project();
		pj.setId(id);
		if(project != null)
			pj.setProject(project);
		if(title != null)
			pj.setTitle(title);
		if(caption != null)
			pj.setCaption(caption);
		if(content != null)
			pj.setContent(content);
		return pj;
	}
}