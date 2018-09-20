package com.jt.dubbo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.dubbo.pojo.Item;
import com.jt.dubbo.pojo.ItemDesc;
import com.jt.dubbo.service.DubboItemService;

@Controller
@RequestMapping("/items")
public class ItemController {
	@Autowired
	private DubboItemService itemService;
	
    @RequestMapping("/{itemId}")
	public String findItemById(@PathVariable Long itemId,Model model){
    	//itemService.findItemById(itemId);
    	Item item = itemService.findItemById(itemId);
		ItemDesc itemDesc = itemService.findItemDescById(itemId);
		
		model.addAttribute("item", item);
		model.addAttribute("itemDesc", itemDesc);
		System.out.println("=======web============"+item.toString());
		return "item";
	}
	
}
