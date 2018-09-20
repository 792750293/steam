package com.jt.dubbo.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.dubbo.common.service.BaseService;
import com.jt.dubbo.common.vo.EasyUIResult;
import com.jt.dubbo.common.vo.SysResult;
import com.jt.dubbo.manage.mapper.ItemDescMapper;
import com.jt.dubbo.manage.mapper.ItemMapper;
import com.jt.dubbo.pojo.Item;
import com.jt.dubbo.pojo.ItemDesc;
import com.jt.dubbo.service.DubboItemService;

@Service
public class ItemServiceImp extends BaseService<Item> implements ItemService,DubboItemService {
	@Autowired
	ItemMapper itemMapper;
	@Autowired
	ItemDescMapper itemDescMapper;
	@Override
	public List<Item> findItemAll() {
		// TODO Auto-generated method stub
			return itemMapper.findItemAll();
	}

	@Override
	public int findItemrow() {
		// TODO Auto-generated method stub
		
		
		return itemMapper.selectCount(null);
	}

	public EasyUIResult findItemByPage(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		/**
		 * 通用Mapper 查询操作时  如果传入的数据不为null,则会充当where条件
		 * select count(*) from tb_item
		 * select * from tb_item limit 0,20
s		   select * from tb_item limit 20,20
s          select * from tb_item limit 40,20
		 */
		int total = itemMapper.selectCount(null);
		
		int start = (page - 1) * rows;
		
		List<Item> itemList = itemMapper.findItemByPage(start,rows);
		
		EasyUIResult result = new EasyUIResult(total, itemList);
		
		return result;
	}

	@Override
	public void saveItem(Item item,String desc) {
		// TODO Auto-generated method stub
		item.setStatus(1);
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		System.out.println(item);
		itemMapper.insert(item);
		ItemDesc itemDesc = new ItemDesc();
		System.out.println("item.getId()"+item.getId());
		itemDesc.setItemId(item.getId()); //?????有数据吗???
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(item.getCreated());
		itemDesc.setUpdated(item.getCreated());
		itemDescMapper.insert(itemDesc);

	}
	@Override
	public void deleteItem(Long[] ids) {
		// TODO Auto-generated method stub
		itemMapper.deleteByIDS(ids);
		itemDescMapper.deleteByIDS(ids);
	}

	@Override
	public ItemDesc findItemDesc(Long itemId) {
		// TODO Auto-generated method stub
		return itemDescMapper.selectByPrimaryKey(itemId);
	}

	@Override
	public void updateItem(Item item, String desc) {
		// TODO Auto-generated method stub
		//为数据赋值
				item.setUpdated(new Date());
				itemMapper.updateByPrimaryKeySelective(item);
				//商品描述信息更新
				ItemDesc itemDesc= new ItemDesc();
				itemDesc.setItemDesc(desc);
				itemDesc.setItemId(item.getId());
				itemDesc.setUpdated(item.getUpdated());
				itemDescMapper.updateByPrimaryKeySelective(itemDesc);
	}

	@Override
	public Item findItemById(Long itemId) {
		// TODO Auto-generated method stub
		return itemMapper.selectByPrimaryKey(itemId);
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		// TODO Auto-generated method stub
		//ItemDesc itemDesc = itemService.findItemDescById(itemId);
		return itemDescMapper.selectByPrimaryKey(itemId);
	}
	
}
