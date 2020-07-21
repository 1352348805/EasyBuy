package com.alimama.easybuy.admin.servlet;

import com.alimama.easybuy.product.bean.Product;
import com.alimama.easybuy.product.service.ProductService;
import com.alimama.easybuy.product.service.impl.ProductServiceImpl;
import com.alimama.easybuy.util.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author asuk
 * @date 2020/7/17 23:30
 */

@WebServlet("/admin/product")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("index".equals(action)) {
            ProductService productService = new ProductServiceImpl();
            try {
               String currentPage = req.getParameter("currentPage");
               Integer pageIndex = null;
               if(currentPage != null){
                   try{
                       pageIndex = new Integer(currentPage);
                   }catch (NumberFormatException e){
                       pageIndex = 1;
                   }
               }else{
                   pageIndex = 1;
               }
                Page<Product> page = productService.getPageProductIndex(pageIndex);
                List<Product> product = page.getData();
                req.setAttribute("products",product);
                req.setAttribute("pa",page);
            } catch (Exception e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("/WEB-INF/page/admin/product/productlist.jsp").forward(req,resp);
        }


    }
}
