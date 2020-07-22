package com.alimama.easybuy.product.service;

import com.alimama.easybuy.product.bean.ProductCategory;
import com.alimama.easybuy.product.to.ProductCategoryAndParentInfo;
import com.alimama.easybuy.util.Page;

import java.util.List;

/**
 * @author asuk
 * @date 2020/7/21 19:49
 */
public interface ProductCategoryService {

    //获取分类列表
    Page<ProductCategoryAndParentInfo> getProductCategoryList(Integer pageIndex);

}
