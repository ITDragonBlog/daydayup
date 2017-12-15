package com.itdragon.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itdragon.common.ItdragonUtils;
import com.itdragon.pojo.User;
import com.itdragon.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST)
	public String register(@Valid User user, HttpServletRequest request) {
//		userService.registerUser(user);
        try {
        	// 注册成功直接将当前用户保存到session中  
        	request.getSession().setAttribute("user", user);  
        } catch (Exception e) {
			e.printStackTrace();
		} 
		return "redirect:/login";
	}
	
}
