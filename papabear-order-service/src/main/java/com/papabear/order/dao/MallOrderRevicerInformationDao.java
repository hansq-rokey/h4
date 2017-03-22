package com.papabear.order.dao;

import org.apache.ibatis.annotations.Param;

import com.papabear.order.entity.MallOrderRevicerInformation;


public interface MallOrderRevicerInformationDao {
    int deleteByPrimaryKey(Long id);

    int insert(MallOrderRevicerInformation record);

    int insertSelective(MallOrderRevicerInformation record);

    MallOrderRevicerInformation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallOrderRevicerInformation record);

    int updateByPrimaryKey(MallOrderRevicerInformation record);
    
    MallOrderRevicerInformation getOrderRevicerInformation(@Param("userId")Long userId,@Param("orderNumber")String orderNumber);
    
    int updateExpressNoByOrderNumber(@Param("expressNo")String expressNo,@Param("orderNumber")String orderNumber);
}