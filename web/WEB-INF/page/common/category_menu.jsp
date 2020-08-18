<%--
  Created by IntelliJ IDEA.
  User: asuk
  Date: 2020/7/27
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<div class="menu_bg">
    <div class="menu">
        <!--Begin 商品分类详情 Begin-->
        <div class="nav">
            <div class="nav_t">全部商品分类</div>
            <div class="leftNav">
                <ul>
                    <c:forEach var="oneType" items="${menu}">
                        <li>
                            <div class="fj">
                                <span class="n_img"><span></span><img src="images/nav1.png" /></span>
                                <span class="fl">${oneType.name}</span>
                            </div>
                            <div class="zj">
                                <div class="zj_l">
                                    <c:forEach var="twoType" items="${oneType.subClass}">
                                        <div class="zj_l_c">
                                            <h2>${twoType.name}</h2>
                                            <c:forEach var="threeType" items="${twoType.subClass}">
                                                <a href="<%=path%>/Product?action=queryProductList&level=3&cid=${threeType.id}">${threeType.name}</a>|
                                            </c:forEach>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="zj_r">
                                    <a href="#"><img src="images/n_img1.jpg" width="236" height="200" /></a>
                                    <a href="#"><img src="images/n_img2.jpg" width="236" height="200" /></a>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <!--End 商品分类详情 End-->
        <ul class="menu_r">
            <li><a href="<%=path%>/Home?action=index">首页</a></li>
            <c:forEach var="oneType" items="${menu}">
                <li><a href="<%=path%>/Product?action=queryProductList&level=1&cid=${oneType.id}">${oneType.name}</a></li>
            </c:forEach>
        </ul>
        <div class="m_ad">中秋送好礼！</div>
    </div>
</div>
