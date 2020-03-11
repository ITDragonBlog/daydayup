package com.itdragon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itdragon.pojo.User;
import com.itdragon.repository.ITDragonMongoHelper;

/**
 * ITDragonMongoHelper的bean配置管理类 
 * @author itdragon
 *
 */
@Configuration
public class MongodbBeansConfig {
	
	@Bean // 该方法名很重要
	public ITDragonMongoHelper userMongoHelper() {
		return new ITDragonMongoHelper(User.class);
	}

}
