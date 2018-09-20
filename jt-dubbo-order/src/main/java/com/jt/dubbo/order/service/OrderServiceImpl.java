package com.jt.dubbo.order.service;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.dubbo.common.service.HttpClientService;
import com.jt.dubbo.order.mapper.OrderItemMapper;
import com.jt.dubbo.order.mapper.OrderMapper;
import com.jt.dubbo.order.mapper.OrderShippingMapper;
import com.jt.dubbo.pojo.Order;
import com.jt.dubbo.pojo.OrderItem;
import com.jt.dubbo.pojo.OrderShipping;
import com.jt.dubbo.service.DubboOrderService;


public class OrderServiceImpl implements DubboOrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderItemMapper orderItemMapper;

	@Autowired
	private OrderShippingMapper orderShippingMapper;

	@Override
	public Order findOrderById(String orderId) {
		// TODO Auto-generated method stub
		//1.获取order数据  2.获取orderShipping对象 3.获取orderItem数据
				//4.将数据进行组装
				Order order = orderMapper.selectByPrimaryKey(orderId);
				OrderShipping orderShipping = orderShippingMapper.selectByPrimaryKey(orderId);
				
				OrderItem orderItem = new OrderItem();
				orderItem.setOrderId(orderId);
				List<OrderItem> orderItems = orderItemMapper.select(orderItem);
				
				//数据封装
				order.setOrderShipping(orderShipping);
				order.setOrderItems(orderItems);
				return order;

	}

	@Override
	public void updatePaymentStatus(Date date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String saveOrder(Order order) {
		// TODO Auto-generated method stub
		String orderId=order.getUserId()+""+System.currentTimeMillis();
		Date date=new Date();
		
		order.setOrderId(orderId);
		order.setStatus(1);
		order.setUpdated(date);
		order.setCreated(date);
		orderMapper.insert(order);
		System.err.println("订单入库成功！！！");
		
		OrderShipping orderShipping=order.getOrderShipping();
		orderShipping.setOrderId(orderId);
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		orderShippingMapper.insert(orderShipping);
		System.out.println("物流订单信息入库成功！！！");
		
		return orderId;
		
	}

	
}
