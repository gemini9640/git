package com.dew.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dew.db.ImageInfo;
import com.dew.mapper.ImageInfoMapper;
import com.dew.service.ImgService;
import com.dew.util.Const;
import com.dew.util.ImageUtil;

@Service("imgService")
public class ImgServiceImpl implements ImgService{
	
	@Autowired
	private ImageInfoMapper imageInfoMapper;
	
	public ImageInfo add(Integer projectId, String nameOld, String nameNew, Integer cover, Integer index) {
		ImageInfo imageInfo = ImageUtil.assemblePictureInfo(projectId, nameOld, nameNew, cover, index);
		imageInfoMapper.insertSelective(imageInfo);
		return imageInfo;
	}
	
	public List<ImageInfo> queryByPrjectId(Integer id) {
		return imageInfoMapper.selectByProjectId(id);
	}
	
	public ImageInfo queryByImageId(Integer id) {
		return imageInfoMapper.selectByPrimaryKey(id);
	}
	
	public String deleteByImageId(Integer imgId) {
		ImageInfo imageInfo = imageInfoMapper.selectByPrimaryKey(imgId);
		if(imageInfoMapper.deleteByPrimaryKey(imgId)>0) {
			ImageUtil.delete(Const.IMGPATH+imageInfo.getProductid()+"/"+imageInfo.getNewName());
			return null;
		}
		return "删除失败";
	}
}