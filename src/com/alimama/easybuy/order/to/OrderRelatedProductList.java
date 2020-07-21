package com.alimama.easybuy.order.to;

import com.alimama.easybuy.order.entity.Order;
import com.alimama.easybuy.product.bean.Product;

import java.util.List;

/**
 * 订单相关商品列表
 */
public class OrderRelatedProductList extends Order {

    //商品列表
    List<OrderByProductInfo> productList;

    public List<OrderByProductInfo> getProductList() {
        return productList;
    }

    public void setProductList(List<OrderByProductInfo> productList) {
        this.productList = productList;
    }
}
