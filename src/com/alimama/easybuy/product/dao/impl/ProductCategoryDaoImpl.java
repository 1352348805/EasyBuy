package com.alimama.easybuy.product.dao.impl;

import com.alimama.easybuy.product.bean.ProductCategory;
import com.alimama.easybuy.product.dao.ProductCategoryDao;
import com.alimama.easybuy.product.to.ProductCategoryAndParentInfo;
import com.alimama.easybuy.util.BaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        sql.append("ORDER BY TYPE LIMIT ?,?");
        ResultSet rs = executeQuery(sql.toString(), start, pageSize);
        while (rs.next()) {
            ProductCategoryAndParentInfo productCategoryAndParentInfo = new ProductCategoryAndParentInfo();
            productCategoryAndParentInfo.setId(rs.getInt("id"));
            productCategoryAndParentInfo.setName(rs.getString("name"));
            productCategoryAndParentInfo.setParentId( rs.getInt("parentId"));
            productCategoryAndParentInfo.setType(rs.getInt("type"));
            productCategoryAndParentInfo.setPname(rs.getString("pname"));
            productCategoryAndParentInfo.setPtype(rs.getInt("ptype"));
            productCategoryAndParentInfoList.add(productCategoryAndParentInfo);
        }
        return productCategoryAndParentInfoList;
    }
}
