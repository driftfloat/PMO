$(document).ready(function() {    
    $('#updateEmployeeForm').bootstrapValidator({
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
            }
            
        }
    }) 
});



