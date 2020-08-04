package com.alimama.easybuy.userweb.servlet;

import com.alimama.easybuy.product.service.ProductCategoryService;
import com.alimama.easybuy.product.service.impl.ProductCategoryServiceImpl;
import com.alimama.easybuy.product.to.ProductCategoryWithSubClass;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author asuk
 * @date 2020/7/20 18:38
 */
@WebServlet({"/Home","/"})
public class HomeServlet extends HttpServlet {

    Logger logger = Logger.getLogger(HomeServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductCategoryService productCategoryService = new ProductCategoryServiceImpl();

        List<ProductCategoryWithSubClass> menu = productCategoryService.getProductCategoryMenu(req);
        req.setAttribute("menu", menu);
        req.getRequestDispatcher("/WEB-INF/page/userweb/Home.jsp").forward(req,resp);

    }


}
