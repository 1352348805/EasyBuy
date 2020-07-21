package com.alimama.easybuy.userweb.servlet;

import com.alimama.easybuy.user.bean.User;
import com.alimama.easybuy.user.service.UserService;
import com.alimama.easybuy.user.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author asuk
 * @date 2020/7/18 11:40
 */

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name =req.getParameter("loginName");
        System.out.println(name);
        UserService service=new UserServiceImpl();
        try {
            User user=  service.Login(name);
            if (user==null) {
                req.setAttribute("msg", "用户名不存在！！！");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/page/userweb/Login.jsp").forward(req,resp);
    }
}
