$(document).ready(function() {

    $('#candidateForm').bootstrapValidator({
	//	message: 'This value is not valid',

        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	
        	candidateName: {
                group: '.group',
				validators: {
                    notEmpty: {
                        message: 'Please enter name'
                    },
                    /*regexp: {
                        regexp: /^([\u4E00-\u9FA5])*$/,

                        message: '请输入中文字符'

                    },*/
                    stringLength: {
                        max: 64,
                        message: 'Exceeded the maxLength'
                    }
                }
            },
            
            tel: {
         		group: '.group',
         		validators: {
                     notEmpty: {
                    	 message: 'Please enter phone number'
                     },
                     regexp: {
                         regexp: /^1[3|5|7|8]\d{9}$/,
                         message: 'Please enter a correctly formatted phone number'
                     },
                     remote: {
                    	 type:"POST",
                    	 url: path+'/service/resume/checkTel',
                    	 data:{
                    		 tel:function(){return $("#candidateTel").val();}
                    	 },
                    	 message:"Phone number already exists"
                     },
                        
                 }
             },
             
             age: {
          		group: '.group',
          		validators: {
                      /*notEmpty: {
                          message: 'Please enter age'
                      },*/
                      regexp: {
                          regexp: "^[1-9][0-9]$",
                          message: 'Please enter a double-digit number'
                      }
                      
                  }
              },
              
              gender: {
          		group: '.group',
          		validators: {
                      notEmpty: {
                          message: 'Please enter sex'
                      },
                      
                  }
              },
              education: {
          		group: '.group',
          		validators: {
                      notEmpty: {
                          message: 'Please enter education'
                      },
                      
                  }
              },
              
              college: {
                  group: '.group',
  				validators: {
                      notEmpty: {
                          message: 'Please enter university'
                      },
                      /*regexp: {
                          regexp: /^([\u4E00-\u9FA5])*$/,

                          message: '请输入中文字符'

                      },*/
                      stringLength: {
                          max: 32,
                          message: 'Exceeded the maxLength'
                      }
                  }
              },
              major: {
          		group: '.group',
          		validators: {
                      notEmpty: {
                          message: 'Please enter whether it is a computer major'
                      },
                      
                  }
              },
              experience_years: {
          		group: '.group',
          		validators: {
                      notEmpty: {
                          message: 'Please enter working years'
                      },
                      
                  }
              },
              
              skill: {
          		group: '.group',
          		validators: {
                      notEmpty: {
                          message: 'Please enter skill'
                      },
                      
                  }
              },
              GRADUATE_DATE1: {
          		group: '.group',
          		validators: {
                      notEmpty: {
                          message: 'Please enter graduation date'
                      },
                      date : {  
                          format : 'YYYY-MM-DD',  
                          message : 'Time format is incorrect'  
                      }
                  }
              },
              
              English_level: {
          		group: '.group',
          		validators: {
                      notEmpty: {
                          message: 'Please enter english level'
                      },
                      
                  }
              },
              candidate_status: {
          		group: '.group',
          		validators: {
                      notEmpty: {
                          message: 'Please enter candidate status'
                      },
                      
                  }
              },
              
              source: {
          		group: '.group',
          		validators: {
                      notEmpty: {
                          message: 'Please enter resume source'
                      },
                      
                  }
              },
              role: {
          		group: '.group',
          		validators: {
                      notEmpty: {
                          message: 'Please enter role'
                      },
                      
                  }
              },
              
              email: {
         		group: '.group',
         		validators: {
                     notEmpty: {
                         message: 'Please enter email'
                     },
                     regexp: {
                         regexp: /^[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)*@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
                         message: 'Please enter the correct format of the mailbox'
                     },
                    
                 }
             },
             
             resume_path: {
         		group: '.group',
         		validators: {
                     notEmpty: {
                         message: 'Please upload the resume'
                     },
                 }
             },
             
             entry_date: {
                 group: '.group',
 				validators: {
                     stringLength: {
                         max: 25,
                         message: 'Please enter entry date information'
                     }
                 }
             },
             expected_salary: {
            	 group: '.group',
            	 validators: {
            		 regexp: {
            	            regexp: "^[1-9][0-9]*$",
            	            message: 'Please enter an integer greater than 0'
            	        },
            	 }
             },
             real_salary: {
            	 group: '.group',
            	 validators: {
            		 regexp: {
            			 regexp: "^[1-9][0-9]*$",
            			 message: 'Please enter an integer greater than 0'
            		 },
            	 }
             },
        }
    }).on('success.form.bv', function(e) {
        // Prevent submit form
        e.preventDefault();

        var $form     = $(e.target);
            validator = $form.data('bootstrapValidator');
        if(validator){
        	addCandidate(e.target);
        }
    });
    
    
    
    $('#updateResumeFrom').bootstrapValidator({
    	//	message: 'This value is not valid',

            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
            	
            	candidateName: {
                    group: '.group',
    				validators: {
                        notEmpty: {
                            message: 'Please enter name'
                        },
                       /* regexp: {
                            regexp: /^([\u4E00-\u9FA5])*$/,

                            message: '请输入中文字符'

                        },*/
                        stringLength: {
                            max: 25,
                            message: 'Exceeded the max length'
                        }
                    }
                },
                
                tel: {
             		group: '.group',
             		validators: {
                         notEmpty: {
                        	 message: 'Please enter phone number'
                         },
                         regexp: {
                             regexp: /^1[3|5|7|8]\d{9}$/,
                             message: 'Please enter a correctly formatted phone number'
                         },
                     }
                 },
                 
                 age: {
              		group: '.group',
              		validators: {
                          /*notEmpty: {
                              message: 'Please enter age'
                          },*/
                          regexp: {
                              regexp: "^[1-9][0-9]$",
                              message: 'Please enter a double-digit number'
                          }
                          
                      }
                  },
                  
                  gender: {
              		group: '.group',
              		validators: {
                          notEmpty: {
                              message: 'Please enter sex'
                          },
                          
                      }
                  },
                  education: {
              		group: '.group',
              		validators: {
                          notEmpty: {
                              message: 'Please enter education'
                          },
                          
                      }
                  },
                  
                  college: {
                      group: '.group',
      				validators: {
                          notEmpty: {
                              message: 'Please enter university'
                          },
                          /*regexp: {
                              regexp: /^([\u4E00-\u9FA5])*$/,

                              message: '请输入中文字符'

                          },*/
                          stringLength: {
                              max: 25,
                              message: 'Exceeded the max length'
                          }
                      }
                  },
                  major: {
              		group: '.group',
              		validators: {
                          notEmpty: {
                              message: 'Please enter whether it is a computer major'
                          },
                          
                      }
                  },
                  experience_years: {
              		group: '.group',
              		validators: {
                          notEmpty: {
                              message: 'Please enter working years'
                          },
                          
                      }
                  },
                  
                  skill: {
              		group: '.group',
              		validators: {
                          notEmpty: {
                              message: 'Please enter skill'
                          },
                          
                      }
                  },
                  GRADUATE_DATE1: {
              		group: '.group',
              		validators: {
                          notEmpty: {
                              message: 'Please enter graduation date'
                          },
                          date : {  
                              format : 'YYYY-MM-DD',  
                              message : 'Time format is incorrect'  
                          }
                      }
                  },
                  
                  English_level: {
              		group: '.group',
              		validators: {
                          notEmpty: {
                              message: 'Please enter english level'
                          },
                          
                      }
                  },
                  candidate_status: {
              		group: '.group',
              		validators: {
                          notEmpty: {
                              message: 'Please enter candidate status'
                          },
                          
                      }
                  },
                  
                  source: {
              		group: '.group',
              		validators: {
                          notEmpty: {
                              message: 'Please enter resume source'
                          },
                          
                      }
                  },
                  role: {
              		group: '.group',
              		validators: {
                          notEmpty: {
                              message: 'Please enter role'
                          },
                          
                      }
                  },
                  
                  email: {
             		group: '.group',
             		validators: {
                         notEmpty: {
                             message: 'Please enter email'
                         },
                         regexp: {
                        	 regexp: /^[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)*@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
                             message: 'Please enter the correct format of the mailbox'
                         },
                        
                     }
                 },
                 
                 resume_path: {
             		group: '.group',
             		validators: {
                         notEmpty: {
                             message: 'Please upload the resume'
                         },
                     }
                 },
                 
                 entry_date: {
                     group: '.group',
     				validators: {
                         stringLength: {
                             max: 25,
                             message: 'Please enter entry date information'
                         }
                     }
                 },
                 expected_salary: {
                	 group: '.group',
                	 validators: {
                		 regexp: {
                	            regexp: "^[1-9][0-9]*$",
                	            message: 'Please enter an integer greater than 0'
                	        },
                	 }
                 },
                 real_salary: {
                	 group: '.group',
                	 validators: {
                		 regexp: {
                			 regexp: "^[1-9][0-9]*$",
                			 message: 'Please enter an integer greater than 0'
                		 },
                	 }
                 },
            }
        }).on('success.form.bv', function(e) {
            // Prevent submit form
            e.preventDefault();

            var $form     = $(e.target);
                validator = $form.data('bootstrapValidator');
            if(validator){
            	toUpdateResumeNew();
            }
        });
});

$(function () {
    $("#upload").click(function () {
        ajaxFileUpload();
    })
})




 function ajaxFileUpload()
{
	 var uploadId = $("#uploadId").val();
	if(uploadId==''){
		alert("Please upload file!");
		return;
	}else{
		var fileType=uploadId.slice(-3);
		if(fileType=='pdf'|fileType=='PDF'){
			$.ajaxFileUpload({
		        url:path+'/service/uploadFile.html',//用于文件上传的服务器端请求地址
		        secureuri:false ,//一般设置为false
		        fileElementId:'uploadId',//文件上传控件的id属性  <input type="file" id="upload" name="upload" />
		        dataType: 'text',//返回值类型 一般设置为json
		        catch:false,
		        success: function (data)  //服务器成功响应处理函数
		        {
		          var jsonOut = eval('('+data+')');
		          
		          $("#resume_path").val(jsonOut.url)
		          alert("Uploaded successfully!");
		           
		        },
		        error: function (data, status, e)//服务器响应失败处理函数
		        {
		            alert(e);
		        }
		    });
			
		}else{
			alert("Please upload a resume in PDF format!");
		}
	}
	

return false;
}

 
 

function addCandidate(){
	if($("#resume_path").val()==''){
		alert("Please upload file!");
		return;
	}
	var candidateName = $("#candidateName").val();
	var tel = $("#candidateTel").val();
	var age = $("#candidateAge").val();
	var gender = $("#gender").val();
	var education = $("#education").val();
	var college = $("#college").val();
	var major = $("#major").val();
	var experience_years = $("#experience_years").val();
	var skill = $("#skill").val();
	var graduate_date = $("#graduate_date").val();
	var skill = $("#skill").val();
	var English_level = $("#English_level").val();
	var candidate_status = $("#candidate_status").val();
	var email = $("#email").val();
	var entry_date = $("#entry_date").val();
	var source = $("#source").val();
	var role = $("#role").val();
	var resume_path = $("#resume_path").val();
	var expected_salary = $("#expected_salary").val();
	var real_salary = $("#real_salary").val();
	var old_company = $("#old_company").val();
	var remark = $("#remark").val();
	var create_user = $("#create_user").val();
	$.ajax({
		url:path+'/service/resume/add.html',
		dataType:"json",
		data:{ "candidateName":candidateName,
				"tel":tel,
				"age":age,
				"gender":gender,
				"education":education,
				"college":college,
				"major":major,
				"experience_years":experience_years,
				"skill":skill,
				"graduate_date":graduate_date,
				"English_level":English_level,
				"candidate_status":candidate_status,
				"email":email,
				"entry_date":entry_date,
				"source":source,
				"role":role,
				"resume_path":resume_path,
				"expected_salary":expected_salary,
				"real_salary":real_salary,
				"old_company":old_company,
				"remark":remark,
				"create_user":create_user
				},
		async:true,
		cache:false,
		type:"post",
		success:function(flag){
			if(flag=='0'){
				$("html,body").animate({scrollTop:0}, 500);
				$('#successAlert').html('Candidate:'+candidateName+' information added succesffully').show();
				setTimeout(function () {
					$('#successAlert').hide();
				}, 4000);
				var urlTo = path+"/service/candidate/getMyCandidate.html";
				window.location.href = urlTo;
				/*window.location.href=path+"/service/resume/input.html";*/
			}else{
				alert("Information added unsuccesffully!")
			}
		}
	})
}