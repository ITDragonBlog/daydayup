package com.itdragon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itdragon.mapper.pojo.SearchProductResult;
import com.itdragon.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService searchService;
	
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}
	
	@RequestMapping(value="/query", method=RequestMethod.GET)
	@ResponseBody
	public SearchProductResult search(@RequestParam("q")String query, 
			@RequestParam(defaultValue="1")Integer page, 
			@RequestParam(defaultValue="60")Integer rows) {
		SearchProductResult searchResult = null;
		try {
			// 处理乱码问题
			query = new String(query.getBytes("iso8859-1"), "utf-8");
			searchResult = searchService.search(query, page, rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchResult;
		
	}

}
