package com.papabear.order.dao;

import org.apache.ibatis.annotations.Param;

import com.papabear.order.entity.MallOrderItemExtend;

public interface MallOrderItemExtendDao {
    int deleteByPrimaryKey(Long id);

    int insert(MallOrderItemExtend record);

    int insertSelective(MallOrderItemExtend record);

    MallOrderItemExtend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallOrderItemExtend record);

    int updateByPrimaryKey(MallOrderItemExtend record);
    
    MallOrderItemExtend getOrderItemExtend(@Param("userId")Long userId,@Param("orderItemId")Long orderItemId);
}