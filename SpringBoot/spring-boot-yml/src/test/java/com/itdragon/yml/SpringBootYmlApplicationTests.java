package com.itdragon.yml;

import com.itdragon.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootYmlApplicationTests {

	@Autowired
	private UserInfo userInfo;

	@Autowired
	private YamlEntity yamlEntity;

	@Autowired
	private ConfigurationPropertiesEntity configurationPropertiesEntity;

	@Autowired
	private ValueEntity valueEntity;

	@Autowired
	private RandomEntity randomEntity;

	@Test
	public void contextLoads() {
//		System.out.println("YAML Grammar : " + yamlEntity);
//		System.out.println("UserInfo : " + userInfo);
//		System.out.println("ConfigurationProperties Grammar : " + configurationPropertiesEntity);
//		System.out.println("Value Grammar : " + valueEntity);
		System.out.println("Random Grammar : " + randomEntity);
	}

}
