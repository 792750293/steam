package com.jt.dubbo.manage.mapper;

import java.util.List;

import com.jt.dubbo.common.mapper.SysMapper;
import com.jt.dubbo.pojo.ItemCat;


public interface ItemCatMapper extends SysMapper<ItemCat> {
	public List<ItemCat> findAll(Integer page, Integer rows);
}
