package com.itdragon.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.test.context.junit4.SpringRunner;

import com.itdragon.StartApplication;
import com.itdragon.pojo.Address;
import com.itdragon.pojo.User;
import com.itdragon.repository.ITDragonMongoHelper;

/**
 * @RunWith	它是一个运行器
 * @RunWith(SpringRunner.class) 表示让测试运行于Spring测试环境，不用启动spring容器即可使用Spring环境
 * @SpringBootTest(classes=StartApplication.class)  表示将StartApplication.class纳入到测试环境中，若不加这个则提示bean找不到。
 * 
 * @author itdragon
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=StartApplication.class)
public class SpringbootStudyApplicationTests {
	
	@Autowired
	private ITDragonMongoHelper userMongoHelper; // 命名规则：需和MongodbBeansConfig配置Bean的方法名一致
	
	@Test
	public void createUser() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^createUser");
		for (int i = 0; i < 25; i++) {	// 插入25条数据
			User user = new User();
			user.setId(Long.valueOf(userMongoHelper.getNextId(User.class.getName())));
			user.setAge(25 + i);
			user.setName("itdragon-" + i);
			Address address = new Address();
			address.setId(Long.valueOf(userMongoHelper.getNextId(Address.class.getName()))); 
			address.setProvince("湖北省");
			address.setCity("武汉市");
			user.setAddress(address);
			ArrayList<String> ability = new ArrayList<>();
			ability.add("Java");
			user.setAbility(ability);
			userMongoHelper.saveOrUpdate(user);
			System.out.println("user : " + user.toString());
		}
	}
	
	@Test
	public void updateUserName() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^updateUserName");
		Map<String, Object> updateMap = new HashMap<>();
		// 查询name为itdragon-1的数据，将name修改为ITDragonBlog
		User user = (User) userMongoHelper.findOne(Criteria.where("name").is("itdragon-1"));
		if (null == user) {
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^User non-existent");
			return ;
		}
		updateMap.put("id", user.getId());
		updateMap.put("name", "ITDragonBlog");
		userMongoHelper.update(updateMap);
	}
	
	@Test
	public void updateUserAddress() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^updateUserAddress");
		Map<String, Object> updateMap = new HashMap<>();
		User user = (User) userMongoHelper.findOne(Criteria.where("name").is("itdragon-3"));
		if (null == user) {
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^User non-existent");
			return ;
		}
		Address address = new Address();
		address.setId(Long.valueOf(userMongoHelper.getNextId(Address.class.getName()))); 
		address.setProvince("湖南省");
		address.setCity("长沙");
		updateMap.put("id", user.getId());
		updateMap.put("address", address);
		userMongoHelper.update(updateMap);
	}
	
	@Test
	public void updateUserAbility() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^updateUserAbility");
		Map<String, Object> updateMap = new HashMap<>();
		User user = (User) userMongoHelper.findOne(Criteria.where("name").is("itdragon-4"));
		if (null == user) {
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^User non-existent");;
			return ;
		}
		ArrayList<String> abilitys = user.getAbility();
		abilitys.add("APP");
		updateMap.put("id", user.getId());
		updateMap.put("ability", abilitys);
		userMongoHelper.update(updateMap);
	}
	
	@Test
	public void findUserPage() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^findUserPage");
		userMongoHelper.setOrderAscField("age"); // 排序
		Integer pageSize = 5; // 每页页数
		Integer pageNumber = 1; // 当前页
		List<User> users = userMongoHelper.find(Criteria.where("age").gt(25), pageSize, pageNumber); // 查询age大于25的数据
		for (User user : users) {
			System.out.println("user : " + user.toString());
		}
	}
	
	@Test
	public void errorUpdateScene() {
		
		// 一天，公司来了一位新同事，错误地更新数据
		Map<String, Object> updateMap = new HashMap<>();
		updateMap.put("id", 35);
		updateMap.put("ability", "MongoDB");
		userMongoHelper.update(updateMap);
		
		// 假设这是很早之前写的逻辑
		User user = (User) userMongoHelper.findOne(Criteria.where("name").is("itdragon-5"));
		ArrayList<String> abilitys = user.getAbility();
		for (String ability : abilitys) {
			// 模拟一些操作
			System.out.println("ablity : " + ability);
		}
	}

}
