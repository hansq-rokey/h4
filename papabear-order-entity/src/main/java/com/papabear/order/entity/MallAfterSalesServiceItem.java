package com.papabear.order.entity;

import java.util.List;

import com.papabear.commons.entity.AbstractEntity;
import com.papabear.product.entity.MallProductPic;

/**
 * 
 * 订单售后服务商品详情
 * 
 * @author yaoweiguo
 * @email  280435353@qq.com
 * @date 2016年3月2日
 * @since 1.0.0
 */
public class MallAfterSalesServiceItem  extends AbstractEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6931252153360834331L;

	private Long productId;

    private Long productModelFormatId;

    private Float num;

    private Long userId;

    private Float unitPrice;

    private Float discountUnitPrice;

    private Long serviceId;
    
    /*额外字段，与数据库无关*/
    private String productTitle;

    private String productFeature;

    private String productModelFormatName;
    
    private String  modelFormatNumber;
    
    // 关联查询 图片
 	private List<MallProductPic> pics;
 	
    /*额外字段，与数据库无关*/
    
    public List<MallProductPic> getPics() {
		return pics;
	}

	public void setPics(List<MallProductPic> pics) {
		this.pics = pics;
	}

	public Long getProductId() {
        return productId;
    }

    public String getModelFormatNumber() {
		return modelFormatNumber;
	}

	public void setModelFormatNumber(String modelFormatNumber) {
		this.modelFormatNumber = modelFormatNumber;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
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
    
}