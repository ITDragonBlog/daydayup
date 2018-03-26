package com.itdragon.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 通过实现InitializingBean,DisposableBean接口自定义初始化方法和销毁方法
 * @author itdragon
 *
 */
@Component
public class ImplInitAndDestroy implements InitializingBean,DisposableBean{
	
	public ImplInitAndDestroy() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^ImplInitAndDestroy Constructor");
	}

	public void destroy() throws Exception {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^ImplInitAndDestroy Destroy");
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^ImplInitAndDestroy AfterPropertiesSet");
	}

}
