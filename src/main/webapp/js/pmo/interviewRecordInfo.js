var currentPage = "";// 当前页码
var pageCount = "";// 共几页
$(function() {
	loadCandidateList();
});

function loadCandidateList(pageState) {
	var candidate = new FormData(document.getElementById("candidateForm"));
	if (null != pageState) {
		candidate.append("pageState", pageState);
	}
	
	var candidateName = $('#candidateNames').val();
	
	var candidateTel = $('#tel').val();
	
	candidate.append("currentPage", currentPage);
	
	candidate.append("pageCount", pageCount);
	
	candidate.append("candidateName",candidateName);
	
	candidate.append("candidateTel",candidateTel);
	
	$.ajax({
				url : path + "/service/interview/getCandidateList",
				dataType : "json",
				async : true,
				data : candidate,
				cache : false,
				processData : false,
				contentType : false,
				type : "post",
				success : function(result) {
					if(result==null){
						return;
					}
					$("#candidateList tbody").remove();
					var tbody = $("<tbody>");
					tbody.appendTo($("#candidateList"));
					for (var i = 0; i < result.data.length; i++) {
						var tr = $("<tr></tr>");
						tr.appendTo(tbody);
						$("<td>"+ result.data[i].candidateName + "</td>")
								.appendTo(tr);
						$("<td>" + result.data[i].candidateSex + "</td>")
								.appendTo(tr);
						$("<td>" + result.data[i].candidateAge + "</td>")
								.appendTo(tr);
						$("<td>" + result.data[i].candidateTel + "</td>")
								.appendTo(tr);
						$("<td>" + result.data[i].email + "</td>").appendTo(tr);
						$("<td>" + result.data[i].role + "</td>").appendTo(tr);
						$("<td>" + result.data[i].experienceYears + "</td>")
								.appendTo(tr);
						$("<td>" + result.data[i].englishLevel + "</td>")
								.appendTo(tr);
						$("<td>" + result.data[i].skill + "</td>").appendTo(tr);
						$("<td><a href='javascript:void(0);' class='btn btn-info btn-small' "
								+"onclick=interviewRecord('"
								+result.data[i].candidateId
								+"')>InterviewRecord</a></td>").appendTo(tr);
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
					$("#nextPage").attr("onclick", "loadCandidateList('next')");
					$("#previousPage").attr("onclick",
							"loadCandidateList('previous')");
					$("#lastPage").attr("onclick", "loadCandidateList('last')");
					$("#fristPage").attr("onclick",
							"loadCandidateList('frist')");
					if (currentPage == pageCount) {
						$("#nextPage").removeAttr("onclick");
						$("#lastPage").removeAttr("onclick");
					}
					if (currentPage == 1) {
						$("#previousPage").removeAttr("onclick");
						$("#fristPage").removeAttr("onclick");
					}
				}
			});
}

 function interviewRecord(candidateId){
	 $("#editForm").attr("action",path+"/service/interview/interviewRecord.html");
	 $("#candidateId").val(candidateId);
	 $("#editForm").submit();
 }

 
 $("#searchBtn").click(function(){
	 loadCandidateList("");
 });
 
 
//if($("#GRADUATE_DATE1").length != 0){
//	  $('#candidateForm').data("bootstrapValidator").revalidateField($("#GRADUATE_DATE1"));
//}
