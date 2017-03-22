/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.papabear.order.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.papabear.commons.entity.enumentity.Constant.Status;
import com.papabear.commons.entity.enumentity.InvalidEnum;
import com.papabear.commons.exception.CommonsResultCode;
import com.papabear.commons.exception.PapabearException;
import com.papabear.commons.utils.Assert;
import com.papabear.order.api.CarItemService;
import com.papabear.order.dao.MallCarItemDao;
import com.papabear.order.dao.MallCarItemExtDao;
import com.papabear.order.dao.MallCarItemExtendDao;
import com.papabear.order.entity.MallCarItem;
import com.papabear.order.entity.MallCarItemExt;
import com.papabear.order.entity.MallCarItemExtend;
import com.papabear.product.api.CategoryQueryService;
import com.papabear.product.entity.MallBasicCategoryModelFormat;
import com.papabear.product.entity.MallFormatExt;

/**
 * 
 * @author yaoweiguo
 * @email 280435353@qq.com
 * @date 2016年3月3日
 * @since 1.0.0
 */
public class CarItemServiceImpl implements CarItemService {
	@Resource
	private MallCarItemDao carItemDao;
	@Resource
	private CategoryQueryService categoryQueryService;
	@Resource
	private MallCarItemExtDao CarItemExtDao;
	@Resource 
	private MallCarItemExtendDao carItemExtendDao;

	
	@Override
	public Long add(Long userId, long formatId, Float num, long productId) {
		
		return this.add(userId, formatId, num, productId, null, null, null, null, null);
	}
	
	@Override
	public Long add(Long userId, long formatId, Float num, long productId,String productFeature,String productTitle,Integer tagCode,String tagName,Float totalPrice) {
		Assert.notNull(userId);
		Assert.notNull(formatId);
		Assert.notNull(productId);
		Assert.notNull(num);

		MallBasicCategoryModelFormat format = categoryQueryService.getCategoryModelFormat(formatId);
		if (format.getIsExtProperties() != null && format.getIsExtProperties().intValue() == 1) {
			return this.addCarItem(userId, format, num, productId,productFeature,productTitle,tagCode,tagName,totalPrice,(byte)2);
		} else {
			MallCarItem carItemOld = carItemDao.getMallCarItem(formatId, userId);
			if (carItemOld == null) {
				return this.addCarItem(userId, format, num, productId,productFeature,productTitle,tagCode,tagName,totalPrice,(byte)1);
			} else {
				carItemOld.setNum(num + carItemOld.getNum());
				carItemDao.updateByPrimaryKeySelective(carItemOld);
				return carItemOld.getId();
			}
		}
	}

	private Long addCarItem(Long userId, MallBasicCategoryModelFormat format, Float num, Long productId,String productFeature,String productTitle,Integer tagCode,String tagName,float totalPrice,Byte type) {
		MallCarItem carItem = new MallCarItem();
		carItem.setUserId(userId);
		carItem.setCreateDateTime(new Date());
		carItem.setNum(num);
		carItem.setUnit(format.getUnit());
		carItem.setInvalid(InvalidEnum.FALSE.getInvalidValue());
		carItem.setStatus(Status.NORMAL.getStatus());
		
		
		carItem.setProductModelFormatId(format.getId());
		carItem.setProductModelFormatName(format.getName());
		carItem.setIsCustomMade(format.getIsCustomMade());
//		carItem.setUnitPrice(format.getPrice());
//		carItem.setDiscountUnitPrice(format.getDiscountPrice());
		
//		carItem.setTotalPrice(totalPrice);

		carItem.setProductId(productId);
		carItem.setProductFeature(productFeature);
		carItem.setProductTitle(productTitle);

		carItem.setTagCode(tagCode);
		carItem.setTagName(tagName);
		carItem.setType(type);
		
		carItemDao.insert(carItem);
		return carItem.getId();
	}

	@Override
	public int delete(Long userId, Long carId) {
		Assert.notNull(userId);
		Assert.notNull(carId);
		MallCarItem carItemInfo = carItemDao.selectByPrimaryKey(carId);
		if (carItemInfo.getUserId().longValue() != userId.longValue()) {
			throw new PapabearException(CommonsResultCode.NO_PERMISSION_MODIFY, null);
		}
		return this.delete(carId);
	}

	@Override
	public int delete(Long userId, Long productId, Long formatId) {
		Assert.notNull(productId);
		MallCarItem car = carItemDao.getMallCarItem(formatId, userId);
		if (car == null || car.getProductId().longValue() != productId.longValue()) {
			return 0;
		}
		return this.delete(car.getId());
	}

	private int delete(Long carId) {
		//删除购物车规格属性表
		CarItemExtDao.deleteByCarItemId(carId);
		return carItemDao.deleteByPrimaryKey(carId);
	}

	@Override
	public int update(MallCarItem carItem) {
		MallCarItem carItemInfo = carItemDao.selectByPrimaryKey(carItem.getId());
		if (carItemInfo == null)
			return 0;
		if (carItemInfo.getUserId().longValue() != carItem.getUserId().longValue()) {
			throw new PapabearException(CommonsResultCode.NO_PERMISSION_MODIFY, null);// 没有权限修改
		}
		MallBasicCategoryModelFormat format = categoryQueryService.getCategoryModelFormat(carItemInfo.getProductModelFormatId());
		if (carItem.getNum() <= 0) {
			return 0;
		}
		if (carItem.getNum() > format.getStock().intValue()) {
			return -1;
		}
		return carItemDao.updateByPrimaryKeySelective(carItem);
	}

	@Override
	public List<MallCarItem> queryCarItemsByUserId(Long userId) {
		return carItemDao.queryCarItemsByUserId(userId);
	}

	@Override
	public List<MallCarItem> confirm(Long userId, String carItemIdList, String carItemNumList) {
		String[] carItemIdArray = carItemIdList.split(",");
		Long[] catitemIds = new Long[carItemIdArray.length];
		for (int i = 0; i < carItemIdArray.length; i++) {
			catitemIds[i] = Long.valueOf(carItemIdArray[i]);
		}
		List<MallCarItem> carItemList = carItemDao.queryPartCarItem(userId, catitemIds);
		return carItemList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.papabear.order.api.CarItemService#updateCarNumIncrease(java.lang.
	 * Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public int updateCarNumIncrease(Long userId, Long productId, Long formatId) {
		// MallBasicCategoryModelFormat
		// format=categoryQueryService.getCategoryModelFormat(formatId);
		return carItemDao.updateCarNumIncrease(userId, productId, formatId, InvalidEnum.FALSE.getInvalidValue());
	}

	@Override
	public int updateCarNumReduce(Long userId, Long productId, Long formatId) {
		// 如果小于1则不能再减少\
		MallCarItem car = carItemDao.getMallCarItem(formatId, userId);
		if (car != null && car.getNum() > 1 && car.getProductId().longValue() == productId.longValue()) {
			return carItemDao.updateCarNumReduce(userId, productId, formatId, InvalidEnum.FALSE.getInvalidValue());
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.papabear.order.api.CarItemService#addCarItemExt(java.util.List,
	 * java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public void addCarItemExt(List<MallFormatExt> formatExts, Long carItemId, Long productId, Long userId) {
		for (MallFormatExt ext : formatExts) {
			MallCarItemExt carItemExt = new MallCarItemExt();
			carItemExt.setCatItemId(carItemId);
			carItemExt.setFormatExtId(ext.getId());
			carItemExt.setUserId(userId);
			carItemExt.setIdentify(ext.getIdentify());
			carItemExt.setProductId(productId);
			carItemExt.setFormatExtValue(ext.getValue());
			carItemExt.setProductModelFormatId(ext.getCategoryModelFormatId());
			carItemExt.setPropertyName(ext.getPropertyName());
			CarItemExtDao.insertSelective(carItemExt);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.papabear.order.api.CarItemService#queryCarItemExts(java.lang.Long,
	 * java.lang.Long)
	 */
	@Override
	public List<MallCarItemExt> queryCarItemExts(Long userId, Long carItemId) {
		// TODO Auto-generated method stub
		return CarItemExtDao.queryCarItemExts(userId, carItemId);
	}
	
	@Override
	public MallCarItemExtend getCarItemExtend(Long userId, Long carItemId) {
		// TODO Auto-generated method stub
		return carItemExtendDao.getCarItemExtend(userId, carItemId);
	}

	/* (non-Javadoc)
	 * @see com.papabear.order.api.CarItemService#getMallCarItem(java.lang.Long, java.lang.Long)
	 */
	@Override
	public MallCarItem getMallCarItem(Long userId, Long id) {
		MallCarItem carItem=carItemDao.selectByPrimaryKey(id);
		if(carItem!=null&&carItem.getUserId().longValue()==userId.longValue()){
			return carItem;
		}
		return null;
	}

	@Override
	public void addCarItemExtend(Float length, Float width, Float height, Float groundArea, Long carItemId, Long productId,Long formatId, Long userId, String unit,
			String groundAreaUnit) {
		MallCarItemExtend carItemExtend=new MallCarItemExtend();
		carItemExtend.setCatItemId(carItemId);
		carItemExtend.setGroundArea(groundArea);
		carItemExtend.setGrountArenUnit(groundAreaUnit);
		carItemExtend.setHeight(height);
		carItemExtend.setLength(length);
		carItemExtend.setWidth(width);
		carItemExtend.setProductId(productId);
		carItemExtend.setProductModelFormatId(formatId);
		carItemExtend.setUnit(unit);
		carItemExtend.setUserId(userId);
		carItemExtendDao.insertSelective(carItemExtend);
	}

}
