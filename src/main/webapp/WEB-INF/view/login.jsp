<%--
  Created by IntelliJ IDEA.
  User: eric_wang
  Date: 02.02.22
  Time: 12:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MOODJA Internal System</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css">

    <!-- jQuery file, must be imported before bootstrap.min.js -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <!-- Newest bootstrap core JavaScript file -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/js/bootstrap.min.js"></script>

    <!-- Bootstrap Validator JS file -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>

    <script type="text/javascript" src="../login.js"></script>
    <style>

    </style>
</head>

<body>
<div class="all-classes-container">
    <h2>MOODJA Internal System</h2>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form:form action="login" method="post" role="form" id="login_form">
        <div class="form_group">
            <div class="form-group">

                <%--
                <label for="userType">Login as: </label>
                <select class="form-control" id="userType" name="userType" onchange=refreshLabel()>
                    <option value="admin">System Administrator</option>
                    <option value="manager">Manager</option>
                    <option value="employee">Employee</option>
                </select>
                --%>
            </div>
            <label for="username" id="username_label">Username:  </label>
            <input type="text" class="form-control" id="username" name="username" placeholder="Enter username: ">
        </div>
        <div class="form-group">
            <label for="password">Password: </label>
            <input type="text" class="form-control" id="password" name="password" placeholder="Enter Password: ">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success">Login</button>
        </div>
    </form:form>
</div>
</body>
</html>