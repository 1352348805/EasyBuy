package com.alimama.easybuy.product;

import com.alimama.easybuy.product.bean.ProductCategory;
import com.alimama.easybuy.product.dao.ProductCategoryDao;
import com.alimama.easybuy.product.dao.impl.ProductCategoryDaoImpl;
import com.alimama.easybuy.product.service.ProductCategoryService;
import com.alimama.easybuy.product.service.impl.ProductCategoryServiceImpl;
import com.alimama.easybuy.product.to.ProductCategoryAndParentInfo;
import com.alimama.easybuy.util.DatabaseUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author asuk
 * @date 2020/7/23 23:37
 */
public class ProductCategoryTest {


    @Test
    public void f1() {
        ProductCategoryService service = new ProductCategoryServiceImpl();
        ProductCategoryAndParentInfo productCategoryAndParent = service.getProductCategoryAndParent(658);
        System.out.println(productCategoryAndParent.getType());
        productCategoryAndParent.getParents().forEach(i -> {
            System.out.println(i.getName());
        });
        System.out.println(productCategoryAndParent.getName());
    }

    @Test
    public void getProductgoryListByType() throws SQLException {
        Connection con = null;
        con = DatabaseUtil.getConnection();
        ProductCategoryDao dao = new ProductCategoryDaoImpl(con);
        List<ProductCategory> productgoryListByType = dao.getProductCategoryListByType(1);
        productgoryListByType.forEach(item -> {
            System.out.println(item.getId());
        });
    }

    @Test
    public void integer() {
        Integer a = 128;
        Integer b = 128;

        System.out.println(a.equals(b));

    }
}
