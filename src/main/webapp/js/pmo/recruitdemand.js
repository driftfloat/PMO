$(function(){
	loadSkill();
	loadPosition();
	loadLocation();
	loadStatus();
	loadOnboarded();
	loadAbort();
	loadDelayed();
	loadHrPriority();
	loadDemandPriority();
	loadSowSigned();
	dateType();
})
function addDemand(){
	var demandId=$('#demandId').val();
	var rr=$('#rr').val();
	var jobCode=$('#jobCode').val();
	var skill=$('#skill').val();
	var requestor=$('#requestor').val();
	var position=$('#position').val();
	var hsbcSubDeptId=$('#hsbcSubDeptId').val();
	var location=$('#location').val();
	var reqPublishedDate=$('#reqPublishedDate').val();
	var ageing=$('#ageing').val();
	var profilesNo=$('#profilesNo').val();
	var interviewedNo=$('#interviewedNo').val();
	var status=$('#status').val();
	var proposedJoiningDate=$('#proposedJoiningDate').val();
	var dcCleared=$('#dcCleared').val();
	var sowSigned=$('#sowSigned').val();
	var onboarded=$('#onboarded').val();
	var abort=$('#abort').val();
	var delayed=$('#delayed').val();
	var reason=$('#reason').val();
	var nextAction=$('#nextAction').val();
	var status2=$('#status2').val();
	var remark=$('#remark').val();
	var csSubDept=$('#csSubDept').val();
	var plannedOnboardDate=$('#plannedOnboardDate').val();
	var doNumber=$('#doNumber').val();
	var hrPriority=$('#hrPriority').val();
	var reqReceivedDate=$('#reqReceivedDate').val();
	var ageingReceived=$('#ageingReceived').val();
	var demandPriority=$('#demandPriority').val();
	var creatDate=$('#creatDate').val();
	var updateDate=$('#updateDate').val();
	var recruitmentCycle=$('#recruitmentCycle').val();
	var completionDay=$('#completionDay').val();
	var completionDate=$('#completionDate').val();
	var onboardDate=$('#onboardDate').val();
	var staffName=$('#staffName').val();
	
	$.ajax({
		url:path+'/service/demand/addDemand',
		dataType:"json",
		//data:{"rr":rr,"jobCode":jobCode,"skill":skill,"requestor":requestor},
		data:{"rr":rr,"jobCode":jobCode,"skill":skill,"requestor":requestor,"position":position,"hsbcSubDeptId":hsbcSubDeptId,"location":location,"reqPublishedDate":reqPublishedDate,"ageing":ageing,"profilesNo":profilesNo,"interviewedNo":interviewedNo,"status":status,"staffName":staffName,"proposedJoiningDate":proposedJoiningDate,"dcCleared":dcCleared,"sowSigned":sowSigned,"onboarded":onboarded,"abort":abort,"delayed":delayed,"reason":reason,"nextAction":nextAction,"status2":status2,"remark":remark,"csSubDept":csSubDept,"plannedOnboardDate":plannedOnboardDate,"doNumber":doNumber,"hrPriority":hrPriority,"reqReceivedDate":reqReceivedDate,"ageingReceived":ageingReceived,"demandPriority":demandPriority,"creatDate":creatDate,"updateDate":updateDate,"recruitmentCycle":recruitmentCycle,"completionDay":completionDay,"completionDate":completionDate,"onboardDate":onboardDate},
		async:true,
		cache:false,
		type:"post",
		success:function(resultFlag){
			if(resultFlag){
				$("html,body").animate({scrollTop:0}, 500);
				$('#successAlert').html('需求信息添加成功').show();
				setTimeout(function () {
					$('#successAlert').hide();
				}, 2000);
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
function loadPosition(){
	var url = path+'/json/msaRole.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#position").append("<option>"+item.name+"</option>");
	       })
	});
}
function loadLocation(){
	var url = path+'/json/staffRegion.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#location").append("<option>"+item.name+"</option>");
	       })
	});
}

function loadStatus(){
	var url = path+'/json/status.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#status").append("<option>"+item.name+"</option>");
	       })
	});
}
function loadOnboarded(){
	var url = path+'/json/nicheSkill.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#onboarded").append("<option>"+item.name+"</option>");
	       })
	});
}
function loadAbort(){
	var url = path+'/json/nicheSkill.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#abort").append("<option>"+item.name+"</option>");
	       })
	});
}
function loadDelayed(){
	var url = path+'/json/nicheSkill.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#delayed").append("<option>"+item.name+"</option>");
	       })
	});
}
function loadHrPriority(){
	var url = path+'/json/hrPriority.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#hrPriority").append("<option>"+item.name+"</option>");
	       })
	});
}
function loadDemandPriority(){
	var url = path+'/json/demandPriority.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#demandPriority").append("<option>"+item.name+"</option>");
	       })
	});
}
function loadSowSigned(){
	var url = path+'/json/sowSigned.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#sowSigned").append("<option>"+item.name+"</option>");
	       })
	});
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

