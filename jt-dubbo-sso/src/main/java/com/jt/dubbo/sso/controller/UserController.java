package com.jt.dubbo.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.jt.dubbo.common.vo.SysResult;
import com.jt.dubbo.pojo.User;
import com.jt.dubbo.sso.service.UserService;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private JedisCluster jedisCluster;
	
	//实现用户的检验 http://sso.jt.com/user/check/{param}/{type}
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public MappingJacksonValue checkUser(@PathVariable String param,
			@PathVariable Integer type,String callback){
		//查询后台数据 返回结果信息 true 信息已存在
		boolean flag = userService.findCheckUser(param,type);
		MappingJacksonValue jacksonValue = 
				new MappingJacksonValue(SysResult.oK(flag));
		jacksonValue.setJsonpFunction(callback);
		return jacksonValue;
	}
	
	
	@RequestMapping("/register")
	@ResponseBody
	public SysResult saveUser(User user){
		try {
			userService.saveUser(user);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "用户新增失败");
	}
	//用户登陆
	@RequestMapping("/login")
	@ResponseBody
	public SysResult findUserByUP(User user){
		try {
			//获取token信息
			String token = userService.findUserByUP(user);
			if(StringUtils.isEmpty(token)){
				throw new RuntimeException();
			}
			
			return SysResult.oK(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "用户查询失败");
	}
	//通过ticketr获得用户信息
	@RequestMapping("/query/{ticket}")
	@ResponseBody
	public MappingJacksonValue findUserByTicket(@PathVariable String ticket,String callback){
		MappingJacksonValue jacksonValue = null;
		try {
			String userJSON=jedisCluster.get(ticket);
			if(!StringUtils.isEmpty(userJSON)){
				jacksonValue=new MappingJacksonValue(SysResult.oK(userJSON));
				jacksonValue.setJsonpFunction(callback);
				return jacksonValue;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jacksonValue = new MappingJacksonValue(SysResult.build(201,"查询数据失败"));
		jacksonValue.setJsonpFunction(callback);
		return jacksonValue;
		
	}
	

}
