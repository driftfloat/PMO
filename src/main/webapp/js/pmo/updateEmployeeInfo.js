$(function(){
	
	loadDept();
	loadStaffCategory();
	loadEngagementType();
	loadRole();
	loadSkill();
	loadBillingEntity();
	loadBillingCurrency();
	loadResourceStatus();
	loadNicheSkill();
	loadCSDept();
	loadStaffRegion();
	loadStaffLocation();
	loadLocationType();
	loadOnshoreOrOffshore();
	dateType();
});

window.onload = function(){
	loadEmployeeInfo();
}

function dateType(){
	$('.form_datetime').datetimepicker({
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
	});
}

function loadPersonalCSDept(employee){
	var csSubDeptId = employee.csSubDeptId;
	$.ajax({
		url:path+'/service/csDept/queryCSDeptById',
		dataType:"json",
		async:true,
		data:{"csSubDeptId":csSubDeptId},
		cache:false,
		type:"post",
		success:function(csDept){
			//$('#csDept').find("option[text='RTP']").attr("selected",true);
			$("#csDept option:contains('"+csDept.csDeptName+"')").attr('selected', true);
			loadCSSubDept(csDept);
		}
	})
}


function loadPersonalHSBCDept(employee){
	var hsbcProjectId = employee.hsbcProjectId;
	$.ajax({
		url:path+'/service/hsbcDept/queryHSBCSubDeptById',
		dataType:"json",
		async:true,
		data:{"hsbcProjectId":hsbcProjectId},
		cache:false,
		type:"post",
		success:function(hsbcDept){
			//$('#csDept').find("option[text='RTP']").attr("selected",true);
			$("#hsbcDept option:contains('"+hsbcDept.hsbcDeptName+"')").attr('selected', true);
			loadHSBCSubDept(hsbcDept,employee);
		}
	})
}

function updateEmployee(){
	var employeeId = $('#employeeId').val();
	var hsbcStaffId = $('#hsbcStaffId').val();
	var staffName = $('#staffName').val();
	var LN = $('#LN').val();
	var staffRegion = $('#staffRegion').val();
	var staffLocation = $('#staffLocation').val();
	var locationType = $('#locationType').val();
	var onshoreOrOffshore = $('#onshoreOrOffshore').val();
	var projectName = $('#projectName').val();
	var sow = $('#sow').val();
	var sowExpiredDate = $('#sowExpiredDate1').val();
	var staffCategory = $('#staffCategory').val();
	var engagementType = $('#engagementType').val();
	var hsbcDOJ = $('#hsbcDOJ2').val();
	var graduationDate = $('#graduationDate1').val();
	var role = $('#role').val();
	var skill = $('#skill').val();
	var billingEntity = $('#billingEntity').val();
	var billingCurrency = $('#billingCurrency').val();
	var billRate = $('#billRate').val();
	var resourceStatus = $('#resourceStatus').val();
	var terminatedDate = $('#terminatedDate1').val();
	var terminationReason = $('#terminationReason').val();
	var eHr = $('#eHr').val();
	var csSubDept = $('#csSubDept').val();
	var nicheSkill = $('#nicheSkill').val();
	$.ajax({
		url:path+'/service/employee/updateEmployee',
		dataType:"json",
		data:{"employeeId":employeeId,"hsbcStaffId":hsbcStaffId,"staffName":staffName,"LN":LN,"staffRegion":staffRegion,"staffLocation":staffLocation,"locationType":locationType,"onshoreOrOffshore":onshoreOrOffshore,"projectName":projectName,"sow":sow,"sowExpiredDate":sowExpiredDate,"staffCategory":staffCategory,"engagementType":engagementType,"hsbcDOJ":hsbcDOJ,"graduationDate":graduationDate,"role":role,"skill":skill,"billingEntity":billingEntity,"billingCurrency":billingCurrency,"billRate":billRate,"resourceStatus":resourceStatus,"terminatedDate":terminatedDate,"terminationReason":terminationReason,"eHr":eHr,"csSubDept":csSubDept,"nicheSkill":nicheSkill},
		async:true,
		cache:false,
		type:"post",
		success:function(resultFlag){
			if(resultFlag){
				$("html,body").animate({scrollTop:0}, 500);
				$('#successAlert').html('员工'+staffName+'信息修改成功').show();
				setTimeout(function () {
					$('#successAlert').hide();
				}, 2000);
			}
		}
	})
}

function loadEmployeeInfo(){
	var employeeId = $('#employeeId').val();
	
	$.ajax({
		url:path+'/service/employee/queryEmployeeById',
		dataType:"json",
		data:{"employeeId":employeeId},
		async:true,
		cache:false,
		type:"post",
		success:function(employee){
			$('#hsbcStaffId').val(employee.hsbcStaffId);
			$('#staffName').val(employee.staffName);
			$('#LN').val(employee.ln);
			$('#staffRegion').val(employee.staffRegion);
			$('#staffLocation').val(employee.staffLocation);
			$('#locationType').val(employee.locationType);
			$('#onshoreOrOffshore').val(employee.onShoreOrOffShore);
			$('#projectName').val(employee.hsbcProjectId);
			$('#sow').val(employee.sow);
			$('#sowExpiredDate1').val(employee.sowExpiredDate);
			$('#staffCategory').val(employee.staffCategory);
			$('#engagementType').val(employee.engagementType);
			$('#hsbcDOJ1').val(employee.hsbcDoj);
			$('#graduationDate1').val(employee.graduationDate);
			$('#role').val(employee.role);
			$('#skill').val(employee.skill);
			$('#billingEntity').val(employee.billingEntity);
			$('#billingCurrency').val(employee.billCurrency);
			$('#billRate').val(employee.billRate);
			$('#resourceStatus').val(employee.resourceStatus);
			$('#terminatedDate1').val(employee.mentionLWD);
			$('#terminationReason').val(employee.reasonForTermination);
			$('#eHr').val(employee.eHr);
			$('#nicheSkill').val(employee.nicheSkill);
			loadPersonalCSDept(employee);
			loadPersonalHSBCDept(employee);
		}
	})
}



function dateType(){
	$('.form_datetime').datetimepicker({
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
	});
}


function loadOnshoreOrOffshore(){
	var url = path+'/json/onshoreOrOffshore.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#onshoreOrOffshore").append("<option>"+item.name+"</option>");
	       })
	});
}


function loadLocationType(){
	var url = path+'/json/locationType.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#locationType").append("<option>"+item.name+"</option>");
	       })
	});
}


function loadStaffLocation(){
	var url = path+'/json/staffLocation.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#staffLocation").append("<option>"+item.name+"</option>");
	       })
	});
}


function loadNicheSkill(){
	var url = path+'/json/nicheSkill.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#nicheSkill").append("<option>"+item.name+"</option>");
	       })
	});
}

function loadStaffRegion(){
	var url = path+'/json/staffRegion.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#staffRegion").append("<option>"+item.name+"</option>");
	       })
	});
}


function loadResourceStatus(){
	var url = path+'/json/resourceStatus.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#resourceStatus").append("<option>"+item.name+"</option>");
	       })
	});
}


function loadBillingCurrency(){
	var url = path+'/json/billingCurrency.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#billingCurrency").append("<option>"+item.name+"</option>");
	       })
	});
}

function loadBillingEntity(){
	var url = path+'/json/billingEntity.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#billingEntity").append("<option>"+item.name+"</option>");
	       })
	});
}

function loadSkill(){
	var url = path+'/json/skill.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#skill").append("<option>"+item.name+"</option>");
	       })
	});
}


function loadRole(){
	var url = path+'/json/msaRole.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#role").append("<option>"+item.name+"</option>");
	       })
	});
}


function loadStaffCategory(){
	var url = path+'/json/staffCategory.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#staffCategory").append("<option>"+item.name+"</option>");
	       })
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


function loadCSSubDept(csDept){
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
			if(csDept != null && csDept != ''){
				$("#csSubDept option:contains('"+csDept.csSubDeptName+"')").attr('selected', true);
			}
		}
	})
}


$("#csDept").change(function(){
	loadCSSubDept();
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


function loadHSBCSubDept(hsbcDept,employee){
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
			$("#hsbcSubDept").append("<option value=''>-- 请选择子交付部 --</option>");
			for(var i = 0;i<list.length;i++){
				$("#hsbcSubDept").append("<option value='"+list[i].hsbcSubDeptId+"'>"+list[i].hsbcSubDeptName+"</option>");
			}
			if(hsbcDept != null && hsbcDept != ''){
				$("#hsbcSubDept option:contains('"+hsbcDept.hsbcSubDeptName+"')").attr('selected', true);
				loadHSBCProject(employee);
			}
		}
	})
}


$("#hsbcDept").change(function(){
	loadHSBCSubDept();
})

$("#hsbcSubDept").change(function(){
	loadHSBCProject();
})

function loadHSBCProject(employee){
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
			if(employee != null && employee != ''){
				selectProjectName(employee);
			}
		}
	})
}


function selectProjectName(employee){
	var hsbcProjectId = employee.hsbcProjectId;
	$.ajax({
		url:path+'/service/hsbcProject/selectProjectName',
		dataType:"json",
		async:true,
		data:{"hsbcProjectId":hsbcProjectId},
		cache:false,
		type:"post",
		success:function(hsbcProject){
			$("#projectName option:contains('"+hsbcProject.hsbcProjectName+"')").attr('selected', true);
		}
	})
}