package com.itdragon.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itdragon.mapper.pojo.FilterQueryKey;
import com.itdragon.mapper.pojo.SearchProductResult;
import com.itdragon.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService searchService;
	
	@RequestMapping("/")
	public String showIndex(Model model) {
		SearchProductResult searchResult;
		try {
			searchResult = searchService.search("*:*", new HashMap<>(), 1, 12);
			model.addAttribute("result", searchResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping(value="/query")
	public String search(@RequestParam("queryString")String query, 
			@RequestParam("catalog_name") String catalog_name, 
			@RequestParam("price") String price,
			@RequestParam("sort") String sort,
			@RequestParam(defaultValue="1")Integer page, 
			@RequestParam(defaultValue="12")Integer rows, Model model) {
		SearchProductResult searchResult = null;
		try {
			// 拼接复杂的查询语句
			Map<String, String> filterQueryMap = new HashMap<>();
			if (StringUtils.isNotBlank(catalog_name)) {
				filterQueryMap.put(FilterQueryKey.CATALOG_NAME.getValue(), catalog_name);
			}
			if (StringUtils.isNotBlank(price)) {
				filterQueryMap.put(FilterQueryKey.PRICE.getValue(), price);
			}
			if (StringUtils.isNotBlank(sort)) {
				filterQueryMap.put(FilterQueryKey.SORT.getValue(), sort);
			}
			searchResult = searchService.search(query, filterQueryMap, page, rows);
			model.addAttribute("result", searchResult);
			model.addAttribute("queryString", query);
			model.addAttribute("catalog_name", catalog_name);
			model.addAttribute("price", price);
			model.addAttribute("sort", sort);
			model.addAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
		
	}

}
