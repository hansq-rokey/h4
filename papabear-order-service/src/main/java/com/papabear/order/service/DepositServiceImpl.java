package com.papabear.order.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.papabear.commons.entity.Pager;
import com.papabear.commons.entity.enumentity.Constant.Status;
import com.papabear.commons.entity.enumentity.InvalidEnum;
import com.papabear.commons.utils.CodeUtil;
import com.papabear.order.api.DepositService;
import com.papabear.order.dao.MallFrontMoneyDao;
import com.papabear.order.dao.MallOrderDao;
import com.papabear.order.dao.MallOrderFrontMoneyRelationDao;
import com.papabear.order.entity.MallFrontMoney;
import com.papabear.order.entity.MallOrder;
import com.papabear.order.entity.MallOrderFrontMoneyRelation;
import com.papabear.order.entity.enums.DepositEnum;
import com.papabear.pay.api.PayService;

public class DepositServiceImpl implements DepositService {
	
	@Resource
	private MallFrontMoneyDao depositDao;
	@Resource
	private MallOrderDao mallOrderDao;
	@Resource
	private PayService payService;
	@Resource
	private MallOrderFrontMoneyRelationDao orderFrontMoneyRelationDao;

	@Override
	public String createDeposit(MallFrontMoney deposit) {
		
		String depositNumber=CodeUtil.getDepositNumber(deposit.getUserId());
		deposit.setStatus(DepositEnum.OBLIGATION.getStatus());
		deposit.setCreateDateTime( new Date());
		deposit.setUpdateTime(new Date());
		deposit.setFrontNumber(depositNumber);
		deposit.setInvalid(InvalidEnum.TRUE.getInvalidValue());
		depositDao.insertSelective(deposit);
		return depositNumber;
	}

	@Override
	public int updateDepositStatus(String depositNumber, Long userId, Byte status) {
		
		return depositDao.updateStatus(status, userId, depositNumber);
	}
	
	@Override
	public int updateDepositStatus(String depositNumber,Byte status) {
		
		return depositDao.updatePayedStatus(status, InvalidEnum.FALSE.getInvalidValue(), depositNumber);
	}

	@Override
	public Pager<MallFrontMoney> queryDeposits(Long userId, Byte status, int pageNo, int pageSize) {
		int count=depositDao.queryDepositsCount(status, userId);
		Pager<MallFrontMoney> pager=new Pager<MallFrontMoney>(count,pageNo,pageSize);
		pager.setList(depositDao.queryDeposits(status, userId));
		
		return pager;
	}
	
	@Override
	public List<MallFrontMoney> queryDeposits(Long userId, Byte status,String[] ids) {
		
		return depositDao.queryDeposits(status, userId);
	}
	
	@Override
	public List<MallFrontMoney> queryDeposits(Long userId, Byte status) {
		
		return depositDao.queryDeposits(status, userId);
	}

	@Override
	public MallFrontMoney getDeposit(Long userId, String depositNumber) {
		// TODO Auto-generated method stub
		return depositDao.getDeposit(userId, depositNumber);
	}

	/* (non-Javadoc)
	 * @see com.papabear.order.api.DepositService#getDeposit(java.lang.String)
	 */
	@Override
	public MallFrontMoney getDeposit(String depositNumber) {
		
		return this.getDeposit(null, depositNumber);
	}

	/* (non-Javadoc)
	 * @see com.papabear.order.api.DepositService#useDeposites(java.lang.String, java.lang.Long, java.lang.String)
	 */
	@Transactional
	@Override
	public int useDeposites(String orderNumber, Long userId, String ids) {
		String[] depositArray=ids.split(",");
		MallOrder order=mallOrderDao.getmMallOrder(userId, orderNumber);
		if(order==null)
			return 0;
		Float depositMoney=0f;
		List<MallFrontMoney> list= depositDao.queryDepositsByIds(DepositEnum.PAID.getStatus(), userId, depositArray);
		
		for(MallFrontMoney deposit:list){
			depositMoney+=deposit.getFrontMoney();
			depositDao.updateStatus(DepositEnum.USED.getStatus(), userId, deposit.getFrontNumber());
			
			//添加定金与定金关联关系
			MallOrderFrontMoneyRelation relation=new MallOrderFrontMoneyRelation();
			relation.setFrontMoneyId(deposit.getId());
			relation.setFrontNumber(deposit.getFrontNumber());
			relation.setOrderNumber(orderNumber);
			orderFrontMoneyRelationDao.insertSelective(relation);
		}
		//更新订单金额及支付相关信息
		MallOrder orderDeposit=new MallOrder();
		orderDeposit.setIsUseFrontMoney((byte)1);
		orderDeposit.setFrontMoney(depositMoney);
		orderDeposit.setDiscountPrice(order.getTotalPrice()-depositMoney);
		orderDeposit.setId(order.getId());
		orderDeposit.setOrderNumber(order.getOrderNumber());;
		mallOrderDao.updateByPrimaryKeySelective(orderDeposit);
		payService.updateOrderPayRealByOrderNumber(orderDeposit.getDiscountPrice(), orderNumber);
		return 1;
	}

}
