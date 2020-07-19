package com.alimama.easybuy.cart.service;

import com.alimama.easybuy.to.CommonResult;

/**
 * @author asuk
 * @date 2020/7/16 15:48
 */
public interface CartService{

    //添加到购物车
    CommonResult addToCart(Integer pid, Integer num);
}
