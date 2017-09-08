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
                        message: '请输入中文名'
                    },
                    regexp: {
                        regexp: /^([\u4E00-\u9FA5])*$/,

                        message: '请输入中文字符'

                    },
                    stringLength: {
                        max: 25,
                        message: '请输入25字以内的中文名'
                    }
                }
            },
            
            tel: {
         		group: '.group',
         		validators: {
                     notEmpty: {
                    	 message: '请输入电话号码'
                     },
                     regexp: {
                         regexp: /^1[3|5|7|8]\d{9}$/,
                         message: '请输入正确的手机号'
                     },
                     remote: {
                    	 type:"POST",
                    	 url: path+'/service/resume/checkTel',
                    	 data:{
                    		 tel:function(){return $("#candidateTel").val();}
                    	 },
                    	 message:"手机号码已存在"
                     },
                        
                 }
             },
             
             age: {
          		group: '.group',
          		validators: {
                      notEmpty: {
                          message: '请输入年龄'
                      },
                      regexp: {
                          regexp: "^[1-9][0-9]$",
                          message: '年龄必须两位整数'
                      }
                      
                  }
              },
              
              gender: {
          		group: '.group',
          		validators: {
                      notEmpty: {
                          message: '请输入性别'
                      },
                      
                  }
              },
              education: {
          		group: '.group',
          		validators: {
                      notEmpty: {
                          message: '请输入学历'
                      },
                      
                  }
              },
              major: {
          		group: '.group',
          		validators: {
                      notEmpty: {
                          message: '请确认是否是计算机专业'
                      },
                      
                  }
              },
              experience_years: {
          		group: '.group',
          		validators: {
                      notEmpty: {
                          message: '请输入工作年限'
                      },
                      
                  }
              },
              
              skill: {
          		group: '.group',
          		validators: {
                      notEmpty: {
                          message: '请输入技能'
                      },
                      
                  }
              },
              GRADUATE_DATE1: {
          		group: '.group',
          		validators: {
                      notEmpty: {
                          message: '请输入毕业时间'
                      },
                      date : {  
                          format : 'YYYY-MM-DD',  
                          message : '日期格式不正确'  
                      }
                  }
              },
              
              English_level: {
          		group: '.group',
          		validators: {
                      notEmpty: {
                          message: '请输入英语水平'
                      },
                      
                  }
              },
              candidate_status: {
          		group: '.group',
          		validators: {
                      notEmpty: {
                          message: '请输入候选人状态'
                      },
                      
                  }
              },
              
              source: {
          		group: '.group',
          		validators: {
                      notEmpty: {
                          message: '请输入简历来源'
                      },
                      
                  }
              },
              role: {
          		group: '.group',
          		validators: {
                      notEmpty: {
                          message: '请输入角色'
                      },
                      
                  }
              },
              
              email: {
         		group: '.group',
         		validators: {
                     notEmpty: {
                         message: '请输入邮箱号'
                     },
                     regexp: {
                         regexp: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
                         message: '请输入正确的邮箱号'
                     },
                    
                 }
             },
             resume_path: {
         		group: '.group',
         		validators: {
                     notEmpty: {
                         message: '简历未上传'
                     },
                 }
             },
             expected_salary: {
            	 group: '.group',
            	 validators: {
            		 regexp: {
            	            regexp: "^[1-9][0-9]*$",
            	            message: '期望薪资为大于0的正整数'
            	        },
            	 }
             },
             real_salary: {
            	 group: '.group',
            	 validators: {
            		 regexp: {
            			 regexp: "^[1-9][0-9]*$",
            			 message: '实际薪资为大于0的正整数'
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
});

$(function () {
    $("#upload").click(function () {
        ajaxFileUpload();
    })
    dateType();
})

function dateType(){
	$('.form_datetime').datetimepicker({
		weekStart: 1,
		minView:'month',
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
		language:'zh-CN',
		format: 'yyyy-mm-dd',
		pickerPosition: 'bottom-left',
		showMeridian: 1
	});
}


 function ajaxFileUpload()
{
	if($("#uploadId").val()==''){
		return;
	}
$.ajaxFileUpload({
        url:path+'/service/uploadFile.html',//用于文件上传的服务器端请求地址
        secureuri:false ,//一般设置为false
        fileElementId:'uploadId',//文件上传控件的id属性  <input type="file" id="upload" name="upload" />
        dataType: 'text',//返回值类型 一般设置为json
        success: function (data)  //服务器成功响应处理函数
        {
          var jsonOut = eval('('+data+')');
          
          $("#resume_path").val(jsonOut.url)
          alert("上传成功！");
            
           
        },
        error: function (data, status, e)//服务器响应失败处理函数
        {
            alert(e);
        }
    });
return false;
}

function addCandidate(){
	if($("#resume_path").val()==''){
		alert("简历未上传！");
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
				$('#successAlert').html('候选人'+candidateName+'录入成功').show();
				setTimeout(function () {
					$('#successAlert').hide();
				}, 4000);
				window.location.href=path+"/service/resume/input.html";
			}else{
				alert("录入失败！！！")
			}
		}
	})
}