package com.itdragon.yml;

import com.itdragon.entity.UserInfo;
import com.itdragon.entity.YamlEntity;
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

	@Test
	public void contextLoads() {
		System.out.println("YAML Grammar : " + yamlEntity);
//		System.out.println("UserInfo : " + userInfo);
	}

}
