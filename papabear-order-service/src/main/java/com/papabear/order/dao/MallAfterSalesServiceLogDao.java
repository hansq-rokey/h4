package com.papabear.order.dao;

import java.util.List;

import com.papabear.order.entity.MallAfterSalesServiceLog;

public interface MallAfterSalesServiceLogDao {
    int deleteByPrimaryKey(Long id);

    int insert(MallAfterSalesServiceLog record);

    int insertSelective(MallAfterSalesServiceLog record);

    MallAfterSalesServiceLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallAfterSalesServiceLog record);

    int updateByPrimaryKey(MallAfterSalesServiceLog record);
    
    List<MallAfterSalesServiceLog> queryAfterSalesServiceLogs(Long serviceId);
}