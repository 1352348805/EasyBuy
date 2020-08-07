<%--
  User: asuk
  Date: 2020/7/19
  Time: 13:46
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<div class="m_left">
    <div class="left_n">管理中心</div>
    <div class="left_m">
        <div class="left_m_t t_bg1">订单中心</div>
        <ul>
            <li><a href="<%=path%>/admin/order?action=index">我的订单</a></li>
            <li><a href="<%=path%>/admin/order?action=queryAllOrder">全部订单</a></li>
            <%--                    <li><a href="Member_Address.html">收货地址</a></li>--%>
            <%--                    <li><a href="#">缺货登记</a></li>--%>
            <%--                    <li><a href="#">跟踪订单</a></li>--%>
        </ul>
    </div>
    <div class="left_m">
        <div class="left_m_t t_bg2">会员中心</div>
        <ul>
            <li><a href="<%=path%>/admin/user?action=index">用户信息</a></li>
            <li><a href="<%=path%>/admin/user?action=queryUserList">用户列表</a></li>
            <%--                    <li><a href="Member_Collect.html">我的收藏</a></li>--%>
            <%--                    <li><a href="Member_Msg.html">我的留言</a></li>--%>
            <%--                    <li><a href="Member_Links.html">推广链接</a></li>--%>
            <%--                    <li><a href="#">我的评论</a></li>--%>
        </ul>
    </div>
    <div class="left_m">
        <div class="left_m_t t_bg1">商品管理</div>
        <ul>
            <li><a href="<%=path%>/admin/productCategory?action=index">分类管理</a></li>
            <li><a href="<%=path%>/admin/product?action=index">商品管理</a></li>
            <li><a href="<%=path%>/admin/product?action=productAdd">商品上架</a></li>
        </ul>
    </div>
    <div class="left_m">
        <div class="left_m_t t_bg1">资讯中心</div>
        <ul>
            <li><a href="<%=path%>/admin/news?action=queryNewsList">资讯列表</a></li>
        </ul>
    </div>
    <%--            <div class="left_m">--%>
    <%--            	<div class="left_m_t t_bg3">账户中心</div>--%>
    <%--                <ul>--%>
    <%--                	<li><a href="Member_Safe.html">账户安全</a></li>--%>
    <%--                    <li><a href="Member_Packet.html">我的红包</a></li>--%>
    <%--                    <li><a href="Member_Money.html">资金管理</a></li>--%>
    <%--                </ul>--%>
    <%--            </div>--%>
    <%--            <div class="left_m">--%>
    <%--            	<div class="left_m_t t_bg4">分销中心</div>--%>
    <%--                <ul>--%>
    <%--                	<li><a href="Member_Member.html">我的会员</a></li>--%>
    <%--                    <li><a href="Member_Results.html">我的业绩</a></li>--%>
    <%--                    <li><a href="Member_Commission.html">我的佣金</a></li>--%>
    <%--                    <li><a href="Member_Cash.html">申请提现</a></li>--%>
    <%--                </ul>--%>
    <%--            </div>--%>
</div>
<script src="<%=path%>/js/jquery-1.8.2.min.js"></script>
<script>
    $(function () {
        $(".left_m a").click(function (obj) {
            obj.addClass("now");
        });
    });
</script>
