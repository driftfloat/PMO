$(document).ready(function() {
    $('#loginForm').bootstrapValidator({
		//message: 'This value is not valid',

        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	userName: {
				validators: {
                    notEmpty: {
                        message: 'Please enter username.'
                    },

                   /* regexp: {
                        regexp: /^([\u4E00-\u9FA5]|\w)*$/,
                        message: '请勿包含特殊字符'
                    },*/
                    stringLength: {
                        min: 1,
                        max: 20,
                        message: 'Please enter the username within 20 characters.'
                    },
                    /* remote: {
                        url: path+'/service/user/checkUser',
                        message: '用戶名不存在',
                       //delay :  2000,//per 2s send a request
                        type: 'POST'
                    }*/
                 }
            },

            password: {
                validators: {
                    notEmpty: {
                        message: 'Please enter password.'
                    },
                    stringLength: {
                        min: 1,
                        max: 15,
                        message: 'Please enter the username within 15 characters.'
                    },
                }
            }
        }
    }) .on('success.form.bv', function(e) {
        // Prevent submit form
        e.preventDefault();

        var $form     = $(e.target);
            validator = $form.data('bootstrapValidator');
        if(validator){
        	login(e.target);
        }

    });
});




//$('#submitBtn').bind("click", function(){
function login(e){
		var userName = $("#userName").val();
		var password = $("#password").val();

		$.ajax({
				url : path+"/service/user/login",
				type : "post",
				async : true,
				cache : false,
				dataType : "json",
				data : {'userName' : userName, 'password' : password},
				timeout : 20000,
				success : function(data) {
					if (data=="0") {
						 window.location.href = path+"/service/user/welcome.html";
					}
					else if(data=="1"){
						//$("#loginAlert").attr("display")
						$("#loginAlert").html('Incorrect username or password.').show();
						$("#loginAlert").css({color:"red"});
					}else if(data=="2"){
						$("#loginAlert").html('User has already written off.').show();
						$("#loginAlert").css({color:"red"});
					}
				}
			});
	//window.location.href = path+"/service/employee/welcome.html";
	
}
