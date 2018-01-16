
window.onload = function(){
	loadEmployeeLogInfo();
}

function loadEmployeeLogInfo(){
	var employeeLogId = $('#employeeLogId').val();
	//alert(employeeLogId);
	$.ajax({
		url:path+'/service/employee/queryEmployeeLogById',
		dataType:"json",
		data:{"employeeLogId":employeeLogId},
		async:true,
		cache:false,
		type:"post",
		success:function(employeeLog){
			$('#employeeId').val(employeeLog.employeeId);
			$('#csSubdeptIdNewName').val(employeeLog.csSubdeptIdNewName);
			$('#csSubdeptIdOriginalName').val(employeeLog.csSubdeptIdOriginalName);
			$('#roleNew').val(employeeLog.roleNew);
			$('#roleOriginal').val(employeeLog.roleOriginal);
			$('#statusNew').val(employeeLog.statusNew);
			$('#statusOriginal').val(employeeLog.statusOriginal);
			$('#updateDate').val(employeeLog.updateDate);
			$('#operationPersonName').val(employeeLog.operationPersonName);
			if(employeeLog.logType=="1"){
				$('#logType').val("Update");
			}
            if(employeeLog.logType=="0"){
            	$('#logType').val("Add");
			}
			$('#changeInformation').val(employeeLog.changeInformation);
		}
	})
}


