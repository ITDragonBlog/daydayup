package com.itdragon.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 异常统一处理
 * 
 * Spring @ControllerAdvice 使用方法介绍： http://blog.csdn.net/jackfrued/article/details/76710885
 * @author itdragon
 */
@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(org.apache.shiro.authz.AuthorizationException.class)
    public String handleException(RedirectAttributes redirectAttributes, Exception exception, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("message", "抱歉！您没有权限执行这个操作，请联系管理员！");
		String url = WebUtils.getRequestUri(request);
		return "redirect:/" + url.split("/")[1];	// 请求的规则 : /page/operate
    }
	
}
