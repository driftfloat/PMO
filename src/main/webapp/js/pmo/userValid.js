$(document).ready(function() {    
    $('#updateUserForm').bootstrapValidator({
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
                        message: 'Please enter your E_HR'
                    },
                    regexp: {
                        regexp: /^E\d{9}$/,
                        message: '只能是E开头的9位数字'
                    },
                 }
            },

            nickName: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your userName'
                    }

                }
            },
            
            buName: {
                validators: {
                    notEmpty: {
                        message: 'Please select your BU'
                    }
                }
            },
            csSubDept: {
                validators: {
                    notEmpty: {
                        message: 'Please select your DU'
                    }

                }
            },
            userType: {
                validators: {
                    notEmpty: {
                        message: 'Please select your Type'
                    }

                }
            },
            email: {
                validators: {
                	 regexp: {
                         regexp:/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/,
                         message: 'Please enter correct Email'
                     },
                     stringLength: {
                         max: 100,
                         message: 'Exceeded the maxLength'
                     }
                }
            }
            
        }
    }) 
});



