package com.itdragon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	/**
	 * 打开首页
	 */
	@RequestMapping("/tree")
	public String showIndex() {
		System.out.println("-------------^^^^");
		return "itemCategroy";
	}
	
	/**
	 * 展示其他页面
	 * <p>Title: showpage</p>
	 * <p>Description: </p>
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String showpage(@PathVariable String page) {
		return page;
	}
}
