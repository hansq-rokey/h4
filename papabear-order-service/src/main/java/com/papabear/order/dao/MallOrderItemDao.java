package com.papabear.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.papabear.order.entity.MallOrderItem;

public interface MallOrderItemDao {
    int deleteByPrimaryKey(Long id);

    int insert(MallOrderItem record);

    int insertSelective(MallOrderItem record);

    MallOrderItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallOrderItem record);

    int updateByPrimaryKey(MallOrderItem record);
    
    List<MallOrderItem> queryOrderItems(@Param("userId")Long userId, @Param("orderNumber")String orderNumber);
    
	/*
	 * 查询商品具体信息
	 */
	MallOrderItem getByOrderNumberAndFormatId(@Param("orderNumber")String orderNumber, @Param("formatId") Long formatId);
}