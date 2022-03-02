<%--
  Created by IntelliJ IDEA.
  User: eric_wang
  Date: 25.01.22
  Time: 5:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE-edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Administrator Profile</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" media="all">
</head>
<body>
<%@ include file="../header.jsp"%>
<%@ include file="../sidebar.jsp"%>

<div class="col-md-9">
    <table class="table table-bordered">
        <caption style="align-content: center; text-align: center"><h2>Administrator Profile</h2></caption>
        <tbody>
        <tr>
            <td><strong>Account Type</strong></td>
            <td>System Administrator</td>
        </tr>
        <tr>
            <td><strong>Username</strong></td>
            <td>${sessionScope.admin.username}</td>
        </tr>
        </tbody>
    </table>
    <a role="button" class="btn btn-warning btn-lg btn-block" href="${pageContext.request.contextPath}/admin/resetPassword"><span class="glyphicon-lock"></span>Reset Password</a>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery.js"></script>
</body>
</html>
