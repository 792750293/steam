package com.jt.dubbo.cart.mapper;

import com.jt.dubbo.common.mapper.SysMapper;
import com.jt.dubbo.pojo.Cart;

public interface CartMapper extends SysMapper<Cart>{

	Cart findCartByUI(Cart cart);

	void updateCartNum(Cart cart);
	
}
