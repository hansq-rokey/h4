package com.papabear.order.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.papabear.commons.entity.Pager;
import com.papabear.commons.entity.enumentity.InvalidEnum;
import com.papabear.commons.exception.PapabearException;
import com.papabear.commons.utils.Assert;
import com.papabear.commons.utils.CodeUtil;
import com.papabear.order.api.OrderService;
import com.papabear.order.dao.MallCarItemDao;
import com.papabear.order.dao.MallCarItemExtDao;
import com.papabear.order.dao.MallCarItemExtendDao;
import com.papabear.order.dao.MallOrderCashRecordDao;
import com.papabear.order.dao.MallOrderDao;
import com.papabear.order.dao.MallOrderHistoryDao;
import com.papabear.order.dao.MallOrderItemDao;
import com.papabear.order.dao.MallOrderItemExtDao;
import com.papabear.order.dao.MallOrderItemExtendDao;
import com.papabear.order.dao.MallOrderRevicerInformationDao;
import com.papabear.order.entity.MallCarItem;
import com.papabear.order.entity.MallCarItemExt;
import com.papabear.order.entity.MallCarItemExtend;
import com.papabear.order.entity.MallOrder;
import com.papabear.order.entity.MallOrderCashRecord;
import com.papabear.order.entity.MallOrderHistory;
import com.papabear.order.entity.MallOrderItem;
import com.papabear.order.entity.MallOrderItemExt;
import com.papabear.order.entity.MallOrderItemExtend;
import com.papabear.order.entity.MallOrderRevicerInformation;
import com.papabear.order.entity.OrderConstant.OrderOperateTye;
import com.papabear.order.entity.OrderConstant.OrderStatus;
import com.papabear.order.entity.enums.HouseTagEnum;
import com.papabear.order.exception.OrderResultCode;
import com.papabear.order.model.OrderParamters;
import com.papabear.pay.api.PayService;
import com.papabear.product.api.CategoryQueryService;
import com.papabear.product.api.ProductQueryService;
import com.papabear.product.entity.MallBasicCategoryModelFormat;
import com.papabear.product.entity.MallFormatExt;
import com.papabear.product.entity.MallProduct;

public class OrderServiceImpl implements OrderService {

	@Resource
	private MallOrderDao orderDao;
	@Resource
	private MallCarItemDao carItemDao;
	@Resource
	private MallCarItemExtDao carItemExtDao;
	@Resource
	private MallOrderItemDao orderItemDao;
	@Resource
	private MallOrderHistoryDao orderHistoryDao;
	@Resource
	private MallOrderRevicerInformationDao orderRevicerInformationDao;
	@Resource
	private CategoryQueryService categoryQueryService;
	@Resource
	private ProductQueryService productQueryService;
	@Resource
	private PayService payService;
	@Resource
	private MallOrderItemExtDao orderItemExtDao;
	@Resource
	private MallOrderItemExtendDao orderItemExtendDao;
	@Resource
	private MallCarItemExtendDao  carItemExtendDao;
	@Resource
	private MallOrderCashRecordDao orderCashRecordDao;
	@Override
	public Pager<MallOrder> queryOrders(int pageNo, int pageSize) {

		return queryOrdersByStatus(null, pageNo, pageSize);
	}

	@Override
	public Pager<MallOrder> queryOrdersByStatus(Byte status, int pageNo, int pageSize) {

		return queryOrdersByUserIdAndStatus(null, status, pageNo, pageSize);
	}

	@Override
	public Pager<MallOrder> queryOrdersByUserIdAndStatus(Long userId, Byte status, int pageNo, int pageSize) {
		Map<String, Object> paramters = new HashMap<String, Object>();
		paramters.put("status", status == null ? null : new Byte[] { status });
		paramters.put("userId", userId);
		paramters.put("pageSize", pageSize);
		// TODO 查询记录数
		int total = orderDao.queryOrdersByUserIdAndStatusCount(paramters);
		Pager<MallOrder> pager = new Pager<MallOrder>(total, pageNo, pageSize);
		paramters.put("numbers", pager.getCurentNumber());
		List<MallOrder> list = orderDao.queryOrdersByUserIdAndStatus(paramters);
		pager.setList(list);
		return pager;
	}

	@Override
	public Pager<MallOrder> queryOrdersByStatusArr(Byte[] statusArr, int pageNo, int pageSize) {

		return this.queryOrdersByUserIdAndStatusArr(null, statusArr, pageNo, pageSize);
	}

	@Override
	public Pager<MallOrder> queryOrdersByUserIdAndStatusArr(Long userId, Byte[] statusArr, int pageNo, int pageSize) {
		Map<String, Object> paramters = new HashMap<String, Object>();
		paramters.put("status", statusArr);
		paramters.put("userId", userId);
		paramters.put("pageSize", pageSize);
		// TODO 查询记录数
		int total = orderDao.queryOrdersByUserIdAndStatusCount(paramters);
		Pager<MallOrder> pager = new Pager<MallOrder>(total, pageNo, pageSize);
		paramters.put("numbers", pager.getCurentNumber());
		List<MallOrder> list = orderDao.queryOrdersByUserIdAndStatus(paramters);
		pager.setList(list);
		return pager;
	}

	@Override
	public Pager<MallOrder> queryOrdersBysearch(String keyword, Date startDate, Date endDate, Byte status, int pageNo,
			int pageSize,Byte invalid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("invalid", invalid);
		map.put("keywords", keyword);
		map.put("startTime", startDate);
		if (endDate != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(endDate);
			calendar.add(Calendar.HOUR_OF_DAY, 23);
			calendar.add(Calendar.MINUTE, 59);
			calendar.add(Calendar.SECOND, 59);
			endDate = calendar.getTime();
		}
		map.put("endTime", endDate);
		List<Byte> statusList = new ArrayList<Byte>();
		if (status == null) {
			for (OrderStatus os : OrderStatus.values()) {
				if (os.getStatus().intValue() != 60) {
					statusList.add(os.getStatus());
				}
			}
		} else if (status != null && status.intValue() != 20) {
			statusList.add(status);
		} else if (status.intValue() == 20) {
			statusList.add(OrderStatus.CUSTOM_MADE_STORAGE.getStatus());
			statusList.add(OrderStatus.PAID.getStatus());
			map.put("isCustomMade", 0);
		}
		map.put("status", statusList);
		int total = orderDao.queryByParamtersCount(map);
		Pager<MallOrder> pager = new Pager<MallOrder>(total, pageNo, pageSize);
		map.put("pageSize", pageSize);
		map.put("numbers", pager.getCurentNumber());
		List<MallOrder> orderList = orderDao.queryByParamters(map);
		pager.setList(orderList);
		return pager;
	}

	@Override
	public Pager<MallOrder> queryTbOrdersBysearch(String keyword, Date startDate, Date endDate, Byte status, int pageNo,
			int pageSize,Byte type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keywords", keyword);
		map.put("startTime", startDate);
		if (endDate != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(endDate);
			calendar.add(Calendar.HOUR_OF_DAY, 23);
			calendar.add(Calendar.MINUTE, 59);
			calendar.add(Calendar.SECOND, 59);
			endDate = calendar.getTime();
		}
		map.put("endTime", endDate);
		List<Byte> statusList = new ArrayList<Byte>();
		if (status == null) {
			for (OrderStatus os : OrderStatus.values()) {
				if (os.getStatus().intValue() != 60) {
					statusList.add(os.getStatus());
				}
			}
		} else if (status != null && status.intValue() != 20) {
			statusList.add(status);
		} else if (status.intValue() == 20) {
			statusList.add(OrderStatus.CUSTOM_MADE_STORAGE.getStatus());
			statusList.add(OrderStatus.PAID.getStatus());
			map.put("isCustomMade", 0);
		}
		map.put("status", statusList);
		map.put("type", type);
		int total = orderDao.queryTbOrdersCount(map);
		Pager<MallOrder> pager = new Pager<MallOrder>(total, pageNo, pageSize);
		map.put("pageSize", pageSize);
		map.put("numbers", pager.getCurentNumber());
		List<MallOrder> orderList = orderDao.queryTbOrders(map);
		pager.setList(orderList);
		return pager;
	}
	
	@Override
	public Pager<MallOrder> queryActivityOrdersBysearch(String keyword, Date startDate, Date endDate, Byte status, int pageNo,
			int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keywords", keyword);
		map.put("startTime", startDate);
		if (endDate != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(endDate);
			calendar.add(Calendar.HOUR_OF_DAY, 23);
			calendar.add(Calendar.MINUTE, 59);
			calendar.add(Calendar.SECOND, 59);
			endDate = calendar.getTime();
		}
		map.put("endTime", endDate);
		List<Byte> statusList = new ArrayList<Byte>();
		if (status == null) {
			for (OrderStatus os : OrderStatus.values()) {
				if (os.getStatus().intValue() != 60) {
					statusList.add(os.getStatus());
				}
			}
		} else if (status != null && status.intValue() != 20) {
			statusList.add(status);
		} else if (status.intValue() == 20) {
			statusList.add(OrderStatus.CUSTOM_MADE_STORAGE.getStatus());
			statusList.add(OrderStatus.PAID.getStatus());
			map.put("isCustomMade", 0);
		}
		map.put("status", statusList);
		map.put("remark", "[货到付款]");
		int total = orderDao.queryByRemarkCount(map);
		Pager<MallOrder> pager = new Pager<MallOrder>(total, pageNo, pageSize);
		map.put("pageSize", pageSize);
		map.put("numbers", pager.getCurentNumber());
		List<MallOrder> orderList = orderDao.queryByRemark(map);
		pager.setList(orderList);
		return pager;
	}
	@Override
	@Transactional
	public MallOrder createCartOrder(MallOrder order, Long addressId, String deliverTimeName, String deliverValue,
			String ip) {
		float totalMoney = 0;// 订单总金额
		float favourablePrice = 0;// 订单优惠后的金额
		float totalNum = 0;// 总购买数量
		List<MallCarItem> carItemList = carItemDao.queryCarItemsByUserId(order.getUserId());
		if (carItemList.size() == 0) {
			throw new PapabearException(OrderResultCode.cart_IS_EMPTY, null);
		}

		String orderNumber = CodeUtil.getOrderNumber(order.getUserId());// 订单编号
		// String payNumber = CodeUtil.getPayNumber(order.getUserId());// 付款编号

		// 保存订单信息
		order.setCreateDateTime(new Date());
		order.setStatus(OrderStatus.OBLIGATION.getStatus());
		order.setOrderNumber(orderNumber);
		// order.setPayNumber(payNumber);
		order.setIsCustomMade((byte) 0);
		orderDao.insert(order);// 保存订单信息
		String remark = "";
		List<MallCarItemExt> carItemExts = null;
		// 保存订单商品信息
		for (MallCarItem item : carItemList) {
			// TODO 查询产品规格属性
			MallBasicCategoryModelFormat format = categoryQueryService.getCategoryModelFormat(item
					.getProductModelFormatId());
			MallProduct product = productQueryService.getProduct(item.getProductId());
			if (format.getStock().intValue() < item.getNum().intValue()) {
				throw new PapabearException(OrderResultCode.STOCK_NOT_ENOUGH, null);
			}
			remark += "--" + product.getTitle();
			if (format.getIsExtProperties() != null && format.getIsExtProperties().intValue() == 1) {
				carItemExts = carItemExtDao.queryCarItemExts(item.getUserId(), item.getId());
			}
			// 保存商品详情
			Long orderItemId = this.addOrderItem(order, product, format, (byte) 0, item.getNum());
			this.addOrderItemExt(null, carItemExts, orderItemId, order.getOrderNumber(), product.getId(),
					format.getId(), order.getUserId());
			totalMoney += item.getTotalPrice();
			favourablePrice += item.getDiscountUnitPrice();
			totalNum += item.getNum();
		}

		String payNumber = payService.createOrderPay(order.getOrderNumber(), totalMoney, favourablePrice,
				order.getUserId(), remark,null);

		MallOrder updateOrder = new MallOrder();
		updateOrder.setTotalNum(totalNum);
		updateOrder.setTotalPrice(totalMoney);
		updateOrder.setDiscountPrice(favourablePrice);
		updateOrder.setPayNumber(payNumber);
		updateOrder.setId(order.getId());
		orderDao.updateByPrimaryKeySelective(updateOrder);
		// 删除购物车
		for (MallCarItem item : carItemList) {
			carItemDao.deleteByPrimaryKey(item.getId());
			carItemExtDao.deleteByCarItemId(item.getId());
		}
		// 添加订单历史记录
		this.addOrderHistory(orderNumber, order.getUserId(), ip, OrderStatus.OBLIGATION.getStatus(),
				OrderOperateTye.USER.getOperateType());
		return order;
	}

	@Override
	@Transactional
	public MallOrder createSingleOrder(MallOrder order, Long addressId, String deliverTimeName, String deliverValue,
			String ip, Long formatId, List<MallFormatExt> extList) {
		MallBasicCategoryModelFormat format = categoryQueryService.getCategoryModelFormat(formatId);
		if (format.getStock().intValue() < order.getTotalNum().intValue()) {
			throw new PapabearException(OrderResultCode.STOCK_NOT_ENOUGH, null);
		}
		MallProduct product = productQueryService.getProductByModeleId(format.getCategoryModelId());
		if (product == null) {
			throw new PapabearException(OrderResultCode.PRODUCT_NOT_FIND, null);
		}

		String orderNumber = CodeUtil.getOrderNumber(order.getUserId());// 订单编号
		// String payNumber = CodeUtil.getPayNumber(order.getUserId());// 付款编号

		// 保存订单信息
		order.setCreateDateTime(new Date());
		order.setStatus(OrderStatus.OBLIGATION.getStatus());
		order.setOrderNumber(orderNumber);
		// order.setPayNumber(payNumber);
		order.setIsCustomMade(format.getIsCustomMade());
		order.setTotalPrice(format.getPrice() * order.getTotalNum());
		order.setDiscountPrice(format.getDiscountPrice() * order.getTotalNum());
		orderDao.insert(order);// 保存订单信息

		// 保存订单商品信息
		Long orderItemId = this.addOrderItem(order, product, format, format.getIsCustomMade(), order.getTotalNum());
		this.addOrderItemExt(extList, null, orderItemId, order.getOrderNumber(), product.getId(), format.getId(),
				order.getUserId());
		// 保存支付信息
		String payNumber = payService.createOrderPay(order.getOrderNumber(), order.getTotalPrice(),
				order.getDiscountPrice(), order.getUserId(), "购买" + product.getTitle(),null);
		order.setPayNumber(payNumber);
		orderDao.updateByPrimaryKeySelective(order);

		// 添加订单历史记录
		this.addOrderHistory(orderNumber, order.getUserId(), ip, OrderStatus.OBLIGATION.getStatus(),
				OrderOperateTye.USER.getOperateType());

		return order;
	}

	@Override
	public MallOrder getOrderByOrderNumber(Long userId, String orderNumber) {
		Assert.notNull(userId);
		Assert.notNull(orderNumber);
		return orderDao.getmMallOrder(userId, orderNumber);
	}

	@Override
	public MallOrder getMallOrder(String orderNumber) {

		return orderDao.getmMallOrder(null, orderNumber);
	}

	@Override
	public MallOrder getMallOrderByPayNumber(String payNumber) {
		return orderDao.getMallOrderByPayNumber(payNumber);
	}

	@Override
	public int updateOrderStatus(Byte status, String orderNumber) {

		return this.updateOrder(null, status, null, orderNumber);
	}

	/**
	 * 保存订单变更信息
	 * 
	 * @param orderNumber
	 *            订单编号
	 * @param userId
	 *            用户ID
	 * @param ip
	 *            ip地址
	 * @param status
	 *            订单状态
	 * @param operateType
	 *            操作类型
	 * 
	 */
	public int addOrderHistory(String orderNumber, Long userId, String ip, Byte status, Byte operateType) {
		return this.addOrderHistory(orderNumber, null, userId, ip, status, operateType);
	}

	public int addOrderHistory(String orderNumber, Long adminId, Long userId, String ip, Byte status, Byte operateType) {
		MallOrderHistory history = new MallOrderHistory();
		history.setOrderNumber(orderNumber);
		history.setOperatorType(operateType);
		history.setUserId(userId);
		history.setAdminId(adminId);
		history.setOperatorIp(ip);
		history.setCreateDateTime(new Date());
		history.setUpdateTime(new Date());
		history.setStatus(status);
		return orderHistoryDao.insert(history);
	}

	/**
	 * 保存订单详细信息
	 * 
	 * @param order
	 *            订单信息
	 * @param product
	 *            产品信息
	 * @param format
	 *            产品规格信息
	 * @param isCustomMade
	 *            是否属于私人定制
	 * @param num
	 *            购买数量
	 * @return
	 */
	@Deprecated
	private Long addOrderItem(MallOrder order, MallProduct product, MallBasicCategoryModelFormat format,
			Byte isCustomMade, Float num) {
		MallOrderItem orderItem = new MallOrderItem();
		orderItem.setUserId(order.getUserId());
		orderItem.setOrderNumber(order.getOrderNumber());
		orderItem.setProductId(product.getId());
		orderItem.setProductModelFormatId(format.getId());
		orderItem.setUnitPrice(format.getPrice());
		orderItem.setDiscountUnitPrice(format.getDiscountPrice());
		orderItem.setNum(num);
		orderItem.setUnit(format.getUnit());
		orderItem.setSendedNum(0f);
		orderItem.setIsCustomMade(isCustomMade);
		orderItem.setProductModelFormatName(format.getName());
		orderItem.setProductFeature(product.getFeature());
		orderItem.setProductTitle(product.getTitle());
		orderItemDao.insert(orderItem);

		return orderItem.getId();
	}

	/**
	 * C端订单单价就是零售价，
	 * B端订单单价=该规格出厂价*B端经销商采购系数，同时将出厂价和系数同时保存
	 * @param userId
	 * @param orderNumber
	 * @param product
	 * @param format
	 * @param num
	 * @param type
	 * @param tagCode
	 * @param totalPrice
	 * @param unitPrice
	 * @param productPurchasePrice
	 * @param fixateProfit
	 * @return
	 */
	private Long addOrderItem(Long userId, String orderNumber, MallProduct product,
			MallBasicCategoryModelFormat format, Float num, Byte type,Integer tagCode,
			Float totalPrice,Float unitPrice,Float productPurchasePrice,Float fixateProfit) {
		MallOrderItem orderItem = new MallOrderItem();
		orderItem.setUserId(userId);
		orderItem.setOrderNumber(orderNumber);
		orderItem.setProductId(product.getId());
		orderItem.setProductModelFormatId(format.getId());
		orderItem.setNum(num);
		orderItem.setUnit(format.getUnit());
		orderItem.setSendedNum(0f);
		orderItem.setIsCustomMade(format.getIsCustomMade());
		orderItem.setProductModelFormatName(format.getName());
		orderItem.setProductFeature(product.getFeature());
		orderItem.setProductTitle(product.getTitle());
		orderItem.setType(type);

		orderItem.setTotalPrice(totalPrice);
		orderItem.setDiscountUnitPrice(unitPrice);
		orderItem.setUnitPrice(unitPrice);
		orderItem.setProductPurchasePrice(productPurchasePrice);
		orderItem.setFixateProfit(fixateProfit);
		
		orderItem.setTagCode(tagCode);
		orderItem.setTagName(HouseTagEnum.getTagName(tagCode));
		orderItemDao.insert(orderItem);

		return orderItem.getId();
	}

	/**
	 * * 保存订单详细信息
	 * 
	 * @param userId
	 *            用户ID
	 * @param orderNumber
	 *            订单信息
	 * @param product
	 *            产品信息
	 * @param formatId
	 *            规格ID
	 * @param unitPrice
	 *            单价
	 * @param discountPrice
	 *            优惠价
	 * @param unit
	 *            单位
	 * @param formatName
	 *            规格名称
	 * @param isCustomMade
	 *            是否属于私人定制
	 * @param num
	 *            购买数量
	 * @return
	 */
//	private Long addOrderItem(Long userId, String orderNumber, MallProduct product, Long formatId, Float unitPrice,
//			Float discountPrice, String unit, String formatName, Byte isCustomMade, Integer num) {
//		MallOrderItem orderItem = new MallOrderItem();
//		orderItem.setUserId(userId);
//		orderItem.setOrderNumber(orderNumber);
//		orderItem.setProductId(product.getId());
//		orderItem.setProductModelFormatId(formatId);
//		orderItem.setUnitPrice(unitPrice);
//		orderItem.setDiscountUnitPrice(discountPrice);
//		orderItem.setNum(num);
//		orderItem.setUnit(unit);
//		orderItem.setSendedNum(0);
//		orderItem.setIsCustomMade(isCustomMade);
//		orderItem.setProductModelFormatName(formatName);
//		orderItem.setProductFeature(product.getFeature());
//		orderItem.setProductTitle(product.getTitle());
//		orderItemDao.insert(orderItem);
//
//		return orderItem.getId();
//	}

	/**
	 * 
	 * @param orderItemId
	 * @param orderNumber
	 * @param productId
	 * @param productModelFormatId
	 * @param groundAreaUnit
	 * @param unit
	 * @param paramters
	 */
	private void addOrderItemExtend(Long orderItemId,String orderNumber, Long productId, Long productModelFormatId, 
			Long userId,Float groundArea,String groundAreaUnit,Float height,Float length,Float width,String unit){
		MallOrderItemExtend orderItemExtend=new MallOrderItemExtend();
		orderItemExtend.setGroundArea(groundArea);
		orderItemExtend.setGroundAreaUnit(groundAreaUnit);
		orderItemExtend.setHeight(height);
		orderItemExtend.setLength(length);
		orderItemExtend.setOrderItemId(orderItemId);
		orderItemExtend.setOrderNumber(orderNumber);
		orderItemExtend.setProductId(productId);
		orderItemExtend.setUnit(unit);
		orderItemExtend.setUserId(userId);
		orderItemExtend.setWidth(width);
		orderItemExtend.setProductModelFormatId(productModelFormatId);
		orderItemExtendDao.insertSelective(orderItemExtend);
		
	}
	private void addOrderItemExt(List<MallFormatExt> extList, List<MallCarItemExt> carItemExts, Long orderItemId,
			String orderNumber, Long productId, Long productModelFormatId, Long userId) {
		if (extList != null && extList.size() > 0) {
			for (MallFormatExt formatExt : extList) {
				MallOrderItemExt ext = new MallOrderItemExt();
				ext.setFormatExtId(formatExt.getId());
				ext.setFormatExtValue(formatExt.getValue());
				ext.setIdentify(formatExt.getIdentify());
				ext.setOrderItemId(orderItemId);
				ext.setPropertyName(formatExt.getPropertyName());
				ext.setOrderNumber(orderNumber);
				ext.setProductId(productId);
				ext.setProductModelFormatId(productModelFormatId);
				ext.setUserId(userId);
				orderItemExtDao.insert(ext);
			}
		} else if (carItemExts != null && carItemExts.size() > 0) {
			for (MallCarItemExt carItemExt : carItemExts) {
				MallOrderItemExt ext = new MallOrderItemExt();
				ext.setFormatExtId(carItemExt.getId());
				ext.setFormatExtValue(carItemExt.getFormatExtValue());
				ext.setIdentify(carItemExt.getIdentify());
				ext.setOrderItemId(orderItemId);
				ext.setOrderNumber(orderNumber);
				ext.setPropertyName(carItemExt.getPropertyName());
				ext.setProductId(productId);
				ext.setProductModelFormatId(productModelFormatId);
				ext.setUserId(userId);
				orderItemExtDao.insert(ext);
			}
		}
	}

	@Override
	public MallOrderRevicerInformation getRevicerByUserIdAndOrderNumber(Long userId, String orderNumber) {

		return orderRevicerInformationDao.getOrderRevicerInformation(userId, orderNumber);
	}

	@Override
	public List<MallOrderItem> queryOrderItems(Long userId, String orderNumber) {

		return orderItemDao.queryOrderItems(userId, orderNumber);
	}

	@Override
	public List<MallOrderHistory> queryHistoryByOrderNumber(String orderNumber) {

		return orderHistoryDao.queryMallOrderHistoryByOrderNumber(orderNumber);
	}

	@Override
	public MallOrderHistory getMallOrderHistory(String orderNumber, Byte status, Long userId) {

		return orderHistoryDao.getMallOrderHistory(orderNumber, status, userId);
	}

	@Override
	public int updateOrderExpressId(Long expressId, String orderNumber) {

		return this.updateOrder(expressId, null, null, orderNumber);
	}

	@Override
	public int updateOrderByPrice(Float price, String orderNumber) {

		return this.updateOrder(null, null, price, orderNumber);
	}

	private int updateOrder(Long expressId, Byte status, Float price, String orderNumber) {

		return orderDao.updateOrder(expressId, status, price, null, orderNumber);
	}

	@Override
	public Long saveMallOrderRevicerInformation(MallOrderRevicerInformation information) {
		orderRevicerInformationDao.insert(information);
		return information.getId();
	}

	@Override
	public int updateOrder(MallOrder order) {
		return orderDao.updateByPrimaryKeySelective(order);
	}

	@Override
	public int updateOrderItem(MallOrderItem orderItem) {
		return orderItemDao.updateByPrimaryKeySelective(orderItem);
	}

	@Override
	public MallOrderItem getMallOrderItem(Long id) {
		return orderItemDao.selectByPrimaryKey(id);
	}

	@Override
	public Pager<MallOrder> queryCustomerOrder(String keywords, Byte isCustomMade, List<Byte> statusList, int pageNo,
			int pageSize) {
		return this.queryCustomerOrder(keywords, null, null, null, statusList, isCustomMade, pageNo, pageSize);
	}

	@Override
	public Pager<MallOrder> queryCustomerOrder(String keywords, Date startDate, Date endDate, Integer payType,
			Byte isCustomMade, int pageNo, int pageSize) {
		List<Byte> statusList = new ArrayList<Byte>();
		for (OrderStatus os : OrderStatus.values()) {
			if (os.getStatus().intValue() > 10 && os.getStatus().intValue() != 60) {
				statusList.add(os.getStatus());
			}
		}
		return this.queryCustomerOrder(keywords, startDate, endDate, payType, statusList, isCustomMade, pageNo,
				pageSize);
	}

	private Pager<MallOrder> queryCustomerOrder(String keywords, Date startDate, Date endDate, Integer payType,
			List<Byte> statusList, Byte isCustomMade, int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();

		// map.put("payType", payType);//目前订单没有这个字段
		map.put("keywords", keywords);
		map.put("startTime", startDate);
		map.put("endTime", endDate);
		map.put("invalid", InvalidEnum.FALSE.getInvalidValue());
		map.put("isCustomMade", isCustomMade);
		map.put("pageSize", pageSize);
		map.put("status", statusList);
		int total = orderDao.queryCustomerOrderCount(map);
		Pager<MallOrder> pager = new Pager<MallOrder>(total, pageNo, pageSize);
		map.put("numbers", pager.getCurentNumber());
		List<MallOrder> list = orderDao.queryCustomerOrder(map);
		pager.setList(list);
		return pager;
	}

	@Override
	public List<MallOrder> queryParentOrders(String orderNumber) {

		return orderDao.queryParentOrders(orderNumber);
	}

	@Override
	public List<MallOrder> queryTaskOrders(Byte status, Date date) {

		return orderDao.queryTaskOrders(status, date);
	}

	@Override
	public int updateOrderIsUseInviteCode(Byte IsUseInvitecode, String orderNumber) {

		return orderDao.updateOrder(null, null, null, IsUseInvitecode, orderNumber);
	}

	@Override
	public MallOrderItem getMallOrderItem(String orderNumber, Long formatId) {

		return orderItemDao.getByOrderNumberAndFormatId(orderNumber, formatId);
	}

	@Override
	public List<MallOrderItem> queryOrderItems(String orderNumber) {

		return this.queryOrderItems(null, orderNumber);
	}

	@Override
	public int addMallOrderItem(MallOrderItem orderItem) {

		return orderItemDao.insertSelective(orderItem);
	}

	@Override
	public int addMallOrder(MallOrder order) {
		return orderDao.insertSelective(order);
	}

	@Override
	public MallOrder getMallOrder(Long id) {

		return orderDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateMallOrderRevicerInformation(String expressNo, String orderNumber) {
		Assert.notNull(expressNo);
		Assert.notNull(orderNumber);
		return orderRevicerInformationDao.updateExpressNoByOrderNumber(expressNo, orderNumber);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.papabear.order.api.OrderService#queryOrderItemExts(java.lang.String, java.lang.Long)
	 */
	@Override
	public List<MallOrderItemExt> queryOrderItemExts(String orderNumber, Long orderItemId) {

		return orderItemExtDao.queryOrderItemExts(orderNumber, orderItemId);
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see com.papabear.order.api.OrderService#createSingleSpecialOrder(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, com.papabear.product.entity.MallBasicCategoryModelFormat, java.lang.Float, java.lang.Byte, java.lang.Byte, java.lang.Byte, java.lang.Byte, java.lang.Float, java.lang.Byte, java.lang.String, java.util.List)
	 */
	@Override
	public MallOrder createSingleSpecialOrder(Long userId, String userName, String bxNum, String remark,
			Float totalNum, MallBasicCategoryModelFormat format, Float totalPrice, Byte isUseInvitecode, Byte source,
			Byte isMakeBill, Byte isInstall, Float frontMoney, Byte isUseFrontMoney, String ip,Integer tagCode,
			List<MallFormatExt> extList) {
		if (format.getStock().intValue() < totalNum.intValue()) {
			throw new PapabearException(OrderResultCode.STOCK_NOT_ENOUGH, null);
		}
		MallProduct product = productQueryService.getProductByModeleId(format.getCategoryModelId());
		if (product == null) {
			throw new PapabearException(OrderResultCode.PRODUCT_NOT_FIND, null);
		}

		String orderNumber = CodeUtil.getOrderNumber(userId);// 订单编号
		MallOrder order = this.createMallOrder(userId, bxNum, userName, remark, source, totalNum, frontMoney,
				isInstall, isMakeBill, isUseFrontMoney, orderNumber, format.getIsCustomMade(), totalPrice,
				format.getDiscountPrice() * totalNum,isUseInvitecode);

		orderDao.insert(order);// 保存订单信息

		// 保存订单商品信息
		Long orderItemId = this.addOrderItem(userId, orderNumber, product, format, totalNum, (byte) 2,tagCode,totalPrice,format.getPrice(),null,null);
		this.addOrderItemExt(extList, null, orderItemId, order.getOrderNumber(), product.getId(), format.getId(),
				order.getUserId());
		// 保存支付信息
		String payNumber = payService.createOrderPay(order.getOrderNumber(), order.getTotalPrice() - frontMoney,
				order.getDiscountPrice(), order.getUserId(), "购买" + product.getTitle(),null);
		order.setPayNumber(payNumber);
		orderDao.updateByPrimaryKeySelective(order);

		// 添加订单历史记录
		this.addOrderHistory(orderNumber, userId, ip, OrderStatus.OBLIGATION.getStatus(),
				OrderOperateTye.USER.getOperateType());

		return order;
	}

	/**
	 * 保存订单信息
	 * @author yaoweiguo
	 * @date 2016年7月26日
	 * @param userId
	 * @param bxNum
	 * @param userName
	 * @param remark
	 * @param source
	 * @param totalNum
	 * @param frontMoney
	 * @param isInstall
	 * @param isMakeBill
	 * @param isUseFrontMoney
	 * @param orderNumber
	 * @param isCustomMade
	 * @param totalPrice
	 * @param discountPrice
	 * @param isUseInvitecode
	 * @return
	 */
	private MallOrder createMallOrder(Long userId, String bxNum, String userName, String remark, Byte source,
			Float totalNum, Float frontMoney, Byte isInstall, Byte isMakeBill, Byte isUseFrontMoney,
			String orderNumber, Byte isCustomMade, Float totalPrice, Float discountPrice,Byte isUseInvitecode) {
		MallOrder order = new MallOrder();
		// 保存订单信息
		order.setUserId(userId);
		order.setBxNum(bxNum);
		order.setUserName(userName);
		order.setRemark(remark);
		order.setSource(source);
		order.setTotalNum(totalNum);
		order.setFrontMoney(frontMoney);
		order.setIsInstall(isInstall);
		order.setIsMakeBill(isMakeBill);
		order.setIsUseFrontMoney(isUseFrontMoney);
		order.setIsUseInvitecode(isUseInvitecode);
		order.setCreateDateTime(new Date());
		order.setStatus(OrderStatus.OBLIGATION.getStatus());
		order.setOrderNumber(orderNumber);
		order.setIsCustomMade(isCustomMade);
		order.setTotalPrice(totalPrice);
		order.setDiscountPrice(discountPrice);
		return order;
	}
	
	private MallOrder createMallOrder(OrderParamters paramters,String orderNumber,Float totalPrice,Byte isCustomMade ) {
		MallOrder order = new MallOrder();
		// 保存订单信息
		order.setUserId(paramters.getUserId());
		order.setBxNum(paramters.getBxNum());
		order.setUserName(paramters.getUserName());
		order.setRemark(paramters.getRemark());
		order.setSource(paramters.getSource());
		order.setTotalNum(paramters.getTotalNum());
		order.setFrontMoney(paramters.getFrontMoney());
		order.setIsInstall(paramters.getIsInstall());
		order.setIsMakeBill(paramters.getIsMakeBill());
		order.setIsUseFrontMoney(paramters.getIsUseFrontMoney());
		order.setIsUseInvitecode(paramters.getIsUseInvitecode());
		order.setCreateDateTime(new Date());
		order.setStatus(OrderStatus.OBLIGATION.getStatus());
		order.setOrderNumber(orderNumber);
		order.setIsCustomMade(isCustomMade);
		order.setTotalPrice(totalPrice);
		order.setDiscountPrice(totalPrice);
		order.setType(paramters.getType());
		order.setEnableCouponMoney(paramters.getCouponMoney());
		order.setShouldPayMoney(totalPrice);
		return order;
	}
	/**
	 * 货到付款订单
	 * @param paramters
	 * @param orderNumber
	 * @param totalPrice
	 * @param isCustomMade
	 * @return
	 */
	private MallOrder createSourceOrder(OrderParamters paramters,String orderNumber,Float totalPrice,Byte isCustomMade ) {
		MallOrder order = new MallOrder();
		// 保存订单信息
		order.setUserId(paramters.getUserId());
		order.setBxNum(paramters.getBxNum());
		order.setUserName(paramters.getUserName());
		order.setRemark(paramters.getRemark());
		order.setSource(paramters.getSource());
		order.setTotalNum(paramters.getTotalNum());
		order.setFrontMoney(paramters.getFrontMoney());
		order.setIsInstall(paramters.getIsInstall());
		order.setIsMakeBill(paramters.getIsMakeBill());
		order.setIsUseFrontMoney(paramters.getIsUseFrontMoney());
		order.setIsUseInvitecode(paramters.getIsUseInvitecode());
		order.setCreateDateTime(new Date());
		order.setStatus(OrderStatus.OBLIGATION.getStatus());
		order.setOrderNumber(orderNumber);
		order.setIsCustomMade(isCustomMade);
		order.setTotalPrice(totalPrice);
		order.setDiscountPrice(totalPrice);
		order.setType(paramters.getType());
		return order;
	}

	@Override
	public MallOrder createSingleCommonOrder(Long userId, String userName, String bxNum, String remark,
			Float totalNum, MallBasicCategoryModelFormat format, Byte isUseInvitecode, Byte source, Byte isMakeBill,
			Byte isInstall, Float frontMoney, Byte isUseFrontMoney, String ip) {
		if (format.getStock().intValue() < totalNum.intValue()) {
			throw new PapabearException(OrderResultCode.STOCK_NOT_ENOUGH, null);
		}
		MallProduct product = productQueryService.getProductByModeleId(format.getCategoryModelId());
		if (product == null) {
			throw new PapabearException(OrderResultCode.PRODUCT_NOT_FIND, null);
		}

		String orderNumber = CodeUtil.getOrderNumber(userId);// 订单编号
		// String payNumber = CodeUtil.getPayNumber(order.getUserId());// 付款编号

		// 保存订单信息
		MallOrder order = this.createMallOrder(userId, bxNum, userName, remark, source, totalNum, frontMoney,
				isInstall, isMakeBill, isUseFrontMoney, orderNumber, format.getIsCustomMade(), format.getPrice()
						* totalNum, format.getDiscountPrice() * totalNum,isUseInvitecode);
		orderDao.insert(order);// 保存订单信息

		// 保存订单商品信息
		this.addOrderItem(userId, orderNumber, product, format, totalNum, (byte) 1,null,format.getPrice()*totalNum,format.getPrice(),null,null);
		// 保存支付信息
		String payNumber = payService.createOrderPay(order.getOrderNumber(), order.getTotalPrice() - frontMoney,
				order.getDiscountPrice(), order.getUserId(), "购买" + product.getTitle(),null);
		order.setPayNumber(payNumber);
		orderDao.updateByPrimaryKeySelective(order);

		// 添加订单历史记录
		this.addOrderHistory(orderNumber, userId, ip, OrderStatus.OBLIGATION.getStatus(),
				OrderOperateTye.USER.getOperateType());

		return order;
	}

	@Override
	public MallOrder createCartCommonOrder(OrderParamters params,List<MallCarItem> carList) {
		float totalMoney = 0;// 订单总金额
		float favourablePrice = 0;// 订单优惠后的金额
		float totalNum = 0;// 总购买数量
		List<MallCarItem> carItemList = carList;
//		if (StringUtils.isNotBlank(orderItemIds)) {
//			// TODO 如果系统支持选择某个购物车中的商品下单时，需要用到这里
//		} else {
//			carItemList = carItemDao.queryCarItemsByUserId(userId);
//
//		}
		if (carItemList.size() == 0) {
			throw new PapabearException(OrderResultCode.cart_IS_EMPTY, null);
		}

		String orderNumber = CodeUtil.getOrderNumber(params.getUserId());// 订单编号

		// 保存订单信息

		// 由于购物车订单总金额、优惠金额通过购物车中所有商品总计后得出，后面进行更新
		MallOrder order = this.createMallOrder(params, orderNumber,totalMoney,(byte)0);
		orderDao.insert(order);
		
		
		StringBuffer payRemark = new StringBuffer("");
		MallCarItemExtend carItemExtend = null;
		// 保存订单商品信息
		for (MallCarItem item : carItemList) {
			// TODO 查询产品规格属性
			MallBasicCategoryModelFormat format = categoryQueryService.getCategoryModelFormat(item
					.getProductModelFormatId());
			MallProduct product = productQueryService.getProduct(item.getProductId());
			if (format.getStock().intValue() < item.getNum().intValue()) {
				throw new PapabearException(OrderResultCode.STOCK_NOT_ENOUGH, null);
			}
			payRemark.append("--" + product.getTitle());
			Long orderItemId = null;
			if (format.getIsExtProperties() != null && format.getIsExtProperties().intValue() == 1) {
				carItemExtend=carItemExtendDao.getCarItemExtend(params.getUserId(), item.getId());
				// 保存发热墙布商品详情TODO
				orderItemId = this.addOrderItem(params.getUserId(), orderNumber, product, format, item.getNum(), item.getType(),item.getTagCode(),
						item.getTotalPrice(),null,null,null);
				this.addOrderItemExtend(orderItemId, orderNumber, product.getId(), format.getId(), params.getUserId(), carItemExtend.getGroundArea(),
						null, carItemExtend.getHeight(), carItemExtend.getLength(), carItemExtend.getWidth(), null);
			}else{
				// 保存普通商品详情TODO
				orderItemId = this.addOrderItem(params.getUserId(), orderNumber, product, format, item.getNum(), item.getType(),item.getTagCode(),
						item.getTotalPrice(),format.getPrice(),null,null);
			}
//			// 保存商品详情TODO
//			Long orderItemId = this.addOrderItem(params.getUserId(), orderNumber, product, format, item.getNum(), item.getType(),item.getTagCode(),
//					item.getTotalPrice(),format.getPrice(),format.getProductPurchasePrice(),null);
//			this.addOrderItemExt(null, carItemExts, orderItemId, order.getOrderNumber(), product.getId(),
//					format.getId(), order.getUserId());
			totalMoney += item.getTotalPrice();
			// TODO 在购物车中添加优惠后的金额
			// favourablePrice += item.getDiscountUnitPrice();
			totalNum += item.getNum();
		}

		String payNumber = payService.createOrderPay(order.getOrderNumber(), totalMoney - params.getFrontMoney(), totalMoney,
				order.getUserId(), payRemark.toString(),null);

		MallOrder updateOrder = new MallOrder();
		updateOrder.setTotalNum(totalNum);
		updateOrder.setTotalPrice(totalMoney);
		updateOrder.setDiscountPrice(totalMoney);
		updateOrder.setPayNumber(payNumber);
		updateOrder.setEnableCouponMoney(0f);
		updateOrder.setShouldPayMoney(totalMoney - params.getFrontMoney());
		updateOrder.setId(order.getId());
		orderDao.updateByPrimaryKeySelective(updateOrder);
		// 删除购物车
		for (MallCarItem item : carItemList) {
			carItemDao.deleteByPrimaryKey(item.getId());
			carItemExtendDao.deleteByCarItemId(item.getId());
		}
		// 添加订单历史记录
		this.addOrderHistory(orderNumber, order.getUserId(), params.getIp(), OrderStatus.OBLIGATION.getStatus(),
				OrderOperateTye.USER.getOperateType());
		return order;
	}
	/* C端
	 * (non-Javadoc)
	 * @see com.papabear.order.api.OrderService#createSingleSpecialOrder(com.papabear.order.model.OrderParamters)
	 */
	@Override
	public MallOrder createSingleSpecialOrder(OrderParamters paramters) {
		MallBasicCategoryModelFormat format=paramters.getFormat();
		if (format.getStock().intValue() < paramters.getTotalNum().intValue()) {
			throw new PapabearException(OrderResultCode.STOCK_NOT_ENOUGH, null);
		}
		MallProduct product = productQueryService.getProductByModeleId(format.getCategoryModelId());
		if (product == null) {
			throw new PapabearException(OrderResultCode.PRODUCT_NOT_FIND, null);
		}

		String orderNumber = CodeUtil.getOrderNumber(paramters.getUserId());// 订单编号


		MallOrder order=this.createMallOrder(paramters, orderNumber,paramters.getTotalPrice(),format.getIsCustomMade());
		orderDao.insert(order);// 保存订单信息

		// 保存订单商品信息
		Long orderItemId = this.addOrderItem(paramters.getUserId(), orderNumber, product, format, paramters.getTotalNum(), 
				(byte) 2,paramters.getTagCode(),paramters.getTotalPrice(),null,null,paramters.getFixateProfit());

//		this.addOrderItemExtend(orderItemId, orderNumber, product.getId(), format.getId(),null, null,paramters);
		this.addOrderItemExtend(orderItemId, orderNumber, product.getId(), format.getId(), paramters.getUserId(), paramters.getGroundArea(),
				null, paramters.getHeight(), paramters.getLength(), paramters.getWidth(), null);
		// 保存支付信息
		String payNumber = payService.createOrderPay(order.getOrderNumber(), order.getTotalPrice() - paramters.getFrontMoney(),order.getTotalPrice() - paramters.getFrontMoney(), order.getUserId(), "购买" + product.getTitle(),paramters.getCityId());
		order.setPayNumber(payNumber);
		orderDao.updateByPrimaryKeySelective(order);

		// 添加订单历史记录
		this.addOrderHistory(orderNumber, paramters.getUserId(), paramters.getIp(), OrderStatus.OBLIGATION.getStatus(),
				OrderOperateTye.USER.getOperateType());

		return order;
	}
	/* B端
	 * (non-Javadoc)
	 * @see com.papabear.order.api.OrderService#createMerchantSingleSpecialOrder(com.papabear.order.model.OrderParamters)
	 */
	@Override
	public MallOrder createMerchantSingleSpecialOrder(OrderParamters paramters) {
		MallBasicCategoryModelFormat format=paramters.getFormat();
		if (format.getStock().intValue() < paramters.getTotalNum().intValue()) {
			throw new PapabearException(OrderResultCode.STOCK_NOT_ENOUGH, null);
		}
		MallProduct product = productQueryService.getProductByModeleId(format.getCategoryModelId());
		if (product == null) {
			throw new PapabearException(OrderResultCode.PRODUCT_NOT_FIND, null);
		}

		String orderNumber = CodeUtil.getOrderNumber(paramters.getUserId());// 订单编号


		MallOrder order=this.createMallOrder(paramters, orderNumber,paramters.getTotalPrice(),format.getIsCustomMade());
		orderDao.insert(order);// 保存订单信息

		// 保存订单商品信息
		Long orderItemId = this.addOrderItem(paramters.getUserId(), orderNumber, product, format, paramters.getTotalNum(), 
				(byte) 2,paramters.getTagCode(),paramters.getTotalPrice(),null,null,paramters.getFixateProfit());

//		this.addOrderItemExtend(orderItemId, orderNumber, product.getId(), format.getId(),null, null,paramters);
		this.addOrderItemExtend(orderItemId, orderNumber, product.getId(), format.getId(), paramters.getUserId(), paramters.getGroundArea(),
				null, paramters.getHeight(), paramters.getLength(), paramters.getWidth(), null);
		// 保存支付信息
		String payNumber = payService.createOrderPay(order.getOrderNumber(), order.getTotalPrice() - paramters.getFrontMoney()-paramters.getCouponMoney(),order.getTotalPrice() - paramters.getFrontMoney()-paramters.getCouponMoney(), order.getUserId(), "购买" + product.getTitle(),paramters.getCityId());
		MallOrder updateOrder = new MallOrder();
		updateOrder.setUpdateTime(new Date());
		updateOrder.setId(order.getId());
		updateOrder.setPayNumber(payNumber);
		updateOrder.setShouldPayMoney(order.getTotalPrice() - paramters.getFrontMoney()-paramters.getCouponMoney());
		orderDao.updateByPrimaryKeySelective(updateOrder);

		// 添加订单历史记录
		this.addOrderHistory(orderNumber, paramters.getUserId(), paramters.getIp(), OrderStatus.OBLIGATION.getStatus(),
				OrderOperateTye.USER.getOperateType());

		return order;
	}

	/* B端
	 * (non-Javadoc)
	 * @see com.papabear.order.api.OrderService#createMerchantSingleCommonOrder(com.papabear.order.model.OrderParamters)
	 */
	@Override
	public MallOrder createMerchantSingleCommonOrder(OrderParamters paramters) {
		MallBasicCategoryModelFormat format=paramters.getFormat();
		Long userId=paramters.getUserId();
		if (format.getStock().intValue() < paramters.getTotalNum().intValue()) {
			throw new PapabearException(OrderResultCode.STOCK_NOT_ENOUGH, null);
		}
		MallProduct product = productQueryService.getProductByModeleId(format.getCategoryModelId());
		if (product == null) {
			throw new PapabearException(OrderResultCode.PRODUCT_NOT_FIND, null);
		}

		String orderNumber = CodeUtil.getOrderNumber(userId);// 订单编号
		// String payNumber = CodeUtil.getPayNumber(order.getUserId());// 付款编号

		// 保存订单信息
		MallOrder order=this.createMallOrder(paramters, orderNumber,paramters.getTotalPrice(),format.getIsCustomMade());
		orderDao.insert(order);// 保存订单信息

		// 保存订单商品信息
//		this.addOrderItem(userId, orderNumber, product, format, totalNum, (byte) 1,null,format.getPrice()*totalNum);
		
		this.addOrderItem(paramters.getUserId(), orderNumber, product, format, paramters.getTotalNum(),
				(byte) 1,null,paramters.getTotalPrice(),format.getProductPurchasePrice()*paramters.getFixateProfit(),
				format.getProductPurchasePrice(),paramters.getFixateProfit());
		// 保存支付信息
		String payNumber = payService.createOrderPay(order.getOrderNumber(), order.getTotalPrice() - paramters.getFrontMoney()-paramters.getCouponMoney(),
				order.getTotalPrice() - paramters.getFrontMoney()-paramters.getCouponMoney(), order.getUserId(), "购买" + product.getTitle(),paramters.getCityId());
		MallOrder updateOrder = new MallOrder();
		updateOrder.setUpdateTime(new Date());
		updateOrder.setId(order.getId());
		updateOrder.setPayNumber(payNumber);
		updateOrder.setShouldPayMoney(order.getTotalPrice() - paramters.getFrontMoney()-paramters.getCouponMoney());
		orderDao.updateByPrimaryKeySelective(updateOrder);

		// 添加订单历史记录
		this.addOrderHistory(orderNumber, userId, paramters.getIp(), OrderStatus.OBLIGATION.getStatus(),
				OrderOperateTye.USER.getOperateType());

		return order;
	}

	
	/**
	 * C端
	 * @param paramters
	 * @return
	 */
	@Override
	public MallOrder createMerchantSingleCommonOrderC(OrderParamters paramters) {
		MallBasicCategoryModelFormat format=paramters.getFormat();
		Long userId=paramters.getUserId();
		if (format.getStock().intValue() < paramters.getTotalNum().intValue()) {
			throw new PapabearException(OrderResultCode.STOCK_NOT_ENOUGH, null);
		}
		MallProduct product = productQueryService.getProductByModeleId(format.getCategoryModelId());
		if (product == null) {
			throw new PapabearException(OrderResultCode.PRODUCT_NOT_FIND, null);
		}

		String orderNumber = CodeUtil.getOrderNumber(userId);// 订单编号
		// String payNumber = CodeUtil.getPayNumber(order.getUserId());// 付款编号

		// 保存订单信息
		MallOrder order=this.createMallOrder(paramters, orderNumber,paramters.getTotalPrice(),format.getIsCustomMade());
		orderDao.insert(order);// 保存订单信息

		// 保存订单商品信息
//		this.addOrderItem(userId, orderNumber, product, format, totalNum, (byte) 1,null,format.getPrice()*totalNum);
		
		this.addOrderItem(paramters.getUserId(), orderNumber, product, format, paramters.getTotalNum(),
				(byte) 1,null,paramters.getTotalPrice(),format.getPrice(),format.getProductPurchasePrice(),null);
		// 保存支付信息
		String payNumber = payService.createOrderPay(order.getOrderNumber(), order.getTotalPrice() - paramters.getFrontMoney(),
				order.getDiscountPrice(), order.getUserId(), "购买" + product.getTitle(),null);
		order.setPayNumber(payNumber);
		orderDao.updateByPrimaryKeySelective(order);

		// 添加订单历史记录
		this.addOrderHistory(orderNumber, userId, paramters.getIp(), OrderStatus.OBLIGATION.getStatus(),
				OrderOperateTye.USER.getOperateType());

		return order;
	}
	@Override
	public MallOrder createMerchantCartCommonOrder(OrderParamters paramters,List<MallCarItem> carItems) {
		float totalMoney = 0;// 订单总金额
		float favourablePrice = 0;// 订单优惠后的金额
		float totalNum = 0;// 总购买数量
		List<MallCarItem> carItemList = carItems;
		Long userId=paramters.getUserId();

		if (carItemList.size() == 0) {
			throw new PapabearException(OrderResultCode.cart_IS_EMPTY, null);
		}

		String orderNumber = CodeUtil.getOrderNumber(userId);// 订单编号

		// 保存订单信息

		// 由于购物车订单总金额、优惠金额通过购物车中所有商品总计后得出，后面进行更新
//		MallOrder order = this.createMallOrder(userId, bxNum, userName, remark, source, totalNum, frontMoney,isInstall, isMakeBill, isUseFrontMoney, orderNumber, (byte) 0, null, null,isUseInvitecode);
		MallOrder order=this.createMallOrder(paramters, orderNumber,totalMoney,(byte)0);
		orderDao.insert(order);
		
		
		StringBuffer payRemark = new StringBuffer("");
//		List<MallCarItemExt> carItemExts = null;
		MallCarItemExtend carItemExtend=null;
		// 保存订单商品信息
		for (MallCarItem item : carItemList) {
			// TODO 查询产品规格属性
			MallBasicCategoryModelFormat format = categoryQueryService.getCategoryModelFormat(item.getProductModelFormatId());
			
			MallProduct product = productQueryService.getProduct(item.getProductId());
			if (format.getStock().intValue() < item.getNum().intValue()) {
				throw new PapabearException(OrderResultCode.STOCK_NOT_ENOUGH, null);
			}
			
			payRemark.append("--" + product.getTitle());
			
			Long orderItemId =null;
			//发热墙布商品单独独立计算
			if (format.getIsExtProperties() != null && format.getIsExtProperties().intValue() == 1) {
				//计算
				carItemExtend=carItemExtendDao.getCarItemExtend(userId, item.getId());
				// 保存发热墙布商品详情TODO
				orderItemId = this.addOrderItem(userId, orderNumber, product, format, item.getNum(), item.getType(),item.getTagCode(),
						item.getTotalPrice(),null,null,paramters.getFixateProfit());
				this.addOrderItemExtend(orderItemId, orderNumber, product.getId(), format.getId(), paramters.getUserId(), carItemExtend.getGroundArea(),
						null, carItemExtend.getHeight(), carItemExtend.getLength(), carItemExtend.getWidth(), null);
			}else{
				// 保存普通商品详情TODO
				orderItemId = this.addOrderItem(userId, orderNumber, product, format, item.getNum(), item.getType(),item.getTagCode(),
						item.getTotalPrice(),format.getProductPurchasePrice()*paramters.getFixateProfit(),format.getProductPurchasePrice(),paramters.getFixateProfit());
			}
			totalMoney += item.getTotalPrice();
			totalNum += item.getNum();
		}

		String payNumber = payService.createOrderPay(order.getOrderNumber(), totalMoney - paramters.getFrontMoney()-paramters.getCouponMoney(), totalMoney - paramters.getFrontMoney()-paramters.getCouponMoney(),order.getUserId(), payRemark.toString(),paramters.getCityId());

		MallOrder updateOrder = new MallOrder();
		updateOrder.setTotalNum(totalNum);
		updateOrder.setTotalPrice(totalMoney);
		updateOrder.setDiscountPrice(totalMoney);
		updateOrder.setPayNumber(payNumber);
		updateOrder.setId(order.getId());
		updateOrder.setShouldPayMoney(totalMoney - paramters.getFrontMoney()-paramters.getCouponMoney());
		orderDao.updateByPrimaryKeySelective(updateOrder);
		// 删除购物车
		for (MallCarItem item : carItemList) {
			carItemDao.deleteByPrimaryKey(item.getId());
//			carItemExtDao.deleteByCarItemId(item.getId());
			carItemExtendDao.deleteByCarItemId(item.getId());
		}
		// 添加订单历史记录
		this.addOrderHistory(orderNumber, order.getUserId(), paramters.getIp(), OrderStatus.OBLIGATION.getStatus(),OrderOperateTye.USER.getOperateType());
		return order;
	}

	@Override
	public MallOrderItemExtend getOrderItemExtend(Long userId, Long orderItemId) {

		return orderItemExtendDao.getOrderItemExtend(userId, orderItemId);
	}

	@Override
	public int addOrderCashRecord(MallOrderCashRecord orderRecord) {
		return orderCashRecordDao.insertSelective(orderRecord);
	}

	@Override
	public int updateinvalidByOrderNumber(Byte invilid, String orderNumber) {
		return orderDao.updateinvalidByOrderNumber(invilid, orderNumber);
	}
}
