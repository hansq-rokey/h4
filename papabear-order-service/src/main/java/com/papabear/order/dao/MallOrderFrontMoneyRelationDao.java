package com.papabear.order.dao;

import com.papabear.order.entity.MallOrderFrontMoneyRelation;

public interface MallOrderFrontMoneyRelationDao {
    int deleteByPrimaryKey(Long id);

    int insert(MallOrderFrontMoneyRelation record);

    int insertSelective(MallOrderFrontMoneyRelation record);

    MallOrderFrontMoneyRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallOrderFrontMoneyRelation record);

    int updateByPrimaryKey(MallOrderFrontMoneyRelation record);
}