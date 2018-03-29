package com.itdragon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.itdragon.entity.ITDragonEntity;

/**
 * 知识点一： 引入外部文件，并从文件中获取值
 * @PropertySource 引入外部文件，支持多个，如果文件不存在会报错，可以通过设置参数ignoreResourceNotFound=true忽略
 * @Value 从外部文件中获取值，支持spel表达式，#{},${},string
 * @author itdragon
 *
 */
@Configuration
@PropertySource(value={"classpath:propertySource.properties","classpth:xxx.properties"},ignoreResourceNotFound=true)
public class ITDragonConfigValue {
	
	@Value("${ACCOUNT}")		// 从配置文件获取数据
	private String ACCOUNT;
	
	@Value("${PASSWORD}")
	private String PASSWORD;
	
	@Value("${AGE}")
	private Integer AGE;
	
	@Value("ITDragon")			// 普通赋值
	private String str;
	
	@Value("#{(1+2-3)/4*5}")	// 算术运算
	private String operator;
	
	@Value("#{1>2 || 2 <= 3}")	// 关系运算
	private Boolean comparison;
	
	@Value("#{systemProperties['java.version']}") // 系统配置：os.name
	private String systemProperties;
	
	@Value("#{T(java.lang.Math).abs(-18)}")	// 表达式
	private String mapExpression;
	
	@Bean("valueBean")
	public ITDragonEntity itdragonEntity() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^ str : " + str);
		System.out.println("^^^^^^^^^^^^^^^^^^^^ operator : " + operator);
		System.out.println("^^^^^^^^^^^^^^^^^^^^ comparison : " + comparison);
		System.out.println("^^^^^^^^^^^^^^^^^^^^ systemProperties : " + systemProperties);
		System.out.println("^^^^^^^^^^^^^^^^^^^^ mapExpression : " + mapExpression);
		return new ITDragonEntity(ACCOUNT, PASSWORD, AGE);
	}

}
