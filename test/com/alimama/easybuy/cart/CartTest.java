package com.alimama.easybuy.cart;

import com.alimama.easybuy.cart.bean.Cart;
import com.alimama.easybuy.cart.bean.CartItem;
import com.alimama.easybuy.news.A;
import com.alimama.easybuy.order.service.OrderService;
import com.alimama.easybuy.order.service.impl.OrderServiceImpl;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Jun Xiao
 * @date 2020/7/15 0:34
 */
public class CartTest {

    @Test
    public void f1() {
        Cart cart = new Cart();

        CartItem cartItem = new CartItem();
        cartItem.setPrice(new BigDecimal("12.5"));
        cartItem.setCount(5);

        CartItem cartItem1 = new CartItem();
        cartItem1.setPrice(new BigDecimal("121.5"));
        cartItem1.setCount(2);
        System.out.println(cartItem.getTotalPrice());

        List<CartItem> cartItems = new ArrayList<>();

        cartItems.add(cartItem);
        cartItems.add(cartItem1);
        cart.setCartItems(cartItems);
        System.out.println(cart.getTotalPrice());
    }

    @Test
    public void pay() {
        OrderServiceImpl orderService = new OrderServiceImpl();

        String result = orderService.payOrder("123321", "1111", "xj", "ttt");
        System.out.println(result);
    }

}