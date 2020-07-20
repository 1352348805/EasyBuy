package com.alimama.easybuy.product.service;

import com.alimama.easybuy.product.bean.Product;
import com.alimama.easybuy.util.Page;

import java.util.List;

/**
 * @author Jun Xiao
 * @date 2020/7/20 8:53
 */
public interface ProductService {

    // 查询数据并进行分页
    public Page<Product> getPageProductIndex(int pageIndex) throws Exception;
}
