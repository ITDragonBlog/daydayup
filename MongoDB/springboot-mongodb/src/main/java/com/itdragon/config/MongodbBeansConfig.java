package com.itdragon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itdragon.pojo.User;
import com.itdragon.repository.ITDragonMongoHelper;

/**
 * 
 * @author itdragon
 *
 */
@Configuration
public class MongodbBeansConfig {
	
	@Bean
	public ITDragonMongoHelper userMongoHelper() {
		return new ITDragonMongoHelper(User.class);
	}

}
