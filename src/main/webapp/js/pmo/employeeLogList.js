
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
				var tr = $("<table class='table table-striped table-bordered' border='1' width='100%'>" +
						"<tr>"+
						"<td style='font-weight:500;line-height:1.1;color:#317eac;'>Date</td>"+
						"<td style='font-weight:500;line-height:1.1;color:#317eac;'>"+result.data[i].updateDate.replace('.0','')+"</td>"+
						"<td style='font-weight:500;line-height:1.1;color:#317eac;'>OperatePerson</td>"+
						"<td style='font-weight:500;line-height:1.1;color:#317eac;'>"+result.data[i].operationPersonName+"</td>"+
						"</tr>"+
						"<tr>"+
						"<td style='font-weight:500;line-height:1.1;color:#317eac;'>NewDU</td>"+
						"<td style='font-weight:500;line-height:1.1;color:#317eac;'>"+result.data[i].csSubdeptIdNewName+"</td>"+
						"<td style='font-weight:500;line-height:1.1;color:#317eac;'>NewStatus</td>"+
						"<td style='font-weight:500;line-height:1.1;color:#317eac;'>"+result.data[i].statusNew+"</td>"+
						"</tr>"+
						"<tr>"+
						"<td style='font-size: 14px;color: #555555;' colspan='4'>"+result.data[i].changeInformation+"</td>"+
						"</tr>"+
						"</table>");
				tr.appendTo(tbody);
				/*var td1 = $("<td>"
						+ result.data[i].updateDate
						+ "</td>");
				var td2 = $("<td>"
						+ result.data[i].operationPersonName
						+ "</td>");
				if(result.data[i].logType=="1"){
					var td3 = $("<td>"
							+ "Update"
							+ "</td>");
				}
				if(result.data[i].logType=="0"){
					var td3 = $("<td>"
							+ "Add"
							+ "</td>");
				}
				//td7 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=employeeLogDetail('"+result.data[i].logId+"')>Detail</a></td>");
				
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);*/
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

