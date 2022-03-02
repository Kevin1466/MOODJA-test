<%--
  Created by IntelliJ IDEA.
  User: eric_wang
  Date: 01.02.22
  Time: 10:17 AM
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

    <!-- jQuery file, must import before bootstrap.min.js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <!-- Newest Bootstrap core JavaScript file -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/js/bootstrap.min.js"></script>

    <!-- Bootstrap Validator JS file -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>

    <!-- Bootstrap Validator template file -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css">
</head>
<body>
<%@ include file="../header.jsp"%>
<%@ include file="../sidebar.jsp"%>

<div class="col-md-9">
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <form:form action="" method="post" role="form" id="doc_form" name="doc_form" enctype="multipart/form-data">

        <div class="form-group">
            <label for="title">Document Title</label>
            <input type="text" class="form-control" id="title" name="title" value="${draft.title}">
        </div>
        <div class="form-group">
            <label>Employee ID: </label>
            <input readonly class="form-control" value="${sessionScope.employee.id}">
        </div>
        <div class="form-group">
            <label>Name: </label>
            <input readonly class="form-control" value="${sessionScope.employee.firstName} ${sessionScope.employee.lastName}">
        </div>
        <div class="form-group">
            <label>Email: </label>
            <input readonly class="form-control" value="${sessionScope.employee.email}">
        </div>
        <div class="form-group">
            <label>Gender: </label>
            <input readonly class="form-control" value="${sessionScope.employee.gender}">
        </div>
        <div class="form-group">
            <label>Date of Birth: </label>
            <input readonly class="form-control" value="${sessionScope.employee.dob}">
        </div>
        <div class="form-group">
            <label>Mobile: </label>
            <input readonly class="form-control" value="${sessionScope.employee.mobile}">
        </div>
        <div class="form-group">
            <label>Position: </label>
            <input readonly class="form-control" value="${sessionScope.employee.position}">
        </div>
        <div class="form-group">
            <label>Department: </label>
            <input readonly class="form-control" value="${sessionScope.employee.department}">
        </div>
        <div class="form-group">
            <label>Nationality: </label>
            <input readonly class="form-control" value="${sessionScope.employee.nationality}">
        </div>
        <div class="form-group">
            <label>Start Year: </label>
            <input readonly class="form-control" value="${sessionScope.employee.startYear}">
        </div>

        <div class="form-group">
            <label>Number: </label>
            <input type="number" id="number" name="number" class="form-control" min="1" placeholder="Amount Number" onchange="rate_cal()" value="100">
        </div>
        <div class="form-group">
            <label>Content</label>
            <textarea class="form-control" id="content" name="content" rows="10">${draft.content}</textarea>
            <input type="hidden" name="draft_id" id="draft_id" value="${draft.id}">
        </div>
        <div class="form-group">
            <label>Summary</label>
            <input readonly class="form-control form-control-plaintext" id="summary" name="summary" value="100">
        </div>
        <div class="form-group">
            <h3>Attachments</h3>
        </div>
        <c:if test="${not empty attachments}">
            <c:forEach items="${attachments}" var="attachment">
                <label>${attachment.name}</label>
                <button class="btn btn-default" onclick="deleteAttachment(this, ${attachment.id}">Delete</button>
            </c:forEach>
        </c:if>
        <div class="form-group">
            <p>
                <input id="addAttachment" type="button" name="addAttachment" class="btn btn-success" value="Add Attachment">
            </p>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success" id="doc_btn" onclick="submitDoc()">Submit</button>
            <button type="submit" class="btn btn-sm btn-warning" id="draft_btn" onclick="saveDraft()">Save Draft</button>
        </div>
    </form:form>
</div>

<script>
    $(document).ready(function () {
        $('#doc_form').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                content: {
                    validators: {
                        notEmpty: {
                            message: 'Please leave a comment'
                        },
                        stringLength: {
                            max: 500,
                            message: 'Content is too long'
                        },
                    }
                },
                title: {
                    validators: {
                        notEmpty: {
                            message: 'Title should not be empty'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9\s]+$/,
                            message: 'Title should only contains letters and numbers'
                        },
                        stringLength: {
                            max: 20,
                            message: 'Title should not be too long'
                        },
                    }
                }
            }
        });
    });

    function submitDoc() {
        document.getElementById("doc_form").action = "${pageContext.request.contextPath}/employee/documents/add";
        document.getElementById("doc_form").submit();
        console.log("Submit document");
    }

    function saveDraft() {
        document.getElementById("doc_form").action = "${pageContext.request.contextPath}/employee/draft/save";
        document.getElementById("doc_form").submit();
        console.log("Submit Draft");
    }

    function deleteAttachment(btn, fileId) {
        console.log("Delete attachment: " + fileId);
        var block = $(btn).parent();
        var p = $("<p/>").insertBefore(block);
        p.append($("<input/>", {
            type: "hidden",
            name: "deleteId",
            id: "deleteId",
            value: fileId
        }));
        block.remove();
    }

    $("attachments").change(function () {
        $("#uploadBtn").val("Upload");
        $("#progressBar").width("0%");
        var file = $(this).prop('files');
        if (file.length != 0) {
            $("#uploadBtn").attr('disabled', false);
        }
    });

    $('#addAttachment').click(function () {
        var $this= $(this);
        var btnCtn = $this.parent();
        var p = $("<p/>").insertBefore(btnCtn);

        p.append($("<input/>", {
            type: "file",
            name: "attachments",
            style: "float:left",
            accept: "document/pdf"
        })).append($("<a/>", {
            href: "#",
            type: "button",
            text: "delete",
        }).click(function () {
            var $deleteBtn = $(this);
            $deleteBtn.parent().remove();
        }));
    });

    function rate_cal() {
        var number = Number(document.getElementById("number").value);
        var summary = document.getElementById("summary");
        console.log(number + " " + summary)
    }
</script>
</body>
</html>
