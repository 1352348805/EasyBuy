package com.alimama.easybuy.order.to;

import com.alimama.easybuy.order.entity.OrderDetail;

/**
 * 订单相关的商品信息
 */
public class OrderByProductInfo extends OrderDetail {

    private String productName;

    private String fileName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
