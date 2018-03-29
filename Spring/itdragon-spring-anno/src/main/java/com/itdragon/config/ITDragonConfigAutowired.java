package com.itdragon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.itdragon.dao.ITDragonDao;

/**
 * <h1>代码有问题，目前请忽略</h1>
 * 自动转载 @Autowired
 * Spring利用依赖注入完成对IOC容器中各个组件的依赖关系赋值。
 * 知识点一：@Autowired自动注入,通过设置required=false，通过@Primary：让Spring进行自动装配的时候，默认使用首选的bean
 * 
 * @Autowired 可以修饰构造器，参数，方法，属性；都是从容器中获取参数组件的值
 * 具体参考ITDragonDao类
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
