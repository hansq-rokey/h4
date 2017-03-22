/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.papabear.order.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.papabear.commons.entity.Pager;
import com.papabear.commons.exception.PapabearException;
import com.papabear.commons.utils.Assert;
import com.papabear.commons.utils.CodeUtil;
import com.papabear.order.api.AfterSaleService;
import com.papabear.order.dao.MallAfterSalesServiceDao;
import com.papabear.order.dao.MallAfterSalesServiceItemDao;
import com.papabear.order.dao.MallAfterSalesServiceLogDao;
import com.papabear.order.dao.MallOrderDao;
import com.papabear.order.dao.MallOrderItemDao;
import com.papabear.order.entity.MallAfterSalesService;
import com.papabear.order.entity.MallAfterSalesServiceItem;
import com.papabear.order.entity.MallAfterSalesServiceLog;
import com.papabear.order.entity.MallOrder;
import com.papabear.order.entity.MallOrderItem;
import com.papabear.order.entity.OrderConstant.OrderStatus;
import com.papabear.order.entity.OrderConstant.ServiceRepairStatusEnum;
import com.papabear.order.exception.OrderResultCode;

/**
 * 售后服务实现
 * 
 * @author yaoweiguo
 * @email 280435353@qq.com
 * @date 2016年3月10日
 * @since 1.0.0
 */
public class AfterSaleServiceImpl implements AfterSaleService {

	@Resource
	private MallOrderDao orderDao;

	@Resource
	private MallOrderItemDao orderItemDao;
	@Resource
	private MallAfterSalesServiceDao afterSalesServiceDao;
	@Resource
	private MallAfterSalesServiceLogDao afterSalesServiceLogDao;
	@Resource
	private MallAfterSalesServiceItemDao afterSalesServiceItemDao;

	@Override
	@Transactional
	public MallAfterSalesService addAfterSalesService(MallAfterSalesService service, Long userId, String ip) {
		Assert.notNull(service);
		Assert.notNull(userId);
		List<MallOrderItem> list = service.getItems();
		if (list == null || list.size() == 0) {
			return null;
		}
		MallOrder order = orderDao.getmMallOrder(userId, service.getOrderNumber());
		if (order == null || order.getStatus().byteValue() != OrderStatus.COMPLETED.getStatus().byteValue()) {
			return null;
		}
		service.setUserId(userId);
		service.setCreateDateTime(new Date());
		service.setStatus(ServiceRepairStatusEnum.REPAIR_APPLY.getCode());// 维修、换货、退货第一步都是提交申请
		String serviceNumber = CodeUtil.getServiceNumber(userId);
		service.setServiceNumber(serviceNumber);
		afterSalesServiceDao.insert(service);
		for (MallOrderItem orderItem : list) {
			if (orderItem == null || orderItem.getId() == null)
				continue;
			MallOrderItem item = orderItemDao.selectByPrimaryKey(orderItem.getId());
			if (item == null || item.getUserId().longValue() != userId.longValue())
				continue;

			MallAfterSalesServiceItem serviceItem = new MallAfterSalesServiceItem();
			serviceItem.setUserId(userId);
			serviceItem.setProductId(item.getProductId());
			serviceItem.setProductModelFormatId(item.getProductModelFormatId());
			serviceItem.setUnitPrice(item.getUnitPrice());
			serviceItem.setDiscountUnitPrice(item.getDiscountUnitPrice());
			if (orderItem.getNum() == null || orderItem.getNum().intValue() < 1 || item.getNum().intValue() < orderItem.getNum().intValue()) {
				throw new PapabearException(OrderResultCode.AFTER_SALE_NUM_ERROR, null);
			}
			serviceItem.setNum(orderItem.getNum());
			serviceItem.setServiceId(service.getId());
			afterSalesServiceItemDao.insert(serviceItem);
		}
		this.addAfterSalesServiceLog(userId, service.getId(), ServiceRepairStatusEnum.REPAIR_APPLY.getCode());
		return service;
	}

	@Override
	public void addAfterSalesServiceLog(Long userId, Long serviceId, Byte serviceStatus) {

		MallAfterSalesServiceLog log = new MallAfterSalesServiceLog();
		log.setAdminId(userId);
		log.setCreateDateTime(new Date());
		log.setServiceId(serviceId);
		log.setServiceStatus(serviceStatus);// 维修、换货、退货第一步都是提交申请
		log.setStatus((byte) 1);
		afterSalesServiceLogDao.insert(log);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.papabear.order.api.AfterSaleService#updateAfterSalesService(java.
	 * lang.Byte, java.lang.Long, java.lang.Long)
	 */
	@Override
	public int updateAfterSalesService(Byte status, Long expressId, Long id) {
		
		return this.updateAfterSalesService(status, expressId, null, id);
	}

	@Override
	public Pager<MallAfterSalesService> queryAfterSalesServiceByServiceType(Long userId, Byte serviceType, int pageNo, int pageSize) {
		int total = 0;
		Pager<MallAfterSalesService> pager = new Pager<MallAfterSalesService>(total, pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("serviceType", serviceType);
		map.put("number", pager.getCurentNumber());
		map.put("pageSize", pageSize);
		List<MallAfterSalesService> list = afterSalesServiceDao.queryMallAfterSalesServiceByStatus(map);
		pager.setList(list);
		return pager;
	}

	@Override
	public List<MallAfterSalesServiceItem> queryAfterSalesServiceItems(Long serviceId) {

		return afterSalesServiceItemDao.queryAfterSalesServiceItemByServiceId(serviceId);
	}

	@Override
	public MallAfterSalesServiceItem getAfterSalesServiceItem(Long id) {
		return afterSalesServiceItemDao.selectByPrimaryKey(id);
	}

	@Override
	public Pager<MallAfterSalesService> queryAfterSalesServices(String keywords, Byte status, Date startDate, Date endDate, int pageNo, int pageSize) {
		
		return this.queryAfterSalesServices(keywords, (byte)1, status, startDate, endDate, pageNo, pageSize);
	}

	@Override
	public MallAfterSalesService getAfterSalesService(Long id) {
		// TODO Auto-generated method stub
		return afterSalesServiceDao.selectByPrimaryKey(id);
	}

	@Override
	public List<MallAfterSalesServiceLog> queryAfterSalesServiceLogs(Long serviceId) {
		
		return afterSalesServiceLogDao.queryAfterSalesServiceLogs(serviceId);
	}

	@Override
	public Pager<MallAfterSalesService> queryAfterSalesServices(String keywords, Byte serviceType, Byte status, Date startDate, Date endDate,
			int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keywords", keywords);
		map.put("serviceType", serviceType);
		map.put("startTime", startDate);
		map.put("endTime", endDate);
		int total = afterSalesServiceDao.queryAfterSalesServicesCount(map);
		Pager<MallAfterSalesService> pager = new Pager<MallAfterSalesService>(total, pageNo, pageSize);
		map.put("number", pager.getCurentNumber());
		map.put("pageSize", pageSize);
		List<MallAfterSalesService> list = afterSalesServiceDao.queryAfterSalesServices(map);
		pager.setList(list);
		return pager;
	}
	
	@Override
	public Pager<MallAfterSalesService> queryMallAfterSalesServices(String keywords,List<Byte> statusList, Byte serviceType, Date startDate, Date endDate,
			int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keywords", keywords);
		map.put("serviceType", serviceType);
		map.put("startTime", startDate);
		map.put("endTime", endDate);
		map.put("status",statusList);
		int total = afterSalesServiceDao.queryMallAfterSalesServicesCount(map);
		Pager<MallAfterSalesService> pager = new Pager<MallAfterSalesService>(total, pageNo, pageSize);
		map.put("number", pager.getCurentNumber());
		map.put("pageSize", pageSize);
		List<MallAfterSalesService> list = afterSalesServiceDao.queryMallAfterSalesServices(map);
		pager.setList(list);
		return pager;
	}

	/* (non-Javadoc)
	 * @see com.papabear.order.api.AfterSaleService#updateAfterSalesService(java.lang.Byte, java.lang.Long, java.lang.String, java.lang.Long)
	 */
	@Override
	public int updateAfterSalesService(Byte status, Long expressId, String refuseMemo, Long id) {
		MallAfterSalesService service = new MallAfterSalesService();
		service.setStatus(status);
		service.setExpressId(expressId);
		service.setRefuseMemo(refuseMemo);
		service.setId(id);
		return afterSalesServiceDao.updateByPrimaryKeySelective(service);
	}

	@Override
	public MallAfterSalesService getByOrderNumber(String orderNumber) {
		return afterSalesServiceDao.getByOrderNumber(orderNumber);
	}

	@Override
	public List<MallAfterSalesService> queryServiceByStatus(Byte status) {
		return afterSalesServiceDao.queryServiceByStatus(status);
	}

}
