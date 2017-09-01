var currentPage = "";//当前页码
var pageCount = "";//共几页 

var csSubDeptName0 = "";
	
var csBuName0 = "";
$(function(){
//	loadCSDept();
	
//	loadEmployeeList();
	loadCandidateList();
//	loadCSSubDept();
	
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
	loadCandidateList();
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
		data:{"condition":condition,"csDeptName":csDeptName,"csSubDeptName":csSubDeptName,"csBuName":csBuName},
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


function editEmployeeInfo(employeeId){
	$("#editForm").attr("action",path+"/service/employee/updateEmployeeInfo.html");
	$("#employeeId").val(employeeId);
	$("#editForm").submit();
}
function updateResumeInfo(candidateId){
	$("#editForm").attr("action",path+"/service/resume/updateResume.html");
	$("#candidateId").val(candidateId);
	$("#editForm").submit();
}

function loadCandidateList(pageState)
{
	var candidateStatus = $("#candidateStatus").find("option:selected").val();
	var education = $("#education").find("option:selected").val();
	var majorStatus = $("#majorStatus").find("option:selected").val();
	var englishLevel = $("#englishLevel").find("option:selected").val();
	var candidateName = $("#candidateName").val();
	var source = $("#source").val();
	var candidateTel = $("#candidateTel").val();
	var email = $("#email").val();
	var experienceYears = $("#experienceYears").val();
	var skill = $("#skill").val();
	var userName = $("#userName").val();

	$.ajax({
		url:path+"/service/candidate/queryCandidateList",
		dataType:"json",
		async:true,
		data:{"candidateStatus":candidateStatus,"education":education,"majorStatus":majorStatus,"englishLevel":englishLevel,"candidateName":candidateName,"source":source,"candidateTel":candidateTel,"email":email,"experienceYears":experienceYears,"skill":skill,"userName":userName,"currentPage":currentPage,"pageCount":pageCount,"pageState":pageState},
		cache:false,
		type:"post",
		success:function(result){
			$("#candidateList tbody").remove();
			var tbody = $("<tbody>");
			tbody.appendTo($("#candidateList"));
			if(result.data.length > 0 ){
				$("#exportExcel").removeAttr("disabled");
			}
			for (var i = 0; i < result.data.length; i++) {
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				$("<td><a href='javascript:void(0);'" +
						"onclick=viewCandidataInfo('"+result.data[i].candidateId+"')>" +
						result.data[i].candidateName+"</a></td>").appendTo(tr);
				$("<td>"+ result.data[i].candidateSex+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].candidateAge+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].candidateTel+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].email+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].source+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].candidateStatus+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].education+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].experienceYears+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].majorStatus+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].englishLevel+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].skill+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].userName+ "</td>").appendTo(tr);
				$("<td><a href='javascript:void(0);'" +
						"onclick=updateResumeInfo('"+result.data[i].candidateId+"')>EDIT</a>" +
						"&nbsp;&nbsp;<a href='javascript:void(0);'" +
							"onclick=editResumeInfo('"+result.data[i].candidateId+"')>DETAIL</a>" +
						"&nbsp;&nbsp;<a href='javascript:void(0);'" +
							"onclick=editEmployeeInfo('"+result.data[i].candidateId+"')>RESUME</a>" +
				"</td>").appendTo(tr);
			}
			$("#candidateList").append("</tbdoy>");
			currentPage = parseInt(result.pageInfo.currentPage);
			pageCount = parseInt(result.pageInfo.pageCount);
			var pageDataCount = parseInt(result.pageInfo.pageDataCount);
			var dataCount = parseInt(result.pageInfo.dataCount);
			$("#pageCount").html(pageCount);
			$("#currentPage").html(currentPage);
			$("#pageDataCount").html(pageDataCount);
			$("#dataCount").html(dataCount);
			$("#nextPage").attr("onclick","loadCandidateList('next')");
			$("#previousPage").attr("onclick","loadCandidateList('previous')");
			if(currentPage == pageCount){
				$("#nextPage").removeAttr("onclick");
			}
			if(currentPage == 1){
				$("#previousPage").removeAttr("onclick");
			}
		}
	})
}