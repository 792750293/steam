package com.jt.dubbo.sso.service;

import com.jt.dubbo.pojo.User;

public interface UserService {

	boolean findCheckUser(String param, Integer type);

	void saveUser(User user);
	public String findUserByUP(User user);
	
}
