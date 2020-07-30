package com.alimama.easybuy.cart.bean;

import java.math.BigDecimal;

/**
 * @author asuk
 * @date 2020/7/16 15:48
 * 购物车单项数据
 */
public class CartItem {

    //商品id
    private Integer pid;

    //商品名称
    private String name;

    //图片名
    private String fileName;

    //商品单价
    private BigDecimal price;

    //商品数量
    private Integer count;

    private BigDecimal totalPrice;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal bigDecimal = price.multiply(new BigDecimal(count.toString()));
        return bigDecimal;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
