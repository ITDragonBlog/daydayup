package com.itdragon.redis;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
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
//		若连接操作，可以用下面的方法延迟连接的时间，当正常的开发环境，一般都在局域网内，不存在超时的情况。若使用缓存还超时，那缓存就失去意义了。
//		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
//		JedisPool pool = new JedisPool(poolConfig, HOST, PORT, 100000);
		Jedis jedis = pool.getResource();
		System.out.println("通过连接池获取 key 为 account 的值 : " + jedis.get("account"));
		jedis.close();
		pool.close();
	}
	
	/**
	 * 集群版测试
	 * 若提示以下类似的错误：
	 * java.lang.NumberFormatException: For input string: "6002@16002"
	 * 若安装的redis 版本大于4，则可能是jedis 的版本低了。选择 2.9.0
	 * 因为 cluster nodes 打印的信息中，4版本之前的是没有 @16002 tcp端口信息
	 * 0968ef8f5ca96681da4abaaf4ca556c2e6dd0242 112.74.83.71:6002@16002 master - 0 1512035804722 3 connected 10923-16383
	 */
	@Test
	public void testJedisCluster() throws IOException {
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort(HOST, 6000));
		nodes.add(new HostAndPort(HOST, 6001));
		nodes.add(new HostAndPort(HOST, 6002));
		nodes.add(new HostAndPort(HOST, 6003));
		nodes.add(new HostAndPort(HOST, 6004));
		nodes.add(new HostAndPort(HOST, 6005));
		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("cluster-key", "cluster-value");
		System.out.println("集群测试 ： " + cluster.get("cluster-key"));
		cluster.close();
	}

}
