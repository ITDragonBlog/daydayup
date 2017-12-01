package com.itdragon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.itdragon.service.JedisClient;
import redis.clients.jedis.JedisCluster;

public class JedisClientCluster implements JedisClient{
	
	@Autowired
	private JedisCluster jedisCluster;

	@Override
	public String get(String key) {
		return jedisCluster.get(key);
	}

	@Override
	public String set(String key, String value) {
		return jedisCluster.set(key, value);
	}

	@Override
	public String hget(String hkey, String key) {
		return jedisCluster.hget(hkey, key);
	}

	@Override
	public long hset(String hkey, String key, String value) {
		return jedisCluster.hset(hkey, key, value);
	}

	@Override
	public long del(String key) {
		return jedisCluster.del(key);
	}

	@Override
	public long hdel(String hkey, String key) {
		return jedisCluster.hdel(hkey, key);
	}
	
}
