package com.papabear.order.dao;

import com.papabear.order.entity.MallOrderCashRecord;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallOrderCashRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(MallOrderCashRecord record);

    int insertSelective(MallOrderCashRecord record);

    MallOrderCashRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallOrderCashRecord record);

    int updateByPrimaryKey(MallOrderCashRecord record);
}