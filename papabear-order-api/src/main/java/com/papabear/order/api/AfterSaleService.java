/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.papabear.order.api;

import java.util.Date;
import java.util.List;

import com.papabear.commons.entity.Pager;
import com.papabear.order.entity.MallAfterSalesService;
import com.papabear.order.entity.MallAfterSalesServiceItem;
import com.papabear.order.entity.MallAfterSalesServiceLog;

/**
 * 售后服务相关接口
 * @author yaoweiguo
 * @email  280435353@qq.com
 * @date   2016年3月10日
 * @since  1.0.0
 */
public interface AfterSaleService {

	/**
	 * 添加售后服务订单
	 * @param service	售后服务对象,必填	
	 * @param userId	用户ID，必填
	 * @param ip		ip地址
	 * @return
	 */
	MallAfterSalesService addAfterSalesService(MallAfterSalesService service,Long userId,String ip);
	/**
	 * 通过主键更新
	 * @param status		状态
	 * @param expressId		物流
	 * @param id			主键ID
	 * @return
	 */
	int updateAfterSalesService(Byte status,Long expressId,Long id);
	
	/**
	 * 通过主键更新
	 * @param status		状态
	 * @param expressId		物流
	 * @param refuseMemo	拒绝原因
	 * @param id			主键ID
	 * @return
	 */
	int updateAfterSalesService(Byte status,Long expressId,String refuseMemo,Long id);
	
    /**
     * 获取售后列表
     * @param userId		用户ID
     * @param serviceType  售后类型
     * @param pageNo		页码
     * @param pageSize		每页数量
     * @return
     */
    Pager<MallAfterSalesService> queryAfterSalesServiceByServiceType(Long userId,Byte serviceType, int pageNo, int pageSize);
	/**
	 * 保存售后服务订单操作日志
	 * @param userId
	 * @param serviceId
	 * @param serviceStatus
	 */
	void addAfterSalesServiceLog(Long userId,Long serviceId,Byte serviceStatus);
	/**
	 * 查询售后服务日志
	 * @param serviceId		服务ID
	 * @return
	 */
	List<MallAfterSalesServiceLog> queryAfterSalesServiceLogs(Long serviceId);
	/**
	 * 查询售后具体商品信息
	 * @param serviceId		售后订单ID
	 * @return
	 */
	List<MallAfterSalesServiceItem> queryAfterSalesServiceItems(Long serviceId);
	/**
	 * 查询售后服务订单详情
	 * @param id
	 * @return
	 */
	MallAfterSalesServiceItem getAfterSalesServiceItem(Long id);
	
	/**
	 * 查询售后服务订单
	 * @param id
	 * @return
	 */
	MallAfterSalesService getAfterSalesService(Long id);
	
	/**
	 * 查询售后服务列表
	 * @param keywords			搜索关键字
	 * @param status			状态
	 * @param startDate			开始时间
	 * @param endDate			介绍时间
	 * @param pageNo			页码
	 * @param pageSize			每页显示记录数
	 * @return
	 */
	Pager<MallAfterSalesService> queryAfterSalesServices(String keywords,Byte status, Date startDate, Date endDate, int pageNo, int pageSize);
	/**
	 * 查询售后服务列表 
	 * @param keywords			搜索关键词
	 * @param serviceType		服务类型
	 * @param status			状态
	 * @param startDate			开始时间
	 * @param endDate			结束时间
	 * @param pageNo			页码
	 * @param pageSize			每页显示记录数
	 * @return
	 */
	Pager<MallAfterSalesService> queryAfterSalesServices(String keywords,Byte serviceType,Byte status, Date startDate, Date endDate, int pageNo, int pageSize);

	
	/**
	 * 根据订单号查询售后服务订单
	 * @param orderNumber
	 * @return
	 */
	MallAfterSalesService getByOrderNumber(String orderNumber);
	
	/**
	 * 根据售后服务状态查询服务订单
	 * @param status
	 * @return
	 */
	List<MallAfterSalesService> queryServiceByStatus(Byte status);
	/**
	 * 查询退款状态是41和50的订单（同意和已退款）
	 * @param keywords
	 * @param serviceType
	 * @param status
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pager<MallAfterSalesService> queryMallAfterSalesServices(String keywords,List<Byte> statusList,
			Byte serviceType, Date startDate, Date endDate,
			int pageNo, int pageSize);
}
