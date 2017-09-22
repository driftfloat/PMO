function onboard(index){
	if(confirm("确定要Onborad吗?")){
		//执行一下查询招聘需求
		$.ajax({
			url:path+'/service/demand/queryDemandList',
			dataType:"json",
			async:true,
			cache:false,
			type:"post",
			data:{"hsbcDept.hsbcDeptName":"","hsbcDept.hsbcSubDeptName":""},
			success:function(result){
					$("#candidateId").val(index);
					var url1 = path+'/service/demand/demandOnboard';
					$("#editForm").attr("action",url1);
					$("#editForm").submit();
				}
			});
		
	}
}

function updateDemand(){
	
	var candidateId = $("#candidateId").val();
	var profilesNo = $("#profilesNo").val();
	var interviewedNo = $('#interviewedNo').val();
	var onboardDate1 = $('#onboardDate1').val();
	var plannedOnboardDate1 = $('#plannedOnboardDate1').val();
	var doNumber = $('#doNumber').val();
	$.ajax({
		url:path+'/service/demand/updateDemandOnBoardById',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		data:{"candidateId":candidateId,"profilesNo":profilesNo,"interviewedNo":interviewedNo,"onboardDate":onboardDate1,"doNumber":doNumber,"plannedOnboardDate":plannedOnboardDate1},
		success:function(result){
				if(result){
					//子窗口刷新父窗口
					self.opener.location.reload();
//					self.close();
					//员工注册
					$("#candId").val(candidateId);
					var url1 = path+'/service/employee/index';
					$("#onBoardForm").attr("action",url1);
					$("#onBoardForm").submit();
				}
			}
		});
}