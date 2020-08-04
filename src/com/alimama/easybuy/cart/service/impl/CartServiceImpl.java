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
import com.alimama.easybuy.util.CookieUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

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
            //需要添加的购物项
            CartItem item = fillCartItem(product,num);
            //查询cookie并封装cart
            cart = fillCartByCookie(request, product, pid, num);

            Object user = request.getSession().getAttribute("user");
            //未登入，购物车使用cookie存入本地
            if (user == null) {
                if (cart == null) {
                    cart = new Cart();
                    cart.setCartItems(Arrays.asList(item));
                }
                String cartJson = JSON.toJSONString(cart);
                CookieUtil.addCookie(response,"cart",URLEncoder.encode(cartJson, "utf-8"), 60*60*24*7);
                return new CommonResult().success(null);
            } else {
                //登录合并并添加子项
                Map<String, CartItem> cartItemMap = mergeCart(request);
                CartItem cartItem;
                if ((cartItem = cartItemMap.get(item.getPid().toString())) != null) {
                    cartItem.setCount(cartItem.getCount() + num);
                    cartItemMap.put(item.getPid().toString(),cartItem);
                } else {
                    cartItemMap.put(item.getPid().toString(),item);
                }
                User u = (User)user;
                request.getServletContext().setAttribute("userCartKey:" + u.getLoginName(),cartItemMap);
                return new CommonResult().success(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult().validateFailed("服务器繁忙,请稍后再试");
        }
    }

    @Override
    public String getCartJson(HttpServletRequest req,HttpServletResponse response) {
        String cartString = "";
        Object userObj = req.getSession().getAttribute("user");
        if (userObj != null) {
            //合并购物车 使用在线购物车
            Map<String, CartItem> cartItemMap = mergeCart(req);
            Cart cart = new Cart();
            List<CartItem> list = new ArrayList<>();
            for (String key:cartItemMap.keySet()) {
                list.add(cartItemMap.get(key));
            }
            cart.setCartItems(list);
            cartString = JSON.toJSONString(cart);
            //清空离线购物车 使cookie失效
            Cookie cookie = new Cookie("cart", null);
            cookie.setMaxAge(0);
            cookie.setPath(req.getContextPath());
            response.addCookie(cookie);
        } else {
            Cart cartByCookies = getCartByCookies(req.getCookies());
            cartString = JSON.toJSONString(cartByCookies);
        }
        return cartString;
    }

    @Override
    public Cart getServerCart(HttpServletRequest req) {
        Cart cart = new Cart();

        User user = (User)req.getSession().getAttribute("user");
        try {
            HashMap<String,CartItem> cartItemHashMap = (HashMap<String,CartItem>)req.getServletContext().getAttribute("userCartKey:" + user.getLoginName());
            List<CartItem> cartItems = new ArrayList<>();
            cartItemHashMap.keySet().forEach(item -> {
                cartItems.add(cartItemHashMap.get(item));
            });
            cart.setCartItems(cartItems);
        } catch (NullPointerException exception) {
            return null;
        }
        return cart;
    }

    /**
     * 合并购物车
     * @param request
     * @return
     */
    private Map<String,CartItem> mergeCart(HttpServletRequest request) {
        Map<String,CartItem> cartItemMap = new HashMap<>();
        //离线购物车
        Cart cartByCookies = getCartByCookies(request.getCookies());
        //在线购物车
        User user = (User) request.getSession().getAttribute("user");
        Object cartByServer = request.getServletContext().getAttribute("userCartKey:" + user.getLoginName());
        if (cartByCookies != null) {
            if (cartByServer != null) {
                Map<String,CartItem> map = (HashMap<String,CartItem>)cartByServer;
                cartByCookies.getCartItems().forEach(i -> {
                    CartItem cartItem;
                    if ((cartItem = map.get(i.getPid().toString())) != null) {
                        cartItem.setCount(cartItem.getCount()+i.getCount());
                        map.put(i.getPid().toString(),cartItem);
                    } else {
                        map.put(i.toString(),i);
                    }
                });
                cartItemMap = map;
            } else {
                Map<String,CartItem> map = new HashMap<>();
                cartByCookies.getCartItems().forEach(i -> {
                    map.put(i.getPid().toString(),i);
                });
                cartItemMap = map;
            }
        } else {
            if (cartByServer != null) {
                cartItemMap = (HashMap<String,CartItem>)cartByServer;
            }
        }
        //合并进在线购物车
        request.getServletContext().setAttribute("userCartKey:" + user.getLoginName(),cartItemMap);
        return cartItemMap;
    }


    private CartItem fillCartItem (Product product,Integer num) {
        CartItem item = new CartItem();
        item.setPid(product.getId());
        item.setName(product.getName());
        item.setCount(num);
        item.setPrice(new BigDecimal(product.getPrice().toString()));
        item.setFileName(product.getFileName());
        return item;
    }

    /**
     * 获取离线购物车
     * @param cookies
     * @return
     * @throws UnsupportedEncodingException
     */
    private Cart getCartByCookies(Cookie[] cookies) {
        Cart cart = null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    try {
                        cart = JSONObject.parseObject(URLDecoder.decode(cookie.getValue(),"utf-8"),Cart.class);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return cart;
    }

    /**
     * 去cookie里查询并封装cart对象
     * @param request
     * @param product
     * @param pid
     * @param num
     * @return
     * @throws UnsupportedEncodingException
     */
    private Cart fillCartByCookie (HttpServletRequest request,Product product,Integer pid,Integer num) throws UnsupportedEncodingException {

        Cart cart = getCartByCookies(request.getCookies());
        if (cart != null) {
            List<CartItem> cartItems = cart.getCartItems();
            //判断要添加的商品是否在集合中，Y:直接添加数量 N:追加
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
        return cart;
    }






}
