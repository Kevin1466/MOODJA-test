<jsp:useBean id="document" scope="request" type="model.Document"/>
<%--
  Created by IntelliJ IDEA.
  User: eric_wang
  Date: 02.02.22
  Time: 4:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MOODJA Internal System</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" media="all">
</head>

<body>
<%@ include file="../header.jsp"%>
<%@ include file="../sidebar.jsp"%>
<div class="col-md-9">
    <table class="table table-bordered">
        <caption style="align-content: center; text-align: center"><h2>Document Detail</h2></caption>
        <tbody>
        <tr>
            <td><strong>Document ID</strong></td>
            <td>${document.id}</td>
        </tr>
        <tr>
            <td><strong>Document Title</strong></td>
            <td>${document.title}</td>
        </tr>
        <tr>
            <td><strong>Employee Name</strong></td>
            <td>${document.employee.firstName} ${document.employee.lastName}</td>
        </tr>
        <tr>
            <td><strong>Email</strong></td>
            <td>${document.employee.email}</td>
        </tr>
        <tr>
            <td><strong>Mobile</strong></td>
            <td>${document.employee.mobile}</td>
        </tr>
        <tr>
            <td><strong>Position</strong></td>
            <td>${document.employee.position}</td>
        </tr>
        <tr>
            <td><strong>Department</strong></td>
            <td>${document.employee.department}</td>
        </tr>
        <tr>
            <td><strong>Gender</strong></td>
            <td>${document.employee.gender}</td>
        </tr>
        <tr>
            <td><strong>Date of Birth</strong></td>
            <td>${document.employee.dob}</td>
        </tr>
        <tr>
            <td><strong>Nationality</strong></td>
            <td>${document.employee.nationality}</td>
        </tr>
        <tr>
            <td><strong>Start Year</strong></td>
            <td>${document.employee.startYear}</td>
        </tr>
        <tr>
            <td><strong>Bank Info</strong></td>
            <td>${document.employee.bankInfo}</td>
        </tr>
        <tr>
            <td><strong>Document content</strong></td>
            <td>${document.content}</td>
        </tr>
        <tr>
            <td><strong>Number</strong></td>
            <td>${document.number}</td>
        </tr>
        <tr>
            <td><strong>Status</strong></td>
            <td>${document.status}</td>
        </tr>
        <tr>
            <td><strong>Comment</strong></td>
            <td>${document.comment}</td>
        </tr>
        <tr>
            <td><strong>Summary</strong></td>
            <td>${document.summary}</td>
        </tr>
        <%-- <jsp:useBean id="attachments" scope="request" type="model.Attachment"/> --%>
        <c:if test="${not empty attachments}">
            <tr>
                <td colspan="2"><strong>Attachments</strong></td>
            </tr>
        </c:if>
        <c:forEach items="${attachments}" var="attachment">
            <tr>
                <td><strong><a href="${pageContext.request.contextPath}/attachment/download?filename=${attachment.name}">Download Attachment File</a> </strong></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${document.status.equals('Responsed')}">
        <a role="button" class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/employee/documents/response?id=${document.id}}"><span class="glyphicon glyphicon-primary"></span>Response</a>
    </c:if>

    <c:if test="${document.status.equals('Accepted')}">
        <a role="button" class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/employee/documents/accept?id=${document.id}"><span class="glyphicon glyphicon-primary"></span>Accept</a>
    </c:if>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/js/bootstrap.min.js"></script>
</body>
</html>
