var csDeptName0 = "";

var csSubDeptName0 = "";
	
var csBuName0 = "";

$(function(){
	loadCusDeptInfo();
	loadEmployeeList();
	dateType1();
	loadEngagementType();
	loadRole();
	loadSkill();
	loadStaffRegion();
	loadDept();
	
})

function loadDept(){
	$.ajax({
		url:path+'/service/hsbcDept/queryDeptName',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			for(var i = 0;i<list.length;i++){
				$("#hsbcDept").append("<option value='"+list[i].hsbcSubDeptId+"'>"+list[i].hsbcDeptName+"</option>");
			}
		}
	})
}

function loadSkill(){
	var url = path+'/json/skill.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#skill").append("<option>"+item.name+"</option>");
	       })
	});
}

var regionMap = new Map();
function loadStaffRegion(){
	var url = path+'/json/staffRegion.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#staffRegion").append("<option>"+item.name+"</option>");
	    	   regionMap.set(item.name,item.key);
	       })
	});
}
function loadResourceStatus(result){
	var url = path+'/json/resourceStatus.json'
	$.getJSON(url,  function(data) {
		   $("#resourceStatus").empty();
		   $("#resourceStatus").append("<option value=''>--Option--</option>");
	       $.each(data, function(i, item) {
	    	   $("#resourceStatus").append("<option>"+item.name+"</option>");
	       })
	       $('#resourceStatus').val(result.pageInfo.resourceStatus);
	});
}

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
	var csSubDeptNames = result.csSubDeptNames;
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
				if(csSubDeptNames.length==1){
					$('#csSubDept').val(result.csSubDeptNames[0]);
					$("#csSubDept").attr("disabled","disabled");
				}else if(csSubDeptNames.length>1){
					$("#csSubDept").empty();
					for(var i = 0;i<csSubDeptNames.length;i++){
						$("#csSubDept").append("<option>"+csSubDeptNames[i]+"</option>");
						$('#csSubDept').val(result.pageInfo.csSubDeptName);
					}
				}
			}else{
				$('#csSubDept').val(result.pageInfo.csSubDeptName);
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
			}else{
				$('#csBu').val(result.pageInfo.csbuName);
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
	
	var rmName= $("#RM").val();

	var pageState = pageState;
	
	$.ajax({
		url:path+"/service/employeeInfo/queryEmployeeList",
		dataType:"json",
		async:true,
		data:{"staffName":staffName,"resourceStatus":resourceStatus,"pageState":pageState,"csBuName":csBuName,"csSubDeptName":csSubDeptName,"hsbcStaffId":hsbcStaffId,"eHr":eHr,"lob":lob,"rmUserId":rmName},
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
				var staffName=result.data[i].staffName;
				var lob=result.data[i].lob;
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				var td1 = $("<td><input id='ls"+ lob + "' type='checkbox' onclick=checkedEmployee('"+lob+"','"+staffName+"') ></td>");
				var td2 = $("<td>"
						+ result.data[i].hsbcStaffId
						+ "</td>");
				var td3 = $("<td>"
						+ result.data[i].eHr
						+ "</td>");
				var td4 = $("<td>"
						+ result.data[i].lob
						+ "</td>");
				var td5 = $("<td>"
						+ result.data[i].staffName
						+ "</td>");
				var td6 = $("<td>"
						+ result.data[i].ln
						+ "</td>");
				var td7 = $("<td>"
						+ result.data[i].csSubDeptName
						+ "</td>");
				var td8 = $("<td>"
						+ result.data[i].resourceStatus
						+ "</td>");
				var td81 = $("<td>"
						+ result.data[i].nickname
						+ "</td>");
				//var td7 = $("<td><a class='btn btn-info' href='javascript:void(0);'> <i class='glyphicon glyphicon-edit icon-white'></i> 编辑</a></td>");
				var td9 = null;
				
				td9 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=employeeDetail('"+result.data[i].employeeId+"')>Detail</a></td>");
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				td7.appendTo(tr);
				td8.appendTo(tr);
				td81.appendTo(tr);
				td9.appendTo(tr);
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
			
			loadResourceStatus(result);
			
			loadCSSubDept(result);
			
			loadCSBu(result);
			
			getUserForRM(result);
		}
		
	})
}


//gkf add
function getUserForRM(result){
	var bu = result.user.bu;//事业部
	var dus = result.user.csDeptId.split(",");//部门
	$.ajax({
		url:path+'/service/user/getUserForRM',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			$("#RM").empty();
			$("#RM").append("<option value=''>--Option--</option>");			
			for(var i = 0;i<list.length;i++){
				if(bu!=null&&dus!=null){
					for(var j = 0;j < dus.length;j++){
						if(bu==list[i].bu&&dus[j]==list[i].csDeptId){
							$("#RM").append("<option value='"+list[i].userId+"'>"+list[i].nickname+"</option>");
						}
					}
				}else{
					$("#RM").append("<option value='"+list[i].userId+"'>"+list[i].nickname+"</option>");
				}
			}		
			$('#RM').val(result.pageInfo.rmUserId);
			
			$("#rmName").empty();
			$("#rmName").append("<option value=''>--Option--</option>");			
			for(var i = 0;i<list.length;i++){
				if(bu!=null&&dus!=null){
					for(var j = 0;j < dus.length;j++){
						if(bu==list[i].bu&&dus[j]==list[i].csDeptId){
							$("#rmName").append("<option value='"+list[i].userId+"'>"+list[i].nickname+"</option>");
						}
					}
				}else{
					$("#rmName").append("<option value='"+list[i].userId+"'>"+list[i].nickname+"</option>");
				}
			}		
			$('#rmName').val(result.pageInfo.rmUserId);	

		}
	})
}

function dateType1(){
	$('.form_datetime1').datetimepicker({
		weekStart: 1,
		minView:'month',
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
		language:'zh-CN',
		format: 'yyyy-mm-dd',
		pickerPosition: 'bottom-left',
		showMeridian: 1
	}).on('changeDate', function(ev){
	
		 $('#registerEmployeeForm').bootstrapValidator('revalidateField', 'sowExpiredDate1'); 
		
	});
}
function loadEngagementType(){
	var url = path+'/json/engagementType.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#engagementType").append("<option>"+item.name+"</option>");
	       })
	});
}
function loadRole(){
	var url = path+'/json/role.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#role").append("<option>"+item.name+"</option>");
	       })
	});
}


function employeeDetail(employeeId){
	$("#editForm").attr("action",path+"/service/interview/employeeDetailInfo.html");
	$("#employeeId").val(employeeId);
	$("#editForm").submit();
}

$("#hsbcDept").change(function(){
	var hsbcSubDeptId = $('#hsbcDept').val();
	$("#projectName").find("option").remove(); 
	$("#projectName").append("<option value=''>-- 请选择项目 --</option>");
	$.ajax({
		url:path+'/service/hsbcDept/querySubDeptName',
		dataType:"json",
		async:true,
		data:{"hsbcSubDeptId":hsbcSubDeptId},
		cache:false,
		type:"post",
		success:function(list){
			$("#hsbcSubDept").find("option").remove(); 
//			if(list.length == 1 && list[0].hsbcSubDeptName == null){
//				$("#hsbcSubDept").append("<option value='"+$('#hsbcDept').find("option:selected").val()+"'>"+$('#hsbcDept').find("option:selected").text()+"</option>");
//			}else{
			$("#hsbcSubDept").append("<option value=''>-- Option --</option>");
			if(list.length == 1 && list[0].hsbcSubDeptName == null){
				$("#hsbcSubDept").append("<option value='"+$('#hsbcDept').find("option:selected").val()+"'>"+$('#hsbcDept').find("option:selected").text()+"</option>");
			}else{
				for(var i = 0;i<list.length;i++){
					$("#hsbcSubDept").append("<option value='"+list[i].hsbcSubDeptId+"'>"+list[i].hsbcSubDeptName+"</option>");
				}
			}
			
			//}
		}
	})
})

$("#hsbcSubDept").change(function(){
	var hsbcSubDeptId = $('#hsbcSubDept').val();
	$.ajax({
		url:path+'/service/hsbcProject/queryprojcetName',
		dataType:"json",
		async:true,
		data:{"hsbcSubDeptId":hsbcSubDeptId},
		cache:false,
		type:"post",
		success:function(list){
			$("#projectName").find("option").remove(); 
			$("#projectName").append("<option value=''>-- 请选择项目 --</option>");
			for(var i = 0;i<list.length;i++){
				$("#projectName").append("<option value='"+list[i].hsbcProjectId+"'>"+list[i].hsbcProjectName+"</option>");
			}
		}
	})
})




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



var lobArray=[];
var staffNameArray =[];
function checkedEmployee(lob,staffName){
	if($('#ls'+lob+'').is(':checked')){
		lobArray.push(lob);
		staffNameArray.push(staffName+":"+lob);
		$('#transferBox').show();
		$('#transBox').val(staffNameArray+";");
	}else{
		for(var i = 0;i < lobArray.length; i++){
			if(lob == lobArray[i]){
				lobArray.splice(i,1);
				if(lobArray.length==0){
					$('#transferBox').hide();
				}
			}
		}
		for(var i = 0;i < staffNameArray.length; i++){
			if((staffName+":"+lob) == staffNameArray[i]){
				staffNameArray.splice(i,1);
				$('#transBox').val(staffNameArray);
			}
		}
	}
}
$("#modifyName").bind("click",function(){
	 var result=$("#modifyName").find("option:selected").val();
	 if(result==0){
		 $("#projectProperties").hide();
		 $("#humanRole").hide();
		 $("#departmentModify").hide();
		 $("#nickName").hide();
	 }else if(result==1){
		 $("#projectProperties").show();
		 $("#humanRole").hide();
		 $("#departmentModify").hide();
		 $("#nickName").hide();
	 }else if(result==2){
		 $("#projectProperties").hide();
		 $("#humanRole").show();
		 $("#departmentModify").hide();
		 $("#nickName").hide();
	 }else if(result==3){
		 $("#projectProperties").hide();
		 $("#humanRole").hide();
		 $("#nickName").hide();
		 $("#departmentModify").show();
	 }else if(result = 4){
		 $("#projectProperties").hide();
		 $("#humanRole").hide();
		 $("#departmentModify").hide();
		 $("#nickName").show();
	 }
	 
})

//批量异动部门
$('#transfer').bind("click", function(){
	if(lobArray.length==0){
		alert("请先选择需要批量修改的员工。");
	}else{
		$('#modifyMadal').modal('show');
		$('#staffNames').val(staffNameArray);
		$('#staffIds').val(lobArray);
	}
});
function loadCusDeptInfo(){
	$.ajax({
		url:path+'/service/candidate/loadCusDeptInfo',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			for(var i = 0;i<list.length;i++){
				$("#csSubdeptName").append("<option value='"+list[i].csSubDeptId+"'>"+list[i].csSubDeptName+"</option>");
			}
		}
	})
}
$("#transSubmit").on("click", function(){
	var result=$("#modifyName").find("option:selected").val();
	 if(result==1){
		 updateProperties();
	 }else if(result==2){
		 updateRoles();
	 }else if(result==3){
		 updateDept();
	 }else{
		 updateRM();
	 }
	 
	});
function updateProperties(){
	var staffIds=$("#staffIds").val();
	var projectName = $("#projectName").val();
	var sowNum = $("#sowName").val();
	var sowExpiredDate= $("#sowExpiredDate1").val();
	var engagementType= $("#engagementType").find("option:selected").val();
	$.ajax({
		url:path+'/service/employee/propertiesModify',
		dataType:"json",
		async:true,
		data:{"staffIds":staffIds,"projectName":projectName,"sowNum":sowNum,"sowExpiredDate":sowExpiredDate,"engagementType":engagementType},
		cache:false,
		type:"post",
		success:function(result){
			if(result){
				alert("员工信息批量修改成功。")
				
			}else{
				alert("员工信息批量修改失败。")
			}
			
		}
	})
}


function updateRoles(){
	var staffIds=$("#staffIds").val();
	var msaRole= $("#role").find("option:selected").val();
	var skill= $("#skill").find("option:selected").val();
	var staffRegion= $("#staffRegion").find("option:selected").val();
	var billRate= $("#billRate").val();
	$.ajax({
		url:path+'/service/employee/rolesModify',
		dataType:"json",
		async:true,
		data:{"staffIds":staffIds,"msaRole":msaRole,"skill":skill,"staffRegion":staffRegion,"billRate":billRate},
		cache:false,
		type:"post",
		success:function(result){
			if(result){
				alert("员工信息批量修改成功。")
				
			}else{
				alert("员工信息批量修改失败。")
			}
			
		}
	})
}

function updateDept(){
	var staffIds=$("#staffIds").val();
	var hsbcDept= $("#hsbcDept").find("option:selected").val();
	var hsbcSubDept= $("#hsbcSubDept").find("option:selected").val();
	var hsbcManager=$("#hsbcManager").val();
	
	$.ajax({
		url:path+'/service/employee/deptModify',
		dataType:"json",
		async:true,
		data:{"staffIds":staffIds,"hsbcDept":hsbcDept,"hsbcSubDept":hsbcSubDept,"hsbcManager":hsbcManager},
		cache:false,
		type:"post",
		success:function(result){
			if(result){
				alert("员工信息批量修改成功。")
				
			}else{
				alert("员工信息批量修改失败。")
			}
			
		}
	})
}

//gkf add
function updateRM(){
	var nickName = $("#rmName").find("option:selected").val();
	var staffIds=$("#staffIds").val();
	$.ajax({
		url:path+'/service/employee/updateRM',
		dataType:'json',
		async:true,
		data:{"staffIds":staffIds,"nickName":nickName},
		cache:false,
		type:"post",
		success:function(result){
			if(result){
				alert("员工信息批量修改成功。");
				$('#modifyMadal').modal('hide');
				loadEmployeeList();
			}else{
				alert("员工信息批量修改失败。");
			}
		}
	});
};
function changeData(){
	var staffRegion = $('#staffRegion').val();
	var role = $('#role').val();
	var skill = $('#skill').val();
	if('' == staffRegion || staffRegion== null){
		return;
	}
	if('' == role || role== null){
		return;
	}
	if('' == skill || skill== null){
		return;
	}
	
	$.ajax({
		url:path+'/service/interview/getBillRate',
		dataType:"json",
		async:true,
		data:{"staffRegion":staffRegion,"role":role,"skill":skill},
		cache:false,
		type:"post",
		success:function(data){
			if(data != null){
				$('#billRate').val(data);
			}
		}
	})
}

