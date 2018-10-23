package com.dew.controller.admin;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dew.controller.base.BaseController;
import com.dew.db.Project;
import com.dew.service.ImgService;
import com.dew.service.ProjectService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


@Controller
@RequestMapping(value="/admin/project")
public class BackProjectController extends BaseController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private ImgService imgService;
	
	@RequestMapping(value="/addStart.do")
	public String addStart(){
		return "WEB-INF/jsp/admin/project/addStart";
	}

	@RequestMapping(value="/add.do")
	public String add(@RequestParam Map<String,String> map){
		
		String project = map.get("project");
		String title = map.get("title");
		String caption = map.get("caption");
		String content = map.get("content");
		
		String errorMsg = null;
		if(StringUtils.isEmpty(project)) 
			errorMsg = "project";
		else if(StringUtils.isEmpty(title)) 
			errorMsg = "title";
		else if(StringUtils.isEmpty(caption)) 
			errorMsg = "caption";
		else if(StringUtils.isEmpty(content)) 
			errorMsg = "content";
//		else if(StringUtils.isEmpty(getUsername()))
//			errorMsg = "用户不存在";
		
		if(errorMsg != null) 
			return "forward:/admin/project/addStart.do";
		projectService.add(project, title, caption, content);
		return "redirect:/admin/project/listAll.do";
	}
	
	@RequestMapping(value="/listAll.do")
	public ModelAndView listAll() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("projectList", projectService.selectAll());
		mv.setViewName("WEB-INF/jsp/admin/project/list");
		return mv;
	}
	
	@RequestMapping(value="/detail.do")
	public ModelAndView detail(Integer id){
		return detailModelAndView(id, null);
	}
	
	private ModelAndView detailModelAndView(Integer id, String resultMsg) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("project", projectService.getProjectById(id));
		mv.addObject("imgs", imgService.queryByPrjectId(id));
		mv.addObject("resultMsg", resultMsg);
		mv.setViewName("WEB-INF/jsp/admin/project/detail");
		return mv;
	}
	
	@RequestMapping(value="/putFrontImg.do")
	@ResponseBody
	public JsonObject putFrontImg(@RequestParam Map<String,String> map) {
		JsonObject jo = new JsonObject();
		String projectId =  map.get("projectId");
		String imgName = map.get("imgName");
		try {
			Project project = projectService.putFrontImg(Integer.parseInt(projectId), imgName); 
	        jo.addProperty("isSuccess", 0);
	        jo.addProperty("errormsg","success");
	        jo.addProperty("project", new Gson().toJson(project));
		} catch (Exception e) {
			e.printStackTrace();
			jo.addProperty("errormsg",e.getMessage());
	        jo.addProperty("isSuccess", 1);
		}
		return jo;
	}
	
	@RequestMapping(value="/delete.do")
	@ResponseBody
	public JsonObject delete(Integer id) {
		JsonObject jo = new JsonObject();
		String resultMsg;
		int isSuccess = 1;
		try {
			if((resultMsg = projectService.deleteById(id))==null) {
				resultMsg = "删除成功";
				isSuccess = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMsg = e.getMessage();
		}
		jo.addProperty("isSuccess", isSuccess);
        jo.addProperty("resultMsg",resultMsg);
		return jo;
	}
	
	@RequestMapping(value="/edit.do")
	public ModelAndView edit(@RequestParam Map<String, String> map) {
		String resultMsg;
		String idStr = map.get("id");
		String project = map.get("project");
		String title = map.get("title");
		String caption = map.get("caption");
		String content = map.get("content");
		Integer id = null;
		if(StringUtils.isEmpty(idStr))
			resultMsg = "id为空，更新失败";
		else {
			try {
				id = Integer.valueOf(idStr);
				resultMsg = projectService.edit(id, project, title, caption, content);
				if(resultMsg == null)
					resultMsg = "更新成功";
			} catch (Exception e) {
				e.printStackTrace();
				resultMsg = e.getMessage();
			}
		}
//        ModelAndView mv = new ModelAndView("forward:/admin/project/detail.do");
//        mv.addObject("id", id);
//		mv.addObject("resultMsg", resultMsg);
//		System.out.println(resultMsg);
		return detailModelAndView(id, resultMsg);
	}
}
