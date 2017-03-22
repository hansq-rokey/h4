package com.papabear.order.entity;

import com.papabear.commons.entity.AbstractEntity;
/**
 * 订单收货人信息实体类
 * 
 * 
 * @author yaoweiguo
 * @email  280435353@qq.com
 * @date   2016年3月2日
 * @since  1.0.0
 */
public class MallOrderRevicerInformation extends AbstractEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4150283887730515057L;

    private String expressName;

    private String expressNo;

    private Float expressMoney;

    private String deliverTimeName;

    private Long orderId;

    private Long userId;

    private Long expressCompanyId;

    private String orderNumber;

    private String deliverTimeValue;

    private String reveiveUserName;

    private String mobilePhone;

    private String telPhone;

    private String provinceCode;

    private String provinceName;

    private String cityCode;

    private String cityName;

    private String districtCode;

    private String detailAddress;

    private String zipcode;

    private String districtName;

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName == null ? null : expressName.trim();
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo == null ? null : expressNo.trim();
    }

    public Float getExpressMoney() {
        return expressMoney;
    }

    public void setExpressMoney(Float expressMoney) {
        this.expressMoney = expressMoney;
    }

    public String getDeliverTimeName() {
        return deliverTimeName;
    }

    public void setDeliverTimeName(String deliverTimeName) {
        this.deliverTimeName = deliverTimeName == null ? null : deliverTimeName.trim();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getExpressCompanyId() {
        return expressCompanyId;
    }

    public void setExpressCompanyId(Long expressCompanyId) {
        this.expressCompanyId = expressCompanyId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getDeliverTimeValue() {
        return deliverTimeValue;
    }

    public void setDeliverTimeValue(String deliverTimeValue) {
        this.deliverTimeValue = deliverTimeValue == null ? null : deliverTimeValue.trim();
    }

    public String getReveiveUserName() {
        return reveiveUserName;
    }

    public void setReveiveUserName(String reveiveUserName) {
        this.reveiveUserName = reveiveUserName == null ? null : reveiveUserName.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone == null ? null : telPhone.trim();
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode == null ? null : districtCode.trim();
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress == null ? null : detailAddress.trim();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }
}