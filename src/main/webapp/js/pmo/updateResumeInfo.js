
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

function toUpdateResume(){
	
	var id = $('#candidateId').val();
	var age = $('#candidateAge').val();
	var gender = $('#candidateSex').val();
	var tel = $('#tel').val();
	var education = $('#education').val();
	var collenge = $('#collenge').val();
	var major = $('#major').val();
	var experience_years = $('#experience_years').val();
	var skill = $('#skill').val();
	var graduate_date = $('#graduate_date').val();
	var English_level = $('#English_level').val();
	var candidate_status = $('#candidate_status').val();
	var expected_salary = $('#expected_salary').val();
	var real_salary = $('#real_salary').val();
	var old_company = $('#old_company').val();
	var remark = $('#remark').val();
	var interview_status = $('#interview_status').val();
	var source = $('#source').val();
	var role = $('#role').val();
	var entry_date=$('#entry_date').val();
	alert(entry_date);
	$.ajax({
		url:path+'/service/resume/toUpdateResumeNew',
		dataType:"json",
		data:{"id":id,
			"candidateName":$('#candidateName').val(),
			"age":age,
			"gender":gender,
			"tel":tel,
			"entry_date":entry_date,
			"education":education,
			"collenge":collenge,
			"major":major,
			"experience_years":experience_years,
			"skill":skill,
			"graduate_date":graduate_date,
			"English_level":English_level,
			"candidate_status":candidate_status,
			"expected_salary":expected_salary,
			"real_salary":real_salary,
			"old_company":old_company,
			"remark":remark,
			"interview_status":interview_status},	
		async:true,
		cache:false,
		type:"post",
		success:function(resultFlag){
			if(resultFlag){
				
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
			$('#GRADUATE_DATE1').val(resume.graduate_date);
			$('#candidateName').val(resume.candidateName);
			$('#tel').val(resume.tel);
			$('#college').val(resume.college);
			$('#experience_years').val(resume.experience_years);
			$('#graduate_date').val(resume.graduateDate);
			$('#candidate_status').val(resume.candidate_status);
			$('#resume_path').val(resume.resumePath);
			$('#hr').val(resume.hr);
			$('#expected_salary').val(resume.expected_salary);
			$('#real_salary').val(resume.real_salary);
			$('#entry_date').val(resume.entry_date);
			$('#old_company').val(resume.old_company);
			$('#remark').val(resume.remark);
			$('#create_user').val(resume.createUser);
			$('#interview_status').val(resume.interviewStatus);

			loadRole();
			function loadRole(){
				var url = path+'/json/role.json'
				$.getJSON(url,  function(data) {
				       $.each(data, function(i, item) {
				    	   $("#role").append("<option >"+item.name+"</option>");
				       })
//				       $("#role option").each(function(){
//				    	   if($(this).text() == resume.role){  
//				               $(this).attr("selected","selected");  
//				           }  
//				       })
//				      alert(resume.role);
				       $('#role').val(resume.role);
				});
			}
			
			loadSource();
			function loadSource(){
				var url = path+'/json/source2.json'
				$.getJSON(url,  function(data) {
				       $.each(data, function(i, item) {
				    	   $("#source").append("<option value='"+item.key+"' >"+item.name+"</option>");
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


