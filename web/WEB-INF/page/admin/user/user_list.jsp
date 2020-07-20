<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        
    
<title>尤洪</title>
</head>
<body>  
<!--Begin Header Begin-->
<jsp:include page="../../common/top_nav.jsp" />
<!--End Header End--> 
<div class="i_bg bg_color">
    <!--Begin 用户中心 Begin -->
    <div class="m_content">



        <jsp:include page="../../common/left_nav.jsp" />
        <div class="m_right" id="content">
            <div class="mem_tit">用户列表</div>
            <p align="right">
                <a href="/EasyBuy_war/admin/user?action=toAddUser" class="add_b">添加用户</a>
                <br>
            </p>
            <br>
            <table border="0" class="order_tab" style="width:930px; text-align:center; margin-bottom:30px;" cellspacing="0" cellpadding="0">
                <tbody>
                <tr>
                    <td width="10%">用户名称</td>
                    <td width="10%">真实姓名</td>
                    <td width="5%">性别</td>
                    <td width="5%">类型</td>
                    <td width="5%" colspan="2">操作</td>
                </tr>

                <tr>
                    <td>admin</td>
                    <td>系统管理员</td>
                    <td>


                        男



                    </td>
                    <td>


                        管理员



                    </td>
                    <td>
                        <a href="/EasyBuy_war/admin/user?action=toUpdateUser&amp;id=2">修改</a>
                    </td>
                    <td>

                    </td>
                </tr>

                <tr>
                    <td>cgn</td>
                    <td>程广宁</td>
                    <td>


                        男



                    </td>
                    <td>



                        用户


                    </td>
                    <td>
                        <a href="/EasyBuy_war/admin/user?action=toUpdateUser&amp;id=10">修改</a>
                    </td>
                    <td>

                        <a href="javascript:void(0);" onclick="deleteUserId('10');" target="_blank">删除</a>

                    </td>
                </tr>

                <tr>
                    <td>hyl</td>
                    <td>韩语良</td>
                    <td>


                        男



                    </td>
                    <td>



                        用户


                    </td>
                    <td>
                        <a href="/EasyBuy_war/admin/user?action=toUpdateUser&amp;id=11">修改</a>
                    </td>
                    <td>

                        <a href="javascript:void(0);" onclick="deleteUserId('11');" target="_blank">删除</a>

                    </td>
                </tr>

                <tr>
                    <td>ck</td>
                    <td>陈康</td>
                    <td>


                        男



                    </td>
                    <td>



                        用户


                    </td>
                    <td>
                        <a href="/EasyBuy_war/admin/user?action=toUpdateUser&amp;id=12">修改</a>
                    </td>
                    <td>

                        <a href="javascript:void(0);" onclick="deleteUserId('12');" target="_blank">删除</a>

                    </td>
                </tr>

                <tr>
                    <td>kys</td>
                    <td>康有沈</td>
                    <td>


                        男



                    </td>
                    <td>



                        用户


                    </td>
                    <td>
                        <a href="/EasyBuy_war/admin/user?action=toUpdateUser&amp;id=13">修改</a>
                    </td>
                    <td>

                        <a href="javascript:void(0);" onclick="deleteUserId('13');" target="_blank">删除</a>

                    </td>
                </tr>

                <tr>
                    <td>sb</td>
                    <td>沈白</td>
                    <td>


                        男



                    </td>
                    <td>



                        用户


                    </td>
                    <td>
                        <a href="/EasyBuy_war/admin/user?action=toUpdateUser&amp;id=14">修改</a>
                    </td>
                    <td>

                        <a href="javascript:void(0);" onclick="deleteUserId('14');" target="_blank">删除</a>

                    </td>
                </tr>

                <tr>
                    <td>lb</td>
                    <td>李白</td>
                    <td>


                        男



                    </td>
                    <td>



                        用户


                    </td>
                    <td>
                        <a href="/EasyBuy_war/admin/user?action=toUpdateUser&amp;id=15">修改</a>
                    </td>
                    <td>

                        <a href="javascript:void(0);" onclick="deleteUserId('15');" target="_blank">删除</a>

                    </td>
                </tr>

                <tr>
                    <td>lgw</td>
                    <td>李高伟</td>
                    <td>


                        男



                    </td>
                    <td>



                        用户


                    </td>
                    <td>
                        <a href="/EasyBuy_war/admin/user?action=toUpdateUser&amp;id=16">修改</a>
                    </td>
                    <td>

                        <a href="javascript:void(0);" onclick="deleteUserId('16');" target="_blank">删除</a>

                    </td>
                </tr>

                <tr>
                    <td>shangzezhong</td>
                    <td>尚泽忠</td>
                    <td>


                        男



                    </td>
                    <td>



                        用户


                    </td>
                    <td>
                        <a href="/EasyBuy_war/admin/user?action=toUpdateUser&amp;id=18">修改</a>
                    </td>
                    <td>

                        <a href="javascript:void(0);" onclick="deleteUserId('18');" target="_blank">删除</a>

                    </td>
                </tr>

                <tr>
                    <td>liguangliang</td>
                    <td>李光亮</td>
                    <td>


                        男



                    </td>
                    <td>



                        用户


                    </td>
                    <td>
                        <a href="/EasyBuy_war/admin/user?action=toUpdateUser&amp;id=19">修改</a>
                    </td>
                    <td>

                        <a href="javascript:void(0);" onclick="deleteUserId('19');" target="_blank">删除</a>

                    </td>
                </tr>

                </tbody>
            </table>



            <script type="text/javascript">
                var contextPath = "/EasyBuy_war";
            </script>
            <div class="pages">

                <a href="/EasyBuy_war//admin/user?action=queryUserList&amp;currentPage=1" class="p_pre">首页</a>



                <a href="/EasyBuy_war//admin/user?action=queryUserList&amp;currentPage=1" class="cur">1</a>





                <a href="/EasyBuy_war//admin/user?action=queryUserList&amp;currentPage=2">2</a>



                <a href="/EasyBuy_war//admin/user?action=queryUserList&amp;currentPage=2" class="p_pre">下一页</a>

                <a href="/EasyBuy_war//admin/user?action=queryUserList&amp;currentPage=2" class="p_pre">尾页</a>


            </div>

        </div>
    </div>
	<!--End 用户中心 End-->
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

</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>
