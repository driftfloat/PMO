
$(function(){
	loadDu();
	loadEmployeeList();
	
})

$("#pageRecordsNum").change(function(){
	loadEmployeeList();
})

function loadEmployeeList(pageState,csSubDeptName,csBuName){

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
		data:{"csBuName":csBuName,"csSubDeptName":csSubDeptName,"staffName":staffName,"pageState":pageState,"hsbcStaffId":hsbcStaffId,"eHr":eHr,"lob":lob,"pageRecordsNum":pageRecordsNum},
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
			loadCSBu(result);
			
		}
		
	})
}

function viewLogList(employeeId){
	$("#viewLogForm").attr("action",path+"/service/employee/employeeLogInfo.html");
	$("#employeeId").val(employeeId);
	$("#viewLogForm").submit();
}

function loadDu(){
	$.ajax({
		url:path+'/service/csDept/queryAllCSSubDept',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			var userType = result.user.userType;
			
			var csSubs = result.csSubDepts;
			if(userType=='0'){
				for(var i = 0;i<result.data.length;i++){
					$("#csSubDept").append("<option value='"+result.data[i].csSubDeptId+"'>"+result.data[i].csSubDeptName+"</option>");
				}
			}else{
				if(csSubs.length==1){
					$("#csSubDept").append("<option value='"+csSubs[0].csSubDeptId+"'>"+csSubs[0].csSubDeptName+"</option>");
					$('#csSubDept').val(csSubs[0].csSubDeptId);
					$("#csSubDept").attr("disabled","disabled");
				}else if(csSubs.length>1){
					$("#csSubDept").empty();
					$("#csSubDept").append("<option value=''>--Option--</option>");
					for(var i = 0;i<csSubs.length;i++){
						$("#csSubDept").append("<option value='"+csSubs[i].csSubDeptId+"'>"+csSubs[i].csSubDeptName+"</option>");
					}
				}
			}
		}
	})
}

function loadCSBu(result){
	var csBuNames = result.csBuNames;
	var userType = result.user.userType;
	var url = path+'/json/csBuName.json'
	$.getJSON(url,  function(data) {
		   $("#csBu").empty();
		   $("#csBu").append("<option value=''>--Option--</option>");
	       $.each(data, function(i, item) {
	    	   $("#csBu").append("<option>"+item.name+"</option>");
	       })
	       if(userType=='1' || userType=='2' || userType=='3' || userType=='4'||userType=='5'){
	    	   if(csBuNames.length==1){   		   
	    		   $('#csBu').val(result.user.bu);
	    		   $("#csBu").attr("disabled","disabled");
	    	   }else if(csBuNames.length>1){
	    		   $("#csBu").empty();
	    		   $("#csBu").append("<option value=''>--Option--</option>");
	    		   for(var i = 0;i<csBuNames.length;i++){
						$("#csBu").append("<option>"+csBuNames[i]+"</option>");
						$('#csBu').val(result.pageInfo.csbuName);
					}
	    	   }
			}else{
				$('#csBu').val(result.pageInfo.csbuName);
			}
	});
}

$('#searchBtn').bind("click", function(){
	var csBuName = $("#csBu").find("option:selected").text();
    //alert(csBuName);
	if(csBuName.indexOf('Option')!=-1){
		csBuName = "";
	}
	
	var csSubDeptName = $("#csSubDept").find("option:selected").text();
	
	if(csSubDeptName.indexOf('Option')!=-1){
		csSubDeptName = "";
	}
	
	loadEmployeeList("",csSubDeptName,csBuName);
});


