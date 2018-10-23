package com.dew.controller.admin;


import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.dew.controller.base.BaseController;
import com.dew.db.ImageInfo;
import com.dew.service.ImgService;
import com.dew.service.ProjectService;
import com.dew.util.Const;
import com.dew.util.ImageUtil;
import com.google.gson.JsonObject;



/** 
 * 说明：登录页面背景图片
 * 创建人：FH Q313596790
 * 创建时间：2016-06-03
 */
@Controller
@RequestMapping(value="/admin/img")
public class BackImgController extends BaseController {
	@Autowired
	private MultipartResolver multipartResolver;
	@Autowired
	private ProjectService projectService; 
	@Autowired
	private ImgService imgService;
	
	@RequestMapping(value="/upload")
	@ResponseBody
	public JsonObject upload(MultipartHttpServletRequest request, Integer projectId){
		String resultmsg = null;
		int isSuccess = 1;
		List<String> imgNames = new ArrayList<String>();;
		try {
			List<ImageInfo> imageInfos = ImageUtil.uploadImageByIOStream(request, Const.IMGPATH+projectId+"/");
			for (ImageInfo imageInfo : imageInfos) {
				imgService.add(projectId, imageInfo.getOldName(), imageInfo.getNewName(), null, null);
				imgNames.add(imageInfo.getNewName());
			}
			isSuccess = 0;
			resultmsg = "作品上传成功";
		} catch (Exception e) {
			resultmsg = e.getMessage();
			imgNames.clear();
			e.printStackTrace();
		}
		JsonObject jo = new JsonObject();
        jo.addProperty("isSuccess", isSuccess);
        jo.addProperty("resultmsg", resultmsg);
        jo.addProperty("imgNames", JSONObject.toJSONString(imgNames));
		return jo;
	}
	
	@RequestMapping(value="/delete.do")
	@ResponseBody
	public JsonObject delete(Integer imgId){
		String resultmsg = null;
		int isSuccess = 1;
		try {
			if((resultmsg = imgService.deleteByImageId(imgId)) == null) {
				isSuccess = 0;
				resultmsg = "作品删除成功";
			}
		} catch (Exception e) {
			resultmsg = e.getMessage();
			e.printStackTrace();
		}
		JsonObject jo = new JsonObject();
        jo.addProperty("isSuccess", isSuccess);
        jo.addProperty("resultmsg", resultmsg);
		return jo;
	}
	
	@RequestMapping(value="/showByPath.do")
	@ResponseBody
	public void showByPath(String folder, String path) {
		OutputStream os = null;
		try{
			String filePath = Const.IMGPATH+folder+"/"+path;
			getResponse().setHeader("Pragma", "No-cache");
			getResponse().setHeader("Cache-Control", "no-cache");
			getResponse().setDateHeader("Expires", 0);
			getResponse().setHeader("content-type", "image/jpeg");
			os = getResponse().getOutputStream();
			ImageUtil.showByIOStream(filePath, os);
		} catch (Exception e) {
			e.printStackTrace();
			responseWriter("error");
		} finally {
			try {
				if(os != null) {
					os.flush();
					os.close();
					getResponse().flushBuffer();
				}
			} catch (IOException e) {
				e.printStackTrace();
				responseWriter("error");
			}
		}
	}
	
	
	/**修改
	 * @param
	 * @throws Exception
	 */
//	@RequestMapping(value="/edit")
//	public ModelAndView edit() throws Exception{
//		ModelAndView mv = this.getModelAndView();
//		loginimgService.edit(pd);
//		mv.addObject("msg","success");
//		mv.setViewName("save_result");
//		return mv;
//	}
	
	
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
