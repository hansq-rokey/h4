package com.papabear.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.papabear.order.entity.MallOrderHistory;


public interface MallOrderHistoryDao {
    int deleteByPrimaryKey(Long id);

    int insert(MallOrderHistory record);

    int insertSelective(MallOrderHistory record);

    MallOrderHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallOrderHistory record);

    int updateByPrimaryKey(MallOrderHistory record);
    
    List<MallOrderHistory> queryMallOrderHistoryByOrderNumber(String orderNumber);
    
    MallOrderHistory getMallOrderHistory(@Param("orderNumber")String orderNumber, @Param("status")Byte status, @Param("userId")Long userId);
}