package com.alimama.easybuy.cart.service.impl;

import com.alimama.easybuy.cart.service.CartService;
import com.alimama.easybuy.to.CommonResult;

/**
 * @author Jun Xiao
 * @date 2020/7/17 8:51
 */
public class CartServiceImpl implements CartService {


    @Override
    public CommonResult addToCart(Integer pid, Integer num) {
        //判断库存

        //if(库存>num)
        //加入购物车
        //
        //else
        //返回描述

        return new CommonResult().success(null);
    }
}
