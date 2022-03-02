<%--
  Created by IntelliJ IDEA.
  User: eric_wang
  Date: 07.02.22
  Time: 2:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charaset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ID-edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MOODJA Internal System</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css">

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <!-- Newest Bootstrap core JavaScript file -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/js/bootstrap.min.js"></script>

    <!-- Bootstrap Validator JS file -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
</head>
<%@ include file="../header.jsp"%>
<%@ include file="../sidebar.jsp"%>

<body>
<div class="col-md-9">
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <form:form action="${pageContext.request.contextPath}/admin/managers/edit" method="post" id="manager_form" name="manager_form">
        <div class="form-group">
            <input type="hidden" name="id" value="${manager.id}">
            <input type="hidden" name="adminId" value="${sessionScope.admin.id}">
            <label for="firstname">First Name</label>
            <input type="text" class="form-control" id="firstname" name="firstname" value="${manager.firstName}">
        </div>
        <div class="form-group">
            <label for="lastname">Last Name</label>
            <input type="text" class="form-control" id="lastname" name="lastname" value="${manager.lastName}">
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="text" class="form-control" id="email" name="email" value="${manager.email}">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" value="#${manager.password}">
        </div>
        <div class="form-group">
            <label for="repeat_password">Repeat Password</label>
            <input type="password" class="form-control" id="repeat_password" name="repeat_password" value="#${manager.password}">
        </div>
        <div class="form-group">
            <label for="mobile">Mobile</label>
            <input type="text" class="form-control" id="mobile" name="mobile" value="${manager.mobile}">
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-success" value="Save">
            <a href="${pageContext.request.contextPath}/admin/managers" role="button" class="btn btn-primary">Return</a>
        </div>
    </form:form>

    <script>
        $(document).ready(function () {
            $('manager_form').bootstrapValidator( {
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    password: {
                        validators: {
                            notEmpty: {
                                message: 'Please enter password'
                            },
                            regexp: {
                                regexp: /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$/,
                                message: 'Password should contain min eight characters, at least one upper case letter, one lower case letter, one number and one special character'
                            },
                            stringLength: {
                                min: 8,
                                max: 35,
                                message: 'Password should be longer than 8 and less than 35 characters'
                            },
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
                    },
                    email: {
                        validators: {
                            notEmpty: {
                                message: 'Email is required'
                            },
                            regexp: {
                                regexp: /[a-zA-Z0-9]+[.a-zA-Z0-9_-]*@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
                                message: 'Email format is incorrect'
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
                                message: 'Mobile number format incorrect'
                            },
                            stringLength: {
                                min: 11,
                                max: 15,
                                message: 'Mobile number should be minimum 11 digits and no more than 15 digits'
                            },
                        }
                    },
                    firstname: {
                        validators: {
                            notEmpty: {
                                message: "First Name is required"
                            },
                            regexp: {
                                regexp: /^[A-Z][a-zA-Z\s]+$/,
                                message: 'First Name should only contain characters and the first letter should be upper case'
                            },
                            stringLength: {
                                min: 1,
                                max: 30,
                                message: 'First name should at least has on letter'
                            },
                        }
                    },
                    lastname: {
                        validators: {
                            notEmpty: {
                                message: 'Last name is required'
                            },
                            regexp: {
                                regexp: /^[A-Z][a-zA-Z\s]+$/,
                                message: 'Last Name should only contain characters and the first letter should be upper case'
                            },
                            stringLength: {
                                min: 1,
                                max: 30,
                                message: 'Last name should at least has on letter'
                            },
                        }
                    }
                }
            });
        })
    </script>
</div>
</body>
</html>