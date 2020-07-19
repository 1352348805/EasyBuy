package com.alimama.easybuy.admin.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jun Xiao
 * @date 2020/7/17 23:30
 */

@WebServlet("/admin/product")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("index".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/page/admin/product/productlist.jsp").forward(req,resp);
        }


    }
}
