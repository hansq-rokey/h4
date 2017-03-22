package com.papabear.order.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.papabear.order.entity.MallOrder;

public interface MallOrderDao {
	int deleteByPrimaryKey(Long id);

	int insert(MallOrder record);

	int insertSelective(MallOrder record);

	MallOrder selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(MallOrder record);

	/**
	 * 更新订单收货人信息
	 * 
	 * @param express
	 * @param orderNumber
	 * @return
	 */
	int updateOrder(@Param("expressId") Long expressId, @Param("status") Byte status, @Param("shouldPayMoney") Float shouldPayMoney,
			@Param("isUseInvitecode") Byte isUseInvitecode, @Param("orderNumber") String orderNumber);

	int updateByPrimaryKey(MallOrder record);

	/**
	 * 根据用户ID、状态查询订单列表
	 * 
	 * @param paramters
	 *            包含分页参数
	 * @return
	 */
	List<MallOrder> queryOrdersByUserIdAndStatus(Map<String, Object> paramters);

	/*
	 * 查询私人订制订单列表
	 */
	List<MallOrder> queryCustomerOrder(Map<String, Object> paramters);

	/*
	 * 查询订单被拆分后的信息
	 */
	List<MallOrder> queryParentOrders(String orderNumber);

	/*
	 * cms后台任务调度订单列表查询
	 */
	List<MallOrder> queryTaskOrders(@Param("status") Byte status, @Param("date") Date date);

	/*
	 * 查询私人订制订单总记录数
	 */
	Integer queryCustomerOrderCount(Map<String, Object> paramters);

	/*
	 * cms搜索订单信息
	 * 
	 * @param map
	 * 
	 * @return
	 */
	List<MallOrder> queryByParamters(Map<String, Object> map);

	Integer queryByParamtersCount(Map<String, Object> map);

	/**
	 * 根据用户ID、状态查询订单列表
	 * 
	 * @param paramters
	 * @return
	 */
	Integer queryOrdersByUserIdAndStatusCount(Map<String, Object> paramters);

	MallOrder getmMallOrder(@Param("userId") Long userId, @Param("orderNumber") String orderNumber);

	MallOrder getMallOrderByPayNumber(@Param("payNumber") String payNumber);
	
	/**
	 * 根据用户ID、条件查询订单列表，用于淘宝、天猫下单的查询
	 * @param userId
	 * @return
	 */
	List<MallOrder> queryTbOrders(Map<String, Object> paramters);
	
	/**
	 * 查询淘宝、天猫订单总数
	 * @param paramters
	 * @return
	 */
	int queryTbOrdersCount(Map<String, Object> paramters);
	
	int queryByRemarkCount(Map<String, Object> paramters);
	
	/**
	 * 根据备注查询货到付款订单
	 * @param paramters
	 * @return
	 */
	List<MallOrder> queryByRemark(Map<String, Object> paramters);
	
	int updateinvalidByOrderNumber(@Param("invalid") Byte invalid, @Param("orderNumber") String orderNumber);
}