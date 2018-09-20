package com.jt.dubbo.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.dubbo.common.mapper.SysMapper;
import com.jt.dubbo.pojo.Item;


public interface ItemMapper extends SysMapper<Item> {
	public List<Item> findItemAll();
	public List<Item> findItemByPage(@Param("start") Integer start,@Param("rows") Integer rows);
}
