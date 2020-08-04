package com.alimama.easybuy.cart.service;

import com.alimama.easybuy.cart.bean.Cart;
import com.alimama.easybuy.to.CommonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author asuk
 * @date 2020/7/16 15:48
 */
public interface CartService{

    //添加到购物车
    CommonResult addToCart(HttpServletRequest request, HttpServletResponse response, Integer pid, Integer num);

    //获取购物车json
    String getCartJson(HttpServletRequest request,HttpServletResponse response);

    //获取在线购物车
    Cart getServerCart(HttpServletRequest request) throws IOException;
}
