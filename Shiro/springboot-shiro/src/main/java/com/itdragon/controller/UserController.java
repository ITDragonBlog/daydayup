package com.itdragon.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itdragon.pojo.ItdragonResult;
import com.itdragon.pojo.SysPermission;
import com.itdragon.pojo.User;
import com.itdragon.service.SysPermissionService;
import com.itdragon.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
	
	private static final transient Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	private static final String PAGE_SIZE = "10";

	@RequestMapping(value="employees", method = RequestMethod.GET)
	public String getSysPermissions(@RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) Integer pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = new HashMap<>();
		Page<User> employees = userService.getUsers(searchParams, pageNumber, pageSize, sortType);
		model.addAttribute("employees", employees);
		model.addAttribute("sortType", sortType);
		return "employees/manageEmployees";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public ItdragonResult userLogin(@RequestParam("username") String username, 
			@RequestParam("password") String password, HttpServletRequest request) {
		ItdragonResult result = new ItdragonResult();
		try {
			Subject currentUser = SecurityUtils.getSubject();
			if (!currentUser.isAuthenticated()) {
	            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//	            token.setRememberMe(true);
	            try {
	                currentUser.login(token);	// 执行登录
	                SavedRequest savedRequest = WebUtils.getSavedRequest(request);
	                // 获取登录前的最后一个页面
	    			String url = null != savedRequest ?
	    					(null != savedRequest.getRequestUrl()?savedRequest.getRequestUrl():""):"";
	                result.setStatus(200);
	                result.setData(url);
	                System.out.println("result : " + result.getData());
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
