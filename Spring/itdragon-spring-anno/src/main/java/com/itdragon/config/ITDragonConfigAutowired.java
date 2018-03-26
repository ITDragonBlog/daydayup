package com.itdragon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.itdragon.dao.ITDragonDao;

/**
 * 自动转载 @Autowired
 * Spring利用依赖注入完成对IOC容器中各个组件的依赖关系赋值。
 * 知识点一：@Autowired自动注入
 * 
 * 
 * @Primary 让Spring进行自动装配的时候，默认使用首选的bean；
 * 
 * @author itdragon
 *
 */
@Configuration
@ComponentScan("com.itdragon")
public class ITDragonConfigAutowired {
	
	
	@Bean("itdragonDao")
	public ITDragonDao itdragonDao() {
		return new ITDragonDao("v2");
	}

}
