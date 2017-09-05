var currentPage = "";//当前页码
var pageCount = "";//共几页 
var exportdata;
$(function(){
	loadCandidateSkillInfo();
})

$('#searchCandidateBtn').bind("click", function(){
	loadCandidateList();
});

$('#exportCandidateExcel').bind("click", function(){	
	exportdata = new FormData(document.getElementById("candidateForm"));
	$('#myCandidateListModal').modal('show');	
});

function exportCondition(){
	var lb = $("label input");
	var exportDataColumn = "";
	var exportPageColumn = "";
	if(lb.length <= 0){
		alert("未勾选导出列！");
		return;
	}
	for (var i=0;i<lb.length;i++)
	{
		if (lb.eq(i).is(':checked'))
		{
			exportDataColumn += lb.eq(i).attr("name")+",";
			exportPageColumn += lb.eq(i).val()+",";
		}
	}
	
	exportdata.append("exportDataColumn",exportDataColumn);
	exportdata.append("exportPageColumn",exportPageColumn);
	$.ajax({
		url:path+'/service/candidate/transformCandidateData',
		dataType:"json",
		data:exportdata,
		async:true,
		cache:false,
		processData:false,
        contentType:false,
		type:"post",
		success:function(result){
			if(result == '1')
			{
				exportData();
			}
		}
	})
}

function exportData(){
	var url = path+'/service/candidate/exportExcel';
	$("#exceltHrefCandidate").attr("href",url);
	document.getElementById("exceltHrefCandidate").click();
	
	$('#myCandidateListModal').modal('hide');
	$("[type='checkbox']").attr("checked","checked");
}

function updateResumeInfo(candidateId){
	$("#editForm").attr("action",path+"/service/resume/toUpdateResume.html");
	$("#candidateId").val(candidateId);
	$("#editForm").submit();
}

function downLoadCandidateResume(candidateId,resumePath){
	if(resumePath == null || resumePath == ''){
		alert("未上传此候选人简历");
		return;
	}
	var url = path+'/service/candidate/downLoadCandidateResume?candidateId='+candidateId;
	$("#exceltHrefCandidate").attr("href",url);
	document.getElementById("exceltHrefCandidate").click();
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
				$("#exportCandidateExcel").attr("disabled",true);
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				$("<td colspan='14' style='color: red;text-align: center;'>未查询到数据！</td>").appendTo(tr);
			}else{
				$("#exportCandidateExcel").removeAttr("disabled");
			}
			for (var i = 0; i < result.data.length; i++) {
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				$("<td><a href='javascript:void(0);'" +
						"onclick=viewCandidataInfo('"+result.data[i].candidateId+"')>" +
						result.data[i].candidateName+"</a></td>" +
				"<td>"+ result.data[i].candidateSex+ "</td>" +
				"<td>"+ result.data[i].candidateAge+ "</td>" +
				"<td>"+ result.data[i].candidateTel+ "</td>" +
				"<td>"+ result.data[i].email+ "</td>" +
				"<td>"+ result.data[i].source+ "</td>" +
				"<td>"+ result.data[i].candidateStatus+ "</td>" +
				"<td>"+ result.data[i].education+ "</td>" +
				"<td>"+ result.data[i].experienceYears+ "</td>" +
				"<td>"+ result.data[i].majorStatus+ "</td>" +
				"<td>"+ result.data[i].englishLevel+ "</td>" +
				"<td>"+ result.data[i].skill+ "</td>" +
				"<td>"+ result.data[i].userName+ "</td>").appendTo(tr);
				
				if(result.data[i].candidateStatus == '闲置中'){
					$("<td><a href='javascript:void(0);' class='btn btn-info btn-small' " +
						"onclick=updateResumeInfo('"+result.data[i].candidateId+"')>EDIT</a>" +
						"<a href='javascript:void(0);' class='btn btn-info btn-small' " +
							"onclick=downLoadCandidateResume('"+result.data[i].candidateId+"','"+result.data[i].resumePath.replace(/\s+/g, "")+"')>RESUME</a>" +
					"</td>").appendTo(tr);
				}else{
					$("<td><a href='javascript:void(0);' class='btn btn-info btn-small' " +
							"onclick=downLoadCandidateResume('"+result.data[i].candidateId+"','"+result.data[i].resumePath.replace(/\s+/g, "")+"')>RESUME</a>" +
					"</td>").appendTo(tr);
				}
				
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
			$("#lastPage").attr("onclick","loadCandidateList('last')");
			$("#fristPage").attr("onclick","loadCandidateList('frist')");
			if(currentPage == pageCount){
				$("#nextPage").removeAttr("onclick");
				$("#lastPage").removeAttr("onclick");
			}
			if(currentPage == 1){
				$("#previousPage").removeAttr("onclick");
				$("#fristPage").removeAttr("onclick");
			}
		}
	})
}