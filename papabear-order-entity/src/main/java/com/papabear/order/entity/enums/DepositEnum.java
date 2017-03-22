/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.papabear.order.entity.enums;

/**
 * 定金状态
 * @author yaoweiguo
 * @email  280435353@qq.com
 * @date   2016年7月26日
 * @since  1.0.0
 */
public enum DepositEnum {

	/** 待付款 */
    OBLIGATION((byte)10,"待付款"),

    /** 已付款（未使用） */
    PAID((byte)20,"未使用"),
    /**已使用*/
    USED((byte)30,"已使用"),//退款中  、已退款、已拒绝、已使用、未使用、审核中
    /**审核退款中**/
    AUDIT_REFUND((byte)40,"审核中"),
    /**退款中*/
    REFUNDING((byte)50,"退款中"),
    /**已退款**/
    REFUNDED((byte)60,"已退款"),
    /**已拒绝*/
    REFUSED((byte)70,"已拒绝");
	
	private byte status;
	
	private String value;
	
	

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private DepositEnum(byte status) {
		this.status = status;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	private DepositEnum(byte status, String value) {
		this.status = status;
		this.value = value;
	}


	
}
