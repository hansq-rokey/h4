package com.papabear.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.papabear.order.entity.MallCarItemExt;

public interface MallCarItemExtDao {
    int deleteByPrimaryKey(Long id);
    /**
     * 根据购物车ID删除
     * @author yaoweiguo
     * @date 2016年5月30日
     * @param id
     * @return
     */
    int deleteByCarItemId(Long carItemId);

    int insert(MallCarItemExt record);

    int insertSelective(MallCarItemExt record);

    MallCarItemExt selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallCarItemExt record);

    int updateByPrimaryKey(MallCarItemExt record);
    
    List<MallCarItemExt> queryCarItemExts(@Param("userId")Long userId, @Param("carItemId")Long carItemId);
}