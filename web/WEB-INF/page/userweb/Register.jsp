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
    <script type="text/javascript" src="<%=path%>/js/user/register.js"></script>
	<script type="text/javascript" src="<%=path%>/js/lrscroll_1.js"></script>
    
    
<title>尤洪</title>
</head>
<body>  
<!--Begin Header Begin-->
<div class="soubg">
	<div class="sou">
        <span class="fr">
        	<span class="fl">你好，请<a href="<%=path%>/Login">登录</a>&nbsp; <a href="<%=path%>/Register" style="color:#ff4e00;">免费注册</a>&nbsp; </span>
            <span class="fl">|&nbsp;关注我们：</span>
            <span class="s_sh"><a href="#" class="sh1">新浪</a><a href="#" class="sh2">微信</a></span>
            <span class="fr">|&nbsp;<a href="#">手机版&nbsp;<img src="<%=path%>/images/s_tel.png" align="absmiddle" /></a></span>
        </span>
    </div>
</div>
<!--End Header End-->
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
<!--Begin Login Begin-->
<input id="path" type="hidden" value="<%=path%>" />
<div class="log_bg">	
    <div class="top">
        <div class="logo"><a href="Index.html"><img src="<%=path%>/images/logo.png" /></a></div>
    </div>
	<div class="regist">
    	<div class="log_img"><img src="<%=path%>/images/l_img.png" width="611" height="425" /></div>
		<div class="reg_c">
        	<form>
            <table border="0" style="width:420px; font-size:14px; margin-top:20px;" cellspacing="0" cellpadding="0">
              <tr height="50" valign="top">
              	<td width="95">&nbsp;</td>
                <td>
                	<span class="fl" style="font-size:24px;">注册</span>
                    <span class="fr">已有商城账号，<a href="Login.jsp" style="color:#ff4e00;">我要登录</a></span>
                </td>
              </tr>
              <tr height="50">
                <td align="right"><font color="#ff4e00">*</font>&nbsp;用户名 &nbsp;</td>
                <td><input type="text" name="loginName" value="" class="l_user" /></td>
              </tr>
              <tr height="50">
                <td align="right"><font color="#ff4e00">*</font>&nbsp;密码 &nbsp;</td>
                <td><input type="password" name="password" value="" class="l_pwd" /></td>
              </tr>
              <tr height="50">
                <td align="right"><font color="#ff4e00">*</font>&nbsp;确认密码 &nbsp;</td>
                <td><input type="password" name="confirmPassword" value="" class="l_pwd" /></td>
              </tr>
                <tr height="50">
                    <td align="right"><font color="#ff4e00">*</font>&nbsp;真实姓名 &nbsp;</td>
                    <td><input type="text" name="userName" value="" class="l_user" /></td>
                </tr>
                <tr height="50">
                    <td align="right"><font color="#ff4e00">*</font>&nbsp;性别 &nbsp;</td>
                    <td>
                        <input type="radio" name="sex" value="1" checked/>男
                        <input type="radio" name="sex" value="0" />女
                    </td>
                </tr>
                <tr height="50">
                    <td align="right">&nbsp;身份证号 &nbsp;</td>
                    <td><input type="text" name="identityCode" value="" class="l_user" /></td>
                </tr>
              <tr height="50">
                <td align="right">&nbsp;邮箱 &nbsp;</td>
                <td><input type="text" name="email" value="" class="l_email" /></td>
              </tr>
              <tr height="50">
                <td align="right">&nbsp;手机 &nbsp;</td>
                <td><input type="text" name="mobile" value="" class="l_tel" /></td>
              </tr>
              <tr>
              	<td>&nbsp;</td>
                <td style="font-size:12px; padding-top:20px;">
                	<span style="font-family:'宋体';" class="fl">
                    	<label class="r_rad"><input type="checkbox" /></label><label class="r_txt">我已阅读并接受《用户协议》</label>
                    </span>
                </td>
              </tr>
              <tr height="60">
              	<td>&nbsp;</td>
                <td><input id="register-btn" type="button" value="立即注册" class="log_btn" /></td>
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
<script>
    $(function () {
        $("#register-btn").click(register);
    });
</script>

<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>
