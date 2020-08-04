package com.alimama.easybuy.userweb.servlet;

import com.alimama.easybuy.cart.service.CartService;
import com.alimama.easybuy.cart.service.impl.CartServiceImpl;
import com.alimama.easybuy.order.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

/**
 * @author asuk
 * @date 2020/7/19 23:22
 */
@WebServlet("/pay")
public class PayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderServiceImpl orderService = new OrderServiceImpl();

        String result = orderService.payOrder(UUID.randomUUID().toString(), "50000", "xj", "ttt");
        PrintWriter out = resp.getWriter();
        out.print(result);
        out.flush();
        out.close();

    }
}
