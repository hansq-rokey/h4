package com.papabear.order.entity;

import com.papabear.commons.entity.AbstractEntity;

public class MallOrderItemExt extends AbstractEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5369930057576835952L;


    private Long userId;

    private String orderNumber;

    private Long orderItemId;

    private Long productId;

    private Long productModelFormatId;

    private Float formatExtValue;
    
    private String propertyName;

    private Long formatExtId;

    private String identify;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductModelFormatId() {
        return productModelFormatId;
    }

    public void setProductModelFormatId(Long productModelFormatId) {
        this.productModelFormatId = productModelFormatId;
    }

    public Float getFormatExtValue() {
        return formatExtValue;
    }

    public void setFormatExtValue(Float formatExtValue) {
        this.formatExtValue = formatExtValue;
    }

    public Long getFormatExtId() {
        return formatExtId;
    }

    public void setFormatExtId(Long formatExtId) {
        this.formatExtId = formatExtId;
    }

    public String getIdentify() {
        return identify;
    }

    public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public void setIdentify(String identify) {
        this.identify = identify == null ? null : identify.trim();
    }
}