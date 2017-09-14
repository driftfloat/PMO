var currentPage = "";//当前页码
var pageCount = "";//共几页 
var exportdata;
//页面加载执行的方法
$(function(){
	loadCandidateSkillInfo();
	loadRole();
	loadStayinList();
})
//查询按钮绑定
$('#searchStayinBtn').bind("click", function(){
	loadStayinList();
});
//到处按钮绑定
$('#exportCandidateExcel').bind("click", function(){	
	exportdata = new FormData(document.getElementById("stayinForm"));
	$('#myStayinListModal').modal('show');	
});
//导出条件
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
		url:path+'/service/stayin/transformCandidateData',
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
	var url = path+'/service/stayin/exportExcel';
	$("#exceltHrefCandidate").attr("href",url);
	document.getElementById("exceltHrefCandidate").click();
	
	$('#myStayinListModal').modal('hide');
	$("[type='checkbox']").attr("checked","checked");
}

/*function editEmployeeInfo(employeeId){
	$("#editForm").attr("action",path+"/service/employee/updateEmployeeInfo.html");
	$("#employeeId").val(employeeId);
	$("#editForm").submit();
}
*/



function loadCandidateSkillInfo(){
	var url = path+'/json/skill.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#skill").append("<option>"+item.name+"</option>");
	       })
	});
}
function loadRole(){
	var url = path+'/json/position.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#role").append("<option>"+item.name+"</option>");
	       })
	});
}


function loadStayinList(pageState)
{
	var candidate = new FormData(document.getElementById("stayinForm"));
	if(null != pageState)
	{
		candidate.append("pageState",pageState);
	}
	candidate.append("currentPage",currentPage);
	candidate.append("pageCount",pageCount);
	$.ajax({
		url:path+"/service/stayin/queryStayinList",
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
						result.data[i].candidateId+"</a></td>").appendTo(tr);
				$("<td>"+ result.data[i].candidateName+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].candidateSex+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].candidateAge+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].candidateTel+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].email+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].skill+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].role+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].collage+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].majorStatus+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].englishLevel+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].education+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].experienceYears+ "</td>").appendTo(tr);
			
				$("<td><a href='javascript:void(0);'" +
						"onclick=onboard('"+result.data[i].candidateId+"')>ONBOARD</a>" +
					     "&nbsp;&nbsp;<a href='javascript:void(0);'"+
					     "onclick=updateDemand('"+result.data[i].candidateId+"')>UPDATE</a>"+
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

function queryDemandList(candidateId){
	var str = '<table id="demandList" class="table table-bordered table-hover">'+
	'<thead><tr><th></th><th>RR</<th><th>Job Code<th/><th>Tech/Skill</th>'+
	'<th>Position</th><th>Deparment</th><th>Sub-Department</th>'+
	'<th>Status</th><th>交付部</th><th>确认更改</th></tr></thead></table>';
	$("#table_area").append(str);
	/*delegate() 方法为指定的元素（属于被选元素的子元素）添加一个或多个事件处理程序，并规定当这些事件发生时运行的函数。
	使用 delegate() 方法的事件处理程序适用于当前或未来的元素（比如由脚本创建的新元素）。*/
	$("#demandList").delegate("tr","click",function(e){
		$(this).find("input[type=radio]").prop("checked",true);
		
	});
	$.ajax({
		url:path+'/service/stayin/addDemandList',
		dataType:'json',
		async:false,
		cache:false,
		type:'post',
		data:{"candidateId":candidateId},
		success:function(result){
			
		}
	})
	
}












