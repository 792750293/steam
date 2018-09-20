package com.jt.dubbo.manage.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.dubbo.common.vo.EasyUIResult;
import com.jt.dubbo.common.vo.SysResult;
import com.jt.dubbo.manage.service.ItemService;
import com.jt.dubbo.pojo.Item;
import com.jt.dubbo.pojo.ItemDesc;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/query1")
	@ResponseBody
	public EasyUIResult findItem(){
		
		List<Item> item=itemService.findItemAll();
		int rows=itemService.findItemrow();
		EasyUIResult eu= new EasyUIResult(rows,item);
		return eu;
		}
	//http://localhost:8091/item/query?page=1&rows=50
	//实现商品分页查询
	@RequestMapping("/query")
	@ResponseBody
	public EasyUIResult findItemByPage(Integer page,Integer rows){
		
		return itemService.findItemByPage(page,rows);
	}
	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveItem(Item item,String desc){
		SysResult result=new SysResult();
		try{
		 itemService.saveItem(item,desc);
		}catch(Exception e){
			return result.build(201, "商品添加失败！");
		} 
		return result.oK();
	}
	//回显
	@RequestMapping("/query/item/desc/{itemId}")
	@ResponseBody
	public SysResult findItemDescById(@PathVariable Long itemId){
		try {
			ItemDesc itemDesc=itemService.findItemDesc(itemId);
			return SysResult.oK(itemDesc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SysResult.build(201, "商品详情查询失败");
	}
	//修改商品信息表//修改商品描述表
	@RequestMapping("/update")
	@ResponseBody
	public SysResult updateItem(Item item,String desc){
		try {
			itemService.updateItem(item,desc);
			return SysResult.oK();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SysResult.build(201, "商品修改失败");
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public SysResult deleteItem(Long[] ids){
		SysResult result=new SysResult();
		try{
			itemService.deleteItem(ids);
		}catch(Exception e){
			//logger.error("!!!!!!!!!!!!!!"+e.getMessage());
			return result.build(201, "商品删除失败！");
		} 
		return result.oK();
	}
}
