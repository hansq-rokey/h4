package com.papabear.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.papabear.order.entity.MallCarItem;


public interface MallCarItemDao {
    int deleteByPrimaryKey(Long id);

    int insert(MallCarItem record);

    int insertSelective(MallCarItem record);

    MallCarItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallCarItem record);

    int updateByPrimaryKey(MallCarItem record);
    /**
     * 查询购物车列表
     * @param userId	用户ID
     * @return
     */
    List<MallCarItem> queryCarItemsByUserId(Long userId);
    
    
    MallCarItem getMallCarItem(@Param("formatId")Long formatId,@Param("userId")Long userId);
    
    /**
     * 查询选中的部分购物车中的商品
     * @param userId
     * @param carItemIdList
     * @return
     */
    List<MallCarItem> queryPartCarItem(@Param("userId")Long userId,@Param("carItemIdList")Long[] carItemIdList);

	int updateCarNumIncrease(@Param("userId") Long userId, @Param("productId") Long productId, @Param("formatId") Long formatId, @Param("invalid") boolean invalid);

	int updateCarNumReduce(@Param("userId") Long userId, @Param("productId") Long productId, @Param("formatId") Long formatId, @Param("invalid") boolean invalid);
}