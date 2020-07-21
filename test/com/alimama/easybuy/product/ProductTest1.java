package com.alimama.easybuy.product;

import com.alimama.easybuy.product.bean.Product;
import com.alimama.easybuy.product.dao.ProductDao;
import com.alimama.easybuy.product.dao.impl.ProductDaoImpl;
import com.alimama.easybuy.product.service.ProductService;
import com.alimama.easybuy.product.service.impl.ProductServiceImpl;
import com.alimama.easybuy.util.DatabaseUtil;
import com.alimama.easybuy.util.Page;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jun Xiao
 * @date 2020/7/20 10:43
 */
public class ProductTest1 {
    @Test
    public void test1()  {
        try{
            ProductService productService = new ProductServiceImpl();
            Page<Product> page = productService.getPageProductIndex(1);
//            for(Product product :page.getNewsList()){
//                System.out.println(product.getId());
//            }

              List<Product> products=page.getData();

            System.out.println(products.get(0).getId());
            System.out.println( page.getData().get(0).getFileName());

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void f2() throws Exception {
        ProductDao dao = new ProductDaoImpl(DatabaseUtil.getConnection());
        List<Product> products = dao.selectProductInfoById(Arrays.asList(735, 737));
        for (Product p : products) {
            System.out.println(p.getName());
        }
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println(p.getName());
        }
    }
}
