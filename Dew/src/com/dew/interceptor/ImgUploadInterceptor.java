package com.dew.interceptor;

import java.util.Iterator;
import java.util.Map;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class ImgUploadInterceptor extends HandlerInterceptorAdapter{
	protected Logger logger = Logger.getLogger(this.getClass());
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse response, Object handler) throws Exception {
	        MultipartResolver multipartResolver= new org.springframework.web.multipart.commons.CommonsMultipartResolver();  
	        if(multipartResolver.isMultipart(req)){  
	            MultipartHttpServletRequest  multipartRequest=(MultipartHttpServletRequest) req;  
	            Map<String, MultipartFile> files= multipartRequest.getFileMap();  
	            Iterator<String> iterator = files.keySet().iterator();  
	            while (iterator.hasNext()) {  
	                String formKey = (String) iterator.next();  
	                MultipartFile multipartFile = multipartRequest.getFile(formKey);  
	                if (StringUtils.isEmpty(multipartFile.getOriginalFilename())) {  
	                    String filename = multipartFile.getOriginalFilename();  
	                    
	                    return checkFile(filename);  
	                }  
	            }  
	            return true;  
	        }else{  
	            return false;  
	        }  
	    }  
	
	    private  boolean checkFile(String fileName){  
	        String suffixList="jpg,gif,png,jpeg";  
	        //获取文件后缀  
	        String suffix=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());  
	          
	        if(suffixList.contains(suffix.trim().toLowerCase()))
	            return true;  
	        return false;  
	    }  
	}
	
