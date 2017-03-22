package com.papabear.order.entity;

import java.io.Serializable;

public class MallCarItemExtend implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -1736971478837852412L;

	private Long id;

    private Long userId;

    private Long catItemId;

    private Long productId;

    private Long productModelFormatId;

    private Float length;

    private Float width;

    private Float height;

    private Float groundArea;

    private String unit;

    private String grountArenUnit;

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

    public Long getCatItemId() {
        return catItemId;
    }

    public void setCatItemId(Long catItemId) {
        this.catItemId = catItemId;
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

    public String getGrountArenUnit() {
        return grountArenUnit;
    }

    public void setGrountArenUnit(String grountArenUnit) {
        this.grountArenUnit = grountArenUnit == null ? null : grountArenUnit.trim();
    }
}