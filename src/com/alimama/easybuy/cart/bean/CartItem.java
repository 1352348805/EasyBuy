package com.alimama.easybuy.cart.bean;

import java.math.BigDecimal;

/**
 * @author asuk
 * @date 2020/7/16 15:48
 * 购物车单项数据
 */
public class CartItem {

    //商品id
    private Integer cid;

    //商品单价
    private BigDecimal price;

    //商品数量
    private Integer count;

    private BigDecimal totalPrice;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
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
