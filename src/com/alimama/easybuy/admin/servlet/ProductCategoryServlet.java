package com.alimama.easybuy.admin.servlet;

import com.alimama.easybuy.product.service.ProductCategoryService;
import com.alimama.easybuy.product.service.impl.ProductCategoryServiceImpl;
import com.alimama.easybuy.product.to.ProductCategoryAndParentInfo;
import com.alimama.easybuy.util.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author asuk
 * @date 2020/7/21 11:00
 */
@WebServlet("/admin/productCategory")
public class  ProductCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("index".equals(action)) {
            Integer i = Page.parseIntPageIndex(req, "index");
            ProductCategoryService productCategoryService = new ProductCategoryServiceImpl();
            Page<ProductCategoryAndParentInfo> page = productCategoryService.getProductCategoryList(i);
            req.setAttribute("page",page);
            req.getRequestDispatcher("/WEB-INF/page/admin/product/product_category_list.jsp").forward(req,resp);
        }
    }
}
