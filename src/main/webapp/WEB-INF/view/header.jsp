<%@ page import="model.Administrator" %>
<%@ page import="model.Manager" %>
<%@ page import="model.Employee" %><%--
  Created by IntelliJ IDEA.
  User: eric_wang
  Date: 25.01.22
  Time: 3:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-inverse" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <img width="128" height="81" src="${pageContext.request.contextPath}/static/img/moodja_logo.png" alt=""/>
            <p class="navbar-brand">MOODJA Internal System</p>
        </div>

        <%
            Administrator admin = null;
            Manager manager = null;
            Employee employee = null;
			String home = null;

			if (session.getAttribute("admin") != null) {
				admin = (Administrator) session.getAttribute("admin");
				home = "/admin/home";
            } else if (session.getAttribute("manager") != null) {
				manager = (Manager) session.getAttribute("manager");
				home = "/manager/home";
            } else if (session.getAttribute("employee") != null) {
				employee = (Employee) session.getAttribute("employee");
				home = "/employee/home";
            }
			if (home != null) {
        %>
        <div class="header-top-login">
            <button class="login-dropbtn"><p>${sessionScope.admin.username}${sessionScope.manager.email}${sessionScope.employee.id} | <a href="${pageContext.request.contextPath}/logout" class="logout-btn">Logout</a> </p></button>
        </div>

        <% } else {%>
        <a href="${pageContext.request.contextPath}/login">Login</a>
        <%--
        <div class="header-top-login">
            <button class="login-dropbtn">Login</button>
            <div class="login-dropdown-content">
                <a href="${pageContext.request.contextPath}/admin">Administrator</a>
                <a href="${pageContext.request.contextPath}/manager">Manager</a>
                <a href="${pageContext.request.contextPath}/employee">Employee</a>
            </div>
        </div>
        --%>
        <% } %>
        <%--
        <div>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${! empty sessionScope.userType}">
                    <li><a href="${pageContext.request.contextPath}/logout">Logout</a> </li>
                </c:if>
                <c:if test="${empty sessionScope.userType}">
                    <li><a href="${pageContext.request.contextPath}/login">Login</a> </li>
                </c:if>
            </ul>
        </div>
        --%>
    </div>
</nav>