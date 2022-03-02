<%--
  Created by IntelliJ IDEA.
  User: eric_wang
  Date: 01.02.22
  Time: 1:03 PM
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" media="all">
</head>
<body>
<%@ include file="../header.jsp"%>
<%@ include file="../sidebar.jsp"%>

<div class="col-md-9">
    <form:form action="/employee/history" method="post" role="form">
        <div class="row">
            <div class="col-md-2">
                <select id="type" name="type" onchange="refreshSearchBar()" class="select picker show-tick- form-control">
                    <option value="1" selected="selected">By Submit Date</option>
                    <option value="0">Title</option>
                </select>
            </div>
            <div class="col-md-4">
                <input type="text" id="title" name="title" placeholder="Search by title: " class="form-control" style="display: none">
                <input type="month" id="month" name="month" class="month">
            </div>
            <div class="col-md-2">
                <input type="submit" class="form-control" value="Search" style="margin-left: 3px">
            </div>
        </div>
    </form:form>

    <c:if test="${empty documents}"><h1>No result found</h1></c:if>
    <c:if test="${not empty documents}">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Document</th>
                <th>Title</th>
                <th>Submit Date</th>
                <th>View Date</th>
                <th>Manager</th>
                <th>Status</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${documents}" var="document">
                <tr class="active">
                    <td>${document.id}</td>
                    <td>${document.title}</td>
                    <td>${document.submitDateString()}</td>
                    <td>${document.viewDateString()}</td>
                    <td>${document.manager.firstName} ${document.manager.lastName}</td>
                    <td>${document.status}</td>
                    <td>
                        <a role="button" class="btn btn-primary" href="${pageContext.request.contextPath}/employee/documents/detail?id=${document.id}">Detail</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/js/bootstrap.min.js"></script>

<script>
    function refreshSearchBar() {
        var type = document.getElementById("type");
        var index = type.selectedIndex;
        var option = type[index];
        var date = document.getElementById("date");
        var title = document.getElementById("title");

        if (option.value === 1) {
            title.style.display = "none";
            date.style.display = "inline";
        } else {
            date.style.display = "none";
            title.style.display = "inline";
        }
    }
</script>
</body>
</html>
