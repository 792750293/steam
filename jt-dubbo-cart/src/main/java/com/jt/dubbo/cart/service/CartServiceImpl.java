package com.jt.dubbo.cart.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.dubbo.cart.mapper.CartMapper;
import com.jt.dubbo.pojo.Cart;
import com.jt.dubbo.service.DubboCartService;

//@Service //cartServiceImpl  可以省略该注解 因为通过bean管理了
public class CartServiceImpl implements DubboCartService {
	
	@Autowired
	private CartMapper cartMapper;

	@Override
	public List<Cart> findCartByUserId(Long userId) {
		Cart cart = new Cart();
		cart.setUserId(userId);
		
		return cartMapper.select(cart);
	}

	/**
	 * 1.查询数据库中是否含有相同记录  userId itemId
	 * 2.如果没有记录则做新增操作
	 * 3.如果有记录 则做数量的修改操作
	 */
	@Override
	public void saveCart(Cart cart) {
		Cart cartDB = cartMapper.findCartByUI(cart);
		if(cartDB == null){
			cart.setCreated(new Date());
			cart.setUpdated(cart.getCreated());
			cartMapper.insert(cart);
		}else{
			int num = cart.getNum() + cartDB.getNum();
			cartDB.setNum(num);
			cartDB.setUpdated(new Date());
			cartMapper.updateByPrimaryKeySelective(cartDB);
		}
	}

	@Override
	public void updateCartNum(Cart cart) {
		
		cartMapper.updateCartNum(cart);
	}

	@Override
	public void deleteCart(Cart cart) {
		
		cartMapper.delete(cart);
	}	
	
	
	
	
	
	
	
}
