package com.papabear.order.api;

import java.util.List;

import com.papabear.commons.entity.Pager;
import com.papabear.order.entity.MallFrontMoney;

/**
 * 定金相关接口处理
 * @author yaoweiguo
 *
 */


public interface DepositService {

	/**
	 * 添加下定金订单
	 * @param deposit
	 * @return
	 */
	String createDeposit(MallFrontMoney deposit);
	
	/**
	 * 更新定金状态
	 * @param despositNumber
	 * @param userId
	 * @param status
	 * @return
	 */
	int updateDepositStatus(String depositNumber,Long userId,Byte status);
	
	/**
	 * 更新定金支付成功后的状态，同时将不可以记录变为可用记录
	 * @author yaoweiguo
	 * @date 2016年7月27日
	 * @param depositNumber
	 * @param invalid
	 * @return
	 */
	int updateDepositStatus(String depositNumber,Byte status);
	
	/**
	 * 获取定金列表
	 * @param userId
	 * @param status
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pager<MallFrontMoney> queryDeposits(Long userId,Byte status, int pageNo, int pageSize);
	
	/**
	 * 获取定金列表
	 * @param userId
	 * @param status
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<MallFrontMoney> queryDeposits(Long userId,Byte status);
	
	/**
	 * 获取定金列表
	 * @param userId
	 * @param status
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<MallFrontMoney> queryDeposits(Long userId,Byte status,String[] ids);
	
	/**
	 * 获取定金资料
	 * @param userId
	 * @param depositNumber
	 * @return
	 */
	MallFrontMoney getDeposit(Long userId,String depositNumber);
	
	/**
	 * 获取定金资料
	 * @param userId
	 * @param depositNumber
	 * @return
	 */
	MallFrontMoney getDeposit(String depositNumber);
	
	/**
	 * 使用定金
	 * @author yaoweiguo
	 * @date 2016年7月26日
	 * @param orderNumber
	 * @param userId
	 * @param ids
	 * @return
	 */
	int useDeposites(String orderNumber,Long userId,String ids);
	
}
