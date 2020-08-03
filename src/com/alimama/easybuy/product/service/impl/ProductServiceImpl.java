package com.alimama.easybuy.product.service.impl;

import com.alimama.easybuy.product.bean.Product;
import com.alimama.easybuy.product.dao.ProductDao;
import com.alimama.easybuy.product.dao.impl.ProductDaoImpl;
import com.alimama.easybuy.product.service.ProductService;
import com.alimama.easybuy.product.vo.ProductQueryParam;
import com.alimama.easybuy.util.DatabaseUtil;
import com.alimama.easybuy.util.Page;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jun Xiao
 * @date 2020/7/20 8:55
 */
public class ProductServiceImpl implements ProductService {

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public Page<Product> getPageProductIndex(ProductQueryParam queryParam, int pageIndex, int pageSize){
        Page<Product> page = new Page<>();
        List<Product> pagesizelist = new ArrayList<Product>();
        Connection con= null;
        try{
            con = DatabaseUtil.getConnection();
            ProductDao productDao = new ProductDaoImpl(con);
            int count = productDao.productSelectTotalCount(queryParam);
            page.setPageSize(pageSize);
            page.setTotalCount(count); // 计算总数据
            page.setCurrPageNo(pageIndex);  // 计算页码
            //  page.getStartIndex() 以那条数据开始分页
            pagesizelist = productDao.productSelectPagesize(queryParam,page.getStartIndex(),page.getPageSize());
            page.setData(pagesizelist);
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            DatabaseUtil.close(con);
        }
        return page;
    }

    @Override
    public boolean productInfoByIdDelete(Integer id) {
        Connection con = null;
        try{
            con=DatabaseUtil.getConnection();
            ProductDao productDao = new ProductDaoImpl(con);
            if(productDao.productdelete(id)){
               return true;
            }
            return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            DatabaseUtil.close(con);
        }
    }

    @Override
    public Product getProductById(Integer id) throws Exception {
        Connection con = null;
        Product productservice = null;
        try{
            con = DatabaseUtil.getConnection();
            ProductDao productDao = new ProductDaoImpl(con);
            productservice = productDao.selectProductByid(id);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            DatabaseUtil.close(con);
        }
        return productservice;
    }

    @Override
    public List<Product> getProductParentOneinfo(Integer categoryLeveOneId){
        Connection con = null;
        List<Product> ProductParentOneinfoList = new ArrayList<>();
        try{
          con = DatabaseUtil.getConnection();
          ProductDao productDao = new ProductDaoImpl(con);
          ProductParentOneinfoList = productDao.selectProductParentOneinfo(categoryLeveOneId);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            DatabaseUtil.close(con);
        }
        return ProductParentOneinfoList;
    }

    @Override
    public boolean productupdate(Product product) throws Exception {
        Connection con = null ;
        try{
            con = DatabaseUtil.getConnection();
            ProductDao productDao = new ProductDaoImpl(con);
            boolean bool =  productDao.productupdate(product);
            if(bool){
                return true;
            }
            return  false;
        }catch(Exception e){
            e.printStackTrace();
            return  false;
        }finally{
            DatabaseUtil.close(con);
        }

    }
}

