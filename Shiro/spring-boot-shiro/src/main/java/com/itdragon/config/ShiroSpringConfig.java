package com.itdragon.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Shiro 配置，重点
 * @author itdragon
 *
 */
@Configuration
public class ShiroSpringConfig {

	private static final transient Logger log = LoggerFactory.getLogger(ShiroSpringConfig.class);

	/**
	 * 配置拦截器
	 *  
	 * 定义拦截URL权限，优先级从上到下 
	 * 1). anon  : 匿名访问，无需登录 
	 * 2). authc : 登录后才能访问 
	 * 3). logout: 登出
	 * 4). roles : 角色过滤器
	 * 
	 * URL 匹配风格
	 * 1). ?：匹配一个字符，如 /admin? 将匹配 /admin1，但不匹配 /admin 或 /admin/；
	 * 2). *：匹配零个或多个字符串，如 /admin* 将匹配 /admin 或/admin123，但不匹配 /admin/1；
	 * 2). **：匹配路径中的零个或多个路径，如 /admin/** 将匹配 /admin/a 或 /admin/a/b
	 * 
	 * 配置身份验证成功，失败的跳转路径
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(DefaultWebSecurityManager securityManager) {
		log.info("^^^^^^^^^^^^^^^^^^^^ ITDragon 配置Shiro拦截工厂");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		filterChainDefinitionMap.put("/static/**", "anon");	// 静态资源匿名访问
		filterChainDefinitionMap.put("/employees/login", "anon");// 登录匿名访问
		filterChainDefinitionMap.put("/logout", "logout");	// 用户退出，只需配置logout即可实现该功能
		filterChainDefinitionMap.put("/**", "authc");		// 其他路径均需要身份认证，一般位于最下面，优先级最低
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		shiroFilterFactoryBean.setLoginUrl("/login");		// 登录的路径
		shiroFilterFactoryBean.setSuccessUrl("/dashboard");	// 登录成功后跳转的路径
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");	// 验证失败后跳转的路径
		return shiroFilterFactoryBean;
	}
	
	/**
     * 配置Shiro生命周期处理器
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    
    /**
     * 自动创建代理类，若不添加，Shiro的注解可能不会生效。
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    
    /**
     * 开启Shiro的注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
	
    /**
     * 配置加密匹配，使用MD5的方式，进行1024次加密
     */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("MD5");
		hashedCredentialsMatcher.setHashIterations(1024);
		return hashedCredentialsMatcher;
	}

	/**
	 * 自定义Realm，可以多个
	 */
	@Bean
	public ITDragonShiroRealm itDragonShiroRealm() {
		ITDragonShiroRealm itDragonShiroRealm = new ITDragonShiroRealm();
		itDragonShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return itDragonShiroRealm;
	}

	/**
	 * SecurityManager 安全管理器；Shiro的核心
	 */
	@Bean
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(itDragonShiroRealm());
		return securityManager;
	}

}
