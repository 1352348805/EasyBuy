package com.alimama.easybuy.product.service.impl;

import com.alimama.easybuy.product.bean.Product;
import com.alimama.easybuy.product.dao.ProductDao;
import com.alimama.easybuy.product.dao.impl.ProductDaoImpl;
import com.alimama.easybuy.product.service.ProductService;
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
    public Page<Product> getPageProductIndex(int pageIndex){
        Page<Product> page = new Page<>();
        List<Product> pagesizelist = new ArrayList<Product>();
        Connection con= null;
        try{
            con = DatabaseUtil.getConnection();
            ProductDao productDao = new ProductDaoImpl(con);
            int count = productDao.productSelectTotalCount();
            page.setTotalCount(count); // 计算总数据
            page.setCurrPageNo(pageIndex);  // 计算页码
            //  page.getStartIndex() 以那条数据开始分页
            pagesizelist = productDao.productSelectPagesize(page.getStartIndex(),page.getPageSize());
            page.setData(pagesizelist);
        }catch(Exception e){
            e.printStackTrace();
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
        }
    }
}
