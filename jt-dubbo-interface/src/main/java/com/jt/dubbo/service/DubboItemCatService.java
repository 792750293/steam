package com.jt.dubbo.service;

import java.util.List;

import com.jt.dubbo.common.vo.EasyUITree;
import com.jt.dubbo.common.vo.ItemCatResult;
import com.jt.dubbo.pojo.ItemCat;


public interface DubboItemCatService {
	public List<ItemCat> findAll(Integer page, Integer rows);
	String findNameById(Long itemId);
	public List<EasyUITree> findItemCatByParentId(Long parentId);
	List<EasyUITree> findCacheByParentId(Long parentId);
	public ItemCatResult findItemCatAll();
	public ItemCatResult findItemCatCache();
}
