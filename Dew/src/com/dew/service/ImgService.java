package com.dew.service;

import java.util.List;

import com.dew.db.ImageInfo;


public interface ImgService {
	public ImageInfo add(Integer projectId, String nameOld, String nameNew, Integer cover, Integer index);
	
	public List<ImageInfo> queryByPrjectId(Integer id);
	
	public ImageInfo queryByImageId(Integer id);
	
	public String deleteByImageId(Integer imgId);
}