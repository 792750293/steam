package com.jt.dubbo.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.jt.dubbo.common.vo.SysResult;
import com.jt.dubbo.pojo.User;
import com.jt.dubbo.service.DubboUserService;


import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private DubboUserService userService;
	@Autowired
	private JedisCluster jedisCluster;
	
	//实现页面的通用跳转  http://www.jt.com/user/login.html
	@RequestMapping("/{moduleName}")
	public String toModule(@PathVariable String moduleName){
		
		return moduleName;
	}
	
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
	
	
	@RequestMapping("/doRegister")
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
	
	
	@RequestMapping("/doLogin")
	@ResponseBody
	public SysResult doLogin(User user,HttpServletResponse response){
		try {
		String token=userService.findUserByUP(user);
		//判断返回值是否有效
		if(StringUtils.isEmpty(token)){
			throw new RuntimeException();
		}
		//正确
		Cookie cookie=new Cookie("JT_TICKET",token);
		cookie.setPath("/");//必要，设置保存路径
		/*
		 * cookie.setMaxAge(0);//不记录cookie,删除Cookie
		 * cookie.setMaxAge(-1);//会话级cookie，关闭浏览器失效
		 * */
		cookie.setMaxAge(3600*24*7);//必要，设置生效时间
		response.addCookie(cookie);
		return SysResult.oK();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SysResult.build(201, "用户登录失败");
		}	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		//
		Cookie[] cookies=request.getCookies();
		try {
			for(Cookie c:cookies){
				if(c.getName().equals("JT_TICKET"))
				{
					String ticket=c.getValue();
					jedisCluster.del(ticket);
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("redis.clients.jedis.exceptions.JedisConnectionException");
			System.out.println("/Logout/Logout/Logout/Logout/Logout/Logout");
			Cookie cookie=new Cookie("JT_TICKET","");
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			return "redirect:/index.html";
		}
		return "redirect:/index.html";
	}
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
