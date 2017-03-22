package com.papabear.order.entity;

import com.papabear.commons.entity.AbstractEntity;

/**
 * 
 * 订单历史记录实体类
 * 
 * @author yaoweiguo
 * @email  280435353@qq.com
 * @date   2016年3月2日
 * @since  1.0.0
 */
public class MallOrderHistory  extends AbstractEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7223391825508559763L;

    private String orderNumber;

    private Byte operatorType;

    private Long userId;

    private Long adminId;

    private String operatorIp;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public Byte getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Byte operatorType) {
        this.operatorType = operatorType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getOperatorIp() {
        return operatorIp;
    }

    public void setOperatorIp(String operatorIp) {
        this.operatorIp = operatorIp == null ? null : operatorIp.trim();
    }
}