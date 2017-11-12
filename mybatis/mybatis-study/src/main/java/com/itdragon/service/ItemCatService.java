package com.itdragon.service;

import java.util.List;

import com.itdragon.pojo.TbItemCat;

public interface ItemCatService {

	 List<TbItemCat> getItemCatList(Long parentId) throws Exception	;
}