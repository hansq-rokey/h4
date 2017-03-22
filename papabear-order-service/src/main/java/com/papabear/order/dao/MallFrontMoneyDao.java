package com.papabear.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.papabear.order.entity.MallFrontMoney;

public interface MallFrontMoneyDao {
    int deleteByPrimaryKey(Long id);

    int insert(MallFrontMoney record);

    int insertSelective(MallFrontMoney record);

    MallFrontMoney selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallFrontMoney record);

    int updateByPrimaryKey(MallFrontMoney record);
    
    int updateStatus(@Param("status")Byte status,@Param("userId")Long userId,@Param("depositNumber")String depositNumber);
    /**
     * 更新支付后状态
     * @author yaoweiguo
     * @date 2016年7月27日
     * @param status			变为支付状态
     * @param valid				记录变为可用
     * @param depositNumber		定金编码
     * @return
     */
    int updatePayedStatus(@Param("status")Byte status,@Param("invalid")Boolean invalid,@Param("depositNumber")String depositNumber);
    
    List<MallFrontMoney> queryDeposits(@Param("status")Byte status,@Param("userId")Long userId);
    
    List<MallFrontMoney> queryDepositsByIds(@Param("status")Byte status,@Param("userId")Long userId,@Param("ids")String[] ids);
    
    int queryDepositsCount(@Param("status")Byte status,@Param("userId")Long userId);
    
    MallFrontMoney getDeposit(@Param("userId")Long userId, @Param("depositNumber")String depositNumber);
}