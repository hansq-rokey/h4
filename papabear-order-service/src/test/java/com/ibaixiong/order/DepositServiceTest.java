package com.ibaixiong.order;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.papabear.commons.utils.CodeUtil;
import com.papabear.order.api.DepositService;
import com.papabear.order.entity.MallFrontMoney;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class DepositServiceTest {

//	@Resource
//	DepositService depositService;
	
	@Test
	public void add(){
		MallFrontMoney deposit=new MallFrontMoney();
		deposit.setAddress("ddd");
		deposit.setBxNum("bxnum");
		deposit.setCityId(3l);
		deposit.setCityName("xxxx");
		deposit.setCountyId(1l);
		deposit.setCountyName("county");
		deposit.setCreateDateTime(new Date());
		deposit.setFrontMoney(12345f);
		deposit.setFrontNumber(CodeUtil.getDepositNumber(1l));
		deposit.setName("傻帽");
		deposit.setProvinceId(2l);
		deposit.setProvinceName("kkkk");
		deposit.setRemark("remark");
		deposit.setSource((byte)3);
		deposit.setTel("tel");
		deposit.setUserId(1l);
		deposit.setUserName("操蛋");
//		depositService.createDeposit(deposit);
		
	}
}
