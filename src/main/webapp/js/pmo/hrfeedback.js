
function feedbackCandidateInfo(candidateId,candidateName){
	$("#hrcandidateId").val(candidateId);
	$("#candidateNameId").val(candidateName);
	$('#hrfeedBackbox').modal('show');
	$('#feedbackList tbody').html("")
	$('#hrFeedBack').html("");
	$.ajax({
		url:path+"/service/hrfeedback/hrfeedbackQuery ",
		dataType:"json",
		async:true,
		data:{'candidateId':candidateId},
		cache:false,
		type:"post",
		success:function(result){
			var tbody = $("<tbody>");
			tbody.appendTo($("#feedbackList"));
			if(result.length<= 0 ){
				$('#recordTable').display = "none";
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				$("<td colspan='4' style='color: red;text-align: center;'>No record!</td>").appendTo(tr);
			}
			for (var i = 0; i < result.length; i++) {
				var tr = $("<tr ></tr>");
				tr.appendTo(tbody);
				$("<td>HR Name:" +result[i].nickname+"</td>" +
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
	$('#hrFeedBackForm').bootstrapValidator({
		message: 'This value is not valid',

        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	hrFeedBack: {
                validators: {
                    notEmpty: {
                        message: 'Please enter Feedback'
                    }
                }
            }
        }
    });
	var bootstrapValidator = $("#hrFeedBackForm").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(!bootstrapValidator.isValid()){
		return;
	}
	$.ajax({
		url:path+"/service/hrfeedback/hrFinalFeedBack",
		dataType:"json",
		async:true,
		data:{'candidateId':candidateId,'hrFeedBack':hrFeedBack},
		cache:false,
		type:"post",
		success:function(result){
			$("#hrFeedBack").val(" ");
			$('#hrFeedBackForm').data('bootstrapValidator').resetForm(); 
			if(result)
			{
			     $('#hrfeedBackbox').modal('hide');
			     loadCandidateList();	
			}else{
				 $('#hrfeedBackbox').modal('hide');
				 alert("Feedback unsuccessfully")
			}
		}
	})
	
}

function interviewConfirm(candidateId,candidateName,csSubdeptName){
	$('#hrInterviewConfirmbox').modal('show');
	$("#hrConfirmCandidateId").val(candidateId);
	$("#hrConfirmCandidateNameId").val(candidateName);
	$("#hrConfirmCandidateNameId").attr("disabled", true);
	$('#confirmDate').val("confirm");
	$("#newInterviewDate").hide();
	$("#newDate").val("");
	
	var candidateId = candidateId;
	var csSubdeptName = csSubdeptName;
	$.ajax({
		url:path+"/service/interview/getNewInterviewRecord",
		dataType:"json",
		async:true,
		data:{'candidateId':candidateId,'csSubdeptName':csSubdeptName},
		cache:false,
		type:"post",
		success:function(result){
			$("#hrConfirmInterviewId").val(result.interviewId);
			$("#interviewDate").val(result.interviewDate);
			$("#interviewDate").attr("disabled", true);
			//$("#newDate").val(" ");
			
		}
	})
	
}
$("#confirmDate").change(function(){
	var confirmDate =  $("#confirmDate").find("option:selected").text()
	if (confirmDate =="取消"){
		$("#newInterviewDate").show();
	}else if (confirmDate =="确认"){
		$("#newInterviewDate").hide();
		$("#newDate").val("");
	}
})

function confirmInterviewDate(){
	var candidateId = $("#hrConfirmCandidateId").val();
	var confirmDateType =  $("#confirmDate").find("option:selected").text()
	var interviewId = $("#hrConfirmInterviewId").val();
	var newDate = $("#newDate").val();
	var bootstrapValidator;
	if($('#newInterviewDate').is(':hidden')){
	      //如果隐藏时的处理方法
	}else{
		//alert("显示");
	      //如果显示时的处理方法
		$('#conformForm').bootstrapValidator({
			message: 'This value is not valid',

	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	newDate: {
	                validators: {
	                    notEmpty: {
	                        message: 'Please enter New Interview Date'
	                    }
	                }
	            }
	        }
	    });
		bootstrapValidator = $("#conformForm").data('bootstrapValidator');
		bootstrapValidator.validate();
		if(!bootstrapValidator.isValid()){
			return;
		}
	}
	$.ajax({
		url:path+"/service/hrfeedback/confirmInterviewDate",
		dataType:"json",
		async:true,
		data:{'confirmDateType':confirmDateType,'newDate':newDate,'candidateId':candidateId,'interviewId':interviewId},
		cache:false,
		type:"post",
		success:function(result){
			$('#conformForm').data('bootstrapValidator').resetForm(); 
			if (result == false) {
				alert("Reconfirm unsuccessfully");
			} else if (result == true) {
				$("#reconfirm").hide();
			}
			$('#hrInterviewConfirmbox').modal('hide');
		}
	})
}

function cancel(){
	$('#hrFeedBackForm').data('bootstrapValidator').resetForm(); 
	$('#hrfeedBackbox').modal('hide');
	$("#hrFeedBack").val("");
}
function cancelConform(){
	$('#conformForm').data('bootstrapValidator').resetForm(); 
	$('#hrInterviewConfirmbox').modal('hide');
	$("#newDate").val("");
}	
