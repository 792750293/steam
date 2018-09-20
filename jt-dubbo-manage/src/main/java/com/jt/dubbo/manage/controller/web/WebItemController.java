package com.jt.dubbo.manage.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.dubbo.manage.service.ItemService;
import com.jt.dubbo.pojo.Item;
import com.jt.dubbo.pojo.ItemDesc;



@Controller
@RequestMapping("/web/item")
//http://manage.jt.com/web/item/findItemById
public class WebItemController {
	
	@Autowired
	ItemService itemService;
	
	@RequestMapping("/findItemById")
	@ResponseBody
	public Item findItemById(Long itemId){
		return itemService.findItemById(itemId);
		
	}
	@RequestMapping("/findItemDescById/{itemId}")
	@ResponseBody
	public ItemDesc findItemDescById(@PathVariable Long itemId){
		
		return itemService.findItemDesc(itemId);
	}

}
