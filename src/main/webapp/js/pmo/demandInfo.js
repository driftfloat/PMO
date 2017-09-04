
$(function(){
	loadSkill();
	loadPosition();
	loadDemandStatus();
	
	loadDepartment();
	loadSubDepartment();
	
	loadDemandList();
	$("#searchBtn").click(function(){
		loadDemandList();
	})
	loadCsBuName();
	loadScSubDeptName();
})

$("#skill").change(function(){
	$("#exportExcel").attr("disabled", true);
})
$("#position").change(function(){
	$("#exportExcel").attr("disabled", true);
})
$("#department").change(function(){
	$("#exportExcel").attr("disabled", true);
})
$("#sub_department").change(function(){
	$("#exportExcel").attr("disabled", true);
})
$("#status").change(function(){
	$("#exportExcel").attr("disabled", true);
})
$("#csBuName").change(function(){
	$("#exportExcel").attr("disabled", true);
})
$("#scSubDeptName").change(function(){
	$("#exportExcel").attr("disabled", true);
})

/*加载skill本地json信息*/
function loadSkill(){
	var url = path+'/json/skill.json';
	$.getJSON(url, function(data){
		$.each(data, function(i, item){
			 $("#skill").append("<option value='"+item.name+"'>"+item.name+"</option>");
		})
	})
}
/*加载position本地json信息*/
function loadPosition(){
	var url = path+'/json/position.json';
	$.getJSON(url, function(data){
		$.each(data, function(i, item){
			$("#position").append("<option value='"+item.name+"'>"+item.name+"</option>");
		})
	})
}
/*加载status本地json信息*/
function loadDemandStatus(){
	var url = path+'/json/demandStatus.json';
	$.getJSON(url, function(data){
		$.each(data, function(i, item){
			$("#status").append("<option value='"+item.name+"'>"+item.name+"</option>");
		})
	})
}
/*加载业务部*/
function loadCsBuName(){
	var url = path+'/json/csBuName.json';
	$.getJSON(url, function(data){
		$.each(data,function(i,item){
			$("#csBuName").append("<option value='"+item.name+"'>"+item.name+"</option>");
		})
	})
}
/*加载交付部*/
function loadScSubDeptName(){
	$("#csBuName").change(function(){
		var csBuName = $("#csBuName").val();
		$("#scSubDeptName").empty();
		$("#scSubDeptName").append("<option value=''>-- select --</option>");
		$.ajax({
			url:path+'/service/demand/loadScSubDeptName',
			dataType:"json",
			async:true,
			cache:false,
			type:"post",
			data:{"csBuName":csBuName},
			success:function(data){
				$.each(data, function(i,item){
					$("#scSubDeptName").append("<option value='"+item.csSubDeptName+"'>"+item.csSubDeptName+"</option>");
				})
			}
		})
	})
}
/*异步加载Department信息*/
function loadDepartment(){
	$.ajax({
		url:path+'/service/demand/loadDepartment',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(data){
			$.each(data, function(i,item){
				$("#department").append("<option value='"+item.hsbcDeptName+"'>"+item.hsbcDeptName+"</option>")
			})
		}
	})
}
/*根据department加载subdepartment的ajax*/
function loadSubDepartment(){
	$("#department").change(function(){
		//var department = $("#department option:selected").text();
		var department = $("#department").val();
		//alert(department);
		//$("#sub_department").find("option").remove();
		$("#sub_department").empty();
		$("#sub_department").append("<option value=''>-- select --</option>");
		$.ajax({
			url:path+'/service/demand/loadSubDepartment',
			dataType:"json",
			async:true,
			cache:false,
			type:"post",
			data:{"hsbcDeptName":department},
			success:function(data){
				$.each(data, function(i,item){
					$("#sub_department").append("<option value='"+item.hsbcSubDeptName+"'>"+item.hsbcSubDeptName+"</option>")
				})
			}
		});
	});
}
	
/*根据条件和当前页加载查询到的信息*/
function loadDemandList(currPage){
	var skill= $("#skill").val();
	var position= $("#position").val();
	var department= $("#department").val();
	var sub_department= $("#sub_department").val();
	var status= $("#status").val();
	var rr= $("#rr").val();
	var csBuName = $("#csBuName").val();
	var csSubDept = $("#scSubDeptName").val();
	//$("#demandList").empty();
	$("#demandList  tr:not(:first)").html("");
	$.ajax({
		url:path+'/service/demand/queryDemandList',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		data:{"csBuName":csBuName,"skill":skill,"position":position,"hsbcDept.hsbcDeptName":department,"hsbcDept.hsbcSubDeptName":sub_department,
			"status":status,"rr":rr,"currPage":currPage,"csSubDept":csSubDept},
		success:function(result){
			//alert(result.list.length);
			if(result.list.length > 0){
				$("#exportExcel").removeAttr("disabled");
			}else{
				$("#demandList").append("<tr><td colspan='7' style='text-align:center'>暂无数据！</td></tr>");
			}
			//$.each(reslut, function(i,data){
			for (var i = 0; i < result.list.length; i++) {
				var tr = $("<tr id='"+result.list[i].rr+"'></tr>");
				var td1 = $("<td>"+result.list[i].rr+"</td>");
				var td2 = $("<td>"+result.list[i].skill+"</td>");
				var td3 = $("<td>"+result.list[i].position+"</td>");
				var td4 = $("<td>"+result.list[i].hsbcDept.hsbcDeptName+"</td>");
				var td5 = $("<td>"+result.list[i].hsbcDept.hsbcSubDeptName+"</td>");
				var td6 = $("<td>"+result.list[i].status+"</td>");
				var td7 = $("<td>"+result.list[i].csSubDept+"</td>");
				var td8 = $("<td><div class='btn-group btn-group-sm'><button class='btn btn-primary' onclick='demandDetail("+result.list[i].demandId+")'>详情</button></div></td>");
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				td7.appendTo(tr);
				td8.appendTo(tr);
				$("#demandList").append(tr);
			}
			//alert(result.pageCondition.totalPage);
			$("#pageCount").html(result.pageCondition.totalPage);
			$("#currentPage").html(result.pageCondition.currPage);
			//request.setAttribute("totalPage",result.pageCondition.totalPage);
			var html='<li><a href="javascript:void(0);" id="fristPage">首页</a></li><li><a href="javascript:void(0);" id="previousPage" >上页</a></li>';
			/*if(result.pageCondition.currPage==1){
				html='<li><a href="#" id="fristPage" onclick="loadDemandList(1)">首页</a></li><li><a href="#" id="previousPage" >&laquo;</a></li><li><a href="#" id="nextPage" >&raquo;</a></li><li><a href="#" id="lastPage" >末页</a></li>';
			}else if(result.pageCondition.currPage==result.pageCondition.totalPage){
				html='<li><a href="#" id="fristPage" onclick="loadDemandList(1)">首页</a></li><li><a href="#" id="previousPage" >&laquo;</a></li><li><a href="#">'+(result.pageCondition.totalPage-2)+'</a></li><li><a href="#">'+(result.pageCondition.totalPage-1)+'</a></li><li><a href="#">'+(result.pageCondition.totalPage)+'</a></li><li><a href="#" id="nextPage" >&raquo;</a></li><li><a href="#" id="lastPage" >末页</a></li>';
			}else {
				html='<li><a href="#" id="fristPage" onclick="loadDemandList(1)">首页</a></li><li><a href="#" id="previousPage" >&laquo;</a></li><li><a href="#">'+(result.pageCondition.currPage-1)+'</a></li><li><a href="#">'+result.pageCondition.currPage+'</a></li><li><a href="#">'+(result.pageCondition.currPage+1)+'</a></li><li><a href="#" id="nextPage" >&raquo;</a></li><li><a href="#" id="lastPage" >末页</a></li>';
			}*/
			var page = 4;
			if(result.pageCondition.totalPage <= page){
				for (var i = 1; i <= result.pageCondition.totalPage; i++) {
					html +='<li><a href="javascript:void(0);" onclick="loadDemandList('+i+')">'+i+'</a></li>';
				}
			}else{
				if(result.pageCondition.currPage >= result.pageCondition.totalPage -page){
					for (var i = result.pageCondition.totalPage - page; i <= result.pageCondition.totalPage; i++) {
						html +='<li><a href="javascript:void(0);" onclick="loadDemandList('+i+')">'+i+'</a></li>';
					}
				}else{
					for (var i = result.pageCondition.currPage; i <= result.pageCondition.totalPage; i++) {
						if(i <=result.pageCondition.currPage + page){
							html +='<li><a href="javascript:void(0);" onclick="loadDemandList('+i+')">'+i+'</a></li>';
						}else{
							html +='<li style="display:none;"><a href="javascript:void(0);" onclick="loadDemandList('+i+')">'+i+'</a></li>';
						}
					}
				}
			}
			if(result.pageCondition.totalPage>page && result.pageCondition.currPage < result.pageCondition.totalPage - page){
				html += '<li class="disabled"><a href="javascript:void(0);">...</a></li>';
			}
			html += '<li><a href="javascript:void(0);" id="nextPage" >下页</a></li><li><a href="javascript:void(0);" id="lastPage" >末页</a></li>';
			if(result.pageCondition.totalPage>page){
				html += '跳至<input style="height:39px;width:39px" type="text" id="toPage">页</input><li style="float:right"><a href="javascript:void(0);" id="sure" >确定</a></li>';
			}
			$("ul.pagination-centered").html(html);
			console.log($("ul.pagination-centered"));
			$("#sure").click(function(){
				var pageNum = $("#toPage").val();
				loadDemandList(pageNum);
			})
			$("#fristPage").attr("onclick","loadDemandList(1)");
			if(result.pageCondition.currPage <= result.pageCondition.totalPage){
				$("#previousPage").attr("onclick","loadDemandList("+(result.pageCondition.currPage - 1)+")");
				$("#nextPage").attr("onclick","loadDemandList("+(result.pageCondition.currPage + 1)+")");
				$("#lastPage").attr("onclick","loadDemandList("+(result.pageCondition.totalPage)+")");
			}
			if(result.pageCondition.currPage==result.pageCondition.totalPage){
				$("#nextPage").parent("li").addClass("disabled");
				$("#nextPage").removeAttr('onclick');
				$("#lastPage").parent("li").addClass("disabled");
				$("#lastPage").removeAttr('onclick');
			}
			if(result.pageCondition.currPage==1){
				$("#fristPage").parent("li").addClass("disabled");
				$("#fristPage").removeAttr('onclick');
				$("#previousPage").parent("li").addClass("disabled");
				$("#previousPage").removeAttr('onclick');
			}
			$("ul.pagination-centered li a").each(function(){
				if($(this).text()==result.pageCondition.currPage){
					//$(this).parent("li").addClass("disabled");
					$(this).parent("li").addClass("active");
					$(this).removeAttr('onclick');
					//$(this).parent("li").removeClass("active");
					//$(this).parent("li").siblings("li").addClass("active");
					//$(this).parent("li").siblings("li").removeClass("disabled");
				}
			});
		}
	})
	
}

$('#exportExcel').bind("click", function(){
	
	$('#myModal').modal('show');
	
});

function exportCondition(){
	var condition="";
	$("label").find(":checkbox:checked").each(function(){
		condition += $(this).attr("name") +",";
	});
	var url = path+'/service/demand/exportExcel';
	$("#condition").val(condition);
	$("#conditionForm").attr("action",url);
	$("#conditionForm").submit();
	
	$('#myModal').modal('hide');
	$("[type='checkbox']").removeAttr("checked");
}

function demandDetail(demandId){
	$("#demandId").val(demandId);
	var url = path+'/service/demand/demandDetail';
	$("#detailForm").attr("action",url);
	$("#detailForm").submit();
}
