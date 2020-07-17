<%@ page import="com.alimama.easybuy.util.DatabaseUtil" %>
<%@ page import="com.alibaba.druid.pool.DruidPooledConnection" %>
<%@ page import="java.sql.Connection" %><%--
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
    Connection con = null;
    out.print(con = DatabaseUtil.getConnection());
    con.close();
%>
</body>
</html>
