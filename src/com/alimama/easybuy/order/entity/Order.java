package com.alimama.easybuy.order.entity;

import java.util.Date;

/**
 * @author W x c
 */
public class Order {
    //用户id
    private Integer user;
    //用户名
    private String loginName;
    //订单号
    private Integer orderid;
    //创建时间
    private Date timi;
    //金额
    private Integer moony;
    //地址
    private String userAddress;

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Order(String user, String orderid, String timi, String moony) {

    }

    public Order() {

    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Date getTimi() {
        return timi;
    }

    public void setTimi(Date timi) {
        this.timi = timi;
    }

    public Integer getMoony() {
        return moony;
    }

    public void setMoony(Integer moony) {
        this.moony = moony;
    }
}
