
function feedbackCandidateInfo(candidateId,candidateName){
	$("#hrcandidateId").val(candidateId);
	$("#candidateNameId").val(candidateName);
	$('#hrfeedBackbox').modal('show');
	$('#feedbackList tbody').html("")
	$('#hrFeedBack').html("");
	$.ajax({
		url:path+"/service/hrfeedback/hrfeedbackQuery",
		dataType:"json",
		async:true,
		data:{'candidateId':candidateId},
		cache:false,
		type:"post",
		success:function(result){
			var tbody = $("<tbody>");
			tbody.appendTo($("#feedbackList"));
			if(result.length<= 0 ){
				$('#recordTable').display = "none";;
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				$("<td colspan='14' style='color: red;text-align: center;'>无反馈记录！</td>").appendTo(tr);
			}
			for (var i = 0; i < result.length; i++) {
				var tr = $("<tr ></tr>");
				tr.appendTo(tbody);
				$("<td>HR Name:" +result[i].userName+"</td>" +
				"<td>FeedbackDATE:"+ result[i].feedbacktime+ "</td>").appendTo(tr);
				var trr = $("<tr ></tr>");
				trr.appendTo(tbody);
				$("<td colspan='2'>Feedback:"+ result[i].hrFeedBack+ "</td>").appendTo(trr);
				
			$("#feedbackList").append("</tbdoy>");
			
			
		}}
	})
	
	
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
	
