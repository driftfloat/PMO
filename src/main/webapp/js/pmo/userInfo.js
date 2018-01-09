var csDeptName0 = "";

var csSubDeptName0 = "";
	
var csBuName0 = "";
var userTypeMap = new Map();
var userTypeMap0 = new Map();
function loadUserType(result){
	var url = path+'/json/userType.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#userType").append("<option value='"+item.key+"'>"+item.name+"</option>");
	    	   userTypeMap.set(item.name,item.key);
	    	   userTypeMap0.set(item.key,item.name);
	       })
	       $('#userType').val(result.pageInfo.userType);
	});
}

$(function(){
	loadUserType();
	loadEmployeeList();
	
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


function loadCSSubDept(result){
	var userType = result.userType;
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
			$('#csSubDept').val(result.pageInfo.du);
		}
	})
}


function editEmployeeInfo(userId){
	$("#editForm").attr("action",path+"/service/user/updateUserInfo.html");
	$("#userId").val(userId);
	$("#editForm").submit();
}

function loadCSBu(result){
	var userType = result.userType;
	var url = path+'/json/csBuName.json'
	$.getJSON(url,  function(data) {
		   $("#csBu").empty();
		   $("#csBu").append("<option value=''>--Option--</option>");
	       $.each(data, function(i, item) {
	    	   $("#csBu").append("<option>"+item.name+"</option>");
	       })
		   $('#csBu').val(result.pageInfo.bu);
	});
}



function loadEmployeeList(pageState,csDeptName,csSubDeptName,csBuName){
	var csDeptName = csDeptName;

	csDeptName0 = csDeptName;
	
	var csSubDeptName = csSubDeptName;
	
	csSubDeptName0 = csSubDeptName;
	
	var csBuName = csBuName;
	
	csBuName0 = csBuName;
	
	
	var eHr = $("#eHr").val();
	
	var userType = $("#userType").val();
	
	var userName = $("#userName").val();

	var pageState = pageState;
	
	$.ajax({
		url:path+"/service/userInfo/queryUserList",
		dataType:"json",
		async:true,
		data:{"userName":userName,"userType":userType,"pageState":pageState,"csBuName":csBuName,"csSubDeptName":csSubDeptName,"eHr":eHr},
		cache:false,
		type:"post",
		success:function(result){
			loadUserType(result);
			$("#userList tbody").remove();
			
			var tbody = $("<tbody>");
			tbody.appendTo($("#userList"));
			for (var i = 0; i < result.data.length; i++) {
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);

				var td1 = $("<td id='tx1'>"
						+ result.data[i].userName
						+ "</td>");
				
				var td2 = $("<td>"
						+ result.data[i].nickname
						+ "</td>");
				var td3 = $("<td>"
						+ userTypeMap0.get(result.data[i].userType)
						+ "</td>");
				var td4 = $("<td>"
						+ result.data[i].bu
						+ "</td>");
				var td5 = $("<td>"
						+ result.data[i].du
						+ "</td>");
				//var td7 = $("<td><a class='btn btn-info' href='javascript:void(0);'> <i class='glyphicon glyphicon-edit icon-white'></i> 编辑</a></td>");
				var td6 = null;
				td6 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=editEmployeeInfo('"+result.data[i].userId+"')>Edit</a></td>");
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
			}
			$("#userList").append("</tbdoy>");
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
			
			loadCSSubDept(result);
			
			loadCSBu(result);
			
		}
		
	})
}