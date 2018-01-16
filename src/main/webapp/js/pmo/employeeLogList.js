
$(function(){
	
	loadEmployeeLogList();
	
})

function loadEmployeeLogList(pageState){
	
	var pageState = pageState;
	var employeeId = $('#employeeId').val();
	var pageRecordsNum = $("#pageRecordsNum").find("option:selected").text();
	
	$.ajax({
		url:path+"/service/employee/queryEmployeeLogByID",
		dataType:"json",
		async:true,
		data:{"employeeID":employeeId,"pageState":pageState,"pageRecordsNum":pageRecordsNum},
		cache:false,
		type:"post",
		success:function(result){
			$("#lob").val(result.employee.lob);
			$("#hsbcStaffId").val(result.employee.hsbcStaffId);
			$("#eHr").val(result.employee.eHr);
			$("#staffName").val(result.employee.staffName);
			
			$("#employeeLogList tbody").remove();
			
			var tbody = $("<tbody>");
			tbody.appendTo($("#employeeLogList"));
			for (var i = 0; i < result.data.length; i++) {
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
		
				var td1 = $("<td>"
						+ result.data[i].employeeId
						+ "</td>");
				
				var td2 = $("<td>"
						+ result.data[i].csSubdeptIdNewName
						+ "</td>");
				var td3 = $("<td>"
						+ result.data[i].csSubdeptIdOriginalName
						+ "</td>");
				var td4 = $("<td>"
						+ result.data[i].updateDate
						+ "</td>");
				var td5 = $("<td>"
						+ result.data[i].operationPersonName
						+ "</td>");
				if(result.data[i].logType=="1"){
					var td6 = $("<td>"
							+ "Update"
							+ "</td>");
				}
				if(result.data[i].logType=="0"){
					var td6 = $("<td>"
							+ "Add"
							+ "</td>");
				}
				td7 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=employeeLogDetail('"+result.data[i].logId+"')>Detail</a></td>");
				
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				td7.appendTo(tr);
			}
			$("#employeeLogList").append("</tbdoy>");
			var pageNum = parseInt(result.pageInfo.currentPage);
			var pageRecordsNum = parseInt(result.pageInfo.pageRecordsNum);
			pageNum = pageNum / pageRecordsNum + 1;
			var totalPage = parseInt(result.pageInfo.pageCount);
			$("#pageCount").html(totalPage);
			$("#currentPage").html(pageNum);
			$("#nextPage").attr("onclick","loadEmployeeLogList('next')");
			$("#previousPage").attr("onclick","loadEmployeeLogList('previous')");
			if(pageNum == totalPage){
				$("#nextPage").removeAttr("onclick");
			}
			if(pageNum == 1){
				$("#previousPage").removeAttr("onclick");
			}
			
		}
		
	})
}

function employeeLogDetail(logID){
	$("#viewLogDetailForm").attr("action",path+"/service/employee/employeeLogDetail.html");
	$("#employeeLogId").val(logID);
	$("#viewLogDetailForm").submit();
}

