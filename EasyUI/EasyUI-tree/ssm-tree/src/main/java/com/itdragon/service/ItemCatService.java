package com.itdragon.service;

import java.util.List;

import com.itdragon.common.pojo.EUTreeNode;

public interface ItemCatService {

	List<EUTreeNode> getCatList(long parentId);
}
