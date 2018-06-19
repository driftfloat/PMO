$(document).ready(function() {
	$('#employeeUpgradeRecordForm').bootstrapValidator({
		message: 'This value is not valid',

        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        excluded:[":disabled"],
        fields: {
        	nowrateupgrade: {
                validators: {
                    numeric: {
                    	message:'Please enter Number'
                    }
                }
            },
            
         
        }
    });
     
});



