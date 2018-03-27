package com.itdragon;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itdragon.config.ITDragonConfig;
import com.itdragon.config.ITDragonConfigAutowired;
import com.itdragon.config.ITDragonConfigLifeCycle;
import com.itdragon.config.ITDragonConfigValue;
import com.itdragon.dao.ITDragonDao;
import com.itdragon.entity.ITDragonEntity;
import com.itdragon.server.ITDragonServer;

/**
 * 测试类
 * @author itdragon
 *
 */
public class ApplicationStart {
	
	public static void main(String[] args) {
//		beanAndComponentScan();
//		scopeAndLazy();
//		lifeCycle();
//		autowired();
		AnnotationConfigApplicationContext annoApplicationContext = new AnnotationConfigApplicationContext(ITDragonConfigValue.class);
		ITDragonEntity itdragon = (ITDragonEntity) annoApplicationContext.getBean("valueBean");
		System.out.println(itdragon);
	}
	
	/**
	 * expected single matching bean but found 2: ITDragonDao,itdragonDao2
	 */
	private static void autowired() {
		AnnotationConfigApplicationContext annoApplicationContext = new AnnotationConfigApplicationContext(ITDragonConfigAutowired.class);
		ITDragonServer itdragonServer = annoApplicationContext.getBean(ITDragonServer.class);
		System.out.println(itdragonServer.toString());
		ITDragonDao itdragonDao = (ITDragonDao) annoApplicationContext.getBean("itdragonDao");
		System.out.println(itdragonDao.toString());
		annoApplicationContext.close();
	}
	
	
	/**
	 * 自定义Bean生命周期中的初始化方法和销毁方法
	 */
	private static void lifeCycle() {
		AnnotationConfigApplicationContext annoApplicationContext = new AnnotationConfigApplicationContext(ITDragonConfigLifeCycle.class);
		annoApplicationContext.close();
	}
	
	/**
	 * scope 和 Lazy 注解的常用语法
	 */
	private static void scopeAndLazy() {
		AnnotationConfigApplicationContext annoApplicationContext = new AnnotationConfigApplicationContext(ITDragonConfig.class);
		System.out.println("^^^^^^^^^^^^^^^^^^^^^Create Context");
		try {
			Thread.sleep(2000); // 用来判断prototype在IOC容器启动后并未创建对象，而是在使用时创建。
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ITDragonEntity scopeTopicBean1 = (ITDragonEntity) annoApplicationContext.getBean("scopeTopicBean");
		ITDragonEntity scopeTopicBean2 = (ITDragonEntity) annoApplicationContext.getBean("scopeTopicBean");
		System.out.println("Scope : " + (scopeTopicBean1 == scopeTopicBean2));
		annoApplicationContext.close();
	}
	
	/**
	 * bean 和 ComponentScan 注解的常用语法
	 */
	private static void beanAndComponentScan() {
		// 通过xml配置文件
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		ITDragonEntity xmlEntity = (ITDragonEntity) applicationContext.getBean("itdragonEntity");
		System.out.println("xmlEntity : " + xmlEntity.toString());
		String[] xmlBeanNames = applicationContext.getBeanDefinitionNames();
		for (String xmlBeanName : xmlBeanNames) {
			System.out.println("xmlBeanName : " + xmlBeanName);
		}
		applicationContext.close();
		// 通过注解JAVA编程配置
		AnnotationConfigApplicationContext annoApplicationContext = new AnnotationConfigApplicationContext(ITDragonConfig.class);
		ITDragonEntity annotationEntity = (ITDragonEntity) annoApplicationContext.getBean("itdragonBean");
		System.out.println("annotationEntity : " + annotationEntity.toString());
		String[] annoBeanNames = annoApplicationContext.getBeanDefinitionNames();
		for (String annoBeanName : annoBeanNames) {
			System.out.println("annoBeanName : " + annoBeanName);
			if ("itdragonFactoryBean".equals(annoBeanName)) { // FactoryBean知识测试，通过bean工厂注册bean
				ITDragonEntity factoryEntity = (ITDragonEntity) annoApplicationContext.getBean("itdragonFactoryBean");
				System.out.println("factoryEntity : " + factoryEntity.getClass());
			}
		}
		annoApplicationContext.close();
	}

}
