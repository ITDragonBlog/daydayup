package com.itdragon.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.itdragon.StartApplication;
import com.itdragon.common.ItdragonUtils;
import com.itdragon.pojo.User;
import com.itdragon.repository.UserRepository;
import com.itdragon.service.UserService;

/**
 * 待修改
 * @RunWith	它是一个运行器
 * @RunWith(SpringRunner.class) 表示让测试运行于Spring测试环境，不用启动spring容器即可使用Spring环境
 * @SpringBootTest(classes=StartApplication.class)  表示将	StartApplication.class纳入到测试环境中，若不加这个则提示bean找不到。
 * 
 * @author itdragon
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=StartApplication.class)
public class SpringbootStudyApplicationTests {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void registerUser() {
		User user = new User();
		user.setAccount("lxlcyy13");
		user.setUserName("ITDragonBlog");
		user.setEmail("itdragon@qq.com");
		user.setIphone("12345677890");
		user.setPlainPassword("987654321");
		user.setPlatform("qq");
		user.setCreatedDate(ItdragonUtils.getCurrentDateTime());
		user.setUpdatedDate(ItdragonUtils.getCurrentDateTime());
		ItdragonUtils.entryptPassword(user);
		userService.registerUser(user);
	}
	
	@Test
	public void findByEmailEndingWithAndCreatedDateLessThan() {
		List<User> users = userRepository.findByEmailEndingWithAndCreatedDateLessThan("qq.com", ItdragonUtils.getCurrentDateTime());
		System.out.println(users.toString());
	}
	
	@Test
	public void getActiveUserCount() {
		long activeUserCount = userRepository.getActiveUserCount("weixin", ItdragonUtils.getCurrentDateTime());
		System.out.println(activeUserCount);
	}
	
	@Test
	public void findByEmailAndIhpneLike() {
		List<User> users = userRepository.findByEmailAndIhpneLike("163.com", "6666");
		System.out.println(users.toString());
	}
	
	public void updateUserEmail() {
		
	}
	
}
