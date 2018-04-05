package com.itdragon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 该方法影响了静态资源的加载问题，待查找原因
 * Spring boot2.x 和 Spring5 不推荐使用WebMvcConfigurerAdapter
 * WebMvcConfigurationSupport
 */
@Configuration
public class ITDragonWebConfig extends WebMvcConfigurerAdapter{

    /**
     * 设置统一的首页路径
     */
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/dashboard.html").setViewName("dashboard");
            }

            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new ITDragonLoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/","/login.html","/user/login");
            }
        };
        return  adapter;
    }


    @Bean
    public LocaleResolver localeResolver() {
        return  new ITDragonLocaleResolver();
    }

}
