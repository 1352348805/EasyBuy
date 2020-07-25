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
        
	<script type="text/javascript" src="<%=path%>/js/select.js"></script>
    <script type="text/javascript" src="<%=path%>/js/shade.js"></script>
    
<title>尤洪</title>
</head>
<body>  
<!--Begin Header Begin-->
<jsp:include page="../../common/top_nav.jsp" />
<div id="fade1" class="black_overlay" style="display: none; height: 1026px;"></div>
<div id="MyDiv1" class="white_content" style="display: none;">
    <div class="white_d">
        <div class="notice_t">
            <span class="fr" style="margin-top:10px; cursor:pointer;" onclick="CloseDiv_1('MyDiv1','fade1')"><img src="<%=path%>/images/close.gif"></span>
        </div>
        <div class="notice_c">
            <table border="0" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr valign="top">
                    <td width="40"><img src="<%=path%>/images/suc.png"></td>
                    <td>
                        <span style="color:#3e3e3e; font-size:18px; font-weight:bold;" id="showMessage">..</span><br>
                    </td>
                </tr>
                </tbody></table>
        </div>
    </div>
</div>
<!--End Header End--> 
<div class="i_bg bg_color">
    <!--Begin 分类中心 Begin -->
    <div class="m_content">



        <jsp:include page="../../common/left_nav.jsp" />
        <div class="m_right" id="content">
            <div class="mem_tit">分类列表</div>
            <p align="right">
                <a href="javascript:void(0);" onclick="toAddProductCategory();" class="add_b">添加分类</a>
                <br>
            </p>
            <br>
            <table border="0" class="order_tab" style="width:930px; text-align:center; margin-bottom:30px;" cellspacing="0" cellpadding="0">
                <tbody>
                <tr>
                    <td width="5%">选择</td>
                    <td width="20%">分类名称</td>
                    <td width="25%">分类级别</td>
                    <td width="25%">父级分类</td>
                    <td width="25%">操作</td>
                </tr>

                <c:forEach var="category" items="${page.data}">
                    <tr>
                        <td width="5%"><input type="radio" value="${category.id}" name="select" onclick="toUpdateProductCategoryList(this);"></td>
                        <td>${category.name}</td>
                        <td>
                            <c:choose>
                                <c:when test="${category.type==1}">
                                    一级分类
                                </c:when>
                                <c:when test="${category.type==2}">
                                    二级分类
                                </c:when>
                                <c:otherwise>
                                    三级分类
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${category.type == 1}">
                                    无
                                </c:when>
                                <c:otherwise>
                                    ${category.parents[0].name}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><a href="javascript:void(0);" onclick="deleteProductCategory(${category.id},1);">删除</a></td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>


            <div class="pages">

                <a href="<%=path%>/admin/productCategory?action=index&index=1" class="p_pre">首页</a>

                <c:if test="${page.currPageNo > 1}">
                    <a href="<%=path%>/admin/productCategory?action=index&index=${page.currPageNo-1}" class="p_pre">上一页</a>
                </c:if>

                <c:forEach var="i" begin="${page.currPageNo-3<0?0:page.currPageNo-3}" end="${page.currPageNo-1}">
                    <c:if test="${i > 0 && i<page.totalPageCount}">
                        <a href="<%=path%>/admin/productCategory?action=index&index=${i}">${i}</a>
                    </c:if>
                </c:forEach>
                <a href="<%=path%>/admin/productCategory?action=index&index=${page.currPageNo}" class="cur">${page.currPageNo}</a>

                <c:forEach var="i" begin="${page.currPageNo+1}" end="${page.currPageNo+3}">
                    <c:if test="${i <= page.totalPageCount}">
                        <a href="<%=path%>/admin/productCategory?action=index&index=${i}">${i}</a>
                    </c:if>
                </c:forEach>


                <c:if test="${page.currPageNo < page.totalPageCount}">
                    <a href="<%=path%>/admin/productCategory?action=index&index=${page.currPageNo+1}" class="p_pre">下一页</a>
                </c:if>

                <a href="<%=path%>/admin/productCategory?action=index&index=${page.totalPageCount}" class="p_pre">尾页</a>


            </div>

            <div id="addProductCategory">

            </div>
        </div>
    </div>
	<!--End 分类中心 End-->
    <!--Begin Footer Begin -->
    <div class="b_btm_bg b_btm_c">
        <div class="b_btm">
            <table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr>
                <td width="72"><img src="<%=path%>/images/b1.png" width="62" height="62" /></td>
                <td><h2>正品保障</h2>正品行货  放心购买</td>
              </tr>
            </table>
			<table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr>
                <td width="72"><img src="<%=path%>/images/b2.png" width="62" height="62" /></td>
                <td><h2>满38包邮</h2>满38包邮 免运费</td>
              </tr>
            </table>
            <table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr>
                <td width="72"><img src="<%=path%>/images/b3.png" width="62" height="62" /></td>
                <td><h2>天天低价</h2>天天低价 畅选无忧</td>
              </tr>
            </table>
            <table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr>
                <td width="72"><img src="<%=path%>/images/b4.png" width="62" height="62" /></td>
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
            <div class="b_er_c"><img src="<%=path%>/images/er.gif" width="118" height="118" /></div>
            <img src="<%=path%>/images/ss.png" />
        </div>
    </div>    
    <div class="btmbg">
		<div class="btm">
        	备案/许可证编号：蜀ICP备12009302号-1-www.dingguagua.com   Copyright © 2015-2018 尤洪商城网 All Rights Reserved. 复制必究 , Technical Support: Dgg Group <br />
            <img src="<%=path%>/images/b_1.gif" width="98" height="33" /><img src="<%=path%>/images/b_2.gif" width="98" height="33" /><img src="<%=path%>/images/b_3.gif" width="98" height="33" /><img src="<%=path%>/images/b_4.gif" width="98" height="33" /><img src="<%=path%>/images/b_5.gif" width="98" height="33" /><img src="<%=path%>/images/b_6.gif" width="98" height="33" />
        </div>    	
    </div>
    <!--End Footer End -->    
</div>
<input type="hidden" id="path" value="<%=path%>" />

</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>
<script src="<%=path%>/js/jquery-1.8.2.min.js"></script>
<script src="<%=path%>/js/product/product_category.js"></script>
