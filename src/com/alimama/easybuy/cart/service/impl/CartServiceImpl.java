package com.alimama.easybuy.cart.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alimama.easybuy.cart.bean.Cart;
import com.alimama.easybuy.cart.bean.CartItem;
import com.alimama.easybuy.cart.service.CartService;
import com.alimama.easybuy.product.bean.Product;
import com.alimama.easybuy.product.service.ProductService;
import com.alimama.easybuy.product.service.impl.ProductServiceImpl;
import com.alimama.easybuy.to.CommonResult;
import com.alimama.easybuy.user.bean.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author asuk
 * @date 2020/7/17 8:51
 */
public class CartServiceImpl implements CartService {


    @Override
    public CommonResult addToCart(HttpServletRequest request, HttpServletResponse response, Integer pid, Integer num) {
        //判断库存
        ProductService productService = new ProductServiceImpl();
        Cart cart = null;
        try {
            Product product = productService.getProductById(pid);
            if (product.getStock() < num) {
                return new CommonResult().validateFailed("库存不足");
            }

            //查询cookie并封装cart
            cart = fillCartByCookie(cart, request, product, pid, num);

            Object user = request.getSession().getAttribute("user");
            //未登入，购物车使用cookie存入本地
            if (user == null) {
                if (cart == null) {
                    cart = new Cart();
                    CartItem item = new CartItem();
                    item.setPid(pid);
                    item.setName(product.getName());
                    item.setCount(num);
                    item.setPrice(new BigDecimal(product.getPrice().toString()));
                    item.setFileName(product.getFileName());
                    cart.setCartItems(Arrays.asList(item));
                }
                String cartJson = JSON.toJSONString(cart);
                Cookie cookie = new Cookie("cart", URLEncoder.encode(cartJson, "utf-8"));
                cookie.setMaxAge(60*60*24*7);
                response.addCookie(cookie);
                return new CommonResult().success(null);
            } else {
                //登入 将本地购物车与在线购物车合并
                if (cart == null) {
                    User u = (User)user;
                    Object userCart = request.getServletContext().getAttribute("userCartKey:" + u.getLoginName());
                    List<CartItem> cartItems;
                    if (userCart == null) {
                        cart = new Cart();
                        cartItems = new ArrayList<>();
                    } else {
                        cart = (Cart)userCart;
                        cartItems = cart.getCartItems();
                    }
                    CartItem item = new CartItem();
                    item.setPid(pid);
                    item.setName(product.getName());
                    item.setCount(num);
                    item.setPrice(new BigDecimal(product.getPrice().toString()));
                    item.setFileName(product.getFileName());
                    cartItems.add(item);
                    cart.setCartItems(cartItems);
                    request.getServletContext().setAttribute("userCartKey:" + u.getLoginName(),cart);
                }

                return new CommonResult().success(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult().validateFailed("服务器繁忙,请稍后再试");
        }
    }

    /**
     * 去cookie里查询并封装cart对象
     * @param cart
     * @param request
     * @param product
     * @param pid
     * @param num
     * @return
     * @throws UnsupportedEncodingException
     */
    private Cart fillCartByCookie (Cart cart,HttpServletRequest request,Product product,Integer pid,Integer num) throws UnsupportedEncodingException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie:cookies) {
                if (cookie.getName().equals("cart")) {
                    //加入购物项
                    //获取老购物车
                    cart = JSONObject.parseObject(URLDecoder.decode(cookie.getValue(),"utf-8"),Cart.class);
                    List<CartItem> cartItems = cart.getCartItems();
                    //判断要添加的商品是否在集合中，Y:直接添加数量 N:追加一项
                    if (cartItems.stream().anyMatch(c -> c.getPid().equals(pid))) {
                        cartItems.stream().forEach(items -> {
                            if (items.getPid().equals(pid)) {
                                items.setCount(items.getCount() + num);
                            }
                        });
                    } else {
                        CartItem item = new CartItem();
                        item.setPid(pid);
                        item.setName(product.getName());
                        item.setPrice(new BigDecimal(product.getPrice().toString()));
                        item.setCount(num);
                        item.setFileName(product.getFileName());
                        cartItems.add(item);
                        cart.setCartItems(cartItems);
                    }
                }
            }
        }
        return cart;
    }



}
