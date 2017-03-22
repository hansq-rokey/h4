package com.papabear.order.model;

import java.io.Serializable;

import com.papabear.product.entity.MallBasicCategoryModelFormat;

/**
 * 创建订单时参数过多所创建的对象
 * 
 * @author ywg
 *
 */
public class OrderParamters implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2985017179242599255L;
	
	/**代理商ID*/
	private Long cityId;
	 /**用户ID*/
	private Long userId;
	 /**用户名*/
	private String userName;
	/**白熊号*/
	private String bxNum;
	/**订单备注*/
	private String remark;
	/**订单数量*/
	private Float totalNum;
	/**规格对象*/
	private MallBasicCategoryModelFormat format;
	/**订单总金额*/
	private Float totalPrice;
	/**是否使用邀请码*/
	private Byte isUseInvitecode;
	/**订单来源*/
	private Byte source;
	/**是否开具发票信息*/
	private Byte isMakeBill;
	/**是否上门安装*/
	private Byte isInstall;
	/**定金金额*/
	private Float frontMoney;
	/**是否使用定金支付*/
	private Byte isUseFrontMoney;
	/**用户IP*/
	private String ip;
	/**标签Code*/
	private Integer tagCode;
	/**发热墙布房间长*/
	private Float length;
	/**房间宽*/
	private Float width;
	/**房间高*/
	private Float height;
	/**房间地面面积*/
	private Float groundArea;
	/**订单类型，1、个人订单  2：经销商订单*/
	private Byte type;
	/**经销商进货价系数*/
	private Float fixateProfit;
	/**使用的优惠券金额*/
	private Float couponMoney;
	
	public Float getCouponMoney() {
		return couponMoney;
	}
	public void setCouponMoney(Float couponMoney) {
		this.couponMoney = couponMoney;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBxNum() {
		return bxNum;
	}
	public void setBxNum(String bxNum) {
		this.bxNum = bxNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Float getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Float totalNum) {
		this.totalNum = totalNum;
	}
	public MallBasicCategoryModelFormat getFormat() {
		return format;
	}
	public void setFormat(MallBasicCategoryModelFormat format) {
		this.format = format;
	}
	public Float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Byte getIsUseInvitecode() {
		return isUseInvitecode;
	}
	public void setIsUseInvitecode(Byte isUseInvitecode) {
		this.isUseInvitecode = isUseInvitecode;
	}
	public Byte getSource() {
		return source;
	}
	public void setSource(Byte source) {
		this.source = source;
	}
	public Byte getIsMakeBill() {
		return isMakeBill;
	}
	public void setIsMakeBill(Byte isMakeBill) {
		this.isMakeBill = isMakeBill;
	}
	public Byte getIsInstall() {
		return isInstall;
	}
	public void setIsInstall(Byte isInstall) {
		this.isInstall = isInstall;
	}
	public Float getFrontMoney() {
		return frontMoney;
	}
	public void setFrontMoney(Float frontMoney) {
		this.frontMoney = frontMoney;
	}
	public Byte getIsUseFrontMoney() {
		return isUseFrontMoney;
	}
	public void setIsUseFrontMoney(Byte isUseFrontMoney) {
		this.isUseFrontMoney = isUseFrontMoney;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getTagCode() {
		return tagCode;
	}
	public void setTagCode(Integer tagCode) {
		this.tagCode = tagCode;
	}
	public Float getLength() {
		return length;
	}
	public void setLength(Float length) {
		this.length = length;
	}
	public Float getWidth() {
		return width;
	}
	public void setWidth(Float width) {
		this.width = width;
	}
	public Float getFixateProfit() {
		return fixateProfit;
	}
	public void setFixateProfit(Float fixateProfit) {
		this.fixateProfit = fixateProfit;
	}
	public Float getHeight() {
		return height;
	}
	public void setHeight(Float height) {
		this.height = height;
	}
	public Float getGroundArea() {
		return groundArea;
	}
	public void setGroundArea(Float groundArea) {
		this.groundArea = groundArea;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	

}
