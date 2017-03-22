package com.papabear.order.entity;

import java.util.List;

import com.papabear.commons.entity.AbstractEntity;
import com.papabear.product.entity.MallProductPic;

public class MallOrderItem extends AbstractEntity implements Cloneable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3445056274010259148L;

    private Long userId;

    private String orderNumber;

    private Long productId;

    private String productTitle;

    private String productFeature;

    private Long productModelFormatId;

    private String productModelFormatName;

    private Float unitPrice;

    private Float discountUnitPrice;

    private Float num;

    private Byte isCustomMade;

    private Float sendedNum;

    private Byte isUseInvitecode;
    
    private String unit;//规格计量单位
    
    private Float totalPrice;

    private Integer tagCode;

    private String tagName;
    
    private Byte type;
    
    private Float productPurchasePrice;
    
    private Float fixateProfit;
    
	// 关联查询 图片
	private List<MallProductPic> pics;
	//无关联，查询扩展订单详情属性
	private List<MallOrderItemExt> orderItemExts;
	private MallOrderItemExtend orderItemExtend;
	// 查询 规格编号
	private String categoryModelFormatNumber;

    public MallOrderItemExtend getOrderItemExtend() {
		return orderItemExtend;
	}

	public void setOrderItemExtend(MallOrderItemExtend orderItemExtend) {
		this.orderItemExtend = orderItemExtend;
	}

	public Float getProductPurchasePrice() {
		return productPurchasePrice;
	}

	public void setProductPurchasePrice(Float productPurchasePrice) {
		this.productPurchasePrice = productPurchasePrice;
	}

	public Float getFixateProfit() {
		return fixateProfit;
	}

	public void setFixateProfit(Float fixateProfit) {
		this.fixateProfit = fixateProfit;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle == null ? null : productTitle.trim();
    }

    public String getProductFeature() {
        return productFeature;
    }

    public void setProductFeature(String productFeature) {
        this.productFeature = productFeature == null ? null : productFeature.trim();
    }

    public Long getProductModelFormatId() {
        return productModelFormatId;
    }

    public void setProductModelFormatId(Long productModelFormatId) {
        this.productModelFormatId = productModelFormatId;
    }

    public String getProductModelFormatName() {
        return productModelFormatName;
    }

    public void setProductModelFormatName(String productModelFormatName) {
        this.productModelFormatName = productModelFormatName == null ? null : productModelFormatName.trim();
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

    public Float getNum() {
        return num;
    }

    public void setNum(Float num) {
        this.num = num;
    }

    public Byte getIsCustomMade() {
        return isCustomMade;
    }

    public void setIsCustomMade(Byte isCustomMade) {
        this.isCustomMade = isCustomMade;
    }

    public Float getSendedNum() {
        return sendedNum;
    }

    public void setSendedNum(Float sendedNum) {
        this.sendedNum = sendedNum;
    }

    public Byte getIsUseInvitecode() {
        return isUseInvitecode;
    }

    public void setIsUseInvitecode(Byte isUseInvitecode) {
        this.isUseInvitecode = isUseInvitecode;
    }
    
    public List<MallProductPic> getPics() {
		return pics;
	}

	public void setPics(List<MallProductPic> pics) {
		this.pics = pics;
	}

	public String getCategoryModelFormatNumber() {
		return categoryModelFormatNumber;
	}

	public void setCategoryModelFormatNumber(String categoryModelFormatNumber) {
		this.categoryModelFormatNumber = categoryModelFormatNumber;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<MallOrderItemExt> getOrderItemExts() {
		return orderItemExts;
	}

	public void setOrderItemExts(List<MallOrderItemExt> orderItemExts) {
		this.orderItemExts = orderItemExts;
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

	public MallOrderItem clone() {
		MallOrderItem o = null;
		try {
			o = (MallOrderItem) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
	
}