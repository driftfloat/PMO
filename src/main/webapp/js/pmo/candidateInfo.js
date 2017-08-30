var currentPage = "";//当前页码
var pageCount = "";//共几页 

$(function(){
	loadCandidateSkillInfo();
	loadCandidateList();	
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


function editEmployeeInfo(employeeId){
	$("#editForm").attr("action",path+"/service/employee/updateEmployeeInfo.html");
	$("#employeeId").val(employeeId);
	$("#editForm").submit();
}
function loadCandidateSkillInfo(){
	var url = path+'/json/skill.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#skill").append("<option>"+item.name+"</option>");
	       })
	});
}
function loadCandidateList(pageState)
{
	var candidate = new FormData(document.getElementById("candidateForm"));
	if(null != pageState)
	{
		candidate.append("pageState",pageState);
	}
	candidate.append("currentPage",currentPage);
	candidate.append("pageCount",pageCount);
	$.ajax({
		url:path+"/service/candidate/queryCandidateList",
		dataType:"json",
		async:true,
		data:candidate,
		cache:false,
		processData:false,
        contentType:false,
		type:"post",
		success:function(result){
			$("#candidateList tbody").remove();
			var tbody = $("<tbody>");
			tbody.appendTo($("#candidateList"));
			if(result.data.length <= 0 ){
				$("#exportExcel").attr("disabled",true);
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				$("<td colspan='14' style='color: red;text-align: center;'>未查询到数据！</td>").appendTo(tr);
			}else{
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
						"onclick=editEmployeeInfo('"+result.data[i].candidateId+"')>EDIT</a>" +
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