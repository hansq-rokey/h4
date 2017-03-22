package com.papabear.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.papabear.order.entity.MallCarItemExtend;

public interface MallCarItemExtendDao {
    int deleteByPrimaryKey(Long id);

    int insert(MallCarItemExtend record);

    int insertSelective(MallCarItemExtend record);

    MallCarItemExtend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallCarItemExtend record);

    int updateByPrimaryKey(MallCarItemExtend record);
    
    MallCarItemExtend getCarItemExtend(@Param("userId")Long userId, @Param("carItemId")Long carItemId);
    /**
     * 根据购物车ID删除
     * @author yaoweiguo
     * @date 2016年5月30日
     * @param id
     * @return
     */
    int deleteByCarItemId(Long carId);
}