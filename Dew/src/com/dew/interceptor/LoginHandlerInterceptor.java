package com.dew.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
//		if(user == null) {
//			logger.info("user dont have permission to access: redirect to "+Const.LOGIN);
//			response.sendRedirect(request.getContextPath() + Const.LOGIN);
//			return false;		
//		}
		return true;
	}
	
}
