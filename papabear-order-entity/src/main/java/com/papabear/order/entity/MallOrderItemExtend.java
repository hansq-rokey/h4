package com.papabear.order.entity;

import java.io.Serializable;

public class MallOrderItemExtend implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5636565419866850389L;

	private Long id;

    private Long userId;

    private String orderNumber;

    private Long orderItemId;

    private Long productId;

    private Long productModelFormatId;

    private Float length;

    private Float width;

    private Float height;

    private Float groundArea;

    private String unit;

    private String groundAreaUnit;

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

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getGroundArea() {
        return groundArea;
    }

    public void setGroundArea(Float groundArea) {
        this.groundArea = groundArea;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getGroundAreaUnit() {
        return groundAreaUnit;
    }

    public void setGroundAreaUnit(String groundAreaUnit) {
        this.groundAreaUnit = groundAreaUnit == null ? null : groundAreaUnit.trim();
    }
}