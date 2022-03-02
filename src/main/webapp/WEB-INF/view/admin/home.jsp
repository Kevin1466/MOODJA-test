<%@ page import="model.Administrator" %>
<%@ page import="model.Manager" %>
<%@ page import="model.Employee" %><%--
  Created by IntelliJ IDEA.
  User: eric_wang
  Date: 25.01.22
  Time: 5:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MOODJA Internal System</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/css/bootstrap.min.css">
</head>
<body>
<%@ include file="../header.jsp"%>
<%@ include file="../sidebar.jsp"%>
<h1>Welcome ${sessionScope.admin.username}</h1>
</body>
</html>
