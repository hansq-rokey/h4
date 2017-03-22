package com.papabear.order.api;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.papabear.commons.entity.Pager;
import com.papabear.order.entity.MallCarItem;
import com.papabear.order.entity.MallOrder;
import com.papabear.order.entity.MallOrderCashRecord;
import com.papabear.order.entity.MallOrderHistory;
import com.papabear.order.entity.MallOrderItem;
import com.papabear.order.entity.MallOrderItemExt;
import com.papabear.order.entity.MallOrderItemExtend;
import com.papabear.order.entity.MallOrderRevicerInformation;
import com.papabear.order.model.OrderParamters;
import com.papabear.product.entity.MallBasicCategoryModelFormat;
import com.papabear.product.entity.MallFormatExt;

/**
 * 订单操作API
 * 
 * @author yaoweiguo
 * @email 280435353@qq.com
 * @date 2016年3月2日
 * @since 1.0.0
 */
public interface OrderService {

	/**
	 * 查询全部订单
	 * 
	 * @param pageNo
	 *            当前页数
	 * @param pageSize
	 *            页码条数
	 * @return
	 */
	Pager<MallOrder> queryOrders(int pageNo, int pageSize);

	/**
	 * 根据订单状态查询订单信息
	 * <p>
	 * 订单类型查看OrderStatus
	 * 
	 * @see com.papabear.order.entity.OrderConstant
	 * @param status
	 *            订单状态
	 * @param pageNo
	 *            当前页数
	 * @param pageSize
	 *            页码条数
	 * @return
	 */
	Pager<MallOrder> queryOrdersByStatus(Byte status, int pageNo, int pageSize);

	/**
	 * 查询用户及订单状态查询订单信息
	 * 
	 * @param userId
	 *            用户ID
	 * @param status
	 *            订单状态
	 * @param pageNo
	 *            当前页数
	 * @param pageSize
	 *            页码条数
	 * @return
	 */
	Pager<MallOrder> queryOrdersByUserIdAndStatus(Long userId, Byte status, int pageNo, int pageSize);

	/**
	 * 根据订单状态查询订单信息
	 * 
	 * @see
	 * @param statusArr
	 *            订单状态数组
	 * @param pageNo
	 *            当前页数
	 * @param pageSize
	 *            页码条数
	 * @return
	 */
	Pager<MallOrder> queryOrdersByStatusArr(Byte[] statusArr, int pageNo, int pageSize);

	/**
	 * 查询用户及订单状态查询订单信息
	 * 
	 * @param userId
	 *            用户ID
	 * @param statusArr
	 *            订单状态集合
	 * @param pageNo
	 *            当前页数
	 * @param pageSize
	 *            页码条数
	 * @return
	 */
	Pager<MallOrder> queryOrdersByUserIdAndStatusArr(Long userId, Byte[] statusArr, int pageNo, int pageSize);

	/**
	 * 查询用户及订单状态查询订单信息
	 * 
	 * @param userId
	 *            用户ID
	 * @param statusArr
	 *            订单状态集合
	 * @param pageNo
	 *            当前页数
	 * @param pageSize
	 *            页码条数
	 * @return
	 */
	Pager<MallOrder> queryOrdersBysearch(String keyword, Date startDate, Date endDate, Byte status, int pageNo, int pageSize,Byte invilid);
	/**
	 * 查询天猫、淘宝上下单的订单信息
	 * 
	 * @param 
	 *            
	 * @param statusArr
	 *            订单状态集合
	 * @param pageNo
	 *            当前页数
	 * @param pageSize
	 *            页码条数
	 * @return
	 */
	Pager<MallOrder> queryTbOrdersBysearch(String keyword, Date startDate, Date endDate, Byte status, int pageNo, int pageSize,Byte type);

	/**
	 * 查询私人订制订单信息
	 * 
	 * @param keyword			查询关键词
	 * @param isCustomMade		是否私人订制
	 * @param status			状态
	 * @param pageNo			页码
	 * @param pageSize			每页显示记录数
	 * @return
	 */
	Pager<MallOrder> queryCustomerOrder(String keyword,Byte isCustomMade,List<Byte> statusList, int pageNo, int pageSize);
	/**
	 * 查询私人订制订单信息
	 * 
	 * @param keyword			查询关键词
	 * @param startDate			开始时间
	 * @param endDate			结束时间
	 * @param payType			支付方式
	 * @param isCustomMade		是否私人订制
	 * @param pageNo			页码
	 * @param pageSize			每页显示记录数
	 * @return
	 */
	Pager<MallOrder> queryCustomerOrder(String keyword, Date startDate, Date endDate, Integer payType, Byte isCustomMade, int pageNo, int pageSize);

	/**
	 * 查询订单被拆分后的订单列表
	 * 
	 * @param orderNumber	订单编号
	 * @return
	 */
	List<MallOrder> queryParentOrders(String orderNumber);
	
	/**
	 * 任务调度订单列表查询
	 * 
	 * @param status
	 *            状态
	 * @param date
	 *            截止时间
	 * @return
	 */
	List<MallOrder> queryTaskOrders(Byte status, Date date);
	
	/**
	 * 主要用于前台查询 后期需要对这个接口的引用重新调整，将cms、erp等后台用其他调用，同时，后期代码加上断言
	 * 
	 * @param user
	 *            用户ID，必填项
	 * @param orderNumber
	 *            订单号,必填
	 * @return
	 */
	MallOrder getOrderByOrderNumber(Long userId, String orderNumber);
	/**
	 * 主要用户后台查询，可以根据订单号直接查询
	 * 
	 * @param orderNumber  订单编号
	 * @return
	 */
	MallOrder getMallOrder(String orderNumber);


	/**
	 * 主要在后台用到，尽量少用这个方法，统一通过订单编号来查询订单信息
	 * <p>后期进行优化调整
	 * @param id	订单ID
	 * @return
	 */
	MallOrder getMallOrder(Long id);
	
	/**
	 * 购物车下单，默认将购物车内商品转换为订单商品，同时删除购物车内商品
	 * <p>
	 * 关于订单相关参数通过订单对象来进行传参
	 * 
	 * @param order
	 *            订单对象
	 * @param addressId
	 *            订单所选择的收货人地址ID
	 * @param deliverTimeName
	 *            收货时间内容
	 * @param deliverValue
	 *            收货时间ID
	 * @param ip
	 *            IP地址
	 * @return
	 */
	@Deprecated
	MallOrder createCartOrder(MallOrder order, Long addressId, String deliverTimeName, String deliverValue, String ip);
	
	/**
	 * 购物车下单，默认将购物车内商品转换为订单商品，同时删除购物车内商品
	 * <p>
	 * 关于订单相关参数通过订单对象来进行传参
	 * 
	 * @param order
	 *            订单对象
	 * @param addressId
	 *            订单所选择的收货人地址ID
	 * @param deliverTimeName
	 *            收货时间内容
	 * @param deliverValue
	 *            收货时间ID
	 * @param ip
	 *            IP地址
	 * @since V1.1
	 * @return
	 */
	MallOrder createCartCommonOrder(OrderParamters params,List<MallCarItem> carList);

	/**
	 * 单个商品下单，其中购买数量就是订单中的总数量来代替
	 * 
	 * @param order
	 *            订单对象
	 * @param addressId
	 *            订单所选择的收货人地址ID
	 * @param deliverTimeName
	 *            收货时间内容
	 * @param deliverValue
	 *            收货时间ID
	 * @param ip
	 *            IP地址
	 * @param formatId
	 *            产品规格ID
	 * @return
	 */
	@Deprecated
	MallOrder createSingleOrder(MallOrder order, Long addressId, String deliverTimeName, String deliverValue, String ip, Long formatId,List<MallFormatExt> extList);
	/**
	 * 创建特殊商品单个下单接口，例如  发热墙纸
	 * @param userId				用户ID
	 * @param userName				用户名
	 * @param bxNum					白熊号
	 * @param remark				订单备注
	 * @param totalNum				订单数量
	 * @param format				规格对象
	 * @param totalPrice			订单总金额
	 * @param isUseInvitecode		是否使用邀请码
	 * @param source				订单来源
	 * @param isMakeBill			是否开具发票信息
	 * @param isInstall				是否上门安装
	 * @param frontMoney			定金金额
	 * @param isUseFrontMoney		是否使用定金支付
	 * @param ip					用户IP
	 * @param extList				扩展属性集合
	 * @since V1.1
	 * @return
	 */
	MallOrder createSingleSpecialOrder(Long userId,String userName, String bxNum,String remark,Float totalNum,
			MallBasicCategoryModelFormat format,Float totalPrice,Byte isUseInvitecode, Byte source, Byte isMakeBill,
			Byte isInstall, Float frontMoney, Byte isUseFrontMoney, String ip,Integer tagCode,List<MallFormatExt> extList);
	/**
	 * 创建普通商品单个下单接口
	 * @param userId				用户ID
	 * @param userName				用户名
	 * @param bxNum					白熊号
	 * @param remark				订单备注
	 * @param totalNum				订单数量
	 * @param format				规格对象
	 * @param isUseInvitecode		是否使用邀请码
	 * @param source				订单来源
	 * @param isMakeBill			是否开具发票信息
	 * @param isInstall				是否上门安装
	 * @param frontMoney			定金金额
	 * @param isUseFrontMoney		是否使用定金支付
	 * @param ip					用户IP
	 * @since V1.1
	 * @return
	 */
	MallOrder createSingleCommonOrder(Long userId,String userName, String bxNum,String remark,Float totalNum,MallBasicCategoryModelFormat format,Byte isUseInvitecode, 
			Byte source, Byte isMakeBill, Byte isInstall, Float frontMoney, Byte isUseFrontMoney, String ip);
	
	
	/**
	 * 创建B端经销商下单，特殊商品单个下单接口，例如  发热墙纸
	 * @param paramters  参数	
	 */
	MallOrder createMerchantSingleSpecialOrder(OrderParamters paramters);
	
	/**
	 * C端直接下单
	 * @param paramters
	 * @return
	 */
	MallOrder createSingleSpecialOrder(OrderParamters paramters);
	
	/**
	 * 创建B端经销商下单，创建普通商品单个下单接口
	 * @param paramters  参数，具体详见{@link com.papabear.order.model.OrderParamters}
	 * @since V1.3.2
	 * @return
	 */
	MallOrder createMerchantSingleCommonOrder(OrderParamters paramters);
	
	/**
	 * 创建C端经销商下单，创建普通商品单个下单接口
	 * @param paramters  参数，具体详见{@link com.papabear.order.model.OrderParamters}
	 * @since V1.3.2
	 * @return
	 */
	MallOrder createMerchantSingleCommonOrderC(OrderParamters paramters);
	
	/**
	 * 创建B端经销商购物车下单,默认将购物车内商品转换为订单商品，同时删除购物车内商品
	 * <p>
	 * 关于订单相关参数通过订单对象来进行传参
	 * @author ywg 
	 * @param paramters			参数对象
	 * @param orderItemIds		选中部分购物车中产品进行下单，目前默认全部
	 * @since V1.3.2
	 * @return
	 */
	MallOrder createMerchantCartCommonOrder(OrderParamters paramters,List<MallCarItem> carItems);
	
	/**
	 * 创建订单信息,只负责往订单表中插入记录，其他表不涉及
	 * 
	 * @param order		订单实体对象
	 * @return
	 */
	int addMallOrder(MallOrder order);

	/**
	 * 更新订单状态
	 * 
	 * @param status
	 *            订单状态
	 * @param orderNumber
	 *            订单编号
	 * @return
	 */
	int updateOrderStatus(Byte status, String orderNumber);

	/**
	 * 更新是否使用邀请码
	 * 
	 * @param IsUseInvitecode
	 * @param orderNumber
	 * @return
	 */
	int updateOrderIsUseInviteCode(Byte IsUseInvitecode, String orderNumber);

	/**
	 * 更新订单状态
	 * @param order 订单实体对象
	 * @return
	 */
	int updateOrder(MallOrder order);

	/**
	 * 更新订单状态
	 * 
	 * @param status
	 *            订单状态
	 * @param orderNumber
	 *            订单编号
	 * @return
	 */
	int updateOrderExpressId(Long expressId, String orderNumber);

	/**
	 * 手动更改价格
	 * 
	 * @param price
	 *            优惠后的价格
	 * @param orderNumber
	 *            订单编号
	 * @return
	 */
	int updateOrderByPrice(Float price, String orderNumber);
	/**
	 * 更新订单收货人中的物流信息
	 * @param expressNo		物流编号，必填
	 * @param orderNumber	订单编号，必填
	 * @return
	 */
	int updateMallOrderRevicerInformation(String expressNo,String orderNumber);

	/**
	 * 根据订单编号和用户ID查询订单收货人信息
	 * 
	 * @param userId
	 *            用户ID
	 * @param orderNumber
	 *            订单编号
	 * @return
	 */
	MallOrderRevicerInformation getRevicerByUserIdAndOrderNumber(Long userId, String orderNumber);
	/**
	 * 保存订单收货人信息
	 * 
	 * @param information  收货人实体对象
	 * @return
	 */
	Long saveMallOrderRevicerInformation(MallOrderRevicerInformation information);

	
	
	/**
	 * 查询订单历史记录
	 * 
	 * @param orderNumber
	 *            订单编号
	 * @return
	 */
	List<MallOrderHistory> queryHistoryByOrderNumber(String orderNumber);
	/**
	 * 查询某订单状态变更历史记录
	 * @param orderNumber
	 * @param orderStatusEnum
	 * @param user
	 * @return
	 */
	MallOrderHistory getMallOrderHistory(String orderNumber, Byte status, Long userId);
	/**
	 * 只要修改了订单数据，都需要在订单历史记录表进行添加一条记录
	 * 
	 * @param orderNumber		订单编号
	 * @param userId			用户ID
	 * @param ip				用户操作IP
	 * @param status			订单状态
	 * @param operateType		操作类型 {@link com.papabear.order.entity.OrderConstant.OrderOperateTye}
	 * @return
	 */
	int addOrderHistory(String orderNumber, Long userId, String ip, Byte status, Byte operateType);

	/**
	 * 保存操作订单历史记录
	 * 
	 * @param orderNumber		订单编号
	 * @param adminId			 后台管理人员ID
	 * @param userId			用户ID
	 * @param ip				操作IP
	 * @param status			状态
	 * @param operateType		操作类型{@link com.papabear.order.entity.OrderConstant.OrderOperateTye}
	 * @return
	 */
	int addOrderHistory(String orderNumber, Long adminId, Long userId, String ip, Byte status, Byte operateType);

	/**
	 * TODO，对于cms系统引用的重新调整 根据订单编号和用户ID查询订单商品信息 主要服务于前台系统，
	 * 
	 * @param userId
	 *            用户ID，必填项
	 * @param orderNumber
	 *            订单编号
	 * @return
	 */
	List<MallOrderItem> queryOrderItems(Long userId, String orderNumber);

	/**
	 * 根据订单编号查询订单商品信息 主要服务于cms、erp等后台管理系统
	 * 
	 * @param userId
	 *            用户ID 可以为null
	 * @param orderNumber
	 *            订单编号
	 * @return
	 */
	List<MallOrderItem> queryOrderItems(String orderNumber);
	
	/**
	 * 查询订单扩展属性值
	 * @author yaoweiguo
	 * @date 2016年5月31日
	 * @param orderNumber
	 * @param orderItemId
	 * @return
	 */
	List<MallOrderItemExt> queryOrderItemExts(String orderNumber,Long orderItemId);

	/**
	 * 更新订单详细商品信息
	 * 
	 * @param orderItem
	 * @return
	 */
	int updateOrderItem(MallOrderItem orderItem);

	/**
	 * 查询商品具体信息
	 * 
	 * @param id
	 *            详情ID
	 * @return
	 */
	MallOrderItem getMallOrderItem(Long id);

	/**
	 * 查询商品具体信息
	 * 
	 * @param orderNumber
	 *            订单编号
	 * @param formatId
	 *            规格ID
	 * @return
	 */
	MallOrderItem getMallOrderItem(String orderNumber, Long formatId);

	/**
	 * 添加订单具体信息
	 * 
	 * @param orderItem
	 * @return
	 */
	int addMallOrderItem(MallOrderItem orderItem);

	/**
	 * 根据支付单号取得订单信息
	 * @param payNumber
	 * @return
	 */
	MallOrder getMallOrderByPayNumber(String payNumber);

	MallOrderItemExtend getOrderItemExtend(Long userId, Long orderItemId);
	
	/**
	 * 保存订单金额变更记录
	 * @param orderRecord
	 * @return
	 */
	int addOrderCashRecord(MallOrderCashRecord orderRecord);
	
	Pager<MallOrder> queryActivityOrdersBysearch(String keyword, Date startDate, Date endDate, Byte status, int pageNo,
			int pageSize);
	
	int updateinvalidByOrderNumber(Byte invilid,String orderNumber);
}
