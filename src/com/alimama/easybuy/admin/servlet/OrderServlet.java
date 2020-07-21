package com.alimama.easybuy.admin.servlet;

import com.alimama.easybuy.order.service.OrderService;
import com.alimama.easybuy.order.service.impl.OrderServiceImpl;
import com.alimama.easybuy.order.to.OrderRelatedProductList;
import com.alimama.easybuy.util.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author asuk
 * @date 2020/7/20 13:36
 */

@WebServlet("/admin/order")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("index".equals(action)) {
            //TODO 查询指定用户的订单
            req.getRequestDispatcher("/WEB-INF/page/admin/order/order_list.jsp").forward(req,resp);
        } else if ("queryAllOrder".equals(action)) {
            //TODO 查询所有用户的订单
            String index = req.getParameter("index");
            int i = 0;
            try {
                i = Integer.parseInt(index);
            } catch (Exception e) {
            }

            try {
                OrderService orderService = new OrderServiceImpl();
                Page<OrderRelatedProductList> page = orderService.getOrderList(i);
                System.out.println(page.getData().size());
                req.setAttribute("page", page);
                req.getRequestDispatcher("/WEB-INF/page/admin/order/order_list.jsp").forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
