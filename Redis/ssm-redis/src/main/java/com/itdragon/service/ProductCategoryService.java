package com.itdragon.service;

import java.util.List;

import com.itdragon.common.pojo.EUTreeNode;
import com.itdragon.common.pojo.ResponseResult;

public interface ProductCategoryService {
	
	List<EUTreeNode> getCategoryList(Long parentId);
	
	ResponseResult createCategoryList(Long parentId, String name);
	
	ResponseResult updateCategoryList(Long categoryId, String name);
	
	ResponseResult deleteCategoryList(Long parentId, Long categoryId);
	
}
