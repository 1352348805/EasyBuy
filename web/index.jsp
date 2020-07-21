<%@ page import="com.alimama.easybuy.util.DatabaseUtil" %>
<%@ page import="java.sql.Connection" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/7/20
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Connection connection = DatabaseUtil.getConnection();
    out.print(connection);
%>
</body>
</html>
