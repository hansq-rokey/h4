package com.papabear.order.entity;

import java.io.Serializable;
import java.util.Date;

public class MallOrderCashRecord implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7570241631790193378L;

	private Long id;

    private Long userId;

    private Long adminId;

    private String orderNumber;

    private Float beforeMoney;

    private Float afterMoney;

    private Date createDateTime;

    private String remark;

    private Byte type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public Float getBeforeMoney() {
        return beforeMoney;
    }

    public void setBeforeMoney(Float beforeMoney) {
        this.beforeMoney = beforeMoney;
    }

    public Float getAfterMoney() {
        return afterMoney;
    }

    public void setAfterMoney(Float afterMoney) {
        this.afterMoney = afterMoney;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}