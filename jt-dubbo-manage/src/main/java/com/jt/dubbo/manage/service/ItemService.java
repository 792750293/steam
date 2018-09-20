package com.jt.dubbo.manage.service;

import java.util.List;

import com.jt.dubbo.common.vo.EasyUIResult;
import com.jt.dubbo.pojo.Item;
import com.jt.dubbo.pojo.ItemDesc;



public interface ItemService {
	public List<Item> findItemAll();
	public int findItemrow();
	EasyUIResult findItemByPage(Integer page, Integer rows);
	void saveItem(Item item,String desc);
	void deleteItem(Long[] ids);
	public ItemDesc findItemDesc(Long itemId);
	public void updateItem(Item item,String desc);
	public Item findItemById(Long itemId);
}
