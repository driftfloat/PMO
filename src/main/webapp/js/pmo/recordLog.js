
$(function(){
	
	loadEmployeeList();
	
})

function loadEmployeeList(pageState){

	var lob = $("#lob").val();
	var hsbcStaffId = $("#hsbcStaffId").val();
	var eHr = $("#eHr").val();
	var staffName = $("#staffName").val();
	
	var pageState = pageState;
	
	var pageRecordsNum = $("#pageRecordsNum").find("option:selected").text();
	
	$.ajax({
		url:path+"/service/employeeInfo/queryList",
		dataType:"json",
		async:true,
		data:{"staffName":staffName,"pageState":pageState,"hsbcStaffId":hsbcStaffId,"eHr":eHr,"lob":lob,"pageRecordsNum":pageRecordsNum},
		cache:false,
		type:"post",
		success:function(result){
			$("#employeeList tbody").remove();
			
			var tbody = $("<tbody>");
			tbody.appendTo($("#employeeList"));
			
			if(result.data.length > 0 ){
				$("#exportExcel").removeAttr("disabled");
			}
			var userType = result.user.userType;
			for (var i = 0; i < result.data.length; i++) {
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				
				var td1 = $("<td>"
						+ result.data[i].staffName
						+ "</td>");
				var td2 = $("<td>"
						+ result.data[i].eHr
						+ "</td>");
				var td3 = $("<td>"
						+ result.data[i].lob
						+ "</td>");
				var td4 = $("<td id='tx1'>"
						+ result.data[i].hsbcStaffId
						+ "</td>");
				var td5 = $("<td>"
						+ result.data[i].csSubDeptName
						+ "</td>");
				var td6 = $("<td><center>"
						+ result.data[i].engagementType
						+ "</center></td>");
				var td7 = $("<td>"
						+ result.data[i].resourceStatus
						+ "</td>");
				/*var td71 = $("<td>"
						+ result.data[i].nickname
						+ "</td>");*/
				//var td7 = $("<td><a class='btn btn-info' href='javascript:void(0);'> <i class='glyphicon glyphicon-edit icon-white'></i> 编辑</a></td>");
				var td8 = null;
				var engagementType = result.data[i].engagementType.replace(/\s+/g,"");
				if(userType=='5' || userType=='6'){
					td8 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=viewLogList('"+result.data[i].employeeId+"')>ViewLog</a></td>");
				}else if(userType=='3'){
					if($("#userId").val()==result.data[i].rmUserId || result.data[i].rmUserId=='' || result.data[i].rmUserId==null){
						td8 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=viewLogList('"+result.data[i].employeeId+"')>ViewLog</a>" +
								"</td>");
					}else{
						td8 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=viewLogList('"+result.data[i].employeeId+"')>ViewLog</a></td>");
					}
				}else{
					td8 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=viewLogList('"+result.data[i].employeeId+"')>ViewLog</a>" +
							"</td>");
				}
				if((result.data[i].hsbcStaffId)==null){
					var td4 = $("<td></td>");
				}
				if((result.data[i].eHr)==null){
					var td2 = $("<td></td>");
				}
				if((result.data[i].lob)==null){
					var td3 = $("<td></td>");
				}
				if((result.data[i].csSubDeptName)==null){
					var td5 = $("<td></td>");
				}
				if((result.data[i].resourceStatus)==null){
					var td7 = $("<td></td>");
				}
				/*if((result.data[i].nickname)==null){
					var td71 = $("<td></td>");
				}*/
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				td7.appendTo(tr);
				//td71.appendTo(tr);
				td8.appendTo(tr);
			}
			$("#employeeList").append("</tbdoy>");
			//alert(window.location.href);
			var pageNum = parseInt(result.pageInfo.currentPage);
			var pageRecordsNum = parseInt(result.pageInfo.pageRecordsNum);
			pageNum = pageNum / pageRecordsNum + 1;
			var totalPage = parseInt(result.pageInfo.pageCount);
			$("#pageCount").html(totalPage);
			$("#currentPage").html(pageNum);
			$("#nextPage").attr("onclick","loadEmployeeList('next')");
			$("#previousPage").attr("onclick","loadEmployeeList('previous')");
			if(pageNum == totalPage){
				$("#nextPage").removeAttr("onclick");
			}
			if(pageNum == 1){
				$("#previousPage").removeAttr("onclick");
			}
			
		}
		
	})
}

function viewLogList(employeeId){
	$("#viewLogForm").attr("action",path+"/service/employee/employeeLogInfo.html");
	$("#employeeId").val(employeeId);
	$("#viewLogForm").submit();
}

$('#searchBtn').bind("click", function(){
	loadEmployeeList("");
});


