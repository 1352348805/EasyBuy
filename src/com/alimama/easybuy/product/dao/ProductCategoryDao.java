package com.alimama.easybuy.product.dao;

import com.alimama.easybuy.product.bean.ProductCategory;
import com.alimama.easybuy.product.to.ProductCategoryAndParentInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * @author asuk
 * @date 2020/7/22 10:23
 */
public interface ProductCategoryDao {

    //获取总记录数
    Integer getProductCategoryCount() throws SQLException;

    //获取分类列表
    List<ProductCategoryAndParentInfo> getProductCategoryList(Integer start, Integer pageSize) throws SQLException;
}
