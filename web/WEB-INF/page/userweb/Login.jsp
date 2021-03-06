<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css" />
    <!--[if IE 6]>
    <script src="<%=path%>/js/iepng.js" type="text/javascript"></script>
        <script type="text/javascript">
           EvPNG.fix('div, ul, img, li, input, a'); 
        </script>
    <![endif]-->
    <script type="text/javascript" src="<%=path%>/js/jquery-1.11.1.min_044d0927.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.bxslider_e88acd1b.js"></script>
    
    <script type="text/javascript" src="<%=path%>/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/menu.js"></script>
        
	<script type="text/javascript" src="<%=path%>/js/select.js"></script>
    
	<script type="text/javascript" src="<%=path%>/js/lrscroll.js"></script>
    
    <script type="text/javascript" src="<%=path%>/js/iban.js"></script>
    <script type="text/javascript" src="<%=path%>/js/fban.js"></script>
    <script type="text/javascript" src="<%=path%>/js/f_ban.js"></script>
    <script type="text/javascript" src="<%=path%>/js/mban.js"></script>
    <script type="text/javascript" src="<%=path%>/js/bban.js"></script>
    <script type="text/javascript" src="<%=path%>/js/hban.js"></script>
    <script type="text/javascript" src="<%=path%>/js/tban.js"></script>
    <script type="text/javascript" src="<%=path%>/js/shade.js"></script>


    <script type="text/javascript" src="<%=path%>/js/lrscroll_1.js"></script>
    
    
<title>易买网</title>
</head>
<body>  
<!--Begin Header Begin-->
<div class="soubg">
	<div class="sou">
        <span class="fr">
        	<span class="fl">你好，请<a href="Login.jsp">登录</a>&nbsp; <a href="Register.jsp" style="color:#ff4e00;">免费注册</a>&nbsp; </span>
            <span class="fl">|&nbsp;关注我们：</span>
            <span class="s_sh"><a href="#" class="sh1">新浪</a><a href="#" class="sh2">微信</a></span>
            <span class="fr">|&nbsp;<a href="#">手机版&nbsp;<img src="images/s_tel.png" align="absmiddle" /></a></span>
        </span>
    </div>
</div>
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
                        <span style="color:#3e3e3e; font-size:18px; font-weight:bold;" id="showMessage">用户名不能为空.</span><br>
                    </td>
                </tr>
                </tbody></table>
        </div>
    </div>
</div>
<!--End Header End--> 
<!--Begin Login Begin-->
<input id="path" type="hidden" value="<%=path%>" />
<div class="log_bg">	
    <div class="top">
        <div class="logo"><a href="Index.html"><img src="images/logo.png" /></a></div>
    </div>
	<div class="login">
    	<div class="log_img"><img src="images/l_img.png" width="611" height="425" /></div>
		<div class="log_c">
        	<form>
            <table border="0" style="width:370px; font-size:14px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr height="50" valign="top">
              	<td width="55">&nbsp;</td>
                <td>
                	<span class="fl" style="font-size:24px;">登录</span>
                    <span class="fr">还没有商城账号，<a href="<%=path%>/Register" style="color:#ff4e00;">立即注册</a></span>
                </td>
              </tr>
              <tr height="70">
                <td>用户名</td>
                <td><input type="text" value="" class="l_user"  id="loginName" name="loginName"/></td>
              </tr>
              <tr height="70">
                <td>密&nbsp; &nbsp; 码</td>
                <td><input type="password" value="" class="l_pwd"  id="password" name="password"/></td>
              </tr>
              <tr>
              	<td>&nbsp;</td>
                <td style="font-size:12px; padding-top:20px;">
                	<span style="font-family:'宋体';" class="fl">
                    	<label class="r_rad"><input type="checkbox" /></label><label class="r_txt">请保存我这次的登录信息</label>
                    </span>
                    <span class="fr"><a href="#" style="color:#ff4e00;">忘记密码</a></span>
                </td>
              </tr>
              <tr height="60">
              	<td>&nbsp;</td>
                <td><input id="login-btn" type="button" value="登录" class="log_btn" /></td>
              </tr>
            </table>
            </form>

        </div>
    </div>
</div>
<!--End Login End--> 
<!--Begin Footer Begin-->
<jsp:include page="../common/footer_file.jsp"/>
<!--End Footer End -->    

</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>

<script src="<%=path%>/js/user/login.js"></script>
<script>
    $(function () {
        $("#login-btn").click(login);
    });
</script>