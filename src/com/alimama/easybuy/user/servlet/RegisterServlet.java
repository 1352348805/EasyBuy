package com.alimama.easybuy.user.servlet;

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

/**
 * @author Jun Xiao
 * @date 2020/7/18 15:11
 */

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/page/user/Register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginName = req.getParameter("loginName");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String sex = req.getParameter("sex");
        String identityCode = req.getParameter("identityCode");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        User user = new User();
        user.setLoginName(loginName);
        user.setUserName(userName);
        user.setPassword(password);
        user.setSex(Integer.parseInt(sex));
        user.setIdentityCode(identityCode);
        user.setEmail(email);
        user.setMobile(mobile);
        UserService userService = new UserServiceImpl();
        Integer i = userService.register(user);
        if (i > 0) {
            //注册成功，提示并跳转登录界面
            PrintWriter out = resp.getWriter();
            out.print("<script>alert('注册成功！确认返回登录页...');location.href='/EasyBuy/ServletLogin'</script>");
            out.flush();
            out.close();
        } else if (i == 0) {
            req.setAttribute("msg", "用户名已存在");
            req.getRequestDispatcher("/WEB-INF/page/user/Register.jsp").forward(req,resp);
        }
    }
}
