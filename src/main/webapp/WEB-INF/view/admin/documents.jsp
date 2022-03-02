<%--
  Created by IntelliJ IDEA.
  User: eric_wang
  Date: 02.02.22
  Time: 4:02 PM
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" media="all">
</head>
<body>
<%@ include file="../header.jsp"%>
<%@ include file="../sidebar.jsp"%>

<div class="col-md-9">
    <c:if test="${empty documents}"><h1>No document found</h1></c:if>
    <c:if test="${not empty documents}">
        <table class="table table-condensed">
            <thead>
            <tr>
                <th>Document</th>
                <th>Title</th>
                <th>Submit Date</th>
                <th>Employee ID</th>
                <th>Manager ID</th>
                <th>Status</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${documents}" var="document">
                <tr class="active">
                    <td>${document.id}</td>
                    <td>${document.title}</td>
                    <td>${document.submitDate}</td>
                    <td>${document.employee}</td>
                    <td>${document.manager}</td>
                    <td>${document.status}</td>
                    <td>
                        <form:form role="form" method="post" action="/admin/documents/delete">
                            <input type="hidden" name="id" id="id" value="${document.id}">
                            <input type="submit" class="btn btn-warning" value="Delete">
                        </form:form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/js/bootstrap.min.js"></script>
</body>
</html>
