<%--
  Created by IntelliJ IDEA.
  User: eric_wang
  Date: 01.02.22
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MOODJA Internal System</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<%@ include file="../header.jsp"%>
<%@ include file="../sidebar.jsp"%>

</body>

<div class="col-md-9">
    <table class="table table-bordered">
        <caption style="align-content: center; text-align: center"><h2>Profile</h2></caption>
        <tbody>
        <tr>
            <td><strong>Account Type</strong></td>
            <td>Manager</td>
        </tr>
        <tr>
            <td><strong>Manager</strong></td>
            <td>${sessionScope.manager.id}</td>
        </tr>
        <tr>
            <td><strong>Name</strong></td>
            <td>${sessionScope.manager.firstName} ${sessionScope.manager.lastName}</td>
        </tr>
        <tr>
            <td><strong>Mobile</strong></td>
            <td>${sessionScope.manager.mobile}</td>
        </tr>
        <tr>
            <td><strong>Email</strong></td>
            <td>${sessionScope.manager.email}</td>
        </tr>
        </tbody>
    </table>

    <div class="form-group">
        <a role="button" class="btn btn-primary" href="${pageContext.request.contextPath}/manager/account/edit"><span class="glyphicon glyphicon-pencil"></span>Edit</a>
        <a role="button" class="btn btn-warning" href="${pageContext.request.contextPath}/manager/account/resetPassword"><span class="glyphicon glyphicon-lock"></span>Reset Password</a>
    </div>
</div>
</html>
