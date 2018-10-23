package com.dew.mapper;

import java.util.List;

import com.dew.db.ImageInfo;

public interface ImageInfoMapper {
    int deleteByPrimaryKey(Integer id);

	int insert(ImageInfo record);

	int insertSelective(ImageInfo record);

	ImageInfo selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ImageInfo record);

	int updateByPrimaryKey(ImageInfo record);
	
	List<ImageInfo> selectByProjectId(Integer id);
    
    int deleteByProjectId(Integer id);
}