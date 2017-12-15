package com.itdragon.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.itdragon.StartApplication;
import com.itdragon.common.ItdragonUtils;
import com.itdragon.pojo.User;
import com.itdragon.service.UserService;

/**
 * 待修改
 * @RunWith	//这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。
 * @SpringBootTest	//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下 
 * 
 * @author itdragon
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=StartApplication.class)
public class SpringbootStudyApplicationTests {
	
	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {
		
	}
	
	@Test
	public void registerUser() {
		User user = new User();
		user.setAccount("itdragon");
		user.setUserName("ITDragonBlog");
		user.setEmail("itdragon@163.com");
		user.setIphone("12345677890");
		user.setPlainPassword("987654321");
		user.setPlatform("weixin");
		user.setCreatedDate(ItdragonUtils.getCurrentDateTime());
		user.setUpdatedDate(ItdragonUtils.getCurrentDateTime());
		ItdragonUtils.entryptPassword(user);
		userService.registerUser(user);
	}

}
