package com.itdragon.redis;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestJedisOperate {
	
	private final static String HOST = "112.74.83.71";
	private final static int PORT = 6379;

	/**
	 * jedis 的语法和 redis 的语法几乎一致，比较常用的有Hash，String，List
	 */
	@Test
	public void jedisSignle() {
		Jedis jedis = new Jedis(HOST, PORT);
		System.out.println("================== String 类型  =================");
		jedis.set("account", "itdragon");
		System.out.println("set , get 操作 : " + jedis.get("account"));
		jedis.mset("account:01", "itdragon01", "account:02", "itdragon02");
		System.out.println("mset , mget 操作 : " + jedis.mget("account:01", "account:02"));
		System.out.println("strlen 操作 : " + jedis.strlen("account"));
		System.out.println("del 操作 : " + jedis.del("account"));
		System.out.println("================== Hash 类型  =================");
		jedis.hset("user", "name", "ITDragon");
		System.out.println("hset , hget 操作 : " + jedis.hget("user", "name"));
		Map<String, String> userMap = new HashMap<>();
		userMap.put("password", "123456");
		userMap.put("position", "Java");
		jedis.hmset("user", userMap);
		System.out.println("hmset , hmget 操作 : " + jedis.hmget("user", "name", "password", "position"));
		System.out.println("================== List 类型  =================");
		if (0 == jedis.llen("userList")) {
			jedis.lpush("userList", "1", "2", "3");
		}
		System.out.println("List 类型 lpush , lrange 操作 : " + jedis.lrange("userList", 0, -1));
		System.out.println("================== Set 类型  =================");
		jedis.sadd("userSet", "1", "2", "2");
		System.out.println("Set 类型 sadd , smembers 操作 : " + jedis.smembers("userSet"));
		System.out.println("================== Sortedset 类型  =================");
		Map<String, Double> scoreMembers = new HashMap<>();
		scoreMembers.put("A", 65.0);
		scoreMembers.put("C", 67.0);
		scoreMembers.put("B", 66.0);
		jedis.zadd("userScore", scoreMembers);
		System.out.println("Set 类型 zadd , zrange 操作 : " + jedis.zrange("userScore", 0, -1));
		jedis.close();
	}
	
	@Test
	public void testJedisPool() {
		JedisPool pool = new JedisPool(HOST, PORT);
		Jedis jedis = pool.getResource();
		System.out.println("通过连接池获取 key 为 account 的值 : " + jedis.get("account"));
		jedis.close();
		pool.close();
	}

}
