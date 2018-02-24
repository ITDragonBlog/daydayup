package com.itdragon.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * 配置拦截器FIlter
 * @author itdragon
 *
 */
public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter{  
  
    @Override  
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {  
        builder.addDecoratorPath("/*", "/WEB-INF/layouts/default.jsp")  // 配置装饰页面
               .addExcludedPath("/static/*") 	// 静态资源不拦截
               .addExcludedPath("/login**");  	// 登录页面不拦截
    }  

}
