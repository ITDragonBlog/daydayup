package com.itdragon.service;

public interface JedisClient {
	
	String get(String key);
	String set(String key, String value);
	String hget(String hkey, String key);
	long hset(String hkey, String key, String value);
	long del(String key);
	long hdel(String hkey, String key);

}
