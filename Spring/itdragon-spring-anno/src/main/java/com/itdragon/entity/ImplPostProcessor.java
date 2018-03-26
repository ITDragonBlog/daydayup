package com.itdragon.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Bean的后置处理器，通过实现BeanPostProcessor接口，在bean初始化前后执行逻辑
 * @author itdragon
 *
 */
@Component
public class ImplPostProcessor implements BeanPostProcessor{

	// 在初始化之后工作
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean.toString().contains("ITDragon")) { //过滤ITDragonEntity避免影响打印
			return bean;
		}
		System.out.println("^^^^^^^^^^^^^^^^^^^^^postProcessAfterInitialization : " + bean + " \t beanName : " + beanName);
		return bean;
	}

	// 在初始化之前工作
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean.toString().contains("ITDragon")) {
			return bean;
		}
		System.out.println("^^^^^^^^^^^^^^^^^^^^^postProcessBeforeInitialization : " + bean + " \t beanName : " + beanName);
		return bean;
	}

}
