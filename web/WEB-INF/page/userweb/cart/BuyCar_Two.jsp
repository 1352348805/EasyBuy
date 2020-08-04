<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css" />
    <!--[if IE 6]>
    <script src="<%=path%>/js/iepng.js" type="text/javascript"></script>
        <script type="text/javascript">
           EvPNG.fix('div, ul, img, li, input, a'); 
        </script>
    <![endif]-->
    
    <script type="text/javascript" src="<%=path%>/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/menu.js"></script>
                
	<script type="text/javascript" src="<%=path%>/js/n_nav.js"></script>
    
    <script type="text/javascript" src="<%=path%>/js/select.js"></script>
    
    <script type="text/javascript" src="<%=path%>/js/num.js">
    	var jq = jQuery.noConflict();
    </script>     
    
    <script type="text/javascript" src="<%=path%>/js/shade.js"></script>
    
<title>尤洪</title>
</head>
<body>  
<!--Begin Header Begin-->
<jsp:include page="../../common/top_nav.jsp" />
<!--End Header End--> 
<!--Begin Menu Begin-->
<jsp:include page="../../common/category_menu.jsp" />
<!--End Menu End--> 
<div class="i_bg">  
    <div class="content mar_20">
    	<img src="images/img2.jpg" />        
    </div>
    
    <!--Begin 第二步：确认订单信息 Begin -->
    <div class="content mar_20">
    	<div class="two_bg">
        	<div class="two_t">
            	<span class="fr"><a href="#">修改</a></span>商品列表
            </div>
            <table border="0" class="car_tab" style="width:1110px;" cellspacing="0" cellpadding="0">
              <tr>
                <td class="car_th" width="550">商品名称</td>
                <td class="car_th" width="150">购买数量</td>
                <td class="car_th" width="130">小计</td>
              </tr>

                <c:forEach var="cartItem" items="${cart.cartItems}">
                    <tr>
                        <td>
                            <div class="c_s_img"><img src="<%=path%>/images/${cartItem.fileName}" width="73" height="73" /></div>
                                ${cartItem.name}
                        </td>
                        <td align="center">${cartItem.count}</td>
                        <td align="center" style="color:#ff4e00;">￥${cartItem.totalPrice}</td>
                    </tr>
                </c:forEach>


<%--              <tr>--%>
<%--                <td colspan="5" align="right" style="font-family:'Microsoft YaHei';">--%>
<%--                    商品总价：￥1899.00 ； 返还积分 56R  --%>
<%--                </td>--%>
<%--              </tr>--%>
            </table>
            
            <div class="two_t">
            	<span class="fr"><a href="#">修改</a></span>收货人信息
            </div>
            <table border="0" class="peo_tab" style="width:1110px;" cellspacing="0" cellpadding="0">
              <tr>
                <td class="p_td" width="160">姓名</td>
                <td width="395">${user.userName}</td>
                <td class="p_td" width="160">电子邮件</td>
                <td width="395">${user.email}</td>
              </tr>
              <tr>
                <td class="p_td">详细信息</td>
                <td>四川省成都市武侯区</td>
                <td class="p_td">邮政编码</td>
                <td>6011111</td>
              </tr>
              <tr>
                <td class="p_td">电话</td>
                <td></td>
                <td class="p_td">手机</td>
                <td>${user.mobile}</td>
              </tr>
              <tr>
                <td class="p_td">标志建筑</td>
                <td></td>
                <td class="p_td">最佳送货时间</td>
                <td></td>
              </tr>
            </table>

            
<%--            <div class="two_t">--%>
<%--            	配送方式--%>
<%--            </div>--%>
<%--            <table border="0" class="car_tab" style="width:1110px;" cellspacing="0" cellpadding="0">--%>
<%--              <tr>--%>
<%--                <td class="car_th" width="80"></td>--%>
<%--                <td class="car_th" width="200">名称</td>--%>
<%--                <td class="car_th" width="370">订购描述</td>--%>
<%--                <td class="car_th" width="150">费用</td>--%>
<%--                <td class="car_th" width="135">免费额度</td>--%>
<%--                <td class="car_th" width="175">保价费用</td>--%>
<%--              </tr>--%>
<%--              <tr>--%>
<%--              	<td align="center"><input type="checkbox" name="ch" checked="checked" /></td>--%>
<%--                <td align="center" style="font-size:14px;"><b>申通快递</b></td>--%>
<%--                <td>江、浙、沪地区首重为15元/KG，其他地区18元/KG，续重均为5-6元/KG， 云南地区为8元</td>--%>
<%--                <td align="center">￥15.00</td>--%>
<%--                <td align="center">￥0.00</td>--%>
<%--                <td align="center">不支持保价</td>--%>
<%--              </tr>--%>
<%--              <tr>--%>
<%--              	<td align="center"><input type="checkbox" name="ch" /></td>--%>
<%--                <td align="center" style="font-size:14px;"><b>城际快递</b></td>--%>
<%--                <td>运费固定</td>--%>
<%--                <td align="center">￥15.00</td>--%>
<%--                <td align="center">￥0.00</td>--%>
<%--                <td align="center">不支持保价</td>--%>
<%--              </tr>--%>
<%--              <tr>--%>
<%--              	<td align="center"><input type="checkbox" name="ch" /></td>--%>
<%--                <td align="center" style="font-size:14px;"><b>邮局平邮</b></td>--%>
<%--                <td>运费固定</td>--%>
<%--                <td align="center">￥15.00</td>--%>
<%--                <td align="center">￥0.00</td>--%>
<%--                <td align="center">不支持保价</td>--%>
<%--              </tr>--%>
<%--              <tr>--%>
<%--              	<td colspan="6">--%>
<%--                	<span class="fr"><label class="r_rad"><input type="checkbox" name="baojia" /></label><label class="r_txt">配送是否需要保价</label></span>--%>
<%--                </td>--%>
<%--              </tr>--%>
<%--            </table> --%>
            
            <div class="two_t">
            	支付方式
            </div>
            <ul class="pay">
<%--                <li class="checked">余额支付<div class="ch_img"></div></li>--%>
<%--                <li>银行亏款/转账<div class="ch_img"></div></li>--%>
<%--                <li>货到付款<div class="ch_img"></div></li>--%>
                <li>支付宝<div class="ch_img"></div></li>
            </ul>
            
<%--            <div class="two_t">--%>
<%--            	商品包装--%>
<%--            </div>--%>
<%--            <table border="0" class="car_tab" style="width:1110px;" cellspacing="0" cellpadding="0">--%>
<%--              <tr>--%>
<%--                <td class="car_th" width="80"></td>--%>
<%--                <td class="car_th" width="490">名称</td>--%>
<%--                <td class="car_th" width="180">费用</td>--%>
<%--                <td class="car_th" width="180">免费额度</td>--%>
<%--                <td class="car_th" width="180">图片</td>--%>
<%--              </tr>--%>
<%--              <tr>--%>
<%--              	<td align="center"><input type="checkbox" name="pack" checked="checked" /></td>--%>
<%--                <td><b style="font-size:14px;">不要包装</b></td>--%>
<%--                <td align="center">￥15.00</td>--%>
<%--                <td align="center">￥0.00</td>--%>
<%--                <td align="center"></td>--%>
<%--              </tr>--%>
<%--              <tr>--%>
<%--              	<td align="center"><input type="checkbox" name="pack" /></td>--%>
<%--                <td><b style="font-size:14px;">精品包装</b></td>--%>
<%--                <td align="center">￥15.00</td>--%>
<%--                <td align="center">￥0.00</td>--%>
<%--                <td align="center"><a href="#" style="color:#ff4e00;">查看</a></td>--%>
<%--              </tr>--%>
<%--            </table> --%>
            
<%--            <div class="two_t">--%>
<%--            	祝福贺卡--%>
<%--            </div>--%>
<%--            <table border="0" class="car_tab" style="width:1110px;" cellspacing="0" cellpadding="0">--%>
<%--              <tr>--%>
<%--                <td class="car_th" width="80"></td>--%>
<%--                <td class="car_th" width="490">名称</td>--%>
<%--                <td class="car_th" width="180">费用</td>--%>
<%--                <td class="car_th" width="180">免费额度</td>--%>
<%--                <td class="car_th" width="180">图片</td>--%>
<%--              </tr>--%>
<%--              <tr>--%>
<%--              	<td align="center"><input type="checkbox" name="wish" checked="checked" /></td>--%>
<%--                <td><b style="font-size:14px;">不要贺卡</b></td>--%>
<%--                <td align="center">￥15.00</td>--%>
<%--                <td align="center">￥0.00</td>--%>
<%--                <td align="center"></td>--%>
<%--              </tr>--%>
<%--              <tr>--%>
<%--              	<td align="center" style="border-bottom:0; padding-bottom:0;"><input type="checkbox" name="wish" /></td>--%>
<%--                <td style="border-bottom:0; padding-bottom:0;"><b style="font-size:14px;">祝福贺卡</b></td>--%>
<%--                <td align="center" style="border-bottom:0; padding-bottom:0;">￥15.00</td>--%>
<%--                <td align="center" style="border-bottom:0; padding-bottom:0;">￥0.00</td>--%>
<%--                <td align="center" style="border-bottom:0; padding-bottom:0;"><a href="#" style="color:#ff4e00;">查看</a></td>--%>
<%--              </tr>--%>
<%--              <tr valign="top">--%>
<%--              	<td align="center"></td>--%>
<%--                <td colspan="4">--%>
<%--                	<span class="fl"><b style="font-size:14px;">祝福语：</b></span>--%>
<%--                    <span class="fl"><textarea class="add_txt" style="width:860px; height:50px;"></textarea></span>--%>
<%--                </td>--%>
<%--              </tr>--%>
<%--            </table> --%>
            
<%--            <div class="two_t">--%>
<%--            	其他信息--%>
<%--            </div>--%>
<%--            <table border="0" class="car_tab" style="width:1110px;" cellspacing="0" cellpadding="0">--%>
<%--              <tr>--%>
<%--                <td width="145" align="right" style="padding-right:0;"><b style="font-size:14px;">使用红包：</b></td>--%>
<%--                <td>--%>
<%--                	<span class="fl" style="margin-left:50px; margin-right:10px;">选择已有红包</span>--%>
<%--                    <select class="jj" name="city">--%>
<%--                      <option value="0" selected="selected">请选择</option>--%>
<%--                      <option value="1">50元</option>--%>
<%--                      <option value="2">30元</option>--%>
<%--                      <option value="3">20元</option>--%>
<%--                      <option value="4">10元</option>--%>
<%--                    </select>--%>
<%--                    <span class="fl" style="margin-left:50px; margin-right:10px;">或者输入红包序列号</span>--%>
<%--                    <span class="fl"><input type="text" value="" class="c_ipt" style="width:220px;" />--%>
<%--                    <span class="fr" style="margin-left:10px;"><input type="submit" value="验证红包" class="btn_tj" /></span>--%>
<%--                </td>--%>
<%--			  </tr>--%>
<%--              <tr valign="top">--%>
<%--                <td align="right" style="padding-right:0;"><b style="font-size:14px;">订单附言：</b></td>--%>
<%--                <td style="padding-left:0;"><textarea class="add_txt" style="width:860px; height:50px;"></textarea></td>--%>
<%--              </tr>--%>
<%--              <tr>--%>
<%--              	<td align="right" style="padding-right:0;"><b style="font-size:14px;">缺货处理：</b></td>--%>
<%--                <td>--%>
<%--                	<label class="r_rad"><input type="checkbox" name="none" checked="checked" /></label><label class="r_txt" style="margin-right:50px;">等待所有商品备齐后再发</label>--%>
<%--                    <label class="r_rad"><input type="checkbox" name="none" /></label><label class="r_txt" style="margin-right:50px;">取下订单</label>--%>
<%--                    <label class="r_rad"><input type="checkbox" name="none" /></label><label class="r_txt" style="margin-right:50px;">与店主协商</label>--%>
<%--                </td>--%>
<%--              </tr>--%>
<%--            </table>--%>
            
            <table border="0" style="width:900px; margin-top:20px;" cellspacing="0" cellpadding="0">
<%--              <tr>--%>
<%--                <td align="right">--%>
<%--                	该订单完成后，您将获得 <font color="#ff4e00">1815</font> 积分 ，以及价值 <font color="#ff4e00">￥0.00</font> 的红包 <br />--%>
<%--                    商品总价: <font color="#ff4e00">￥1815.00</font>  + 配送费用: <font color="#ff4e00">￥15.00</font>--%>
<%--                </td>--%>
<%--              </tr>--%>
              <tr height="70">
                <td align="right">
                	<b style="font-size:14px;">应付款金额：<span style="font-size:22px; color:#ff4e00;">￥${cart.totalPrice}</span></b>
                </td>
              </tr>
              <tr height="70">
                <td align="right"><a href="<%=path%>/pay"><img src="images/btn_sure.gif" /></a></td>
              </tr>
            </table>

            
        	
        </div>
    </div>
	<!--End 第二步：确认订单信息 End-->
    
    
    <!--Begin Footer Begin -->
    <div class="b_btm_bg bg_color">
        <div class="b_btm">
            <table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr>
                <td width="72"><img src="images/b1.png" width="62" height="62" /></td>
                <td><h2>正品保障</h2>正品行货  放心购买</td>
              </tr>
            </table>
			<table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr>
                <td width="72"><img src="images/b2.png" width="62" height="62" /></td>
                <td><h2>满38包邮</h2>满38包邮 免运费</td>
              </tr>
            </table>
            <table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr>
                <td width="72"><img src="images/b3.png" width="62" height="62" /></td>
                <td><h2>天天低价</h2>天天低价 畅选无忧</td>
              </tr>
            </table>
            <table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr>
                <td width="72"><img src="images/b4.png" width="62" height="62" /></td>
                <td><h2>准时送达</h2>收货时间由你做主</td>
              </tr>
            </table>
        </div>
    </div>
    <div class="b_nav">
    	<dl>                                                                                            
        	<dt><a href="#">新手上路</a></dt>
            <dd><a href="#">售后流程</a></dd>
            <dd><a href="#">购物流程</a></dd>
            <dd><a href="#">订购方式</a></dd>
            <dd><a href="#">隐私声明</a></dd>
            <dd><a href="#">推荐分享说明</a></dd>
        </dl>
        <dl>
        	<dt><a href="#">配送与支付</a></dt>
            <dd><a href="#">货到付款区域</a></dd>
            <dd><a href="#">配送支付查询</a></dd>
            <dd><a href="#">支付方式说明</a></dd>
        </dl>
        <dl>
        	<dt><a href="#">会员中心</a></dt>
            <dd><a href="#">资金管理</a></dd>
            <dd><a href="#">我的收藏</a></dd>
            <dd><a href="#">我的订单</a></dd>
        </dl>
        <dl>
        	<dt><a href="#">服务保证</a></dt>
            <dd><a href="#">退换货原则</a></dd>
            <dd><a href="#">售后服务保证</a></dd>
            <dd><a href="#">产品质量保证</a></dd>
        </dl>
        <dl>
        	<dt><a href="#">联系我们</a></dt>
            <dd><a href="#">网站故障报告</a></dd>
            <dd><a href="#">购物咨询</a></dd>
            <dd><a href="#">投诉与建议</a></dd>
        </dl>
        <div class="b_tel_bg">
        	<a href="#" class="b_sh1">新浪微博</a>            
        	<a href="#" class="b_sh2">腾讯微博</a>
            <p>
            服务热线：<br />
            <span>400-123-4567</span>
            </p>
        </div>
        <div class="b_er">
            <div class="b_er_c"><img src="images/er.gif" width="118" height="118" /></div>
            <img src="images/ss.png" />
        </div>
    </div>    
    <div class="btmbg">
		<div class="btm">
        	备案/许可证编号：蜀ICP备12009302号-1-www.dingguagua.com   Copyright © 2015-2018 尤洪商城网 All Rights Reserved. 复制必究 , Technical Support: Dgg Group <br />
            <img src="images/b_1.gif" width="98" height="33" /><img src="images/b_2.gif" width="98" height="33" /><img src="images/b_3.gif" width="98" height="33" /><img src="images/b_4.gif" width="98" height="33" /><img src="images/b_5.gif" width="98" height="33" /><img src="images/b_6.gif" width="98" height="33" />
        </div>    	
    </div>
    <!--End Footer End -->    
</div>

</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>
<script>
    $(".leftNav").css("display","none");
</script>