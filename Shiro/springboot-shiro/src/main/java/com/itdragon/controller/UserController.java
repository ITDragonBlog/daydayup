package com.itdragon.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itdragon.pojo.ItdragonResult;
import com.itdragon.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
	
	private static final transient Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public ItdragonResult userLogin(@RequestParam("username") String username, 
			@RequestParam("password") String password) {
		ItdragonResult result = new ItdragonResult();
		try {
			System.out.println("`````````username : " + username);
			System.out.println("`````````password : " + password);
			Subject currentUser = SecurityUtils.getSubject();
			if (!currentUser.isAuthenticated()) {
	            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//	            token.setRememberMe(true);
	            try {
	                currentUser.login(token);	// 执行登录
	                result.setStatus(200);
	            } catch (AuthenticationException ae) {
	            	log.info("^^^^^^^^^^^^^^^^^^^^ ITDragon 登录失败: " + ae.getMessage());
	            	ae.printStackTrace();
	            	result.setStatus(500);
	            	result.setMsg("账号密码不匹配");
	            }
	        }
			result.setStatus(200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
	}
	
}
