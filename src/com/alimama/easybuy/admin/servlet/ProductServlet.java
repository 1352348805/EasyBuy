package com.alimama.easybuy.admin.servlet;

import com.alibaba.fastjson.JSON;
import com.alimama.easybuy.product.bean.Product;
import com.alimama.easybuy.product.service.ProductService;
import com.alimama.easybuy.product.service.impl.ProductServiceImpl;
import com.alimama.easybuy.to.CommonResult;
import com.alimama.easybuy.util.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
                req.setAttribute("page",page);
            } catch (Exception e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("/WEB-INF/page/admin/product/product_list.jsp").forward(req,resp);
        } else if ("add".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/page/admin/product/product_add.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommonResult commonResult = new CommonResult();
        try{
            ProductService productService = new ProductServiceImpl();
            int productid = Integer.parseInt(req.getParameter("productid"));
            boolean bool = productService.productInfoByIdDelete(productid);
            if(bool){
                commonResult.setMessage("删除成功");
            }
            else{
                commonResult.setMessage("删除失败");
            }
            PrintWriter writer = resp.getWriter();
            writer.print(JSON.toJSONString(commonResult));
            writer.flush();
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     *@Description 对用户请求修改操作
     *@Param
     *@Author Wang.li.ming
     *@Date 2020/7/23
     *@Time 11:04
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    /**
     *@Description 对用户请求添加操作
     *@Param
     *@Author Wang.li.ming
     *@Date 2020/7/23
     *@Time 11:05
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
