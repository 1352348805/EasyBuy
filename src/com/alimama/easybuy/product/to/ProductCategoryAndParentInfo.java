package com.alimama.easybuy.product.to;

import com.alimama.easybuy.product.bean.ProductCategory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author asuk
 * @date 2020/7/22 12:15
 */
public class ProductCategoryAndParentInfo extends ProductCategory {

    private LinkedList<ProductCategory> parents;

    public LinkedList<ProductCategory> getParents() {
        return parents;
    }

    public void setParents(LinkedList<ProductCategory> parents) {
        this.parents = parents;
    }
}
