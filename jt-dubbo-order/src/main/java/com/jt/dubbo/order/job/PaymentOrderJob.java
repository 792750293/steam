package com.jt.dubbo.order.job;

import java.util.Date;

import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.jt.dubbo.order.mapper.OrderMapper;

public class PaymentOrderJob extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		//删除2天天的恶意订单
		ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap().get("applicationContext");
		
		
		OrderMapper ordermapper=applicationContext.getBean(OrderMapper.class);
		Date timeOut = new DateTime().minusDays(2).toDate();
		ordermapper.updateStatus(timeOut);
		//通过spring容器调用Order接口文件执行方法
		//applicationContext.getBean(OrderMapper.class).paymentOrder(new DateTime().minusDays(2).toDate());
	}
	
}
