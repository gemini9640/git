package com.dew.controller.web;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dew.controller.base.BaseController;
import com.dew.util.Const;
import com.dew.util.ImageUtil;

@Controller
@RequestMapping(value="/img")
public class ImgController extends BaseController {
	@RequestMapping(value="/show.do")
	@ResponseBody
	public void show(String folder, String path) {
		try{
			String filePath = Const.IMGPATH+folder+"/"+path;
			getResponse().setHeader("Pragma", "No-cache");
			getResponse().setHeader("Cache-Control", "no-cache");
			getResponse().setDateHeader("Expires", 0);
			getResponse().setHeader("content-type", "image/jpeg");
			ImageUtil.showByIOStream(filePath, getResponse().getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			responseWriter("error");
		} finally {
			try {
				if(getResponse().getOutputStream() != null) {
					getResponse().getOutputStream().flush();
					getResponse().getOutputStream().close();
					getResponse().flushBuffer();
				}
			} catch (IOException e) {
				e.printStackTrace();
				responseWriter("error");
			}
		}
	}
}
