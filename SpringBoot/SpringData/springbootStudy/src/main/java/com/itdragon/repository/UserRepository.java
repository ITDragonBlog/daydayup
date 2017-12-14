package com.itdragon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.itdragon.pojo.User;

/**
 * 核心知识：SpringData Repository 接口
 * 
 * PagingAndSortingRepository 提供了分页与排序功能
 * JpaSpecificationExecutor 提供了Specification(封装 JPA Criteria查询条件)的查询功能
 *
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long>, 
	JpaSpecificationExecutor<User>{
	
	/**
	 * 重点知识：SpringData 查询方法定义规范
	 * 
	 * 语法
	 * 查询方法名一般以 find | read | get 开头，建议用find
	 * findByAccount : 通过account查询User
	 * findByEmailEndingWithAndCreatedDateLessThan : 查询在指定时间前注册，并以xx邮箱结尾的用户
	 * account是User的属性，拼接时首字母需大写
	 * And : 并且
	 * EndingWith : 以某某结尾
	 * LessThan : 小于
	 * 类似的命名规则还有很多，比如 Or,Between,isNull,Like,In等
	 * 
	 * 注意
	 * 若有User(用户表) Platform(用户平台表) 存在一对一的关系，且User表中有platformId字段
	 * SpringData 为了区分：
	 * findByPlatFormId 表示通过platformId字段查询
	 * findByPlatForm_Id 表示通过platform表中id字段查询
	 * 
	 * 开发建议
	 * 表的设计，尽量做单表查询，以确保高并发场景减轻数据库的压力。
	 */
	
	User findByAccount(String account);
	
	List<User> findByEmailEndingWithAndCreatedDateLessThan(String email, String createdDate);

	/**
	 * 重点知识：使用 @Query 注解
	 * 
	 * 上面的方法虽然简单(不用写sql语句)，但它有最为致命的问题-----不支持复杂查询，其次是命名太长
	 * 使用@Query 注解实现复杂查询，设置 nativeQuery=true 使查询支持原生sql，配合@Modifying 注解实现创建，修改，删除操作
	 * getActiveUserCount 获取某平台活跃用户数量
	 * 
	 * 注意
	 * 若@Query 中有多个参数，SpringData 提供两种方法：
	 * 第一种 ?1 ... ?2 		要求参数顺序一致
	 * 第二种 :xxx ... :yyy 	xxx 和 yyy 必须是实体类对应的属性值，不要求参数顺序
	 * 模糊查询可使用 %xxx%
	 * 
	 * 开发建议
	 * 参数填写的顺序要一致，不要给自己添加麻烦。
	 */
	@Query(value="SELECT count(u.id) FROM User u WHERE u.platform = :platform AND u.updatedDate = :updatedDate")
	long getActiveUserCount(String platform, String updatedDate);
	
	@Query(value="SELECT u FROM User u WHERE u.email = %?1% AND u.iphone = %?2%")
	List<User> findByEmailAndIhpneLike(String email, String iphone);
	
	
	
}
