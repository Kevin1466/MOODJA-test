<%--
  Created by IntelliJ IDEA.
  User: eric_wang
  Date: 01.02.22
  Time: 9:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <a role="button" class="btn btn-primary btn-lg btn-block" href="${pageContext.request.contextPath}/employee/documents/add"><span class="glyphicon glyphicon-pencil"></span>Create</a>
    <c:if test="${not empty sessionScope.employee.draft}">
        <a role="button" class="btn btn-success btn-lg btn-block" href="${pageContext.request.contextPath}/employee/documents/add?draftId=${sessionScope.employee.draft.id}"><span class="glyphicon glyphicon-floppy-open"></span>Open Draft</a>
        <a role="button" class="btn btn-danger btn-lg btn-block" href="${pageContext.request.contextPath}/employee/draft/delete"><span class="glyphicon glyphicon-trash"></span>Delete Draft</a>
    </c:if>
    <c:if test="${empty documents}"><h1>There are no documents yet.</h1></c:if>
    <c:if test="${not empty documents}">
        <table class="table table-condensed">
            <thead>
            <tr>
                <th>Document</th>
                <th>Title</th>
                <th>Submit Date</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${documents}" var="document">
                <tr class="active">
                    <td>${document.id}</td>
                    <td>${document.title}</td>
                    <td>${document.submitDateString()}</td>
                    <td>${document.status}</td>
                    <td>
                        <a role="button" class="btn btn-primary" href="./employee/documents/detail?id=${document.id}">Detail</a>
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
