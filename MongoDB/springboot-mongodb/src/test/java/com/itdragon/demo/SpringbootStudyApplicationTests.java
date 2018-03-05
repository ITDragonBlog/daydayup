package com.itdragon.demo;

import java.util.ArrayList;
import java.util.HashMap;
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
	private ITDragonMongoHelper mongoHelper;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * MongoDB save 方法
	 * 参数：实体类 ， 直接调用MongoTemplate 的save 方法
	 */
	@Test
	public void createUser() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^createUser");
		for (int i = 0; i < 25; i++) {	// 插入25条数据
			User user = new User();
			user.setId(Long.valueOf(getNextId(User.class.getName())));
			user.setAge(25 + i);
			user.setName("itdragon-" + i);
			Address address = new Address();
			address.setId(Long.valueOf(getNextId(Address.class.getName()))); 
			address.setProvince("湖北省");
			address.setCity("武汉市");
			user.setAddress(address);
			ArrayList<String> ability = new ArrayList<>();
			ability.add("Java");
			user.setAbility(ability);
			mongoTemplate.save(user);
			System.out.println("user : " + user.toString());
		}
	}
	
	@Test
	public void updateUser() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^updateUser");
		Map<String, Object> updateMap = new HashMap<>();
		// 查询name为itdragon-0的数据，将name修改为ITDragonGit
		User user = findOne(Criteria.where("name").is("itdragon-0"));
		if (null == user) {
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^User non-existent");
			return ;
		}
		updateMap.put("id", user.getId());
		updateMap.put("name", "ITDragonGit");
		itdragonUpdate(updateMap);
	}
	
	// 以下写法很难做到通用，有待封装。
	
	/**
	 * 通过条件查询一条数据
	 * @param criteria
	 * @return
	 */
	public User findOne(Criteria criteria) {
		Query query = new Query(criteria).limit(1);
		// 第一个参数是查询条件，第二个参数是对象，第三个参数是表名
		return this.mongoTemplate.findOne(query, User.class, "user");
	}
	
	/**
	 * MongoDB Update 封装方法
	 * @param requestArgs 参数形如 {属性： 属性值}
	 * @return
	 */
	public Boolean itdragonUpdate(Map<String, Object> requestArgs) {
		Object id = requestArgs.get("id");
		if (null == id) {
			return Boolean.valueOf(false);
		}
		try {
			Update updateObj = new Update();
			requestArgs.remove("id");
			for (String key : requestArgs.keySet()) {
				updateObj.set(key, requestArgs.get(key));
			}
			// 第一个参数是查询条件，第二个参数是需要更新的字段，第三个参数是对象，第四个参数是表明
			mongoTemplate.findAndModify(new Query(Criteria.where("id").is(id)), updateObj, User.class, "user");
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.valueOf(false);
		}
		return Boolean.valueOf(true);
	}
	
	
	// ID 自增长
	public String getNextId(String seq_name) {
		String sequence_collection = "seq";
		String sequence_field = "seq";
		DBCollection seq = this.mongoTemplate.getCollection(sequence_collection);
		DBObject query = new BasicDBObject();
		query.put("_id", seq_name);
		DBObject change = new BasicDBObject(sequence_field, Integer.valueOf(1));
		DBObject update = new BasicDBObject("$inc", change);
		DBObject res = seq.findAndModify(query, new BasicDBObject(), new BasicDBObject(), false, update, true, true);
		return res.get(sequence_field).toString();
	}

}
