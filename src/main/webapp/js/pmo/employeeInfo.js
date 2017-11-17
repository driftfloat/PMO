var csDeptName0 = "";

var csSubDeptName0 = "";
	
var csBuName0 = "";

$(function(){
	
	loadEmployeeList();
	
})

function loadResourceStatus(){
	var url = path+'/json/resourceStatus.json'
	$.getJSON(url,  function(data) {
		   $("#resourceStatus").empty();
		   $("#resourceStatus").append("<option value=''>--Option--</option>");
	       $.each(data, function(i, item) {
	    	   $("#resourceStatus").append("<option>"+item.name+"</option>");
	       })
	});
}


$("#csDept").change(function(){
	$("#exportExcel").attr("disabled", true);
})


$("#csSubDept").change(function(){
	$("#exportExcel").attr("disabled", true);
})


$("#csBu").change(function(){
	$("#exportExcel").attr("disabled", true);
})


/*function loadCSDept(){
	$.ajax({
		url:path+'/service/csDept/queryCSDeptName',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			for(var i = 0;i<list.length;i++){
				$("#csDept").append("<option value='"+list[i].csSubDeptId+"'>"+list[i].csDeptName+"</option>");
			}
		}
	})
}*/


$('#searchBtn').bind("click", function(){
	var csDeptName = $("#csDept").find("option:selected").text();

	if(csDeptName.indexOf('Option')!=-1){
		csDeptName = "";
	}
	
	var csBuName = $("#csBu").find("option:selected").text();

	if(csBuName.indexOf('Option')!=-1){
		csBuName = "";
	}
	
	var csSubDeptName = $("#csSubDept").find("option:selected").text();
	
	if(csSubDeptName.indexOf('Option')!=-1){
		csSubDeptName = "";
	}
	
	loadEmployeeList("",csDeptName,csSubDeptName,csBuName);
});

$('#exportExcel').bind("click", function(){
	
	$('#myModal').modal('show');
	
});

function selectAll(){

	$("input[type='checkbox']").attr("checked",'true');
}

function exportCondition(){
	var lb = $("label input");
	var condition = "";
	for (var i=0;i<lb.length;i++)
	{
		if (lb.eq(i).is(':checked'))
		{
			condition += lb.eq(i).attr("name")+",";
		}
	}

	$.ajax({
		url:path+'/service/employeeInfo/setEmpConditon',
		dataType:"json",
		data:{"condition":condition},
		async:true,
		cache:false,
		type:"post",
		success:function(resultFlag){
			var url = path+'/service/employee/exportExcel';
			$("#exceltHref").attr("href",url);
			document.getElementById("exceltHref").click();
			$('#myModal').modal('hide');
			$("[type='checkbox']").removeAttr("checked");

		}
	})
	
	
}





function loadCSSubDept(result){
	var userType = result.user.user_type;
	$.ajax({
		url:path+'/service/csDept/queryAllCSSubDeptName',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			$("#csSubDept").empty();
			$("#csSubDept").append("<option value=''>--Option--</option>");
			for(var i = 0;i<list.length;i++){
				$("#csSubDept").append("<option>"+list[i].csSubDeptName+"</option>");
			}
			
			if(userType=='2' || userType=='3' || userType=='4'){
				$('#csSubDept').val(result.csSubDeptName);
				$("#csSubDept").attr("disabled","disabled");
			}
		}
	})
}


function editEmployeeInfo(employeeId){
	$("#editForm").attr("action",path+"/service/employee/updateEmployeeInfo.html");
	$("#employeeId").val(employeeId);
	$("#editForm").submit();
}

function loadCSBu(result){
	var userType = result.user.user_type;
	var url = path+'/json/csBuName.json'
	$.getJSON(url,  function(data) {
		   $("#csBu").empty();
		   $("#csBu").append("<option value=''>--Option--</option>");
	       $.each(data, function(i, item) {
	    	   $("#csBu").append("<option>"+item.name+"</option>");
	       })
	       if(userType=='1' || userType=='2' || userType=='3' || userType=='4'){
				$('#csBu').val(result.user.bu);
				$("#csBu").attr("disabled","disabled");
			}
	});
}

function loadEmployeeList(pageState,csDeptName,csSubDeptName,csBuName){
	var csDeptName = csDeptName;

	csDeptName0 = csDeptName;
	
	var csSubDeptName = csSubDeptName;
	
	csSubDeptName0 = csSubDeptName;
	
	var csBuName = csBuName;
	
	csBuName0 = csBuName;
	
	var hsbcStaffId = $("#hsbcStaffId").val();
	
	var eHr = $("#eHr").val();
	
	var lob = $("#lob").val();
	
	var resourceStatus = $("#resourceStatus").val();
	
	var staffName = $("#staffName").val();

	var pageState = pageState;
	
	$.ajax({
		url:path+"/service/employeeInfo/queryEmployeeList",
		dataType:"json",
		async:true,
		data:{"staffName":staffName,"resourceStatus":resourceStatus,"pageState":pageState,"csBuName":csBuName,"csSubDeptName":csSubDeptName,"hsbcStaffId":hsbcStaffId,"eHr":eHr,"lob":lob},
		cache:false,
		type:"post",
		success:function(result){
			$("#employeeList tbody").remove();
			
			var tbody = $("<tbody>");
			tbody.appendTo($("#employeeList"));
			
			if(result.data.length > 0 ){
				$("#exportExcel").removeAttr("disabled");
			}
			var userType = result.user.user_type;
			for (var i = 0; i < result.data.length; i++) {
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);

				var td1 = $("<td id='tx1'>"
						+ result.data[i].hsbcStaffId
						+ "</td>");
				var td2 = $("<td>"
						+ result.data[i].eHr
						+ "</td>");
				var td3 = $("<td>"
						+ result.data[i].lob
						+ "</td>");
				var td4 = $("<td>"
						+ result.data[i].staffName
						+ "</td>");
				var td5 = $("<td>"
						+ result.data[i].ln
						+ "</td>");
				var td6 = $("<td>"
						+ result.data[i].csSubDeptName
						+ "</td>");
				var td7 = $("<td>"
						+ result.data[i].resourceStatus
						+ "</td>");
				//var td7 = $("<td><a class='btn btn-info' href='javascript:void(0);'> <i class='glyphicon glyphicon-edit icon-white'></i> 编辑</a></td>");
				var td8 = null;
				if(userType=='5' || userType=='6'){
					td8 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=employeeDetail('"+result.data[i].employeeId+"')>Detail</a></td>");
				}else{
					td8 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=employeeDetail('"+result.data[i].employeeId+"')>Detail</a>" +
						"<a href='javascript:void(0);' class='btn btn-info btn-small' onclick=editEmployeeInfo('"+result.data[i].employeeId+"')>Edit</a></td>");
				}
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				td7.appendTo(tr);
				td8.appendTo(tr);
			}
			$("#employeeList").append("</tbdoy>");
			//alert(window.location.href);
			var pageNum = parseInt(result.pageInfo.currentPage);
			pageNum = pageNum / 10 + 1;
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
			
			
			
			loadResourceStatus();
			
			loadCSSubDept(result);
			
			loadCSBu(result);
		}
		
	})
}

function employeeDetail(employeeId){
	$("#editForm").attr("action",path+"/service/interview/employeeDetailInfo.html");
	$("#employeeId").val(employeeId);
	$("#editForm").submit();
}
