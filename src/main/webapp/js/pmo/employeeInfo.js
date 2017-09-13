var csDeptName0 = "";

var csSubDeptName0 = "";
	
var csBuName0 = "";

$(function(){
	
	loadCSDept();
	
	loadEmployeeList();
	
	loadCSSubDept();
	
})


$("#csDept").change(function(){
	$("#exportExcel").attr("disabled", true);
})


$("#csSubDept").change(function(){
	$("#exportExcel").attr("disabled", true);
})


$("#csBu").change(function(){
	$("#exportExcel").attr("disabled", true);
})


function loadCSDept(){
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
}


$('#searchBtn').bind("click", function(){
	var csDeptName = $("#csDept").find("option:selected").text();

	if(csDeptName.indexOf('请选择')!=-1){
		csDeptName = "";
	}
	
	var csBuName = $("#csBu").find("option:selected").text();

	if(csBuName.indexOf('请选择')!=-1){
		csBuName = "";
	}
	
	var csSubDeptName = $("#csSubDept").find("option:selected").text();
	
	if(csSubDeptName.indexOf('请选择')!=-1){
		csSubDeptName = "";
	}
	
	loadEmployeeList("",csDeptName,csSubDeptName,csBuName);
});

$('#exportExcel').bind("click", function(){
	
	$('#myModal').modal('show');
	
});



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
	
	var csDeptName = csDeptName0;

	var csSubDeptName = csSubDeptName0;
	
	var csBuName = csBuName0;

	$.ajax({
		url:path+'/service/employeeInfo/setEmpConditon',
		dataType:"json",
		data:{"condition":condition,"csSubDeptName":csSubDeptName,"csBuName":csBuName},
		async:true,
		cache:false,
		type:"post",
	})
	
	var url = path+'/service/employee/exportExcel';
	$("#exceltHref").attr("href",url);
	document.getElementById("exceltHref").click();
	
	
	$('#myModal').modal('hide');
	$("[type='checkbox']").removeAttr("checked");
}


function loadCSSubDept(){
	$.ajax({
		url:path+'/service/csDept/queryAllCSSubDeptName',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			for(var i = 0;i<list.length;i++){
				$("#csSubDept").append("<option>"+list[i].csSubDeptName+"</option>");
			}
		}
	})
}

/*$("#csDept").change(function(){
	var csSubDeptId = $('#csDept').val();
	$("#csSubDept").find("option").remove(); 
	$("#csSubDept").append("<option value=''>-- 请选择项目 --</option>");
	$.ajax({
		url:path+'/service/csDept/queryCSSubDeptName',
		dataType:"json",
		async:true,
		data:{"csSubDeptId":csSubDeptId},
		cache:false,
		type:"post",
		success:function(list){
			$("#csSubDept").find("option").remove(); 
			$("#csSubDept").append("<option value=''>-- 请选择子交付部 --</option>");
			for(var i = 0;i<list.length;i++){
				$("#csSubDept").append("<option value='"+list[i].csSubDeptId+"'>"+list[i].csSubDeptName+"</option>");
			}
		}
	})
})
*/

function editEmployeeInfo(employeeId){
	$("#editForm").attr("action",path+"/service/employee/updateEmployeeInfo.html");
	$("#employeeId").val(employeeId);
	$("#editForm").submit();
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

	var pageState = pageState;
	
	$.ajax({
		url:path+"/service/employeeInfo/queryEmployeeList",
		dataType:"json",
		async:true,
		data:{"pageState":pageState,"csBuName":csBuName,"csSubDeptName":csSubDeptName,"hsbcStaffId":hsbcStaffId,"eHr":eHr},
		cache:false,
		type:"post",
		success:function(result){
			$("#employeeList tbody").remove();
			
			var tbody = $("<tbody>");
			tbody.appendTo($("#employeeList"));
			
			if(result.data.length > 0 ){
				$("#exportExcel").removeAttr("disabled");
			}
			
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
						+ result.data[i].staffName
						+ "</td>");
				var td4 = $("<td>"
						+ result.data[i].ln
						+ "</td>");
				var td5 = $("<td>"
						+ result.data[i].csSubDeptName
						+ "</td>");
				//var td7 = $("<td><a class='btn btn-info' href='javascript:void(0);'> <i class='glyphicon glyphicon-edit icon-white'></i> 编辑</a></td>");
				var td6 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=editEmployeeInfo('"+result.data[i].employeeId+"')>EDIT</a></td>");
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				
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
		}
	})
}
