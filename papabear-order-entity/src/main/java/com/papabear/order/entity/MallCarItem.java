package com.papabear.order.entity;

import com.papabear.commons.entity.AbstractEntity;

/**
 * 
 * 购物车实体类
 * 
 * @author yaoweiguo
 * @email  280435353@qq.com
 * @date 2016年3月2日
 * @since 1.0.0
 */
public class MallCarItem  extends AbstractEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2506208088593573810L;

    private Long userId;

    private Long productId;

    private Long productModelFormatId;

    private Float num;
    
    private String unit;
    
    private String productTitle;

    private String productFeature;

    private String productModelFormatName;

    private Float unitPrice;

    private Float discountUnitPrice;

    private Byte isCustomMade;

    private Float totalPrice;

    private Integer tagCode;

    private String tagName;
    
    private Byte type;//类型  1、普通商品   2、特殊商品

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Float getNum() {
        return num;
    }

    public void setNum(Float num) {
        this.num = num;
    }

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getProductFeature() {
		return productFeature;
	}

	public void setProductFeature(String productFeature) {
		this.productFeature = productFeature;
	}

	public String getProductModelFormatName() {
		return productModelFormatName;
	}

	public void setProductModelFormatName(String productModelFormatName) {
		this.productModelFormatName = productModelFormatName;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Float getDiscountUnitPrice() {
		return discountUnitPrice;
	}

	public void setDiscountUnitPrice(Float discountUnitPrice) {
		this.discountUnitPrice = discountUnitPrice;
	}

	public Byte getIsCustomMade() {
		return isCustomMade;
	}

	public void setIsCustomMade(Byte isCustomMade) {
		this.isCustomMade = isCustomMade;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getTagCode() {
		return tagCode;
	}

	public void setTagCode(Integer tagCode) {
		this.tagCode = tagCode;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}
	
}