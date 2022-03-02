<%--
  Created by IntelliJ IDEA.
  User: eric_wang
  Date: 25.01.22
  Time: 5:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-md-2">
    <ul class="nav nav-list">
        <c:if test="${not empty sessionScope.employee}">
            <li class="nav-header">Welcome ${sessionScope.employee.firstName} ${sessionScope.employee.lastName}</li>
            <li class="active"><a href="${pageContext.request.contextPath}/employee"><i class="icon-white ui-icon-home"></i>HOME</a> </li>
            <li><a href="${pageContext.request.contextPath}/employee/documents"><span class="glyphicon glyphicon-file"></span>Document</a></li>
            <li><a href="${pageContext.request.contextPath}/employee/history"><span class="glyphicon glyphicon-dashboard"></span>History</a></li>
            <li><a href="${pageContext.request.contextPath}/employee/account"><span class="glyphicon glyphicon-user"></span>Profile</a></li>
        </c:if>

        <c:if test="${not empty sessionScope.manager}">
            <li class="nav-header">Welcome ${sessionScope.manager.firstName} ${sessionScope.manager.lastName}</li>
            <li class="active"><a href="${pageContext.request.contextPath}/manager"><i class="icon-white ui-icon-home"></i>HOME</a></li>
            <li><a href="${pageContext.request.contextPath}/manager/documents"><span class="glyphicon glyphicon-file"></span>Document</a></li>
            <li><a href="${pageContext.request.contextPath}/manager/account"><span class="glyphicon glyphicon-user"></span>Profile</a></li>
        </c:if>

        <c:if test="${not empty sessionScope.admin}">
            <li class="nav-header">Welcome ${sessionScope.admin.username}</li>
            <li class="active"><a href="${pageContext.request.contextPath}/admin"><i class="icon-white ui-icon-home"></i>HOME</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/managers"><span class="glyphicon glyphicon-user"></span>Manager</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/documents"><span class="glyphicon glyphicon-file"></span>Document</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/profile"><span class="glyphicon glyphicon-user"></span>Profile</a></li>
        </c:if>
    </ul>
</div>