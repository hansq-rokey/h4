package com.papabear.order.entity;

import com.papabear.commons.entity.AbstractEntity;
/**
 * 定金
 * 
 * @author yaoweiguo
 * @email  280435353@qq.com
 * @date   2016年7月25日
 * @since  1.0.0
 */
public class MallFrontMoney extends AbstractEntity{


    /**
	 * 
	 */
	private static final long serialVersionUID = 220348021112520240L;

	private String frontNumber;

    private String payNumber;

    private String remark;

    private Long userId;

    private Byte source;

    private String userName;

    private String bxNum;

    private Float frontMoney;

    private Long provinceId;

    private String provinceName;

    private Long cityId;

    private String cityName;

    private Long countyId;

    private String countyName;

    private String address;

    private String name;

    private String tel;
    
    private Long productId;

    public String getFrontNumber() {
        return frontNumber;
    }

    public void setFrontNumber(String frontNumber) {
        this.frontNumber = frontNumber == null ? null : frontNumber.trim();
    }

    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber == null ? null : payNumber.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Byte getSource() {
        return source;
    }

    public void setSource(Byte source) {
        this.source = source;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getBxNum() {
        return bxNum;
    }

    public void setBxNum(String bxNum) {
        this.bxNum = bxNum == null ? null : bxNum.trim();
    }

    public Float getFrontMoney() {
        return frontMoney;
    }

    public void setFrontMoney(Float frontMoney) {
        this.frontMoney = frontMoney;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Long getCountyId() {
        return countyId;
    }

    public void setCountyId(Long countyId) {
        this.countyId = countyId;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName == null ? null : countyName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
    
}