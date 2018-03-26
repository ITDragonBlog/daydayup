package com.itdragon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.itdragon.entity.ITDragonEntity;
import com.itdragon.server.ITDragonServer;

/**
 * 组件注入知识配置类
 * 被注解@Configuration修饰的类表示配置类，类似spring的applicationContext.xml
 * @author itdragon
 */
@Configuration
/**
 * 知识点二：配置自动扫描包路径
 * 一、注解ComponentScan的value值设置自动扫描包的路径
 * 二、注解ComponentScan的excludeFilters值设置扫描排除的规则
 * 	1)、通过注解@Filter设置排除的类型,type=ANNOTATION表示按照注解包含排除。classes是具体的注解，如Controller,Server,Repository
 * 三、注解ComponentScan的includeFilters值设置扫描加入的规则
 *  1)、通过设置useDefaultFilters=false关闭Spring默认扫描全部的功能，激活includeFilters作用。
 *  
 * 知识点三：@Filter常用的拦截类型
 * 一、FilterType.ANNOTATION：按照注解
 * 二、FilterType.ASSIGNABLE_TYPE：按照给定的类型，包括其子类和实现类
 * 三、FilterType.CUSTOM：使用自定义规则
 * 
 * 第一个ComponentScan注解表示在指定包下不扫描通过Controller注解修饰的类和ITDragonServer类及其子类和实现类
 * 第二个ComponentScan注解表示在指定包下只扫描通过Controller注解修饰的类
 * 第三个ComponentScan注解表示在指定包下根据自定义拦截规则，不扫描满足规则的类
 */
@ComponentScan(value="com.itdragon",excludeFilters={@Filter(type=FilterType.ANNOTATION,classes={Controller.class}),
		@Filter(type=FilterType.ASSIGNABLE_TYPE,classes={ITDragonServer.class})})
//@ComponentScan(value="com.itdragon",includeFilters={@Filter(type=FilterType.ANNOTATION,classes={Controller.class})},useDefaultFilters=false)
//@ComponentScan(value="com.itdragon",excludeFilters={@Filter(type=FilterType.CUSTOM,classes={ITDragonCustomTypeFilter.class})})
public class ITDragonConfig {
	
	/**
	 * 知识点一：配置bean
	 * 一、注解Bean的value值表示bean的id
	 * 二、注解Bean的value值未设置，则方法名表示bean的id
	 * @return
	 */
	@Bean(value="itdragonBean")
	public ITDragonEntity itdragonEntity() { //方法名很重要，类似xml的id名，也可以通过@bean的value值重定义
		return new ITDragonEntity("itdragon", "configuration-password", 25);
	}
	
	/**
	 * 知识点四：Scope属性
	 * @Scope，调整作用域，默认单实例
	 * singleton：单实例：ioc容器启动后创建对象放到ioc容器中，需要是从容器中获取。
	 * prototype：多实例：ioc容器启动后每次获取对象时都要创建对象。
	 * request：同一次请求创建一个实例
	 * session：同一个session创建一个实例
	 * 
	 * 知识点五：懒加载
	 * 针对单实例而言，在容器启动后不创建对象，在第一次使用Bean时创建对象。可以理解为单实例的一种补充。
	 * 
	 */
//	@Scope("prototype")
	@Lazy
	@Bean
	public ITDragonEntity scopeTopicBean() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^Create Bean");
		return new ITDragonEntity("scopeBean", "singleton-prototype-request-session", 25);
	}
	
	/**
	 * 知识点六：Conditional条件判断
	 * 满足条件才会注册bean，可以修饰在类上，管理整个类下的组件注入。
	 */
	@Bean
	@Conditional({ITDragonCustomCondition.class})
	public ITDragonEntity conditionalBean() {
		return new ITDragonEntity("conditionalBean", "Conditional-Condition-CustomCondition", 25);
	}
	
	/**
	 * 知识点七：FactoryBean工厂Bean
	 * FactoryBean默认通过调用getObject创建的对象，通过调用isSingleton设置单实例和多实例。
	 */
	@Bean
	public ITDragonFactoryBean itdragonFactoryBean() {
		return new ITDragonFactoryBean();
	}

}
