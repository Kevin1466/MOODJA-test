<%--
  Created by IntelliJ IDEA.
  User: eric_wang
  Date: 25.01.22
  Time: 5:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MOODJA Internal System</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/css/bootstrap.min.css">
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <!-- Newest Bootstrap core JavaScript file -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.min.js"></script>

    <!-- Bootstrap Validator file -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>

    <!-- CDNJS lib file -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha512-bLT0Qm9VnAYZDflyKcBaQ2gg0hSYNQrJ8RilYldYQ1FxQYoCLtUjuuRuZo+fjqhx/qtq/1itJ0C2ejDxltZVFg==" crossorigin="anonymous"></script>
</head>
<body>
<%@ include file="../header.jsp"%>
<%@ include file="../sidebar.jsp"%>

<div class="col-md-9">
    <h1>Reset Password for ${sessionScope.admin.username}</h1>
    <form:form action="${pageContext.request.contextPath}/admin/resetPassword" method="post" role="form" id="reset_password" name="reset_password">
        <div class="form-group">
            <input type="hidden" id="id" name="id" value="${sessionScope.admin.id}">
            <label for="newPassword">New Password</label>
            <input type="password" class="form-control" id="newPassword" name="newPassword" value="">
        </div>
        <div class="form-group">
            <label for="repeatPassword">Confirm New Password</label>
            <input type="password" class="form-control" id="repeatPassword" name="repeatPassword" value="">
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-success" value="Save">
            <a href="${pageContext.request.contextPath}/admin/profile" role="button" class="btn btn-primary">Return</a>
        </div>
    </form:form>
</div>

<script>
    $(document).ready(function () {
        $('#reset_password').bootstrapValidator( {
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                newPassword: {
                    validators: {
                        notEmpty: {
                            message: 'Please enter password'
                        },
                        stringLength: {
                            min: 8,
                            max: 35,
                            message: 'Password should be longer than 6 characters and less than 35 characters'
                        },
                        regexp: {
                            regexp: /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$/,
                            message: 'Password should at least contain one upper case letter, one lower case letter one number, and one special character'
                        }
                    }
                },
                repeatPassword: {
                    validators: {
                        notEmpty: {
                            message: 'Repeat password should not be empty'
                        },
                        callback: {
                            message: 'Password not matching'
                        },
                        callback: function (value, validator) {
                            var newPassword = document.getElementById("newPassword");
                            return value === newPassword.value;
                         }
                    }
                }
            }
        })
    });

</script>
</body>
</html>
