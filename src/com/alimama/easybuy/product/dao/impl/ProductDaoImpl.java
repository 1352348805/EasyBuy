package com.alimama.easybuy.product.dao.impl;

import com.alimama.easybuy.product.bean.Product;
import com.alimama.easybuy.product.dao.ProductDao;
import com.alimama.easybuy.util.BaseDao;
import com.alimama.easybuy.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



/**
 * @author Jun Xiao
 * @date 2020/7/17 12:28
 */


public class ProductDaoImpl extends BaseDao implements ProductDao {
    // 子类继承父类的构造方法
    public ProductDaoImpl(Connection connection){
        super(connection);
    }
    @Override
    public List<Product> productSelectPagesize(int pageIndex,int pagesize) {
        List<Product> productpagesize = new ArrayList<Product>();
        String sql="SELECT * FROM `easybuy_product`  LIMIT ?,?";
        Product product = null;
        try{
           ResultSet rs =this.executeQuery(sql,pageIndex,pagesize);
           while(rs.next()){
               product = new Product();
               product.setId(rs.getInt("id"));
               product.setName(rs.getString("name"));
               product.setDescription(rs.getString("description"));
               product.setPrice(rs.getFloat("price"));
               product.setStock(rs.getInt("stock"));
               product.setCategoryLevel1Id(rs.getInt("categoryLevel1Id"));
               product.setCategoryLevel2Id(rs.getInt("categoryLevel2Id"));
               product.setCategoryLevel3Id(rs.getInt("categoryLevel3Id"));
               product.setFileName(rs.getString("fileName"));
               product.setIsDelete(rs.getInt("isDelete"));
               productpagesize.add(product);
           }
        }catch(Exception e){
            e.printStackTrace();
        }
        return productpagesize;
    }

    @Override
    public Integer productSelectTotalCount() {
        int productSumsize = 0 ;
        String sql="SELECT COUNT(1) FROM `easybuy_product` ";
        try{
            ResultSet rs = this.executeQuery(sql);
            if (rs.next()) {
                productSumsize = rs.getInt(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return productSumsize;
    }

    @Override
    public boolean productupdate(int type) {
        try{
            if(type ==1){
               String sql ="SELECT * FROM `easybuy_product_category` WHERE type = 1";
            }
            if(type == 2){
                String sql ="SELECT * FROM `easybuy_product_category` WHERE type = 2";
            }
            if(type == 3){
                String sql ="SELECT * FROM `easybuy_product_category` WHERE type = 3";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean productadd() {
        return false;
    }

    @Override
    public boolean productdelete() {
        return false;
    }

    @Override
    public List<Product> selectProductInfoById(List<Integer> ids) throws Exception {
        List<Product> products = null;
        if (ids != null && ids.size() > 0)
        {
            String sql = "SELECT * FROM `easybuy_product` WHERE id IN(";
            for (int i = 0; i < ids.size(); i++) {
                sql += "?,";
            }
            sql = sql.substring(0,sql.length()-1);
            sql += ")";

            ResultSet rs = executeQuery(sql, ids.toArray());
            products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getFloat("price"));
                product.setStock(rs.getInt("stock"));
                product.setCategoryLevel1Id(rs.getInt("categoryLevel1Id"));
                product.setCategoryLevel2Id(rs.getInt("categoryLevel2Id"));
                product.setCategoryLevel3Id(rs.getInt("categoryLevel3Id"));
                product.setFileName(rs.getString("fileName"));
                product.setIsDelete(rs.getInt("isDelete"));
                products.add(product);
            }
        }
        return products;
    }
}
