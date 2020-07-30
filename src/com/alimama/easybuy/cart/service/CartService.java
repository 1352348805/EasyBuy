package com.alimama.easybuy.cart.service;

import com.alimama.easybuy.cart.bean.Cart;
import com.alimama.easybuy.to.CommonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author asuk
 * @date 2020/7/16 15:48
 */
public interface CartService{

    //添加到购物车
    CommonResult addToCart(HttpServletRequest request, HttpServletResponse response, Integer pid, Integer num);

    String getCartJson(HttpServletRequest request,HttpServletResponse response);
}
