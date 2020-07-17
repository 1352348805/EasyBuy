package com.alimama.easybuy.cart.service;

import com.alimama.easybuy.to.CommonResult;

/**
 * @author Jun Xiao
 * @date 2020/7/16 15:48
 */
public interface CartService{

    CommonResult addToCart(Integer pid, Integer num);
}
