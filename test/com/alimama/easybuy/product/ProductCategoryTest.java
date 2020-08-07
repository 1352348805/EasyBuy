package com.alimama.easybuy.product;

import com.alimama.easybuy.product.bean.Product;
import com.alimama.easybuy.product.bean.ProductCategory;
import com.alimama.easybuy.product.dao.ProductCategoryDao;
import com.alimama.easybuy.product.dao.ProductDao;
import com.alimama.easybuy.product.dao.impl.ProductCategoryDaoImpl;
import com.alimama.easybuy.product.dao.impl.ProductDaoImpl;
import com.alimama.easybuy.product.service.ProductCategoryService;
import com.alimama.easybuy.product.service.impl.ProductCategoryServiceImpl;
import com.alimama.easybuy.product.to.ProductCategoryAndParentInfo;
import com.alimama.easybuy.product.to.ProductCategoryWithSubClass;
import com.alimama.easybuy.product.vo.ProductQueryParam;
import com.alimama.easybuy.util.DatabaseUtil;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author asuk
 * @date 2020/7/23 23:37
 */
public class ProductCategoryTest {

    ProductCategoryService service = null;

    ProductCategoryDao productCategoryDao = null;
    @Before
    public void before() throws SQLException {
        service = new ProductCategoryServiceImpl();
        productCategoryDao = new ProductCategoryDaoImpl(DatabaseUtil.getConnection());
    }

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
    public void integer() throws Exception {
        ProductDao dao = new ProductDaoImpl(DatabaseUtil.getConnection());
        ProductQueryParam product = new ProductQueryParam();
        //product.setName("香");
        product.setCategoryLevel1Id(548);
        List<Product> products = dao.productSelectPagesize(null, 0, 5);
        products.forEach(item ->{
            System.out.println(item.getName());
        });
    }

    @Test
    public void fff() throws SQLException {
        ProductCategoryWithSubClass productCategoryWithSubClasses = new ProductCategoryWithSubClass();

        fillMenu(0,productCategoryWithSubClasses);
        System.out.println("---------------");

        productCategoryWithSubClasses.getSubClass().forEach(item1 -> {
            System.out.println(item1);
            item1.getSubClass().forEach(item2 -> {
                System.out.println(item2);
                item2.getSubClass().forEach(item3 -> {
                    System.out.println(item3);
                });
            });
        });

    }

    /**
     * 递归获取菜单分类
     * @param parentId 父级分类id
     * @param productCategoryWithSubClass 该分类的子分类集合
     */
    private void fillMenu(Integer parentId,ProductCategoryWithSubClass productCategoryWithSubClass)  {
        AtomicReference<List<ProductCategoryWithSubClass>> listAtomicReference = new AtomicReference<>();
        listAtomicReference.set(new ArrayList<>());
        try {
            List<ProductCategory> productCategoryListByParentId = productCategoryDao.getProductCategoryListByParentId(parentId);
            productCategoryListByParentId.forEach(item -> {
                ProductCategoryWithSubClass subClassList = new ProductCategoryWithSubClass();
                subClassList.setId(item.getId());
                subClassList.setName(item.getName());
                subClassList.setParentId(item.getParentId());
                subClassList.setType(item.getType());
                subClassList.setIconClass(item.getIconClass());
                fillMenu(item.getId(), subClassList);
                listAtomicReference.get().add(subClassList);
            });
            productCategoryWithSubClass.setSubClass(listAtomicReference.get());
        } catch (Exception e) {

        }
    }
}
