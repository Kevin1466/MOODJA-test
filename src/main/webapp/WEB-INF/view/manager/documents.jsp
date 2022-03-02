<%--
  Created by IntelliJ IDEA.
  User: eric_wang
  Date: 01.02.22
  Time: 4:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<div class="col-md-9">
    <c:if test="${empty documents}"><h1>No un-viewed documents</h1></c:if>
    <c:if test="${not empty documents}">
        <table class="table table-condensed">
            <thead>
            <tr>
                <td>Document</td>
                <td>Title</td>
                <td>Submit Date</td>
                <td>Employee</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${documents}" var="document">
                <tr class="active">
                    <td>${document.id}</td>
                    <td>${document.title}</td>
                    <td>${document.submitDateString()}</td>
                    <td>${document.employee.firstName} ${document.employee.lastName}</td>
                    <td>
                        <a role="button" class="btn btn-primary btn-lg btn-block" href="${pageContext.request.contextPath}/manager/documents/detail?id=${document.id}">View</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/js/bootstrap.min.js"></script>
</body>
</html>
