package com.papabear.order.entity;

public class MallOrderFrontMoneyRelation {
    private Long id;

    private String orderNumber;

    private String frontNumber;

    private Long frontMoneyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getFrontNumber() {
        return frontNumber;
    }

    public void setFrontNumber(String frontNumber) {
        this.frontNumber = frontNumber == null ? null : frontNumber.trim();
    }

    public Long getFrontMoneyId() {
        return frontMoneyId;
    }

    public void setFrontMoneyId(Long frontMoneyId) {
        this.frontMoneyId = frontMoneyId;
    }
}