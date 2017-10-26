
$(function(){
	
	
	dateType();
});

window.onload = function(){
	loadResume();
}



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

function loadResumeUpdate(resume){
	$('#candidateId').val(resume.candidateId)
	var id = $('candidateId').val();
	loadId(candidateId,resume);
}

function toUpdateResumeNew(){
	var id = $('#candidateId').val();
	var resume_path = $('#resume_path').val();
	alert(resume_path);
	$.ajax({
		url:path+'/service/resume/toUpdateResumeNew',
		dataType:"json",
		data:{"id":id,
			"candidateName":$('#candidateName').val(),
			"tel":$('#candidateTel').val(),
			"age":$('#candidateAge').val(),
			"gender":$('#gender').val(),
			"education":$('#education').val(),
			"college":$('#college').val(),
			"major":$('#major').val(),
			"experience_years":$('#experience_years').val(),
			"skill":$('#skill').val(),
			"graduate_date":$('#GRADUATE_DATE1').val(),
			"English_level":$('#english_level').val(),
			"candidate_status":$('#candidate_status').val(),
			"email":$('#email').val(),
			"entry_date":$('#entry_date').val(),
			"old_company":$('#old_company').val(),
			"role":$('#role').val(),
			"expected_salary":$('#expected_salary').val(),
			"real_salary":$('#real_salary').val(),
			"source":$('#source').val(),
			"remark":$('#remark').val(),
			"interview_status":$('#interview_status').val(),
//			"resume_path":resume_path
			
			},	
			
			
		async:true,
		cache:false,
		type:"post",
		success:function(resultFlag)
		{
			
			
			if(resultFlag){
//				alert('Modify successfully');
				$("html,body").animate({scrollTop:0}, 500);
				$('#successAlert').html('候选人'+$('#candidateName').val()+'信息修改成功').show();
				setTimeout(function () {
					$('#successAlert').hide();
				}, 2000);
				
			}
			if(!resultFlag){
				alert('1');
			}
		}
	})
}




function loadResume(){
	var candidateId = $('#candidateId').val();

	$.ajax({
		url:path+'/service/resume/queryResumeInfoById',
		dataType:"json",
		data:{"candidateId":candidateId},
		async:true,
		cache:false,
		type:"post",
		success:function(resume){
			$('#email').val(resume.email);
			$('#candidateTel').val(resume.tel);
			$('#candidateAge').val(resume.age);
			$('#GRADUATE_DATE1').val(resume.graduate_date);
			$('#candidateName').val(resume.candidateName);
			$('#tel').val(resume.tel);
			$('#college').val(resume.college);
			$('#experience_years').val(resume.experience_years);
			$('#graduate_date').val(resume.graduateDate);
			$('#candidate_status').val(resume.candidate_status);
			$('#resume_path').val(resume.resumePath);
			$('#expected_salary').val(resume.expected_salary);
			$('#real_salary').val(resume.real_salary);
			$('#entry_date').val(resume.entry_date);
			$('#old_company').val(resume.old_company);
			$('#remark').val(resume.remark);
			$('#create_user').val(resume.createUser);
			$('#interview_status').val(resume.interviewStatus);
			$('#resume_path').val(resume.resume_path);
			
			$('#source').val(resume.source);
			 
			
			loadRole();
			function loadRole(){
				 
				var url = path+'/json/role.json'
				
				$.getJSON(url,  function(data) {
					
				       $.each(data, function(i, item) {
				    	 
				    	   $("#role").append("<option >"+item.name+"</option>");
				    	
				       })
				     /*  $("#role option").each(function(){
				    	   if($(this).text() == resume.role){  
				               $(this).attr("selected","selected");  
				           }  
				       })*/
				       $('#role').val(resume.role);
				});
			}
		
			
			loadSource();
			function loadSource(){
				var url = path+'/json/source2.json'
				$.getJSON(url,  function(data) {
				       $.each(data, function(i, item) {
				    	   $("#source").append("<option >"+item.name+"</option>");
				       })
				       $('#source').val(resume.source);
				});
			}

			
			loadGender();
			function loadGender(){
				var url = path+'/json/gender.json'
				$.getJSON(url,  function(data) {
				       $.each(data, function(i, item) {
				    	   $("#gender").append("<option value='"+item.key+"' >"+item.name+"</option>");
				       })
				       $('#gender').val(resume.gender);
				});
			}
			
			
			
			loadMajor();
			function loadMajor(){
				var url = path+'/json/major.json'
				$.getJSON(url,  function(data) {
				       $.each(data, function(i, item) {
				    	   $("#major").append("<option value='"+item.key+"' >"+item.name+"</option>");
				       })
				       $('#major').val(resume.major);
				});
			}
			
			loadSkill();
			function loadSkill(){
				var url = path+'/json/skill.json'
				$.getJSON(url,  function(data) {
				       $.each(data, function(i, item) {
				    	   $("#skill").append("<option>"+item.name+"</option>");
				       })
				       $('#skill').val(resume.skill);
				});
			}
			
			
			loadCandidate_status();
			function loadCandidate_status(){
				var url = path+'/json/candidateStatus.json'
				$.getJSON(url,function(data){
					$.each(data,function(i,item){
						$("#candidate_status").append("<option value='"+item.key+"' >"+item.name+"</option>");
					})
					$('#candidate_status').val(resume.candidate_status);
				});
			}
			loadEducation();
			//学历
			function loadEducation(){
				var url = path+'/json/education.json'
				$.getJSON(url,function(data){
					$.each(data,function(i,item){
						$("#education").append("<option value='"+item.key+"' >"+item.name+"</option>");
					})
					$('#education').val(resume.education);
				});
			}
			
			
			loadEnglish_level();
			function loadEnglish_level(){
				var url = path+'/json/englishLevel.json'
				$.getJSON(url,  function(data) {
				       $.each(data, function(i, item) {
				    	   $("#english_level").append("<option value='"+item.key+"' >"+item.name+"</option>");
				       })
				       
				       $('#english_level').val(resume.english_level);
				});
			}
			
			
		}
	})
}


