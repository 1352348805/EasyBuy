package com.alimama.easybuy.order.service;

import com.alimama.easybuy.order.dao.OrderDao;
import com.alimama.easybuy.order.entity.Order;
import com.alimama.easybuy.order.to.OrderRelatedProductList;
import com.alimama.easybuy.util.Page;

import java.util.List;

public interface OrderService {

    //页面输出数据
    Page<OrderRelatedProductList> getOrderList(Integer pageIndex);
}
