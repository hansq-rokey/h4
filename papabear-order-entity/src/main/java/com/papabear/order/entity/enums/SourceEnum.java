/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.papabear.order.entity.enums;

/**
 * 
 * @author yaoweiguo
 * @email  280435353@qq.com
 * @date   2016年5月23日
 * @since  1.0.0
 */
public enum SourceEnum {
	
	MALL_PC((byte)1),/**商城pc 1*/
	MALL_H5((byte)2),/**商城H5 2*/
	MALL_APP((byte)3);/**商城ios 3*/
	
	private byte source;

	private SourceEnum(byte source) {
		this.source = source;
	}

	public byte getSource() {
		return source;
	}
	
	
}
