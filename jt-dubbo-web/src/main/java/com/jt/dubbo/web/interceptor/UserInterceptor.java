package com.jt.dubbo.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.dubbo.pojo.User;
import com.jt.dubbo.web.thread.UserThreadLocal;

import redis.clients.jedis.JedisCluster;

public class UserInterceptor implements HandlerInterceptor
{

	@Autowired
	private JedisCluster jedisCluster;
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

	//在Controller方法执行之前
		/**
		 * 通过拦截器的方式实现用户登陆检测.如果用户没有登陆则跳转登录页面
		 * 问题:
		 * 		如何检测用户是否登录
		 * 		1.检测Cookie是否有值    JT_TICKET:token
		 *      2.检测redis缓存服务器是否有数据    token:userJSON
		 * 实现步骤:
		 * 		1.获取Cookie信息   获取token
		 *      2.获取redis中缓存数据
		 *      3.如果上述过程中有一个为null,应该跳转登录页面
		 */

	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		Cookie[] cookies=request.getCookies();
		String token=null;
		for(Cookie c:cookies){
			if("JT_TICKET".equals(c.getName())){
				token=c.getValue();
				break;
			}
		}
		if(!StringUtils.isEmpty(token)){
			String userJSON=jedisCluster.get(token);
			User user=objectMapper.readValue(userJSON, User.class);
			UserThreadLocal.set(user);
			System.out.println("writewrite++++++"+user.getId());
			System.out.println("readread++++++"+UserThreadLocal.get().getId());
			return true;
		}
		
		response.sendRedirect("/user/login.html");
		
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		//UserThreadLocal.remove();
	}
	
}
