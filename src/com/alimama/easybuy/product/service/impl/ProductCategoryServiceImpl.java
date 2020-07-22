package com.alimama.easybuy.product.service.impl;

import com.alimama.easybuy.product.bean.ProductCategory;
import com.alimama.easybuy.product.dao.ProductCategoryDao;
import com.alimama.easybuy.product.dao.impl.ProductCategoryDaoImpl;
import com.alimama.easybuy.product.service.ProductCategoryService;
import com.alimama.easybuy.product.to.ProductCategoryAndParentInfo;
import com.alimama.easybuy.util.DatabaseUtil;
import com.alimama.easybuy.util.Page;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author asuk
 * @date 2020/7/21 19:49
 */
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private ProductCategoryDao productCategoryDao;

    @Override
    public Page<ProductCategoryAndParentInfo> getProductCategoryList(Integer pageIndex) {
        Page<ProductCategoryAndParentInfo> page = new Page<>();
        Connection con = null;
        try {
            con = DatabaseUtil.getConnection();
            productCategoryDao = new ProductCategoryDaoImpl(con);
            Integer count = productCategoryDao.getProductCategoryCount();
            page.setPageSize(8);
            page.setTotalCount(count);
            page.setCurrPageNo(pageIndex);
            System.out.println(page.getTotalPageCount());
            Integer start = page.getStartIndex();
            List<ProductCategoryAndParentInfo> productCategoryList =
                    productCategoryDao.getProductCategoryList(start, page.getPageSize());
            page.setData(productCategoryList);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseUtil.close(con);
        }
        return page;
    }
}
