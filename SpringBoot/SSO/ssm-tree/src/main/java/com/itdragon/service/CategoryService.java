package com.itdragon.service;

import java.util.List;

import com.itdragon.common.pojo.EUTreeNode;

public interface CategoryService {

	/**
	 * 通过父节点，异步加载树菜单
	 * @param parentId
	 * @return
	 */
	List<EUTreeNode> getCategoryList(int parentId);
	
	/**
	 * 一次全部加载所有树节点
	 * @return
	 */
	List<EUTreeNode> getCategoryList();
}
