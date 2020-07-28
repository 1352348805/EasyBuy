package com.alimama.easybuy.product.dao.impl;

import com.alimama.easybuy.product.bean.ProductCategory;
import com.alimama.easybuy.product.dao.ProductCategoryDao;
import com.alimama.easybuy.product.to.ProductCategoryAndParentInfo;
import com.alimama.easybuy.util.BaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author asuk
 * @date 2020/7/22 10:25
 */
public class ProductCategoryDaoImpl extends BaseDao implements ProductCategoryDao {

    public ProductCategoryDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public Integer getProductCategoryCount() throws SQLException {
        String sql = "SELECT COUNT(1) FROM `easybuy_product_category`";
        ResultSet rs = executeQuery(sql);
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public List<ProductCategoryAndParentInfo> getProductCategoryList(Integer start, Integer pageSize) throws SQLException {
        List<ProductCategoryAndParentInfo> productCategoryAndParentInfoList = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.`id`,a.`name`,a.`parentId`,a.`type`,p.`name` AS pname ,p.`type` AS ptype ");
        sql.append("FROM `easybuy_product_category` a LEFT JOIN `easybuy_product_category` p ");
        sql.append("ON (a.`parentId` = p.`id`) ");
        sql.append("ORDER BY TYPE,id LIMIT ?,?");
        ResultSet rs = executeQuery(sql.toString(), start, pageSize);
        while (rs.next()) {
            ProductCategoryAndParentInfo productCategoryAndParentInfo = new ProductCategoryAndParentInfo();
            productCategoryAndParentInfo.setId(rs.getInt("id"));
            productCategoryAndParentInfo.setName(rs.getString("name"));
            productCategoryAndParentInfo.setParentId( rs.getInt("parentId"));
            productCategoryAndParentInfo.setType(rs.getInt("type"));

            LinkedList<ProductCategory> parents = new LinkedList<>();
            ProductCategory productCategory = new ProductCategory();
            productCategory.setName(rs.getString("pname"));
            productCategory.setType(rs.getInt("ptype"));
            parents.add(productCategory);
            productCategoryAndParentInfo.setParents(parents);
            productCategoryAndParentInfoList.add(productCategoryAndParentInfo);
        }
        return productCategoryAndParentInfoList;
    }

    @Override
    public Integer deleteProductCategoryById(Integer id) throws SQLException {
        String sql = "DELETE FROM `easybuy_product_category` WHERE id = ?";
        return executeUpdate(sql, id);
    }

    @Override
    public List<ProductCategory> getProductCategoryListByParentId(Integer parentId) throws SQLException {
        List<ProductCategory> productCategoryList = new ArrayList<>();
        String sql = "SELECT * FROM `easybuy_product_category` WHERE `parentId` = ?";
        ResultSet rs = executeQuery(sql, parentId);
        while (rs.next()) {
            ProductCategory productCategory = packagingData(rs);
            productCategoryList.add(productCategory);
        }
        return productCategoryList;
    }

    @Override
    public Integer modifyProductCategory(ProductCategory productCategory) throws SQLException {
        String sql = "UPDATE `easybuy_product_category` SET `name` = ?,`parentId` = ?,`type` = ? WHERE id = ?";
        return executeUpdate(sql,
                productCategory.getName(),
                productCategory.getParentId(),
                productCategory.getType(),
                productCategory.getId());
    }

    @Override
    public Integer insertProductCategory(ProductCategory productCategory) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO `easybuy_product_category`(`name`,`parentId`,`type`) ");
        sql.append("VALUES(?,?,?)");
        return executeUpdate(sql.toString()
                , productCategory.getName()
                , productCategory.getParentId()
                , productCategory.getType());
    }

    @Override
    public ProductCategory getProductCategoryById(Integer id) throws SQLException {
        ProductCategory productCategory = null;
        String sql = "SELECT * FROM `easybuy_product_category` WHERE id = ?";
        ResultSet rs = executeQuery(sql, id);
        if (rs.next()) {
            productCategory = packagingData(rs);
        }
        return productCategory;
    }

    @Override
    public List<ProductCategory> getProductCategoryListByType(Integer type) throws SQLException {
        List<ProductCategory> productCategoryList = new ArrayList<>();
        String sql = "SELECT * FROM `easybuy_product_category` WHERE TYPE = ?";
        ResultSet rs = executeQuery(sql,type);
        while (rs.next()) {
            ProductCategory productCategory = packagingData(rs);
            productCategoryList.add(productCategory);
        }
        return productCategoryList;
    }


    private ProductCategory packagingData(ResultSet rs) throws SQLException {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(rs.getInt("id"));
        productCategory.setName(rs.getString("name"));
        productCategory.setParentId(rs.getInt("parentId"));
        productCategory.setType(rs.getInt("type"));
        productCategory.setIconClass(rs.getString("iconClass"));
        return productCategory;
    }
}
