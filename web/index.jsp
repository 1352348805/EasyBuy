<%@ page import="com.alimama.easybuy.util.DBPool" %>
<%@ page import="com.alibaba.druid.pool.DruidPooledConnection" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/7/16
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    DruidPooledConnection connection = DBPool.getConnection();
    out.print(connection);
%>
</body>
</html>
