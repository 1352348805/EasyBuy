package com.alimama.easybuy.product.dao.impl;

import com.alimama.easybuy.product.bean.Product;
import com.alimama.easybuy.product.dao.ProductDao;
import com.alimama.easybuy.product.vo.ProductQueryParam;
import com.alimama.easybuy.util.BaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public List<Product> productSelectPagesize(ProductQueryParam queryParams, int pageIndex, int pagesize) {
        List<Product> productpagesize = new ArrayList<Product>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM `easybuy_product` WHERE 1=1 ");
        List list = new ArrayList();
        if (queryParams != null) {
            if (queryParams.getName() != null && !queryParams.getName().equals("")) {
                sb.append("AND `name` LIKE CONCAT('%',?,'%') ");
                list.add(queryParams.getName());
            }
            if (queryParams.getCategoryLevel1Id() != null) {
                sb.append("AND `categoryLevel1Id` = ? ");
                list.add(queryParams.getCategoryLevel1Id());
            }
            if (queryParams.getCategoryLevel2Id() != null) {
                sb.append("AND `categoryLevel2Id` = ? ");
                list.add(queryParams.getCategoryLevel2Id());
            }
            if (queryParams.getCategoryLevel3Id() != null) {
                sb.append("AND `categoryLevel3Id` = ? ");
                list.add(queryParams.getCategoryLevel3Id());
            }
        }

        sb.append("LIMIT ?,?");
        list.add(pageIndex);
        list.add(pagesize);
        Product product = null;
        try{
           ResultSet rs =this.executeQuery(sb.toString(),list.toArray());
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
    public Product selectProductByid(Integer id){
        String sql = "SELECT * FROM `easybuy_product` WHERE id = ? ";
        Product productdao = null;
        try{
           ResultSet rs = this.executeQuery(sql,id);
           while(rs.next()){
               productdao = packagingProductData(rs);
           }
        }catch(Exception e){
            e.printStackTrace();
        }
        return productdao;
    }

    @Override
    public List<Product> selectProductParentOneinfo(Integer categoryLeveOneId){
        List<Product> SelectCategoryLeveOneIdList = new ArrayList<>();
        String sql = " SELECT * FROM `easybuy_product` WHERE `categoryLevel1Id` = ?";
        Product productdao = null;
        try{
          ResultSet rs = this.executeQuery(sql,categoryLeveOneId);
          while(rs.next()){
              productdao = packagingProductData(rs);
              SelectCategoryLeveOneIdList.add(productdao);
          }
        }catch(Exception e){
            e.printStackTrace();
        }
        return SelectCategoryLeveOneIdList;
    }

    @Override
    public Integer productSelectTotalCount(ProductQueryParam queryParam) {
        int productSumsize = 0 ;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(1) FROM `easybuy_product` WHERE 1=1 ");
        List list = new ArrayList();
        if (queryParam != null) {
            if (queryParam.getName() != null && !queryParam.getName().equals("")) {
                sb.append("AND `name` LIKE CONCAT('%',?,'%') ");
                list.add(queryParam.getName());
            }
            if (queryParam.getCategoryLevel1Id() != null) {
                sb.append("AND `categoryLevel1Id` = ? ");
                list.add(queryParam.getCategoryLevel1Id());
            }
            if (queryParam.getCategoryLevel2Id() != null) {
                sb.append("AND `categoryLevel2Id` = ? ");
                list.add(queryParam.getCategoryLevel2Id());
            }
            if (queryParam.getCategoryLevel3Id() != null) {
                sb.append("AND `categoryLevel3Id` = ? ");
                list.add(queryParam.getCategoryLevel3Id());
            }
        }
        try{
            ResultSet rs = this.executeQuery(sb.toString(),list.toArray());
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
    public boolean productdelete(Integer id) {
        String sql = "DELETE FROM `easybuy_product` WHERE id=?";
        try{
            Object[] objects = {id};
            int row = this.executeUpdate(sql,objects);
            if(row > 0) {
             return  true;
            }
            return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

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

    private Product packagingProductData(ResultSet rs) throws SQLException {
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
       return product;
    }

}
