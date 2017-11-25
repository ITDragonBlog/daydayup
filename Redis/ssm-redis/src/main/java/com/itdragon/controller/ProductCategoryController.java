package com.itdragon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itdragon.common.pojo.EUTreeNode;
import com.itdragon.common.pojo.ResponseResult;
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
	
	@RequestMapping("/category/create")
	@ResponseBody
	public ResponseResult createCategoryList(@RequestParam("parentId") Long parentId, @RequestParam("name") String name) {
		return categoryService.createCategoryList(parentId, name);
	}
	
	@RequestMapping("/category/update")
	@ResponseBody
	public ResponseResult updateCategoryList(@RequestParam("categoryId") Long categoryId, @RequestParam("name") String name) {
		System.out.println(categoryId);
		System.out.println(name);
		return categoryService.updateCategoryList(categoryId, name);
	}
	
	@RequestMapping("/category/delete")
	@ResponseBody
	public ResponseResult deleteCategoryList(@RequestParam("parentId") Long parentId, @RequestParam("categoryId") Long categoryId) {
		System.out.println(categoryId);
		System.out.println(parentId);
		return categoryService.deleteCategoryList(parentId, categoryId);
	}

}
