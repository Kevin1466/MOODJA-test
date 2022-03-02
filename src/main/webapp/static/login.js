$(document).ready(function (){

    $('#login_form').bootstrapValidator({
        message:'This value is not valid', //Verify error messages
        feedbackIcons: { // Verify display icon
            valid: 'glyphicon glyphicon-ok', // correct
            invalid: 'glyphicon glyphicon-remove', //incorrect
            validating: 'glyphicon glyphicon-refresh' // refresh
        },
        fields: {
            password: {
                validators: {
                    notEmpty: {
                        message: 'Please enter password'
                    },
                }
            },
            username: {
                validators: {
                    notEmpty: {
                        message: 'Please enter a valid value'
                    },
                    callback: {
                        message: 'Please enter a valid value',
                        callback: function(value, validator) {
                            var reg_str;

                            var userType = document.getElementById("userType");
                            var index = userType.selectedIndex;
                            var option = userType.options[index];

                            if (option.value === "employee") {
                                reg_str = /^[0-9]{8}/;
                            }
                            if (option.value === "manager") {
                                reg_str = /^[a-zA-Z0-9]+[.a-zA-Z0-9_-]*@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
                            }
                            if (option.value === "admin") {
                                reg_str = /^[a-zA-Z0-9_]+$/;
                            }
                            console.log("test something, reg: " + reg_str + ", value: " + value + ", result: " +reg_str.test(value));
                            return reg_str.test(value);
                        }
                    }
                }
            }
        }
    });
});

function refreshLabel() {
    var label = document.getElementById("username_label");
    var userType = document.getElementById("userType");
    var index = userType.selectedIndex;

    if (userType.options[index].value === "employee") {
        label.innerText = "Employee ID";
        $("#username_label").html("Employee ID");
        document.getElementById("username").setAttribute("placeholder", "Enter Employee ID: ");
    }

    if (userType.options[index].value === "manager") {
        label.innerText = "Email Address";
        $("#username_label").html("Email Address");
        document.getElementById("username").setAttribute("placeholder", "Email Address: ");
    }

    if (userType.options[index].value === "admin") {
        label.innerText = "Username";
        $("#username_label").html("Username");
        document.getElementById("username").setAttribute("placeholder", "Enter Username: ");
    }
    document.getElementById("username").value = "";
}