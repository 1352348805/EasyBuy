package com.alimama.easybuy.order.dao.impe;

import com.alimama.easybuy.order.dao.OrderDao;
import com.alimama.easybuy.order.entity.Order;
import com.alimama.easybuy.order.to.OrderByProductInfo;
import com.alimama.easybuy.product.bean.Product;
import com.alimama.easybuy.util.BaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    public OrderDaoImpl(Connection conn) {
        super(conn);
    }

    /**
     * 获取订单分页
     * @param start  起始数
     * @param pageSize 每页多少条
     * @return
     */
    @Override
    public List<Order> getOrderList(Integer start, Integer pageSize) throws SQLException {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM `easybuy_order` LIMIT ?,?";

        ResultSet rs = executeQuery(sql,start,pageSize);

        while (rs.next()){
            Order oc=new Order();
            oc.setUser(rs.getInt("userId"));
            oc.setLoginName(rs.getString("loginName"));
            oc.setOrderid(rs.getInt("id"));
            oc.setTimi(rs.getDate("createTime"));
            oc.setMoony(rs.getInt("cost"));
            oc.setUserAddress(rs.getString("userAddress"));
            list.add(oc);
        }
        return list;
    }

    @Override
    public Integer getOrderCount() throws SQLException {
        Integer count = 0;
        String sql = "SELECT COUNT(1) FROM `easybuy_order`";
        ResultSet rs = executeQuery(sql);
        if (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }

    @Override
    public List<OrderByProductInfo> getProductListByOrderId(Integer orderid) throws SQLException {
        List<OrderByProductInfo> list = new ArrayList<>();
        String sql = "SELECT * FROM `easybuy_order_detail` AS a INNER JOIN `easybuy_product` AS b ON (a.productId = b.id) "
                + "WHERE a.orderId = ?";
        ResultSet rs = executeQuery(sql, orderid);
        while (rs.next()) {
            OrderByProductInfo productInfo = new OrderByProductInfo();
            productInfo.setId(rs.getInt("id"));
            productInfo.setProductName(rs.getString("name"));
            productInfo.setQuantity(rs.getInt("quantity"));
            productInfo.setCost(rs.getFloat("cost"));
            productInfo.setFileName(rs.getString("fileName"));
            list.add(productInfo);
        }
        return list;
    }
}
