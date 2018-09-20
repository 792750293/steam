package com.jt.dubbo.service;

import com.jt.dubbo.pojo.User;

public interface DubboUserService {

	void saveUser(User user);

	String findUserByUP(User user);
	boolean findCheckUser(String param, Integer type);


}
