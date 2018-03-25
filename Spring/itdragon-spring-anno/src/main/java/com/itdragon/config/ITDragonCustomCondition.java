package com.itdragon.config;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 
 * @author itdragon
 *
 */
public class ITDragonCustomCondition implements Condition{

	/**
	 * 判断注册的bean中是否含有指定的bean
	 */
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// 获取bean的注册类
		BeanDefinitionRegistry registry = context.getRegistry();
		return registry.containsBeanDefinition("scopeTopicBean"); // 有则加载conditionalBean
	}

}
