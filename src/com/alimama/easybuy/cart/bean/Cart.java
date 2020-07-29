package com.alimama.easybuy.cart.bean;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author asuk
 * @date 2020/7/16 17:34
 * 整个购物车
 */
public class Cart {

    //购物车项的集合
    private List<CartItem> cartItems;

    //购物车项的数量
    private Integer count;

    //购物车总价
    private BigDecimal totalPrice;

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Integer getCount() {
        if(cartItems!=null){
            AtomicInteger integer = new AtomicInteger(0);
            cartItems.forEach((cartItem)->{
                integer.getAndAdd(cartItem.getCount());
            });
            return integer.get();
        }else {
            return 0;
        }
    }

    public BigDecimal getTotalPrice() {
        if (cartItems != null) {
            AtomicReference<BigDecimal> total = new AtomicReference<>(new BigDecimal("0"));
            cartItems.forEach(item -> {
                BigDecimal add = total.get().add(item.getTotalPrice());
                total.set(add);
            });
            return total.get();
        }
        return new BigDecimal("0");
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
