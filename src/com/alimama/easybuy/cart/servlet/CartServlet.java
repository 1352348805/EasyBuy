package com.alimama.easybuy.cart.servlet;

import com.alibaba.fastjson.JSON;
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

/**
 * @author asuk
 * @date 2020/7/16 15:46
 */

@WebServlet("/Cart")
public class CartServlet extends HttpServlet {

    private CartService cartService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String pid = req.getParameter("pid");
        String num = req.getParameter("num");
        cartService = new CartServiceImpl();
        CommonResult commonResult = cartService.addToCart(Integer.parseInt(pid), Integer.parseInt(num));
        String resultJsonString = JSON.toJSONString(commonResult);
        PrintWriter out = resp.getWriter();
        out.print(resultJsonString);
        out.flush();
        out.close();
    }
}
