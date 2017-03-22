package com.papabear.order.dao;

import java.util.List;

import com.papabear.order.entity.MallAfterSalesServiceItem;

public interface MallAfterSalesServiceItemDao {
    int deleteByPrimaryKey(Long id);

    int insert(MallAfterSalesServiceItem record);

    int insertSelective(MallAfterSalesServiceItem record);

    MallAfterSalesServiceItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallAfterSalesServiceItem record);

    int updateByPrimaryKey(MallAfterSalesServiceItem record);
    /**
     * 查询售后服务具体列表
     * @param serviceId
     * @return
     */
    List<MallAfterSalesServiceItem> queryAfterSalesServiceItemByServiceId(Long serviceId);
}