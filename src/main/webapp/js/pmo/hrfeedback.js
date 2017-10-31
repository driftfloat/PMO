
function feedbackCandidateInfo(candidateId,candidateName){
	$("#hrcandidateId").val(candidateId);
	$("#candidateNameId").val(candidateName);
	$('#hrfeedBackbox').modal('show');
}

function updateHRFeedBack(){
	var candidateId=$("#hrcandidateId").val();
	var hrFeedBack=$("#hrFeedBack").val();
	$.ajax({
		url:path+"/service/hrfeedback/hrFinalFeedBack",
		dataType:"json",
		async:true,
		data:{'candidateId':candidateId,'hrFeedBack':hrFeedBack},
		cache:false,
		type:"post",
		success:function(result){
			if(result)
			{
			     $('#hrfeedBackbox').modal('hide');
			     loadCandidateList();	
			}else{
				 $('#hrfeedBackbox').modal('hide');
				 alert("反馈失败。")
			}
		}
	})
	
}
	
