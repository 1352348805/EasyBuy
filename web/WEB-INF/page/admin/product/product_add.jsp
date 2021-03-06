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

    <script>

        function queryProductCategoryList(obj,selectId) {
            var parentid = $(obj).val();  //获取用户选中下拉框的id值
            $.ajax({
                url : "<%=path%>/admin/product" ,
                type : "get",
                data: {
                    action : "queryProductCategoryList",
                    parentId : parentid
                },
                dataType : "json",
                success : function (jsonStr) {
                    var result = eval(jsonStr);
                    if(result.code==200){
                        var options = "<option value='0'>"+"请选择..."+"</option>";
                        for(var i = 0 ; i<result.data.length; i++){
                            var option = "<option value="+result.data[i].id+">"+result.data[i].name+"</option>";
                            options=options+option;
                        }
                        $("#"+selectId).html(options);
                    }

                }
            });
        }

        function checkProduct() {
            var productCategoryLevelone = $("#productCategoryLevel1").val();
            var productCategoryLeveltwo = $("#productCategoryLevel2").val();
            var name = $("#name").val();
            var filename = $("#fileid").val();
            var price = $("#price").val();
            var stock = $("#stock").val();
            var description = $("#descriptionid").val();
            prices = /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/;
            numer = /^[1-9]\d{0,5}$/; // 判断6位整正数
            if(productCategoryLevelone == 0){
                alert("请选择商品一级分类");
                return false;
            }
            if(productCategoryLeveltwo == 0){
                alert("请选择商品二级分类");
                return false;
            }

            if(name == null || name == ""){
                alert("商品名称不能为空");
                return false;
            }
            if(name.length < 2 || name.length >9){
                alert("商品名称为2到9个字符");
                return false;
            }
            if(filename == null || filename == ""){
                alert("商品图片不能为空");
                return false;
            }
            if(price == null || price == ""){
                alert("商品单价不能为空");
                return false;
            }
            if(prices.test(price) == false){
                alert("请输入合法的价格");
                return false;
            }
            if(stock == null || stock == ""){
                alert("商品库存不能为空");
                return false;
            }
            if(numer.test(stock) == false){
                alert("请输入合法的商品库存量");
                return false;
            }
            return  true;
        }



    </script>


    <title>尤洪</title>
</head>
<body>
<!--Begin Header Begin-->
<jsp:include page="../../common/top_nav.jsp" />
<!--End Header End-->
<div class="i_bg bg_color">
    <!--Begin 分类中心 Begin -->
    <div class="m_content">



        <jsp:include page="../../common/left_nav.jsp" />
        <div class="m_right" id="content">
            <div class="mem_tit">


                修改商品



            </div>

            <br>
            <form action="<%=path%>/admin/product?action=productAdd" method="post" enctype="multipart/form-data" id="productadd" onsubmit="return checkProduct();" >
                <table border="0" class="add_tab" style="width:930px;" cellspacing="0" cellpadding="0">
                    <tbody><tr>
                        <td width="135" align="right">一级分类</td>
                        <td colspan="3" style="font-family:'宋体';">
                            <select name="categoryLevel1Id" style="background-color:#f6f6f6;" id="productCategoryLevel1" onchange="queryProductCategoryList(this,'productCategoryLevel2');">
                                <option value="0" selected="selected">请选择...</option>
                                <c:forEach items="${oneTypeadd}" var="onetype" >
                                   <option value="${onetype.id}">${onetype.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td width="135" align="right">二级分类</td>
                        <td>
                            <select name="categoryLevel2Id" style="background-color:#f6f6f6;" id="productCategoryLevel2" onchange="queryProductCategoryList(this,'productCategoryLevel3');">
                                <option value="0" selected="selected">请选择...</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td width="135" align="right">三级分类</td>
                        <td>
                            <select name="categoryLevel3Id" style="background-color:#f6f6f6;" id="productCategoryLevel3">
                                <option value="0" selected="selected">请选择...</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td width="135" align="right">商品名称</td>
                        <td>
                            <input type="text" value="" class="add_ipt" name="name" id="name">（必填）
                            <input type="hidden" name="id" value="" id="id">
                        </td>
                    </tr>
                    <tr>
                        <td width="135" align="right">商品图片</td>
                        <td>
                            <input type="file" class="text" name="photo" id="fileid"><span></span>
                        </td>
                    </tr>
                    <tr>
                        <td width="135" align="right">单价</td>
                        <td>
                            <input type="text" value="" class="add_ipt" name="price" id="price">
                        </td>
                    </tr>
                    <tr>
                        <td width="135" align="right">库存</td>
                        <td>
                            <input type="text" value="" class="add_ipt" name="stock" id="stock">
                        </td>
                    </tr>
                    <tr>
                        <td width="135" align="right">描述</td>
                        <td>
                            <textarea name="description" id="descriptionid"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="submit" value="添加商品" class="s_btn" id="btadd">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
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

</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>

