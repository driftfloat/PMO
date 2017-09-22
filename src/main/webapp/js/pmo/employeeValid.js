$(document).ready(function() {
	$('#registerEmployeeForm').bootstrapValidator({
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
                    remote:{
                    	type:"post",
                    	url: path+'/service/employee/checkEhr',
                        message:"eHr already exists"
                    }

                 }
            },

            lob: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your lob'
                    },
                    numeric: {
                    	message:'Please enter number'
                    },
                    remote:{
                    	type:"post",
                    	url: path+'/service/employee/checkLob',
                        message:"lob already exists"
                    }
                }
            },
            hsbcStaffId: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your hsbcStaffId'
                    },
                    numeric: {
                    	message:'Please enter number'
                    },
                    remote:{
                    	type:"post",
                    	url: path+'/service/employee/checkHSBCStaffID',
                        message:"hsbcStaffId already exists"
                    }
                }
            },
            staffName: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your staffName'
                    }

                }
            },
            LN: {
                validators: {
                    notEmpty: {
                        message: 'Please select your LN'
                    }

                }
            },

            hsbcProjectName: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your hsbcProjectName'
                    }

                }
            },
        
            hsbcProjectManager: {
                validators: {
                    notEmpty: {
                        message: 'Please select your hsbcProjectManager'
                    }

                }
            },
            sow: {
                validators: {
                    notEmpty: {
                        message: 'Please select your sow'
                    }

                }
            },
            billRate: {
                validators: {
                    notEmpty: {
                        message: 'Please Choose your billRate'
                    },
                    numeric:{
                    	message:'please enter number'
                    }

                }
            }
           
        }
    });
    
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
                    /*remote:{
                    	type:"post",
                    	url: path+'/service/employee/checkEhr',
                        message:"eHr already exists"
                    }*/

                 }
            },

            lob: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your lob'
                    },
                    numeric: {
                    	message:'Please enter number'
                    },
                   /* remote:{
                    	type:"post",
                    	url: path+'/service/employee/checkLob',
                        message:"lob already exists"
                    }*/
                }
            },
            hsbcStaffId: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your hsbcStaffId'
                    },
                    numeric: {
                    	message:'Please enter number'
                    },
                    /*remote:{
                    	type:"post",
                    	url: path+'/service/employee/checkHSBCStaffID',
                        message:"hsbcStaffId already exists"
                    }*/
                }
            },
            staffName: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your staffName'
                    }

                }
            },
            LN: {
                validators: {
                    notEmpty: {
                        message: 'Please select your LN'
                    }

                }
            },

            hsbcProjectName: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your hsbcProjectName'
                    }

                }
            },
        
            hsbcProjectManager: {
                validators: {
                    notEmpty: {
                        message: 'Please select your hsbcProjectManager'
                    }

                }
            },
            sow: {
                validators: {
                    notEmpty: {
                        message: 'Please select your sow'
                    }

                }
            },
            billRate: {
                validators: {
                    notEmpty: {
                        message: 'Please Choose your billRate'
                    },
                    numeric:{
                    	message:'please enter number'
                    }

                }
            }
           
        }
    }) 
});



