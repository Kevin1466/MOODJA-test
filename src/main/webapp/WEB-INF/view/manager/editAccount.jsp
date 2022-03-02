<%--
  Created by IntelliJ IDEA.
  User: eric_wang
  Date: 02.02.22
  Time: 9:24 AM
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css">

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
</head>
<%@ include file="../header.jsp"%>
<%@ include file="../sidebar.jsp"%>
<body>
<div class="col-md-9">
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <form:form action="${pageContext.request.contextPath}/manager/account/edit" method="post" id="manager_form" name="manager_form">
        <div class="form-group">
            <input type="hidden" name="id" id="id" value="${sessionScope.manager.id}">
            <label for="firstname">First Name: </label>
            <input class="form-control" name="firstName" id="firstName" value="${sessionScope.manager.firstName}" placeholder="Firstname..">
        </div>
        <div class="form-group">
            <label for="lastname">Last Name: </label>
            <input class="form-control" name="lastName" id="lastName" value="${sessionScope.manager.lastName}" placeholder="Lastname..">
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" name="email" id="email" value="${sessionScope.manager.email}" placeholder="Email Address..">
        </div>
        <div class="form-group">
            <label for="mobile">Mobile</label>
            <input class="form-control" name="mobile" id="mobile" value="${sessionScope.manager.mobile}" placeholder="Mobile Number..">
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-success" value="Save">
            <a href="${pageContext.request.contextPath}/manager/account" role="button" class="btn btn-primary">Return</a>
        </div>
    </form:form>
</div>

<script>
    $(document).ready(function () {
        $('#manager_form').bootstrapValidator ({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                email: {
                    validators: {
                        notEmpty: {
                            message: 'Email is required'
                        },
                        regexp: {
                            regexp: /[^@ \t\r\n]+@[^@ \t\r\n]+\.[^@ \t\r\n]+/,
                            message: 'Email format incorrect'
                        }
                    }
                },
                mobile: {
                    validators: {
                        notEmpty: {
                            message: 'Mobile number is required'
                        },
                        regexp: {
                            regexp: /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/,
                            message: 'Match a phone number with "-" and/or country code'
                        },
                        stringLength: {
                            min: 11,
                            max: 15,
                            message: 'Mobile number should be minimum 11 digits and maximum 15 digits'
                        },
                    }
                },
                firstname: {
                    validators: {
                        notEmpty: {
                            message: 'Firstname is required'
                        },
                        regexp: {
                            regexp: /^[A-Z][a-zA-Z\s]+$/,
                            message: 'Firstname should only contain letters'
                        },
                        stringLength: {
                            min: 1,
                            max: 20,
                            message: 'Firstname should not be too long and first letter should be always be upper case'
                        },
                    }
                },
                lastname: {
                    validators: {
                        notEmpty: {
                            message: 'Lastname is required'
                        },
                        regexp: {
                            regexp: /^[A-Z][a-zA-Z\s]+$/,
                            message: 'Lastname should only contain letters'
                        },
                        stringLength: {
                            min: 1,
                            max: 20,
                            message: 'Lastname should not be too long and first letter should be always be upper case'
                        },
                    }
                }
            }
        });
    })
</script>
</body>
</html>
