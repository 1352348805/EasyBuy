package com.alimama.easybuy.cart.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alimama.easybuy.cart.bean.Cart;
import com.alimama.easybuy.cart.service.CartService;
import com.alimama.easybuy.cart.service.impl.CartServiceImpl;
import com.alimama.easybuy.to.CommonResult;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

/**
 * @author asuk
 * @date 2020/7/16 15:46
 */

@WebServlet("/Cart")
public class CartServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("addToCart")) {
            String pid = req.getParameter("pid");
            String num = req.getParameter("num");
            CartService cartService = new CartServiceImpl();
            CommonResult commonResult = cartService.addToCart(req,resp,Integer.parseInt(pid), Integer.parseInt(num));
            String resultJsonString = JSON.toJSONString(commonResult);
            PrintWriter out = resp.getWriter();
            out.print(resultJsonString);
            out.flush();
            out.close();
        } else if (action.equals("refreshCart")) {
            String cartString = "";
            String cookieString =req.getHeader("cookie");
            if (cookieString != null && !cookieString.equals("")) {
                String[] cookies = URLDecoder.decode(cookieString,"utf-8").split(";");
                for (String cookie : cookies) {
                    if (cookie.indexOf("cart=") != -1) {
                        cartString = cookie.substring(cookie.indexOf("=") + 1);
                    }
                }
            }
            PrintWriter out = resp.getWriter();
            out.print(cartString);
            out.flush();
            out.close();
        }
    }
}
