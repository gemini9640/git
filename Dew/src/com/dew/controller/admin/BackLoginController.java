package com.dew.controller.admin;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dew.controller.base.BaseController;

/**
 * 总入口
 */
@Controller
public class BackLoginController extends BaseController {

	/**访问登录页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login_toLogin")
	public ModelAndView toLogin(ModelAndView mv)throws Exception{
		mv.setViewName("WEB-INF/jsp/admin/index");
		return mv;
	}
	
	@RequestMapping(value="/logout")
	public String logout()throws Exception{
		getSession().invalidate();
		return "redirect:/login_toLogin";
	}
	
	/**请求登录，验证用户
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login_login.do")
	public ModelAndView login(@RequestParam Map<String,String> map)throws Exception{
		ModelAndView mv = new ModelAndView();
		String errInfo = "";
		String username = map.get("username");
		String password = map.get("password");
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			errInfo = "用户名或密码不能留空"; 	
			mv.addObject("errInfo", errInfo);
		} 
		mv.setViewName("forward:/login_toLogin");
		return mv;
	}
	
}
