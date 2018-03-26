package com.itdragon.entity;

/**
 * 用于测试@Bean通过指定init-method和destroy-method自定义初始化和销毁方法的实体类
 * @author itdragon
 *
 */
public class BeanInitAndDestroy {

	public BeanInitAndDestroy() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^BeanInitAndDestroy Constructor");
	}
	
	public void init() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^BeanInitAndDestroy Init");
	}
	
	public void destroy() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^BeanInitAndDestroy Destroy");
	}


}
