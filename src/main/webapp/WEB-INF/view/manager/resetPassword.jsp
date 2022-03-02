<%--
  Created by IntelliJ IDEA.
  User: eric_wang
  Date: 02.02.22
  Time: 3:02 PM
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
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css">

    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
</head>
<body>
<%@ include file="../header.jsp"%>
<%@ include file="../sidebar.jsp"%>

<div class="col-md-9">
    <h1>Reset password for ${sessionScope.manager.firstName} ${sessionScope.manager.lastName}</h1>
    <form:form action="${pageContext.request.contextPath}/manager/account/resetPassword" method="post" role="form" id="reset_password" name="reset_password">
        <div class="form-group">
            <input type="hidden" id="id" name="id" value="${sessionScope.manager.id}">
            <label for="password">New Password</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>

        <div class="form-group">
            <label for="repeatPassword">Repeat Password</label>
            <input type="password" class="form-control" id="repeatPassword" name="repeatPassword">
        </div>

        <div class="form-group">
            <input type="submit" class="btn btn-success" value="save">
            <a href="${pageContext.request.contextPath}/manager/account/" role="button" class="btn btn-primary">Return</a>
        </div>
    </form:form>
</div>

<script>
    $(document).ready(function () {
        $('#reset_password').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphcion glyphicon-refresh'
            },
            field: {
                password: {
                    validators: {
                        notEmpty: {
                            message: 'Please enter password'
                        },
                        stringLength: {
                            min: 6,
                            max: 35,
                            message: 'Password should be longer than 6 digits and less than 35 digits'
                        },
                        regexp: {
                            regexp: /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$/,
                            message: 'Minimum eight characters, at least one upper case English letter, one lower case English letter, one number and one special character'
                        }
                    }
                },
                repeatPassword: {
                    validators: {
                        notEmpty: {
                            message: 'Repeat password should not be empty'
                        },
                        callback: {
                            message: 'Password does not match',
                            callback: function (value, validator) {
                                var newPassword = document.getElementById("password");
                                return value === newPassword.value;
                            }
                        }
                    }
                }
            }
        });
    })
</script>
</body>
</html>
