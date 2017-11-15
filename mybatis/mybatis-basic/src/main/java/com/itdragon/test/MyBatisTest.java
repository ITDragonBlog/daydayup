package com.itdragon.test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.itdragon.pojo.Classroom;
import com.itdragon.pojo.Person;

public class MyBatisTest {
	
	public SqlSession getSqlSession() {
		String resource = "SqlMapConfig.xml"; 
		InputStream is = MyBatisTest.class.getClassLoader().getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession(true); // false 默认手动提交, true 自动提交
		return session;
	}
	
	// crud 操作
	@Test
	public void getPersonById() {
		String statement = "com.itdragon.mapper.PersonMapper.getPersonById";
		Person person = getSqlSession().selectOne(statement, 2);
		System.out.println(person);
		
		statement = "com.itdragon.mapper.PersonMapper.getPersonByIdOne";
		person = getSqlSession().selectOne(statement, 2);
		System.out.println(person);
		
		statement = "com.itdragon.mapper.PersonMapper.getPersonByIdTwo";
		person = getSqlSession().selectOne(statement, 2);
		System.out.println(person);
	}
	@Test
	public void getAllperson() {
		String statement = "com.itdragon.mapper.PersonMapper.getAllperson";
		List<Person> persons = getSqlSession().selectList(statement);
		System.out.println(persons);
	}
	@Test
	public void createPerson() {
		String statement = "com.itdragon.mapper.PersonMapper.createPerson";
		int result = getSqlSession().insert(statement, new Person(3, "itdragon@qq.com", "ITDragon"));
		System.out.println(result);
	}
	@Test
	public void updatePersonById() {
		String statement = "com.itdragon.mapper.PersonMapper.updatePersonById";
		int result = getSqlSession().update(statement, new Person(4, "itdragon@qq.com", "ITDragon博客"));
		System.out.println(result);
	}
	@Test
	public void deletePersonById() {
		String statement = "com.itdragon.mapper.PersonMapper.deletePersonById";
		int result = getSqlSession().delete(statement, 4);
		System.out.println(result);
	}
	
	// 关联表的查询
	@Test
	public void getClassroomById() {
		String statement = "com.itdragon.mapper.ClassroomMapper.getClassroomById";
		Classroom classroom = getSqlSession().selectOne(statement, 1);
		System.out.println(classroom);
		
		statement = "com.itdragon.mapper.ClassroomMapper.getClassroom2ById";
		classroom = getSqlSession().selectOne(statement, 1);
		System.out.println(classroom);
		
		statement = "com.itdragon.mapper.ClassroomMapper.getClassroom3ById";
		classroom = getSqlSession().selectOne(statement, 1);
		System.out.println(classroom);
		
		statement = "com.itdragon.mapper.ClassroomMapper.getClassroom4ById";
		classroom = getSqlSession().selectOne(statement, 1);
		System.out.println(classroom);
		
	}
	
	// 调用存储过程
	@Test
	public void getPersonCountGtId(){
		String statement = "com.itdragon.mapper.PersonMapper.getPersonCountGtId";
		Map<String, Integer> parameterMap = new HashMap<String, Integer>();
		parameterMap.put("personId", 1);
		parameterMap.put("personCount", -1);
		getSqlSession().selectOne(statement, parameterMap);
		Integer result = parameterMap.get("personCount");
		System.out.println(result);
	}
	
	// 动态sql语句和模糊查询
	@Test
	public void getPersonLikeKey() {
		String statement = "com.itdragon.mapper.PersonMapper.getPersonLikeKey";
		List<Person> persons = getSqlSession().selectList(statement, new Person(1, "%cyy%", null));
		System.out.println(persons);
		
	}
}
