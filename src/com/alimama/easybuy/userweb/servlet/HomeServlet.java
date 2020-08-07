package com.alimama.easybuy.userweb.servlet;

import com.alimama.easybuy.product.bean.Product;
import com.alimama.easybuy.product.service.ProductCategoryService;
import com.alimama.easybuy.product.service.ProductService;
import com.alimama.easybuy.product.service.impl.ProductCategoryServiceImpl;
import com.alimama.easybuy.product.service.impl.ProductServiceImpl;
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
@WebServlet("/Home")
public class HomeServlet extends HttpServlet {

    Logger logger = Logger.getLogger(HomeServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductCategoryService productCategoryService = new ProductCategoryServiceImpl();
        ProductService productService = new ProductServiceImpl();
        try{
            List<ProductCategoryWithSubClass> menu = productCategoryService.getProductCategoryMenu(req);
            req.setAttribute("menu", menu);

            // 化妆品
            List<Product> Cosmetic = productService.getselectProductParentIdInfo(548,6);
            req.setAttribute("cosmetic",Cosmetic);

            // 箱包
            List<Product> Luggage = productService.getselectProductParentIdInfo(681,6);
            req.setAttribute("luggage",Luggage);

            // 电子商品
            List<Product> ElectronicCommodity = productService.getselectProductParentIdInfo(670,6);
            req.setAttribute("electronicCommodity",ElectronicCommodity);

            // 进口食品/生鲜
            List<Product> ImportedFood = productService.getselectProductParentIdInfo(660,6);
            req.setAttribute("importedFood",ImportedFood);

            //保健食品
            List<Product> HealthyFood = productService.getselectProductParentIdInfo(676,6);
            req.setAttribute("healthyFood",HealthyFood);

            // 家用商品
            List<Product> HouseholdCommodity = productService.getselectProductParentIdInfo(628,6);
            req.setAttribute("householdCommodity",HouseholdCommodity);

        }catch(Exception e){
            e.printStackTrace();
        }

        req.getRequestDispatcher("/WEB-INF/page/userweb/Home.jsp").forward(req,resp);

    }


}
