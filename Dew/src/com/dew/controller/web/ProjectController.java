package com.dew.controller.web;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dew.controller.base.BaseController;
import com.dew.db.Project;
import com.dew.service.ImgService;
import com.dew.service.ProjectService;
import com.google.gson.JsonObject;


@Controller
@RequestMapping(value="/project")
public class ProjectController extends BaseController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private ImgService imgService;
	
	
	
	@RequestMapping(value="/listAll.do")
	@ResponseBody
	public List<Project> listAll() {
		return projectService.selectAll();
	}
	
	@RequestMapping(value="/detail.do")
	public ModelAndView detail(Integer id){
		ModelAndView mv = new ModelAndView();
		if(id == null) {
			mv.setViewName("web/index");
		} else {
			mv.addObject("project", projectService.getProjectById(id));
			mv.addObject("imgs", imgService.queryByPrjectId(id));
			mv.setViewName("web/project");
		}
		return mv;
	}
}
