package com.jt.dubbo.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.jt.dubbo.common.vo.SysResult;
import com.jt.dubbo.pojo.Cart;
import com.jt.dubbo.pojo.Order;
import com.jt.dubbo.service.DubboCartService;
import com.jt.dubbo.service.DubboOrderService;
import com.jt.dubbo.web.thread.UserThreadLocal;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	DubboOrderService orderService;
	@Autowired
	DubboCartService cartService;
	
	@RequestMapping("/create")
	public String toOrderCart(Model model){
		Long userId = UserThreadLocal.get().getId();
		List<Cart> carts = cartService.findCartByUserId(userId);
		model.addAttribute("carts", carts);
		return "order-cart";
	}
	
	//实现订单的新增
		@RequestMapping("/submit")
		@ResponseBody
		public SysResult saveOrder(Order order){
			try {
				Long userId = UserThreadLocal.get().getId();
				order.setUserId(userId);
				
				String orderId = orderService.saveOrder(order);
				
				if(!StringUtils.isEmpty(orderId)){
					
					return SysResult.oK(orderId);
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return SysResult.build(201,"新增失败");
		}
		
		//实现订单的查询
				@RequestMapping("/success")
				public String findOrderById(@RequestParam(value="id") String orderId,Model model){
					Order order = orderService.findOrderById(orderId);
					model.addAttribute("order", order);
					return "success";
				}
}
