package com.jt.dubbo.sso.service;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.dubbo.common.service.BaseService;
import com.jt.dubbo.pojo.User;
import com.jt.dubbo.service.DubboUserService;
import com.jt.dubbo.sso.mapper.UserMapper;

import redis.clients.jedis.JedisCluster;
import sun.security.provider.MD5;

@Service
public class UserServiceImpl extends BaseService<User> implements DubboUserService,UserService{
	
	@Autowired
	private JedisCluster jedisCluster;
	@Autowired
	private UserMapper userMapper;
	ObjectMapper objectMapper=new ObjectMapper();
	@Override
	public boolean findCheckUser(String param, Integer type) {
		//将类型转化为具体的字段名称
		String cloumn = null;
		switch (type) {
			case 1:
				cloumn = "username"; break;
			case 2:
				cloumn = "phone";  break;
			case 3:
				cloumn = "email"; break;
		}
		//如果结果为0 返回false  如果不为0 返回true
		int count = userMapper.findCheckUser(cloumn,param);
		
		return count == 0 ? false : true;
	}

	@Override
	public void saveUser(User user) {
		//补全user数据	
		String md5Password = DigestUtils.md5Hex(user.getPassword());
		user.setPassword(md5Password);
		user.setEmail(user.getPhone());//为了保证数据库不报错 暂时用电话代替
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		userMapper.insert(user);
	}

	@Override
	public String findUserByUP(User user) {
		// TODO Auto-generated method stub
		String token=null;
		
		String md5Password=DigestUtils.md5Hex(user.getPassword());
		user.setPassword(md5Password);
		User userDB=super.queryByWhere(user);
		if(userDB==null){
			throw new RuntimeException();
		}
		try {
			String userJSON=objectMapper.writeValueAsString(userDB);
			token=DigestUtils.md5Hex("JT_TICKET"+System.currentTimeMillis()+user.getUsername());
			jedisCluster.setex(token, 3600 * 24 * 7, userJSON);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
		return token;
	}
	
	
	
	
	
}
