package com.alimama.easybuy.admin.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author asuk
 * @date 2020/7/20 13:38
 */
@WebServlet("/admin/news")
public class NewsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("queryNewsList".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/page/admin/news/news_list.jsp").forward(req,resp);
        }
    }
}
