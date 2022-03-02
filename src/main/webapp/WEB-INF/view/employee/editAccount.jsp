<%--
  Created by IntelliJ IDEA.
  User: eric_wang
  Date: 01.02.22
  Time: 12:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ID-edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MOODJA Internal System</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css">

    <!--<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"></script>-->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
</head>

<%@ include file="../header.jsp"%>
<%@ include file="../sidebar.jsp"%>

<body>
<div class="col-md-9">
    <form:form action="${pageContext.request.contextPath}/employee/account/edit" method="post" role="form" id="employee_form" name="employee_form">
        <div class="form-group">
            <label>Name</label>
            <input class="form-control" value="${sessionScope.employee.firstName}, ${sessionScope.employee.lastName}" readonly="readonly"/>
        </div>
        <div class="form-group">
            <label>Gender</label>
            <input type="text" class="form-control" value="${sessionScope.employee.gender}" readonly="readonly"/>
        </div>
        <div class="form-group">
            <label>Date of Birth</label>
            <input class="form-control" value="${sessionScope.employee.dob}" readonly="readonly"/>
        </div>
        <div class="form-group">
            <label>Nationality</label>
            <input class="form-control" value="${sessionScope.employee.nationality}" readonly="readonly"/>
        </div>
        <div class="form-group">
            <label>Start Year</label>
            <input class="form-control" value="${sessionScope.employee.startYear}" readonly="readonly"/>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="email" value="${sessionScope.employee.email}" placeholder="Email Address"/>
        </div>
        <div class="form-group">
            <label for="mobile">Mobile</label>
            <input class="form-control" id="mobile" name="mobile" value="${sessionScope.employee.mobile}" placeholder="Mobile Number"/>
        </div>
        <div class="form-group">
            <label for="position">Position</label>
            <input type="text" class="form-control" id="position" name="position" value="${sessionScope.employee.position}" placeholder="Position Name"/>
        </div>
        <div class="form-group">
            <label for="department">Department</label>
            <input type="text" class="form-control" id="department" name="department" value="${sessionScope.employee.department}" placeholder="Department Name"/>
        </div>
        <div class="form-group">
            <label for="bankInfo">Bank Info</label>
            <input type="text" class="form-control" id="bankInfo" name="bankInfo" value="${sessionScope.employee.bankInfo}" placeholder="Bank Info"/>
        </div>

        <div class="form-group">
            <input type="hidden" id="id" name="id" value="${sessionScope.employee.id}">
            <input type="submit" class="btn btn-success" value="Save">
            <a href="${pageContext.request.contextPath}/employee/account" role="button" class="btn btn-primary">Return</a>
        </div>
    </form:form>
</div>
</body>
</html>

<script>
    $(document).ready(function () {
        $('#employee_form').bootstrapValidator ({
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
                            message: "Email is required",
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9]+[.a-zA-Z0-9_-]*@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
                            message: "Email format incorrect"
                        }
                    }
                },
                mobile: {
                    validators: {
                        notEmpty: {
                            message: "Mobile Number is required"
                        },
                        regexp: {
                            regexp: /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/,
                            message: "Match a mobile number with "-" and/or country code"
                        },
                        stringLength: {
                            min: 11,
                            max: 15,
                            message: "Mobile number should be minimum 11 digits and maximum 15 digits"
                        },
                    }
                },
                bankInfo: {
                    validators: {
                        notEmpty: {
                            message: "Bank Info is required"
                        },
                        regexp: {
                            //regexp: /\b[A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){4}(?!(?:[ ]?[0-9]){3})(?:[ ]?[0-9]{1,2})?\b/,
                            regexp: /^[a-zA-Z0-9]+$/,
                            message: "Please enter IBAN in the correct format, e.g. DEXX XXXX XXXX XXXX XXXX XX"
                        },
                        stringLength: {
                            max: 30,
                            message: "IBAN should not be longer than 30 digits"
                        },
                    }
                },
                position: {
                    validators: {
                        notEmpty: {
                            message: "Position is required"
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9]+$/,
                            message: "Please fill in position with valid information"
                        },
                        stringLength: {
                            max: 10,
                            message: "Position name should not be longer than 10 digits"
                        },
                    }
                },
                department: {
                    validators: {
                        notEmpty: {
                            message: "Department is required"
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9]+$/,
                            message: "Please fill in department with valid information"
                        },
                        stringLength: {
                            max: 10,
                            message: "Department name should not be longer than 10 digits"
                        },
                    }
                }
            }
        });
    })
</script>