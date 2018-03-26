package com.itdragon.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.itdragon.entity.BeanInitAndDestroy;

/**
 * Bean 的生命周期配置类
 * @author itdragon
 * 
 * 知识点一：Bean的生命周期
 * 单实例：创建--->初始化--->销毁（创建后交给容器管理，使用时从容器获取）
 * 多实例：创建--->初始化（创建后不交给容器管理，使用时再创建，容器不会去销毁）
 * 创建：单实例：容器启动时创建对象。多实例：容器启动后，使用对象时创建对象。
 * 初始化：对象创建后调用初始化方法。
 * 销毁：单实例：容器关闭后销毁对象。
 */
@Configuration
@ComponentScan("com.itdragon.entity")
public class ITDragonConfigLifeCycle {
	
	/**
	 * 知识点二：通过@Bean注解的initMethod和destroyMethod参数自定义初始化方法和销毁方法
	 */
	@Bean(initMethod="init", destroyMethod="destroy")
	public BeanInitAndDestroy beanInitAndDestroy() {
		return new BeanInitAndDestroy();
	}
	
	/**
	 * 知识点三：通过实现InitializingBean,DisposableBean接口自定义初始化方法和销毁方法
	 * 参考ImplInitAndDestroy类
	 */
	
	/**
	 * 知识点四：Bean的后置处理器，通过实现BeanPostProcessor接口，在bean初始化前后执行逻辑
	 * 		一、postProcessBeforeInitialization: 在初始化之前工作
	 * 		二、postProcessAfterInitialization : 在初始化之后工作
	 * 参考ImplPostProcessor类
	 */

}
