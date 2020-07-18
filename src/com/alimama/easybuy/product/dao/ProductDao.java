package com.alimama.easybuy.product.dao;

import com.alimama.easybuy.product.bean.Product;

import java.util.List;

public interface ProductDao {

    // 后台管理 - 查询所有商品列表接口
    public List<Product> productList();


}
