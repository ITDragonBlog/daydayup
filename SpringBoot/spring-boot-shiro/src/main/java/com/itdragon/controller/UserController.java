package com.itdragon.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itdragon.pojo.ItdragonResult;
import com.itdragon.pojo.User;
import com.itdragon.service.UserService;

/**
 * 用户页面的增删改查，分页，搜索
 * @author itdragon
 *
 */
@Controller
@RequestMapping("/employees")
public class UserController {
	
	private static final transient Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	private static final String PAGE_SIZE = "10";

	@RequestMapping(method = RequestMethod.GET)
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
	                // 获取登录前的最后一个页面
//	                SavedRequest savedRequest = WebUtils.getSavedRequest(request);
//	    			String url = null != savedRequest ?
//	    					(null != savedRequest.getRequestUrl()?savedRequest.getRequestUrl():""):"";
	                result.setStatus(200);
	                result.setData("");
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
	
	/**
	 * 删除用户功能，拥有employees:delete权限的用户才能删除
	 * @param id : 需要删除的数据ID
	 * @param redirectAttributes : SpringMVC重定向参数 http://blog.csdn.net/z69183787/article/details/52596987
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	@RequiresPermissions(value={"employees:delete"})
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		userService.deleteUser(id);
		redirectAttributes.addFlashAttribute("message", "删除用户成功");
		return "redirect:/employees";
	}
	
}
