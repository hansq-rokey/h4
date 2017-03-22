package com.papabear.order.entity;

import java.util.List;

import com.papabear.commons.entity.AbstractEntity;

/**
 * 
 * 订单售后服务实体类
 * 
 * @author yaoweiguo
 * @email  280435353@qq.com
 * @date 2016年3月2日
 * @since 1.0.0
 */
public class MallAfterSalesService  extends AbstractEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 445257475101349311L;
	//原始订单信息来源
	private String orderNumber;
    //售后订单编号
    private String serviceNumber;
    //用户ID
    private Long userId;
    //售后类型，查看字典表
    private Byte serviceType;
    //维修费用
    private Float money;
    //备注
    private String remark;
    //用户ID
    private Long expressId;
    //描述
    private String description;
    //描述
    private String refuseMemo;
    //与数据库无关,售后关联用到
    private List<MallOrderItem> items;
    /*查询用，与数据库没有关联*/
//	private MallBasicCategoryModelFormat format;
//		
	private MallOrderRevicerInformation information;

//	private MallProduct product;
    
    private List<MallAfterSalesServiceItem> itemList;
	
    private String email;

    private String phone;
    
    private String bxNum;
    
    private String userName;
    /*查询用，与数据库没有关联*/

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber == null ? null : serviceNumber.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Byte getServiceType() {
        return serviceType;
    }

    public void setServiceType(Byte serviceType) {
        this.serviceType = serviceType;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getExpressId() {
        return expressId;
    }

    public void setExpressId(Long expressId) {
        this.expressId = expressId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRefuseMemo() {
        return refuseMemo;
    }

    public void setRefuseMemo(String refuseMemo) {
        this.refuseMemo = refuseMemo == null ? null : refuseMemo.trim();
    }

	public List<MallOrderItem> getItems() {
		return items;
	}

	public void setItems(List<MallOrderItem> items) {
		this.items = items;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<MallAfterSalesServiceItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<MallAfterSalesServiceItem> itemList) {
		this.itemList = itemList;
	}

	public MallOrderRevicerInformation getInformation() {
		return information;
	}

	public void setInformation(MallOrderRevicerInformation information) {
		this.information = information;
	}
		
}