$(function(){

});

window.onload = function(){
	//  loadEmployeeInfo();
}

function updateInterviewFeedBack(){
	var interviewId = $('#interviewId').val();
	var feedBackInfo = $('#interviewFeedBack').val();
	$.ajax({
		url:path+'/service/candidate/updateInterviewFeedBack',
		dataType:"json",
		data:{"interviewId":interviewId,"feedBackInfo":feedBackInfo},
		async:true,
		cache:false,
		type:"post",
		success:function(resultFlag){
			if(resultFlag){
				$("html,body").animate({scrollTop:0}, 500);
				$('#successAlert').html('面试官反馈成功').show();
				setTimeout(function () {
					$('#successAlert').hide();
				}, 2000);
			}
		}
	})
}
