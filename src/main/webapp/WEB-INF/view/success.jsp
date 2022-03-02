<%--
  Created by IntelliJ IDEA.
  User: eric_wang
  Date: 25.01.22
  Time: 3:57 PM
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" media="all">
</head>
<body>
<%@ include file="header.jsp"%>
<%@ include file="sidebar.jsp"%>

<div class="col-md-9">
    <h1>Your ${filetype} is successfully saved.</h1>
    <p style="text-indent: 2em; margin-top: 30px;">
        The system will redirect in <span id="time">5</span> seconds automatically.
        If it didn't redirect, click <a href="${pageContext.request.contextPath}/employee/documents" title="Redirect">Link</a> Redirect
    </p>
</div>

<script type="text/javascript">
    delayURL();
    function delayURL() {
        var delay = document.getElementById("time").innerHTML;
        var time = setTimeout("delayURL(), 1000");
        if(delay > 0) {
            delay--;
            document.getElementById("time").innerHTML = delay;
        } else {
            clearTimeout(time);
            window.location.href = "${pageContext.request.contextPath}/employee/documents";
        }
    }
</script>

<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/js/bootstrap.min.js"></script>
</body>
</html>
