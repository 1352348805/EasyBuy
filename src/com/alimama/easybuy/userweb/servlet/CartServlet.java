package com.alimama.easybuy.userweb.servlet;

import com.alibaba.fastjson.JSON;
import com.alimama.easybuy.cart.bean.Cart;
import com.alimama.easybuy.cart.bean.CartItem;
import com.alimama.easybuy.cart.service.CartService;
import com.alimama.easybuy.cart.service.impl.CartServiceImpl;
import com.alimama.easybuy.product.bean.Product;
import com.alimama.easybuy.product.service.ProductCategoryService;
import com.alimama.easybuy.product.service.ProductService;
import com.alimama.easybuy.product.service.impl.ProductCategoryServiceImpl;
import com.alimama.easybuy.product.service.impl.ProductServiceImpl;
import com.alimama.easybuy.product.to.ProductCategoryWithSubClass;
import com.alimama.easybuy.to.CommonResult;
import com.alimama.easybuy.user.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author asuk
 * @date 2020/7/30 19:08
 */

@WebServlet("/Cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        CartService cartService = new CartServiceImpl();
        if (action.equals("addToCart"))
        {
            String pid = req.getParameter("pid");
            String num = req.getParameter("num");
            CommonResult commonResult = cartService.addToCart(req,resp,Integer.parseInt(pid), Integer.parseInt(num));
            String resultJsonString = JSON.toJSONString(commonResult);
            PrintWriter out = resp.getWriter();
            out.print(resultJsonString);
            out.flush();
            out.close();
        }
        else if (action.equals("toSettlement"))
        {
            ProductCategoryService productCategoryService = new ProductCategoryServiceImpl();

            List<ProductCategoryWithSubClass> menu = productCategoryService.getProductCategoryMenu(req);
            req.setAttribute("menu", menu);

            Cart cart = cartService.getServerCart(req);
            req.setAttribute("cart",cart);
            req.getRequestDispatcher("/WEB-INF/page/userweb/cart/BuyCar.jsp").forward(req,resp);
        }
        else if (action.equals("toSettlement2"))
        {
            ProductCategoryService productCategoryService = new ProductCategoryServiceImpl();
            List<ProductCategoryWithSubClass> menu = productCategoryService.getProductCategoryMenu(req);
            req.setAttribute("menu", menu);

            Cart cart = cartService.getServerCart(req);
            if (cart == null) {
                resp.sendRedirect(req.getContextPath() + "/Home?action=index");
                return;
            }
            req.setAttribute("cart",cart);

            req.getRequestDispatcher("/WEB-INF/page/userweb/cart/BuyCar_Two.jsp").forward(req,resp);
        }
        else if (action.equals("refreshCart"))
        {
            String cartJson = cartService.getCartJson(req,resp);
            PrintWriter out = resp.getWriter();
            out.print(cartJson);
            out.flush();
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        ProductService productService = new ProductServiceImpl();
        CartService cartService = new CartServiceImpl();
        User user = (User)req.getSession().getAttribute("user");
        HashMap<String,CartItem> cartItemHashMap = (HashMap<String,CartItem>)req.getServletContext().getAttribute("userCartKey:" + user.getLoginName());
        if (action.equals("addUpdate")) {
            String pid = req.getParameter("pid");
            Integer num = Integer.parseInt(req.getParameter("num"));
            CommonResult result = new CommonResult();
            try {
                Product product = productService.getProductById(Integer.parseInt(pid));
                if (product != null) {
                    if (product.getStock() < num) {
                        result.validateFailed("库存不足");
                    } else {
                        CartItem cartItem = cartItemHashMap.get(pid);
                        cartItem.setCount(num);
                        cartItemHashMap.put(cartItem.getPid().toString(),cartItem);
                        Cart cart = cartService.getServerCart(req);
                        result.success(cart);
                    }
                    PrintWriter out = resp.getWriter();
                    out.print(JSON.toJSONString(result));
                    out.flush();
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (action.equals("jianUpdate")) {
            String pid = req.getParameter("pid");
            CommonResult result = new CommonResult();
            CartItem cartItem = cartItemHashMap.get(pid);
            cartItem.setCount(cartItem.getCount()-1);
            cartItemHashMap.put(cartItem.getPid().toString(),cartItem);
            Cart cart = cartService.getServerCart(req);
            result.success(cart);
            PrintWriter out = resp.getWriter();
            out.print(JSON.toJSONString(result));
            out.flush();
            out.close();
        }

    }
}
