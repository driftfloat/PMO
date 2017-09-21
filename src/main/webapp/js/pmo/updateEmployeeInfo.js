$(function(){
	
	loadDept();
	loadStaffCategory();
	loadEngagementType();
	loadRole();
	loadSkill();
	loadSource();
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


function loadPersonalHSBCDept(employee){
	$('#hsbcDept').val(employee.hsbcSubDept)
	var hsbcDept = $('#hsbcDept').val();
	loadHSBCSubDept(hsbcDept,employee);
	
}

function updateEmployee(){
	var bootstrapValidator = $("#updateEmployeeForm").data('bootstrapValidator');
	   bootstrapValidator.validate();
	if(bootstrapValidator.isValid()){
		var employeeId = $('#employeeId').val();
		var eHr = $('#eHr').val();
		var lob = $('#lob').val();
		var hsbcStaffId = $('#hsbcStaffId').val();
		var staffName = $('#staffName').val();
		var LN = $('#LN').val();
		var staffRegion = $('#staffRegion').val();
		var staffLocation = $('#staffLocation').val();
		var locationType = $('#locationType').val();
		var onshoreOrOffshore = $('#onshoreOrOffshore').val();
		var csSubDept = $('#csSubDept').val();
		var hsbcSubDept = $('#hsbcSubDept').val();
		var projectName = $('#hsbcProjectName').val();
		var projectManager = $('#hsbcProjectManager').val();
		var sow = $('#sow').val();
		var sowExpiredDate = $('#sowExpiredDate1').val();
		var staffCategory = $('#staffCategory').val();
		var engagementType = $('#engagementType').val();
		var hsbcDOJ = $('#hsbcDOJ1').val();
		var graduationDate = $('#graduationDate1').val();
		var role = $('#role').val();
		var skill = $('#skill').val();
		var billingCurrency = $('#billingCurrency').val();
		var billRate = $('#billRate').val();
		var resourceStatus = $('#resourceStatus').val();
		var terminatedDate = $('#terminatedDate1').val();
		var terminationReason = $('#terminationReason').val();
		$.ajax({
			url:path+'/service/employee/updateEmployee',
			dataType:"json",
			data:{"employeeId":employeeId,"eHr":eHr,"lob":lob,"hsbcStaffId":hsbcStaffId,"staffName":staffName,"LN":LN,"staffRegion":staffRegion,"staffLocation":staffLocation,"locationType":locationType,"onshoreOrOffshore":onshoreOrOffshore,"csSubDept":csSubDept,"hsbcSubDept":hsbcSubDept,"projectName":projectName,"projectManager":projectManager,"sow":sow,"sowExpiredDate":sowExpiredDate,"staffCategory":staffCategory,"engagementType":engagementType,"hsbcDOJ":hsbcDOJ,"graduationDate":graduationDate,"role":role,"skill":skill,"billingCurrency":billingCurrency,"billRate":billRate,"resourceStatus":resourceStatus,"terminatedDate":terminatedDate,"terminationReason":terminationReason},
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
				if(!resultFlag){
					alert('1');
				}
			}
		})
	}else{
		return;
	}
	
	
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
			$('#lob').val(employee.lob);
			$('#hsbcProjectName').val(employee.projectName);
			$('#hsbcProjectManager').val(employee.projectManager);
			$('#staffName').val(employee.staffName);
			$('#LN').val(employee.ln);
			$('#staffRegion').val(employee.staffRegion);
			$('#staffLocation').val(employee.staffLocation);
			$('#locationType').val(employee.locationType);
			$('#onshoreOrOffshore').val(employee.onshoreOrOffshore);
			$('#sow').val(employee.sow);
			$('#sowExpiredDate1').val(employee.sowExpiredDate);
			$('#staffCategory').val(employee.staffCategory);
			$('#engagementType').val(employee.engagementType);
			$('#hsbcDOJ1').val(employee.hsbcDOJ);
			$('#graduationDate1').val(employee.graduationDate);
			$('#role').val(employee.role);
			$('#skill').val(employee.skill);
			$('#billingCurrency').val(employee.billingCurrency);
			$('#billRate').val(employee.billRate);
			$('#resourceStatus').val(employee.resourceStatus);
			$('#terminatedDate1').val(employee.terminatedDate);
			$('#terminationReason').val(employee.terminationReason);
			$('#eHr').val(employee.eHr);
			$('#csSubDept').val(employee.csSubDept)
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

function loadSource(){
	var url = path+'/json/source.json'
	$.getJSON(url,  function(data) {
		$.each(data, function(i, item) {
			$("#source").append("<option>"+item.name+"</option>");
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
		url:path+'/service/csDept/queryAllCSSubDept',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			for(var i = 0;i<list.length;i++){
				$("#csSubDept").append("<option value='"+list[i].csSubDeptId+"'>"+list[i].csSubDeptName+"</option>");
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
	$.ajax({
		url:path+'/service/hsbcDept/querySubDeptName',
		dataType:"json",
		async:true,
		data:{"hsbcSubDeptId":hsbcSubDeptId},
		cache:false,
		type:"post",
		success:function(list){
			if(list.length == 1 && list[0].hsbcSubDeptName == null){
				$("#hsbcSubDept").append("<option value='"+$('#hsbcDept').find("option:selected").val()+"'>"+$('#hsbcDept').find("option:selected").text()+"</option>");
			}else{
				$("#hsbcSubDept").find("option").remove(); 
				$("#hsbcSubDept").append("<option value=''>-- 请选择子交付部 --</option>");
				for(var i = 0;i<list.length;i++){
					$("#hsbcSubDept").append("<option value='"+list[i].hsbcSubDeptId+"'>"+list[i].hsbcSubDeptName+"</option>");
				}
			}
			if(hsbcDept != null && hsbcDept != ''){
				$("#hsbcSubDept").val(employee.hsbcSubDept)
			}
		}
	})
}


$("#hsbcDept").change(function(){
	loadHSBCSubDept();
})






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