<%--
  Created by IntelliJ IDEA.
  User: eric_wang
  Date: 07.02.22
  Time: 2:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charaset=UTF-8"  language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE-edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>MOODJA Internal System</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>

<body>
<%@ include file="../header.jsp"%>
<%@ include file="../sidebar.jsp"%>

<div class="col-md-9">
  <c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
  </c:if>
  <a role="button" class="btn btn-primary btn-lg btn-block" href="${pageContext.request.contextPath}/admin/managers/add"><span class="glyphicon glyphicon-pencil"></span>Create</a>
  <c:if test="${empty manager}"><h1>No manager found</h1></c:if>
  <c:if test="${not empty manager}">
    <table class="table table-condensed">
      <thead>
      <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>EMAIL</th>
        <th>MOBILE</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${manager}" var="manager">
        <tr class="active">
          <td>${manager.id}</td>
          <td>${manager.firstName} ${manager.lastName}</td>
          <td>${manager.email}</td>
          <td>${manager.mobile}</td>
          <td>
            <a role="button" class="btn btn-success" href="${pageContext.request.contextPath}/admin/managers/edit?managerId=${manager.id}">Edit</a>
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
