package com.itdragon.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * 配置SiteMesh拦截器FIlter，指定装饰页面和不需要拦截的路径
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
