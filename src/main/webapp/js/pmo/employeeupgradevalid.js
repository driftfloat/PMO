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
            effectivedate: {
            	validators: {
                   notEmpty: {
                     message: 'Please select EffectDate'
                   },
                   date : {  
                     format : 'YYYY-MM-DD',  
                     message : 'Time format is incorrect'  
                   }

                }
             }
         
        }
    });
     
});



