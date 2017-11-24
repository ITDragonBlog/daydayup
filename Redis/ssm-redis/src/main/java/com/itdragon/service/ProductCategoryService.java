package com.itdragon.service;

import java.util.List;

import com.itdragon.common.pojo.EUTreeNode;

public interface ProductCategoryService {
	
	List<EUTreeNode> getCategoryList(long parentId);
	
}
