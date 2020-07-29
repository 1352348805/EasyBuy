package com.alimama.easybuy.product.to;

import com.alimama.easybuy.product.bean.ProductCategory;

import java.util.List;

/**
 * @author asuk
 * @date 2020/7/27 8:50
 */
public class ProductCategoryWithSubClass extends ProductCategory {

    //该分类下的子类
    List<ProductCategoryWithSubClass> subClass;

    public List<ProductCategoryWithSubClass> getSubClass() {
        return subClass;
    }

    public void setSubClass(List<ProductCategoryWithSubClass> subClass) {
        this.subClass = subClass;
    }
}
