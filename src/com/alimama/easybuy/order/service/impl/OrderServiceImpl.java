package com.alimama.easybuy.order.service.impl;

import com.alimama.easybuy.order.service.OrderService;
import com.alimama.easybuy.util.Alipay;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alimama.easybuy.order.dao.OrderDao;
import com.alimama.easybuy.order.dao.impe.OrderDaoImpl;
import com.alimama.easybuy.order.entity.Order;
import com.alimama.easybuy.order.service.OrderService;
import com.alimama.easybuy.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author asuk
 * @date 2020/7/20 0:14
 */
public class OrderServiceImpl implements OrderService {

    /**
     * 支付
     * @param out_trade_no 对外交易号
     * @param total_amount 总金额
     * @param subject      标题
     * @param body         描述
     * @return
     */
    public String payOrder(String out_trade_no, String total_amount, String subject, String body) {
        // 1、创建支付宝客户端
        AlipayClient alipayClient = new DefaultAlipayClient(Alipay.gatewayUrl,
                Alipay.app_id,
                Alipay.merchant_private_key,
                "json",
                Alipay.charset,
                Alipay.alipay_public_key,
                Alipay.sign_type);

        // 2、创建一次支付请求
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(Alipay.return_url);
        alipayRequest.setNotifyUrl(Alipay.notify_url);

        // 商户订单号，商户网站订单系统中唯一订单号，必填
        // 付款金额，必填
        // 订单名称，必填
        // 商品描述，可空

        // 3、构造支付请求数据
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
                + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = "";
        try {
            // 4、请求
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;// 支付跳转页的代码
    }

    @Override
    public List<Order> getOrderList () {
        List<Order> list = null;
        Connection con = null;
        try {
            con = DatabaseUtil.getConnection();
            OrderDao dao = new OrderDaoImpl(con);
            list = dao.getOrderList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return list;
    }

}
