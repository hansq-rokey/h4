/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.papabear.order.exception;

import com.papabear.commons.exception.CommonsResultCode;
import com.papabear.commons.exception.ResultCode;

/**
 * 
 * 订单相关异常信息定义
 * @author yaoweiguo
 * @email  280435353@qq.com
 * @date   2016年3月3日
 * @since  1.0.0
 */
public class OrderResultCode extends CommonsResultCode {
	private static final long serialVersionUID = -6704669247155805392L;
	public static final ResultCode STOCK_NOT_ENOUGH = new OrderResultCode(20001, "库存不足");
	public static final ResultCode cart_IS_EMPTY = new OrderResultCode(20002, "购物车为空");
	public static final ResultCode PRODUCT_NOT_FIND = new OrderResultCode(20003, "产品没有找到");
	public static final ResultCode AFTER_SALE_NUM_ERROR = new OrderResultCode(20004, "数量不能小于1或不能超过购买数量");


	public OrderResultCode(int code, String resultDesc) {
		super(code, resultDesc);
	}
}
