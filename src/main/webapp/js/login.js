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
                        message: '请输入您的用户名'
                    },

                   /* regexp: {
                        regexp: /^([\u4E00-\u9FA5]|\w)*$/,
                        message: '请勿包含特殊字符'
                    },*/
                    stringLength: {
                        min: 1,
                        max: 20,
                        message: '请输入长度在1到20位之间的用户名'
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
                        message: '请输入密码'
                    },
                    stringLength: {
                        min: 1,
                        max: 15,
                        message: '请输入长度在1到15位密码'
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
					else {
						//$("#loginAlert").attr("display")
						$("#loginAlert").html('用户名密码错误').show();
						$("#loginAlert").css({color:"red"});
					}
				}
			});
	//window.location.href = path+"/service/employee/welcome.html";
	
}
