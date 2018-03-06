package com.itdragon.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import com.itdragon.StartApplication;
import com.itdragon.pojo.Address;
import com.itdragon.pojo.User;
import com.itdragon.repository.ITDragonMongoHelper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

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
	private ITDragonMongoHelper userMongoHelper; // 
	
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
	
	/**
	 * 更新方法注意事项介绍：
	 * 1. 根据MongoDB的特性，即便传入的Map参数结构和实体类不匹配，依然会更新成功，这会导致查询该实体类时报错
	 * 1. 更新参数传值为Map，
	 */
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
		Integer pageNumber = 3; // 当前页
		List<User> users = userMongoHelper.find(new Criteria(), pageSize, pageNumber);
		for (User user : users) {
			System.out.println("user : " + user.toString());
		}
	}

}
