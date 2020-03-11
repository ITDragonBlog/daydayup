package com.itdragon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 开发阶段页面跳转
 * @author itdragon
 *
 */
@Controller
public class PageController {

//	@RequestMapping("/{page}")
//	public String showLogin(@PathVariable("page") String page) {
//		return page;
//	}
	
	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String dashboard() {
		return "dashboard";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping("/")
    public String index() {
        return "forward:/dashboard";
    }
	
}
