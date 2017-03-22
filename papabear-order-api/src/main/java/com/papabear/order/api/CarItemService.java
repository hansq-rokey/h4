/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.papabear.order.api;

import java.util.List;

import com.papabear.order.entity.MallCarItem;
import com.papabear.order.entity.MallCarItemExt;
import com.papabear.order.entity.MallCarItemExtend;
import com.papabear.product.entity.MallFormatExt;

/**
 * 购物车相关API
 * 
 * @author yaoweiguo
 * @email 280435353@qq.com
 * @date 2016年3月3日
 * @since 1.0.0
 */
public interface CarItemService {
	/**
	 *	添加购物车V1.0
	 * @param userId	用户ID
	 * @param formatId
	 *            购买型号，必填
	 * @param num
	 *            购买数量，必填
	 * @param productId
	 *            产品Id，必填
	 */
	@Deprecated
	Long add(Long userId, long formatId, Float num, long productId);
	/**
	 * 添加购物车
	 * @param userId			用户ID
	 * @param formatId			购买型号，必填
	 * @param num				购买数量，必填
	 * @param productId			产品Id，必填
	 * @param productFeature	产品特点
	 * @param productTitle		产品标题
	 * @param tagCode			标签代码，发热墙纸使用
	 * @param tagName			标签名称，例如 卧室、走廊等
	 * @param totalPrice		总价
	 * @since v1.1
	 * @return
	 */
	Long add(Long userId, long formatId, Float num, long productId,String productFeature,String productTitle,Integer tagCode,String tagName,Float totalPrice);
	
	/**
	 * 添加购物车属性扩展数据
	 * @author yaoweiguo
	 * @date 2016年5月30日
	 * @param formatExts	规格扩展属性集合
	 * @param carItemId		购物车商品ID
	 * @param productId		产品ID
	 * @param userId		用户ID
	 */
	void addCarItemExt(List<MallFormatExt> formatExts,Long carItemId,Long productId,Long userId);
	
	/**
	 * 添加购物车属性扩展数据
	 * @author ywg 
	 * @param length		长
	 * @param width			宽
	 * @param heigth		高
	 * @param groundArea	地面面积
	 * @param carItemId		购物车商品ID
	 * @param productId		产品ID
	 * @param formatId		规格Id
	 * @param userId		用户ID
	 * @param unit			长宽高的计量单位
	 * @param groundAreaUnit地面面积计量单位
	 * @since V1.3.2
	 */
	void addCarItemExtend(Float length,Float width,Float height,Float groundArea,Long carItemId,Long productId,Long formatId,Long userId,String unit ,String groundAreaUnit);

	/**
	 * 删除购物车
	 * 
	 * @param userId		用户ID，必填
	 * @param carItem
	 *            购物车Id，必填
	 * @return
	 */
	int delete(Long userId, Long carId);
	/**
	 * 删除购物车
	 * @param userId		用户ID
	 * @param productId		产品ID，必填
	 * @param formatId		规格ID
	 * @return
	 */
	int delete(Long userId, Long productId, Long formatId);

	/** 只能更新 num 属性 0:num 不能小于1 ； -1 :库存不足 1：正常 */
	int update(MallCarItem carItem);

	/**
	 * 购物车数量递加，每次加1
	 * @param userId		用户ID
	 * @param productId		产品ID
	 * @param formatId		规格ID
	 * @return
	 */
	int updateCarNumIncrease(Long userId, Long productId, Long formatId);

	/**
	 * 购物车数量递减，每次减1
	 * @param userId		用户ID
	 * @param productId		产品ID
	 * @param formatId		规格ID
	 * @return
	 */
	int updateCarNumReduce(Long userId, Long productId, Long formatId);
	/**
	 * 查询购物车列表
	 * @param userId	用户ID
	 * @return
	 */
	List<MallCarItem> queryCarItemsByUserId(Long userId);

	/**
	 * 查询所选中的购物车商品列表
	 * @param userId			用户ID
	 * @param carItemIdList		购物车ID集合， 例如 1,2,3,4...
	 * @param carItemNumList	同上，暂时没有用到
	 * @return
	 */
	List<MallCarItem> confirm(Long userId, String carItemIdList, String carItemNumList);
	
	/**
	 * 查询购物车扩展属性值
	 * @author yaoweiguo
	 * @date 2016年5月30日
	 * @param userId
	 * @param carItemId
	 * @return
	 */
	List<MallCarItemExt> queryCarItemExts(Long userId,Long carItemId);
	
	/**
	 * 查询购物车扩展属性值
	 * @author yaoweiguo
	 * @date 2016年5月30日
	 * @param userId
	 * @param carItemId
	 * @return
	 */
	MallCarItemExtend getCarItemExtend(Long userId,Long carItemId);
	
	MallCarItem getMallCarItem(Long userId,Long id);
}
