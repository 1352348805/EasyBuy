package com.alimama.easybuy.admin.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author asuk
 * @date 2020/7/20 13:37
 */
@WebServlet("/admin/user")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("index".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/page/admin/user/user_info.jsp").forward(req,resp);
        } else if ("queryUserList".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/page/admin/user/user_list.jsp").forward(req,resp);
        }
    }
}
