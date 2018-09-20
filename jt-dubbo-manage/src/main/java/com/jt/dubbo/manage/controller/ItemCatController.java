package com.jt.dubbo.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jt.dubbo.common.vo.EasyUITree;
import com.jt.dubbo.manage.service.ItemCatService;
import com.jt.dubbo.pojo.ItemCat;
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	@Autowired
private ItemCatService itemCatService;
	@RequestMapping("/all/{page}/{rows}")
	@ResponseBody	//返回值是一个Json字符串
	public List<ItemCat> findAll(@PathVariable Integer page,@PathVariable  Integer rows){
		List<ItemCat> itemCatList = itemCatService.findAll(page, rows);
		return itemCatList;
	}
	@RequestMapping(value="/queryItemName",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findItemCatNameById(Long itemId){
		
		return itemCatService.findNameById(itemId);
	}
	/**
	 * @RequestParam(value="id",defaultValue="0",required=true)
	 * 	id表示接收参数的名称
	 *  defaultValue 默认值
	 *  required=true 该参数必须传递,否则SpringMVC校验报错.
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITree> findItemCat(@RequestParam(value="id",defaultValue="0")Long parentId){
		
		//1.查询一级商品分类目录
		//Long parentId = 0L;
		return itemCatService.findItemCatByParentId(parentId);
		//return itemCatService.findCacheByParentId(parentId);
	}
}
