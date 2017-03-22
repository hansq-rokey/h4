package com.papabear.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.papabear.order.entity.MallOrderItemExt;

public interface MallOrderItemExtDao {
    int deleteByPrimaryKey(Long id);

    int insert(MallOrderItemExt record);

    int insertSelective(MallOrderItemExt record);

    MallOrderItemExt selectByPrimaryKey(Long id);
    
    List<MallOrderItemExt> queryOrderItemExts(@Param("orderNumber")String orderNumber, @Param("orderItemId")Long orderItemId);
    
    int updateByPrimaryKeySelective(MallOrderItemExt record);

    int updateByPrimaryKey(MallOrderItemExt record);
}