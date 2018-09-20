package com.jt.dubbo.service;

import java.util.Date;

import com.jt.dubbo.pojo.Order;

public interface DubboOrderService {
	
	public Order findOrderById(String orderId);
	public void updatePaymentStatus(Date date);
	public String saveOrder(Order order);
}
