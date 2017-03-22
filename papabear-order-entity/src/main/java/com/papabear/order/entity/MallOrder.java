package com.papabear.order.entity;

import java.util.List;

import com.papabear.commons.entity.AbstractEntity;
import com.papabear.product.entity.MallBasicCategoryModelFormat;
import com.papabear.product.entity.MallProduct;

/**
 * 
 * 订单实体类
 * 
 * @author yaoweiguo
 * @email  280435353@qq.com
 * @date 2016年3月2日
 * @since 1.0.0
 */
public class MallOrder extends AbstractEntity implements Cloneable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2441822619861722966L;

    private String orderNumber;

    private String parentOrderNumber;

    private String payNumber;

    private Long expressId;

    private String remark;

    private Long userId;

    private Float totalNum;

    private Float totalPrice;

    private Float discountPrice;

    private Byte isCustomMade;

    private Byte isUseInvitecode;
    
    private Byte source;
    
    private String userName;

    private String bxNum;

    private Byte isMakeBill;

    private Byte isInstall;

    private Float frontMoney;

    private Byte isUseFrontMoney;
    
    /*后期需要补充的字段*/
    private Byte payType;
    
    /*查询用，与数据库没有关联*/
	private MallBasicCategoryModelFormat format;
	
	private List<MallOrderItem> orderItems;
	
	private MallOrderRevicerInformation information;

	private MallProduct product;
	
    private String email;

    private String phone;
    /*查询用，与数据库没有关联*/
    //查询用，与数据库没有关联，

    private Byte type;
    
    private Float enableCouponMoney;
    
    private Float shouldPayMoney;
    
    public Float getEnableCouponMoney() {
		return enableCouponMoney;
	}

	public void setEnableCouponMoney(Float enableCouponMoney) {
		this.enableCouponMoney = enableCouponMoney;
	}

	public Float getShouldPayMoney() {
		return shouldPayMoney;
	}

	public void setShouldPayMoney(Float shouldPayMoney) {
		this.shouldPayMoney = shouldPayMoney;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getParentOrderNumber() {
        return parentOrderNumber;
    }

    public void setParentOrderNumber(String parentOrderNumber) {
        this.parentOrderNumber = parentOrderNumber == null ? null : parentOrderNumber.trim();
    }

    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber == null ? null : payNumber.trim();
    }

    public Long getExpressId() {
        return expressId;
    }

    public void setExpressId(Long expressId) {
        this.expressId = expressId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Float getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Float totalNum) {
        this.totalNum = totalNum;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Float getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Float discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Byte getIsCustomMade() {
        return isCustomMade;
    }

    public void setIsCustomMade(Byte isCustomMade) {
        this.isCustomMade = isCustomMade;
    }

    public Byte getIsUseInvitecode() {
        return isUseInvitecode;
    }

    public void setIsUseInvitecode(Byte isUseInvitecode) {
        this.isUseInvitecode = isUseInvitecode;
    }

	public MallBasicCategoryModelFormat getFormat() {
		return format;
	}

	public void setFormat(MallBasicCategoryModelFormat format) {
		this.format = format;
	}

	public List<MallOrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<MallOrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public MallOrderRevicerInformation getInformation() {
		return information;
	}

	public void setInformation(MallOrderRevicerInformation information) {
		this.information = information;
	}

	public MallProduct getProduct() {
		return product;
	}

	public void setProduct(MallProduct product) {
		this.product = product;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBxNum() {
		return bxNum;
	}

	public void setBxNum(String bxNum) {
		this.bxNum = bxNum;
	}

	public Byte getPayType() {
		return payType;
	}

	public void setPayType(Byte payType) {
		this.payType = payType;
	}
	
	public Byte getSource() {
		return source;
	}

	public void setSource(Byte source) {
		this.source = source;
	}

	public Byte getIsMakeBill() {
		return isMakeBill;
	}

	public void setIsMakeBill(Byte isMakeBill) {
		this.isMakeBill = isMakeBill;
	}

	public Byte getIsInstall() {
		return isInstall;
	}

	public void setIsInstall(Byte isInstall) {
		this.isInstall = isInstall;
	}

	public Float getFrontMoney() {
		return frontMoney;
	}

	public void setFrontMoney(Float frontMoney) {
		this.frontMoney = frontMoney;
	}

	public Byte getIsUseFrontMoney() {
		return isUseFrontMoney;
	}

	public void setIsUseFrontMoney(Byte isUseFrontMoney) {
		this.isUseFrontMoney = isUseFrontMoney;
	}

	public MallOrder clone() {  
		MallOrder o = null;  
        try {  
            o = (MallOrder) super.clone();  
        } catch (CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return o;  
    }
}