package com.itdragon.controller;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itdragon.common.pojo.EUTreeNode;
import com.itdragon.service.ProductCategoryService;

@Controller
public class ProductCategoryController {
	
	@Autowired
	private ProductCategoryService categoryService;
	
	@RequestMapping("/")
	public String page() {
		return "redis";
	}
	
	@RequestMapping("/category")
	@ResponseBody
	private List<EUTreeNode> getAsyncCatList(@RequestParam(value="id",defaultValue="0") Long parentId) {
		List<EUTreeNode> results = categoryService.getCategoryList(parentId);
		return results;
	}

}
