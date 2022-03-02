<%--
  Created by IntelliJ IDEA.
  User: eric_wang
  Date: 01.02.22
  Time: 2:30 PM
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

<div class="form-group">
    <div class="form-group">
        <form:form action="/employee/documents/response" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label><h1>Renew document content</h1></label>
                <textarea name="content" id="content" class="form-control" rows="8" style="margin-bottom: 10px">${document.content}</textarea>
            </div>
            <div class="form-group">
                <p>
                    <input id="addAttachment" name="addAttachment" type="button" class="btn btn-success" value="Add Attachment">
                </p>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-primary btn-lg" value="Response">
                <input type="hidden" name="id" id="id" value="${document.id}">
                <input type="hidden" name="result" id="result" value="Responses">
                <a role="button" class="btn btn-success btn-lg" href="./employee/documents/detail?id=${document.id}">Return</a>
            </div>
        </form:form>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/js/bootstrap.min.js"></script>

<script>
    $("#attachments").change(function () {
        $("#uploadBtn").val("Upload");
        $("#progressBar").width("0%");
        var file = $(this).prop('file');
        if (file.length != 0) {
            $("#uploadBtn").attr('disabled', false);
        }
    });

    $("#addAttachment").click(function () {
        var $this =$(this);
        var btnCtn = $this.parent();
        var p = $("<p/>").insertBefore(btnCtn);
        p.append($("<input/>", {
            type: "file",
            name: "attachment",
            style: "float:left"
        })).append($("<a/>", {
            href: "#",
            type: "button",
            text: "delete",
        }).click(function () {
            var $deleteBtn = $(this);
            $deleteBtn.parent().remove();
        }));
    });
</script>
</body>
</html>
