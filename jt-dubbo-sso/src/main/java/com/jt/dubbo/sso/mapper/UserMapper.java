package com.jt.dubbo.sso.mapper;

import org.apache.ibatis.annotations.Param;

import com.jt.dubbo.common.mapper.SysMapper;
import com.jt.dubbo.pojo.User;


public interface UserMapper extends SysMapper<User> {
	int findCheckUser(@Param("cloumn") String cloumn, @Param("param") String param);
}
