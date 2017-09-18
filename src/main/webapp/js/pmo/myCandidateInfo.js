var currentPage = "";//当前页码
var pageCount = "";//共几页 
var exportdata;
$(function(){
	loadCandidateList();	
	loadCandidateSkillInfo();
})

$('#searchCandidateBtn').bind("click", function(){
	loadCandidateList();
});

$('#exportCandidateExcel').bind("click", function(){	
	exportdata = new FormData(document.getElementById("candidateForm"));
	$('#myCandidateListModal').modal('show');	
});

function exportCondition(){
	var lb = $("label input");
	var exportDataColumn = "";
	var exportPageColumn = "";
	if(lb.length <= 0){
		alert("未勾选导出列！");
		return;
	}
	for (var i=0;i<lb.length;i++)
	{
		if (lb.eq(i).is(':checked'))
		{
			exportDataColumn += lb.eq(i).attr("name")+",";
			exportPageColumn += lb.eq(i).val()+",";
		}
	}
	
	exportdata.append("exportDataColumn",exportDataColumn);
	exportdata.append("exportPageColumn",exportPageColumn);
	$.ajax({
		url:path+'/service/candidate/transformCandidateData',
		dataType:"json",
		data:exportdata,
		async:true,
		cache:false,
		processData:false,
        contentType:false,
		type:"post",
		success:function(result){
			if(result == '1')
			{
				exportData();
			}
		}
	})
	
}

function exportData(){
	var url = path+'/service/candidate/exportExcel';
	$("#exceltHrefCandidate").attr("href",url);
	document.getElementById("exceltHrefCandidate").click();
	
	$('#myCandidateListModal').modal('hide');
	$("[type='checkbox']").attr("checked","checked");
}
var statusMap = {"招聘中":"0","offer中":"1","已入职":"2","闲置中":"3","暂不关注":"4","黑名单":"5","入职他司":"6"};
function updateCandidateStatus(candidateId,candidateName,candidateStatus){	
		 
	$('#updateCandidateStatusId').val(candidateId);
	$('#updateCandidateStatusName').val(candidateName);
	$("#myCandidateStatus").val(statusMap[candidateStatus]);
	$('#myCandidateStatusModal').modal('show');
};
$(document).ready(function() {  
	$('#myCandidateStatusModal').on('hide.bs.modal', function(e) {  
        $('#statusCandidateForm').bootstrapValidator('resetForm', true);   
    });
	$('#myCandidatePushModal').on('hide.bs.modal', function(e) {  
        $('#pushCandidateForm').bootstrapValidator('resetForm', true);   
    });
	$("#statusSubmit").on("click", function(){
		   var bootstrapValidator = $("#statusCandidateForm").data('bootstrapValidator');
		   bootstrapValidator.validate();
		   if(bootstrapValidator.isValid())
			   updateStatus();
		   else return;

		});
	$("#pushSubmit").on("click", function(){
		   var bootstrapValidator = $("#pushCandidateForm").data('bootstrapValidator');
		   bootstrapValidator.validate();
		   if(bootstrapValidator.isValid())
			   pushCandidateOk();
		   else return;

		});
	
	//Verify the input information when the Candidate'status is updated 
	$('#statusCandidateForm').bootstrapValidator({
		 feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {       	
	        	myCandidateStatus: {
	          		validators: {
	          			 notEmpty: {
	                          message: 'Please select status'
	                      }
	                  }
	              }
	     
	        }
	    });	   
	//Verify the input information when the Candidate  is pushed 
	$('#pushCandidateForm').bootstrapValidator({
		 feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {       	
	        	csSubdeptName: {
	          		validators: {
	          			 notEmpty: {
	                          message: 'Please select department'
	                      }
	                  }
	              }
	     
	        }
	    });	   
});

function updateStatus(){
	var updateMycandidateStatus = new FormData();
	var candidateStatus = $("#myCandidateStatus").find("option:selected").val();
	updateMycandidateStatus.append("candidateId",$("#updateCandidateStatusId").val());
	updateMycandidateStatus.append("candidateStatus",candidateStatus);
	
	$.ajax({
		url:path+'/service/candidate/updateCandidateStatusOk',
		dataType:"json",
		data:updateMycandidateStatus,
		async:true,
		cache:false,
		processData:false,
        contentType:false,
		type:"post",
		success:function(flag){
			if(flag){
				alert("更新成功！");				    
			}else{
				alert("更新失败！请刷新页面重试！");
			}
			$('#myCandidateStatusModal').modal('hide');	
			loadCandidateList();
		}
	})
}
function pushCandidateToDept(candidateId,candidateName){	
	$('#myCandidatePushModal').on('show.bs.modal', function (e) {
		loadCusDeptInfo();
		$('#pushCandidateId').val(candidateId);
		$('#pushCandidateName').val(candidateName);
	});
	$('#myCandidatePushModal').modal('show');	
};
function loadCusDeptInfo(){
	$.ajax({
		url:path+'/service/candidate/loadCusDeptInfo',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			for(var i = 0;i<list.length;i++){
				$("#csSubdeptName").append("<option value='"+list[i].csSubDeptId+"'>"+list[i].csSubDeptName+"</option>");
			}
		}
	})
}



function pushCandidateOk(){
	var csSubDeptId = $("#csSubdeptName").find("option:selected").val();	
	/*if(csSubDeptId == ''){
		alert("请选择推送部门！");
		return;
	}*/
	var candidatePush = new FormData();
	candidatePush.append("csSubDeptId",csSubDeptId);
	candidatePush.append("candidateId",$("#pushCandidateId").val());
	
	$.ajax({
		url:path+'/service/candidate/pushCandidateOk',
		dataType:"json",
		data:candidatePush,
		async:true,
		cache:false,
		processData:false,
        contentType:false,
		type:"post",
		success:function(flag){
			if(flag == '5'){
				alert("推送成功！");
			}else if(flag == '0'){
				alert("推送失败！此候选人不存在，请刷新页面重试！");
			}else if(flag == '1'){
				alert("推送失败！此候选人状态不在招聘中！不能推送！");
			}else if(flag == '2'){
				alert("推送失败！请勿重复推送！");
			}else if(flag == '3'){
				alert("推送失败！更新候选人状态失败，请刷新页面重试！");
			}else if(flag == '4'){
				alert("推送失败！新增推送候选人数据失败，请刷新页面重试！");
			}
			$('#myCandidatePushModal').modal('hide');	
			loadCandidateList();
		}
	})
}

function backCandidateToDept(candidateId){
	if(confirm("确定要撤回吗?"))
	{
		$.ajax({
			url:path+'/service/candidate/backCandidateToDept',
			dataType:"json",
			data:{"candidateId":candidateId},
			async:true,
			cache:false,
			type:"post",
			success:function(flag){
				if(flag == '3'){
					alert("撤回成功！");
				}else if(flag == '0'){
					alert("撤回失败！此候选人不存在，请刷新页面重试！");
				}else if(flag == '1'){
					alert("撤回失败！此候选人当前状态不能撤回！");
				}else if(flag == '2'){
					alert("撤回失败！更新候选人状态失败，请刷新页面重试！");
				}	
				loadCandidateList();
			}
		})
	}
}

function updateResumeInfo(candidateId){
	$("#editForm").attr("action",path+"/service/resume/updateResume.html");
	$("#candidateId").val(candidateId);
	$("#editForm").submit();
}



function downLoadCandidateResume(candidateId,resumePath){
	if(resumePath == null || resumePath == ''){
		alert("未上传此候选人简历");
		return;
	}
	var url = path+'/service/candidate/downLoadCandidateResume?candidateId='+candidateId;
	$("#exceltHrefCandidate").attr("href",url);
	document.getElementById("exceltHrefCandidate").click();
}

function loadCandidateSkillInfo(){
	var url = path+'/json/skill.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#skill").append("<option>"+item.name+"</option>");
	       })
	});
}

function loadCandidateList(pageState)
{
	var candidate = new FormData(document.getElementById("candidateForm"));
	if(null != pageState)
	{
		candidate.append("pageState",pageState);
	}
	candidate.append("currentPage",currentPage);
	candidate.append("pageCount",pageCount);
	$.ajax({
		url:path+"/service/candidate/queryMyCandidateList",
		dataType:"json",
		async:true,
		data:candidate,
		cache:false,
		processData:false,
        contentType:false,
		type:"post",
		success:function(result){
			$("#candidateList tbody").remove();
			var tbody = $("<tbody>");
			tbody.appendTo($("#candidateList"));
			if(result.data.length <= 0 ){
				$("#exportCandidateExcel").attr("disabled",true);
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				$("<td colspan='15' style='color: red;text-align: center;'>未查询到数据！</td>").appendTo(tr);
			}else{
				$("#exportCandidateExcel").removeAttr("disabled");
			}
			for (var i = 0; i < result.data.length; i++) {
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				$("<td><a href='javascript:void(0);'" +
						"onclick=viewCandidataInfo('"+result.data[i].candidateId+"')>" +
						result.data[i].candidateName+"</a></td>"+
				"<td>"+ result.data[i].candidateSex+ "</td>"+
				"<td>"+ result.data[i].candidateAge+ "</td>"+
				"<td>"+ result.data[i].candidateTel+ "</td>"+
				"<td>"+ result.data[i].email+ "</td>"+
				"<td>"+ result.data[i].source+ "</td>"+
				"<td>"+ result.data[i].candidateStatus+ "</td>"+
				"<td>"+ result.data[i].education+ "</td>"+
				"<td>"+ result.data[i].experienceYears+ "</td>"+
				"<td>"+ result.data[i].majorStatus+ "</td>"+
				"<td>"+ result.data[i].englishLevel+ "</td>"+
				"<td>"+ result.data[i].skill+ "</td>"+
				"<td>"+ result.data[i].interviewStatus+ "</td>").appendTo(tr);
				if(result.data[i].candidateStatus == '招聘中'){
					if(result.data[i].interviewStatus == '未推送'){
						$("<td></td>"+
							"<td><a href='javascript:void(0);' class='btn btn-info btn-small' " +
								"onclick=updateResumeInfo('"+result.data[i].candidateId+"')>EDIT</a>"+
							"<a href='javascript:void(0);' class='btn btn-info btn-small' " +
								"onclick=pushCandidateToDept('"+result.data[i].candidateId+"','"+result.data[i].candidateName+"')>PUSH</a>" +
							"<a href='javascript:void(0);' class='btn btn-info btn-small' " +
								"onclick=updateCandidateStatus('"+result.data[i].candidateId+"','"+result.data[i].candidateName+"','"+result.data[i].candidateStatus+"')>STATUS</a>" +
							"<a href=' ' class='btn btn-info btn-small' " +
								"onclick=feedbackCandidateInfo('"+result.data[i].candidateId+"','"+result.data[i].candidateName+"')>FEEDBACK</a>" +
							"<a href='javascript:void(0);' class='btn btn-info btn-small' " +
								"onclick=downLoadCandidateResume('"+result.data[i].candidateId+"','"+result.data[i].resumePath.replace(/\s+/g, "")+"')>RESUME</a>" +
						"</td>").appendTo(tr);
					}
					if(result.data[i].interviewStatus == '已推送'){
						$("<td>"+ result.data[i].csSubdeptName+ "</td>"+
							"<td><a href='javascript:void(0);' class='btn btn-info btn-small' " +
								"onclick=updateResumeInfo('"+result.data[i].candidateId+"')>EDIT</a>"+
							"<a href='javascript:void(0);' class='btn btn-info btn-small' " +
								"onclick=backCandidateToDept('"+result.data[i].candidateId+"')>BACK</a>" +
							"<a href='javascript:void(0);' class='btn btn-info btn-small' " +
								"onclick=updateCandidateStatus('"+result.data[i].candidateId+"','"+result.data[i].candidateName+"','"+result.data[i].candidateStatus+"')>STATUS</a>" +
							"<a href=' ' class='btn btn-info btn-small' " +
								"onclick=feedbackCandidateInfo('"+result.data[i].candidateId+"','"+result.data[i].candidateName+"')>FEEDBACK</a>" +
							"<a href='javascript:void(0);' class='btn btn-info btn-small' " +
								"onclick=downLoadCandidateResume('"+result.data[i].candidateId+"','"+result.data[i].resumePath.replace(/\s+/g, "")+"')>RESUME</a>" +
						"</td>").appendTo(tr);
					}
				}else{
					$("<td>"+ result.data[i].csSubdeptName+ "</td>"+
						"<td><a href='javascript:void(0);' class='btn btn-info btn-small' " +
								"onclick=updateResumeInfo('"+result.data[i].candidateId+"')>EDIT</a>"+
							"<a href='javascript:void(0);' class='btn btn-info btn-small' " +					
								"onclick=updateCandidateStatus('"+result.data[i].candidateId+"','"+result.data[i].candidateName+"','"+result.data[i].candidateStatus+"')>STATUS</a>" +
							"<a href=' ' class='btn btn-info btn-small' " +
								"onclick=feedbackCandidateInfo('"+result.data[i].candidateId+"','"+result.data[i].candidateName+"')>FEEDBACK</a>" +
						"<a href='javascript:void(0);' class='btn btn-info btn-small' " +
							"onclick=downLoadCandidateResume('"+result.data[i].candidateId+"','"+result.data[i].resumePath.replace(/\s+/g, "")+"')>RESUME</a>" +
					"</td>").appendTo(tr);
				}
			}
			$("#candidateList").append("</tbdoy>");
			currentPage = parseInt(result.pageInfo.currentPage);
			pageCount = parseInt(result.pageInfo.pageCount);
			var pageDataCount = parseInt(result.pageInfo.pageDataCount);
			var dataCount = parseInt(result.pageInfo.dataCount);
			$("#pageCount").html(pageCount);
			$("#currentPage").html(currentPage);
			$("#pageDataCount").html(pageDataCount);
			$("#dataCount").html(dataCount);
			$("#nextPage").attr("onclick","loadCandidateList('next')");
			$("#previousPage").attr("onclick","loadCandidateList('previous')");
			$("#lastPage").attr("onclick","loadCandidateList('last')");
			$("#fristPage").attr("onclick","loadCandidateList('frist')");
			if(currentPage == pageCount){
				$("#nextPage").removeAttr("onclick");
				$("#lastPage").removeAttr("onclick");
			}
			if(currentPage == 1){
				$("#previousPage").removeAttr("onclick");
				$("#fristPage").removeAttr("onclick");
			}
		}
	})
}