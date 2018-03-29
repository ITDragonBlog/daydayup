package com.itdragon.config;

import org.springframework.beans.factory.FactoryBean;

import com.itdragon.entity.ITDragonEntity;

/**
 * 自定义Bean的工厂类
 * @author itdragon
 *
 */
public class ITDragonFactoryBean implements FactoryBean<ITDragonEntity>{

	public ITDragonEntity getObject() throws Exception {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^FactoryBean Create Bean");
		return new ITDragonEntity(); // 创建对象并返回到容器中
	}

	public Class<?> getObjectType() {
		return ITDragonEntity.class;
	}

	public boolean isSingleton() {
		return false; // 设置多实例,true则为单例
	}

}
