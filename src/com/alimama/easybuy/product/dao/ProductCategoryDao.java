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

    //根据分类id删除商品分类
    Integer deleteProductCategoryById(Integer id) throws SQLException;

    //查询该分类id下的子分类集合
    List<ProductCategory> getProductCategoryListByParentId(Integer parentId) throws SQLException;

    //修改分类
    Integer modifyProductCategory(ProductCategory productCategory) throws SQLException;

    //添加分类
    Integer insertProductCategory(ProductCategory productCategory) throws SQLException;

    //以id获取分类
    ProductCategory getProductCategoryById(Integer id) throws SQLException;

    //获取指定分类
    List<ProductCategory> getProductCategoryListByType(Integer type) throws SQLException;

}
