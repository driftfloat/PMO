$(document).ready(function() {
	$('#addUserForm').bootstrapValidator({
		message: 'This value is not valid',

        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	eHr: {
				validators: {
                    notEmpty: {
                        message: 'Please enter your E-HR'
                    },
                    regexp: {
                        regexp: /^E\d{9}$/,
                        message: 'Please enter the E-HR（E and 9 digits）'
                    },
                    remote:{
                    	type:"post",
                    	url: path+'/service/user/checkEhr',
                        message:"E-HR already exists"
                    }

                 }
            },
            name: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your Name'
                    }
                }
            },
            bu: {
                validators: {
                    notEmpty: {
                        message: 'Please select your BU'
                    }
                }
            },
            du: {
                validators: {
                    notEmpty: {
                        message: 'Please select your DU'
                    }

                }
            },
            type: {
                validators: {
                    notEmpty: {
                        message: 'Please select your Type'
                    }

                }
            }
        }
    });
     
});




function addUser(){
	var bootstrapValidator = $("#addUserForm").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()){
		var eHr = $("#eHr").val();
		var name = $("#name").val();
		var bu = $("#bu").val();
		var du = $("#du").val();
		var type = $("#type").val();
		
		$.ajax({
			url:path+'/service/user/addUser',
			dataType:"json",
			data:{"eHr":eHr,"name":name,"bu":bu,"du":du,"type":type},
			async:true,
			cache:false,
			type:"post",
			success:function(resultFlag){
				if(resultFlag){
					$("html,body").animate({scrollTop:0}, 500);
					$('#successAlert').html('user:'+eHr+' information added succesffully').show();
					setTimeout(function () {
						$('#successAlert').hide();
					}, 2000);
				}
			}
		})
		
	}else{
		return;
	}
	
}

