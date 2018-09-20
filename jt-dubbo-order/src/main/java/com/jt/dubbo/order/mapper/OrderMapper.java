package com.jt.dubbo.order.mapper;

import com.jt.dubbo.common.mapper.SysMapper;
import com.jt.dubbo.pojo.Order;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper extends SysMapper<Order> {
  void updateStatus(Date timeOut);
}