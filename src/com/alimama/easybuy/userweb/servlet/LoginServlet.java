package com.alimama.easybuy.userweb.servlet;

import com.alibaba.fastjson.JSON;
import com.alimama.easybuy.to.CommonResult;
import com.alimama.easybuy.user.bean.User;
import com.alimama.easybuy.user.service.UserService;
import com.alimama.easybuy.user.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @author asuk
 * @date 2020/7/18 11:40
 */

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/page/userweb/Login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name =req.getParameter("loginName");
        String password =req.getParameter("password");
        PrintWriter out = resp.getWriter();

        UserService service=new UserServiceImpl();

        CommonResult result = null;
        try {
            User user=  service.Login(name);
            if (user==null) {
                result = new CommonResult().validateFailed("用户名不存在！！！");

            } else if (! password.equals(user.getPassword())){

                result = new CommonResult().validateFailed("密码错误！！！");
            }else {
                req.getSession().setAttribute("user",user);
                result = new CommonResult().success(null);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        String resultJsonString = JSON.toJSONString(result);
        out.print(resultJsonString);
        out.flush();
        out.close();
    }
}
