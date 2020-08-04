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
    	<img src="images/img1.jpg" />        
    </div>
    
    <!--Begin 第一步：查看购物车 Begin -->
    <div class="content mar_20">
    	<table border="0" class="car_tab" style="width:1200px; margin-bottom:50px;" cellspacing="0" cellpadding="0">
            <tr>
                <td class="car_th" width="200">商品名称</td>
                <td class="car_th" width="150">单价</td>
                <td class="car_th" width="150">购买数量</td>
                <td class="car_th" width="130">小计</td>
                <td class="car_th" width="150">操作</td>
            </tr>

           <c:forEach var="cartItem" items="${cart.cartItems}">
               <tr>
                   <td>
                       <div class="c_s_img">
                           <a href="/EasyBuy_war/Product?action=queryProductDeatil&amp;id=734"><img src="<%=path%>/images/${cartItem.fileName}" width="73" height="73"></a>
                       </div>
                       ${cartItem.name}
                   </td>
                   <td align="center" style="color:#ff4e00;">￥${cartItem.price}</td>
                   <td align="center">
                       <div class="c_num">
                           <input type="button" value="" onclick="jianUpdate1(jq(this));" class="car_btn_1" />
                           <input type="text" value="${cartItem.count}" name="" class="car_ipt" />
                           <input type="hidden" value="${cartItem.pid}" class="car_pid" />
                           <input type="button" value="" onclick="addUpdate1(jq(this));" class="car_btn_2" />
                       </div>
                   </td>
                   <td align="center" class="car_total_price" style="color:#ff4e00;">￥${cartItem.totalPrice}</td>
                   <td align="center"><a onclick="ShowDiv('MyDiv','fade')">删除</a>&nbsp; &nbsp;</td>
               </tr>
           </c:forEach>


          <tr height="70">
          	<td colspan="6" style="font-family:'Microsoft YaHei'; border-bottom:0;">
            	<label class="r_rad"><input type="checkbox" name="clear" checked="checked" /></label><label class="r_txt">清空购物车</label>
                <span class="fr">商品总价：<b style="font-size:22px; color:#ff4e00;" id="totalPrice">￥${cart.totalPrice}</b></span>
            </td>
          </tr>
          <tr valign="top" height="150">
          	<td colspan="6" align="right">
            	<a href="#"><img src="<%=path%>/images/buy1.gif" /></a>&nbsp; &nbsp; <a href="<%=path%>/Cart?action=toSettlement2"><img src="images/buy2.gif" /></a>
            </td>
          </tr>
        </table>
        
    </div>
	<!--End 第一步：查看购物车 End--> 
    
    
    <!--Begin 弹出层-删除商品 Begin-->
    <div id="fade" class="black_overlay"></div>
    <div id="MyDiv" class="white_content">             
        <div class="white_d">
            <div class="notice_t">
                <span class="fr" style="margin-top:10px; cursor:pointer;" onclick="CloseDiv('MyDiv','fade')"><img src="images/close.gif" /></span>
            </div>
            <div class="notice_c">
           		
                <table border="0" align="center" style="font-size:16px;" cellspacing="0" cellpadding="0">
                  <tr valign="top">
                    <td>您确定要把该商品移除购物车吗？</td>
                  </tr>
                  <tr height="50" valign="bottom">
                    <td><a href="#" class="b_sure">确定</a><a href="#" class="b_buy">取消</a></td>
                  </tr>
                </table>
                    
            </div>
        </div>
    </div>    
    <!--End 弹出层-删除商品 End-->
    
    
    <!--Begin Footer Begin -->
    <jsp:include page="../../common/footer.jsp"/>
    <jsp:include page="../../common/footer_file.jsp"/>
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