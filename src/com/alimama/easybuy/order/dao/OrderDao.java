package com.alimama.easybuy.order.dao;

import com.alimama.easybuy.order.entity.Order;
import com.alimama.easybuy.order.to.OrderByProductInfo;
import com.alimama.easybuy.product.bean.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @author W x c
 */
public interface OrderDao {
    //页面输出数据
    List<Order> getOrderList(Integer start, Integer pageSize) throws SQLException;

    //获取订单总记录数
    Integer getOrderCount() throws SQLException;

    //根据订单ID获取对应的商品列表
    List<OrderByProductInfo> getProductListByOrderId(Integer orderid) throws SQLException;
}
