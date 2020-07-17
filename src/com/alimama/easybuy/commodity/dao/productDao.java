package com.alimama.easybuy.commodity.dao;

import com.alimama.easybuy.commodity.bean.product;

import java.util.List;

public interface productDao {

    // 后台管理 - 查询所有商品列表接口
    public List<product> productList();


}
