package com.jt.dubbo.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
@Service
public class RedisService {
	/*//启动不注入，调用才注入
	@Autowired(required=false)
	ShardedJedisPool jedisPool;
	@Autowired
	private JedisSentinelPool sentinelPool;
	
	public void set(String key,String value){
		Jedis jedis = sentinelPool.getResource();
		jedis.set(key, value);
		sentinelPool.returnResource(jedis);
	}
	
	public String get(String key){
		Jedis jedis = sentinelPool.getResource();
		String result = jedis.get(key);
		sentinelPool.returnResource(jedis);
		return result;
	}
	
	
	public void set(String key,String value){
		ShardedJedis shardedJedis=jedisPool.getResource();
		shardedJedis.set(key, value);
		jedisPool.returnResource(shardedJedis);
	}
	public String get(String key,int seconds){
		ShardedJedis shardedJedis=jedisPool.getResource();
		String st=shardedJedis.get(key);
		jedisPool.returnResource(shardedJedis);
		return st;
	}*/
}
