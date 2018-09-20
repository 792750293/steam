package com.jt.dubbo.web.thread;

import com.jt.dubbo.pojo.User;

public class UserThreadLocal {
	private static ThreadLocal<User> userThread=new ThreadLocal<User>();
	public static void set(User obj){
		userThread.set(obj);
	}
	public static User get(){
		return userThread.get();
	}
	public static void remove(){
		userThread.remove();
	}
	
}
