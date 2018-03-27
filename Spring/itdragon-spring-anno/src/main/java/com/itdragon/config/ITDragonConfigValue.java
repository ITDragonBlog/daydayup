package com.itdragon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.itdragon.entity.ITDragonEntity;

@Configuration
@PropertySource(value={"classpath:propertySource.properties","classpth:xxx.properties"},ignoreResourceNotFound=true)
public class ITDragonConfigValue {
	
	@Value("${ACCOUNT}")
	private String ACCOUNT;
	
	@Value("${PASSWORD}")
	private String PASSWORD;
	
	@Value("${AGE}")
	private Integer AGE;
	
	
	@Bean("valueBean")
	public ITDragonEntity itdragonEntity() {
		return new ITDragonEntity(ACCOUNT, PASSWORD, AGE);
	}

}
