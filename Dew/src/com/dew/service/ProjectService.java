package com.dew.service;

import java.util.List;

import com.dew.db.Project;

public interface ProjectService {
	public void add(String project, String title, String caption, String content);
	
	public List<Project> selectAll();
	
	public Project getProjectById(Integer id);
	
	public Project putFrontImg(Integer projectId, String imgName);
	
	public String deleteById(Integer id);
	
	public String edit(Integer id, String project, String title, String caption, String content);
}