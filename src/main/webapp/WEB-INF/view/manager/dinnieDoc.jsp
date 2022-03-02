<%--
  Created by IntelliJ IDEA.
  User: eric_wang
  Date: 02.02.22
  Time: 2:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="static model.Document.REFUSED" %>
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
    <div class="form-group">
        <form:form action="/manager/documents/manage" method="post">
            <div class="form-group">
                <label for="comment"><h1>Comment</h1></label>
                <textarea name="comment" id="comment" class="form-control" rows="8" style="margin-bottom: 10px;">${document.comment}</textarea>
            </div>
            <div class="form-group">
                <c:if test="${result.equals('Refused')}"><input type="submit" class="btn btn-danger btn-lg" value="Dennie"></c:if>
                <c:if test="${result.equals('Replied')}"><input type="submit" class="btn btn-primary btn-lg" value="Reply"></c:if>
                <input type="hidden" name="id" id="id" value="${document.id}">
                <input type="hidden" name="result" id="result" value="${result}">
                <a role="button" class="btn btn-success btn-lg" href="${pageContext.request.contextPath}/manager/documents/detail?id=${document.id}">Return</a>
            </div>
        </form:form>
    </div>
</div>

<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/js/bootstrap.min.js"></script>
</body>
</html>
