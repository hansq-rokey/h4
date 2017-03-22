package com.papabear.order.entity;

import com.papabear.commons.entity.AbstractEntity;

/**
 * 
 * 订单售后服务日志实体类
 * 
 * @author yaoweiguo
 * @email  280435353@qq.com
 * @date 2016年3月2日
 * @since 1.0.0
 */
public class MallAfterSalesServiceLog  extends AbstractEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = -3605673753414982393L;

	private Long serviceId;

    private Long adminId;

    private Byte serviceStatus;


    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Byte getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(Byte serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
}