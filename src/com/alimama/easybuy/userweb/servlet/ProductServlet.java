package com.alimama.easybuy.userweb.servlet;

import com.alimama.easybuy.product.bean.Product;
import com.alimama.easybuy.product.service.ProductCategoryService;
import com.alimama.easybuy.product.service.ProductService;
import com.alimama.easybuy.product.service.impl.ProductCategoryServiceImpl;
import com.alimama.easybuy.product.service.impl.ProductServiceImpl;
import com.alimama.easybuy.product.to.ProductCategoryWithSubClass;
import com.alimama.easybuy.product.vo.ProductQueryParam;
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
 * @date 2020/7/27 22:58
 */

@WebServlet("/Product")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("queryProductList"))
        {
            int cid,index;
            try {
                index = Integer.parseInt(req.getParameter("index"));
            } catch (NumberFormatException e) {
                index = 1;
            }
            try {
                cid = Integer.parseInt(req.getParameter("cid"));
            } catch (NumberFormatException e) {
                resp.sendRedirect(req.getContextPath() + "/Home?action=index");
                return;
            }
            String level = req.getParameter("level");
            ProductQueryParam queryParam = new ProductQueryParam();
            switch (level) {
                case "1":
                    queryParam.setCategoryLevel1Id(cid);
                    break;
                case "2":
                    queryParam.setCategoryLevel2Id(cid);
                    break;
                case "3":
                    queryParam.setCategoryLevel3Id(cid);
                    break;
            }
            ProductService productService = new ProductServiceImpl();
            ProductCategoryService productCategoryService = new ProductCategoryServiceImpl();

            List<ProductCategoryWithSubClass> menu = productCategoryService.getProductCategoryMenu(req);
            req.setAttribute("menu", menu);

            Page<Product> page = productService.getPageProductIndex(queryParam, index, 20);
            req.setAttribute("page",page);
            req.getRequestDispatcher("/WEB-INF/page/userweb/product/category_product_list.jsp").forward(req,resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
