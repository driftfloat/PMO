$(function(){
	loadDept();
	loadStaffCategory();
	loadEngagementType();
	loadRole();
	loadSkill();
	loadGbGf();
	loadBillingEntity();
	loadBillingCurrency();
  //loadResourceStatus();
	loadCSDept();
	loadUserType();
	loadStaffRegion();
	loadStaffLocation();
	loadLocationType();
	loadOnshoreOrOffshore();
	dateType();
	dateType0();
	dateType1();
	dateType2();
	loadUserForRM();
})

	$("#staffRegion").change(function(){
		var staffRegion = $("#staffRegion").val();
		$("#staffLocation").val(regionMap.get(staffRegion));
	});


function addEmployee(){
	var bootstrapValidator = $("#registerEmployeeForm").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()){
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
		var hsbcDOJ = $('#hsbcDOJ2').val();
		var graduationDate = $('#graduationDate1').val();
		var role = $('#role').val();
		var skill = $('#skill').val();
		var billingCurrency = $('#billingCurrency').val();
		var billRate = $('#billRate').val();
		var terminationReason = $('#terminationReason').val();
		
		var email = $('#email').val();
		var gbGf = $('#gbGf').val();
		var entryDate = $('#entryDate1').val();
		var rmName= $("#RM").val();
		
		$.ajax({
			url:path+'/service/employee/addEmployee',
			dataType:"json",
			data:{"eHr":eHr,"lob":lob,"hsbcStaffId":hsbcStaffId,"staffName":staffName,"LN":LN,"staffRegion":staffRegion,
				"staffLocation":staffLocation,"locationType":locationType,"onshoreOrOffshore":onshoreOrOffshore,"csSubDept":csSubDept,
				"hsbcSubDept":hsbcSubDept,"projectName":projectName,"projectManager":projectManager,"sow":sow,"sowExpiredDate":sowExpiredDate,
				"staffCategory":staffCategory,"engagementType":engagementType,"hsbcDOJ":hsbcDOJ,"graduationDate":graduationDate,
				"role":role,"skill":skill,"billingCurrency":billingCurrency,"billRate":billRate,"resourceStatus":'Active',"terminatedDate":'',
				"email":email,"gbGf":gbGf,"entryDate":entryDate,"rmUserId":rmName},
			async:true,
			cache:false,
			type:"post",
			success:function(resultFlag){
				
				$.ajax({
					url:path+'/service/candidate/updateOnboardCandidate',
					dataType:"json",
					async:true,
					cache:false,
					type:"post",
					success:function(result){
						if(result){
							$("html,body").animate({scrollTop:0}, 500);
							$('#successAlert').html('Employee:'+staffName+' information added succesffully').show();
							setTimeout(function () {
								$('#successAlert').hide();
							}, 2000);
						}
					}
				})
				
				if(resultFlag){
					$("html,body").animate({scrollTop:0}, 500);
					$('#successAlert').html('Employee:'+staffName+' information added succesffully').show();
					setTimeout(function () {
						$('#successAlert').hide();
					}, 2000);
				}
			}
		})
		
	}else{
		return;
	}
	
	
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
	}).on('changeDate', function(ev){
		 $('#registerEmployeeForm').bootstrapValidator('revalidateField', 'hsbcDOJ1'); 
	
	});
}

function dateType0(){
	$('.form_datetime0').datetimepicker({
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
		 $('#registerEmployeeForm').bootstrapValidator('revalidateField', 'graduationDate1'); 
	
	});
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

function dateType2(){
	$('.form_datetime2').datetimepicker({
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
		 $('#registerEmployeeForm').bootstrapValidator('revalidateField', 'entryDate1'); 
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



var userTypeMap = new Map();
function loadUserType(){
	var url = path+'/json/userType.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#type").append("<option value='"+item.key+"'>"+item.name+"</option>");
	    	   userTypeMap.set(item.name,item.key);
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

function loadGbGf(){
	var url = path + '/json/gbGf.json';
	$.getJSON(url, function(data){
		$.each(data, function(i, item){
			$("#gbGf").append("<option>"+item.name+"</option>");
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
		success:function(result){
			var userType = result.user.userType;
			
			var csSubs = result.csSubDepts;
			if(userType=='0'){
				for(var i = 0;i<result.data.length;i++){
					$("#csSubDept").append("<option value='"+result.data[i].csSubDeptId+"'>"+result.data[i].csSubDeptName+"</option>");
				}
			}else{
				if(csSubs.length==1){
					$("#csSubDept").append("<option value='"+csSubs[0].csSubDeptId+"'>"+csSubs[0].csSubDeptName+"</option>");
					$('#csSubDept').val(csSubs[0].csSubDeptId);
					$("#csSubDept").attr("disabled","disabled");
					loadUserForRM($('#csSubDept').val());
				}else if(csSubs.length>1){
					$("#csSubDept").empty();
					for(var i = 0;i<csSubs.length;i++){
						$("#csSubDept").append("<option value='"+csSubs[i].csSubDeptId+"'>"+csSubs[i].csSubDeptName+"</option>");
					}
					loadUserForRM($('#csSubDept').val());
				}else{
					for(var i = 0;i<result.data.length;i++){
						$("#csSubDept").append("<option value='"+result.data[i].csSubDeptId+"'>"+result.data[i].csSubDeptName+"</option>");
					}
				}
			}
		}
	})
}
$("#csSubDept").change(function(){
	var du =$("#csSubDept").val();
	loadUserForRM(du);
})

function loadUserForRM(du){	
	$.ajax({
		url:path+'/service/user/getUserForRM',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			$("#RM").empty();
			$("#RM").append("<option value=''>--Option--</option>");	
			var RMList = new Array();
			if (du != null && du != "") {
				for (var i = 0; i < list.length; i++) {
					var csDeptIds = list[i].csdeptId.split(",");
					for (var j = 0; j < csDeptIds.length; j++) {
						if (du == csDeptIds[j]) {
							RMList.push(list[i]);
						}
					}
				}
			}else{
				for(var i = 0;i<list.length;i++){
					RMList.push(list[i]);
				}

			}
			//remove duplicates
			var newRMList = new Array();
			for(var i = 0;i < RMList.length;i++){				
				if(newRMList.indexOf(RMList[i])==-1){
					newRMList.push(RMList[i]);
				}
			}
			
			for(var i = 0;i < newRMList.length;i++){
				$("#RM").append("<option value='"+newRMList[i].userId+"'>"+newRMList[i].nickname+"</option>")
			}
			
		}
	})
}
$("#csDept").change(function(){
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
		}
	})
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
