var candidateIdArray = [],candidateNameArray = [];
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
	
	
})

$("#pageRecordsNum").change(function(){
	loadDemandList();
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
function loadCSBu(result){
	var userType = result.user.userType;
	var url = path+'/json/csBuName.json'
	$.getJSON(url,  function(data) {
		$("#csBuName").empty();
		$("#csBuName").append("<option value=''>--Option--</option>");
	       $.each(data, function(i, item) {
	    	   $("#csBuName").append("<option value='"+item.name+"'>"+item.name+"</option>");
	       })
	       if(userType=='1' || userType=='2' || userType=='3' || userType=='4'){
				$('#csBuName').val(result.user.bu);
				$("#csBuName").attr("disabled","disabled");
			}
	});
}

/*加载交付部*/
function loadScSubDeptName(){
	$("#csBuName").change(function(){
		var csBuName = $("#csBuName").val();
		$("#scSubDeptName").empty();
		$("#scSubDeptName").append("<option value=''>--Option--</option>");
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

function loadCSSubDept(){
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
				}else if(csSubs.length>1){
					$("#csSubDept").empty();
					for(var i = 0;i<csSubs.length;i++){
						$("#csSubDept").append("<option value='"+csSubs[i].csSubDeptId+"'>"+csSubs[i].csSubDeptName+"</option>");
					}
				}
			}
		}
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
		//$("#sub_department").find("option").remove();
		$("#sub_department").empty();
		$("#sub_department").append("<option value=''>--Option--</option>");
		$.ajax({
			url:path+'/service/demand/loadSubDepartment',
			dataType:"json",
			async:true,
			cache:false,
			type:"post",
			data:{"hsbcDeptName":department},
			success:function(data){
				$.each(data, function(i,item){
					if(item.hsbcSubDeptName){
						$("#sub_department").append("<option value='"+item.hsbcSubDeptName+"'>"+item.hsbcSubDeptName+"</option>");
					}
				})
			}
		});
	});
}


	
/*根据条件和当前页加载查询到的信息*/
function loadDemandList(currPage){
	/*var skill= $("#skill").val();*/
	/*var position= $("#position").val();*/
	var department= $("#department").val();
	var sub_department= $("#sub_department").val();
	var  pageRecordsNum = $("#pageRecordsNum").find("option:selected").text();
	
	/*var status= $("#status").val();*/
	var rr= $("#rr").val();
	var csBuName = $("#csBuName").val();
	var csSubDept = $("#csSubDept").val();
	var candName= $("#candName").val();
	//$("#demandList").empty();
	$("#demandList  tr:not(:first)").html("");
	$.ajax({
		url:path+'/service/demand/queryDemandList',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		data:{"csBuName":csBuName,"hsbcDept.hsbcDeptName":department,"hsbcDept.hsbcSubDeptName":sub_department,
			"rr":rr,"currPage":currPage,"csSubDept":csSubDept,"flag":1,"candidateName":candName,"pageRecordsNum":pageRecordsNum},
		success:function(result){
			//alert(result.list.length);
			var userType = result.user.userType;
			if(result.list.length > 0){
				$("#exportExcel").removeAttr("disabled");
			}else{
				$("#demandList").append("<tr><td colspan='8' style='text-align:center'>暂无数据！</td></tr>");
			}
			//$.each(reslut, function(i,data){
			for (var i = 0; i < result.list.length; i++) {
				var demandId = result.list[i].demandId;
				var candidateId = result.list[i].candidateId;
				var candidateName = result.list[i].candidateName;
				var tr = $("<tr id='"+result.list[i].rr+"'></tr>");
				var td0 = $("<td><input id='ls"+ candidateId + "' type='checkbox' onclick=checkCand('"+candidateId+"','"+candidateName+"') ></td>");
				var td1 = $("<td>"+result.list[i].rr+"</td>");
				var tdd = $("<td>"+result.list[i].candidateName+"</td>");
				var td2 = $("<td>"+result.list[i].skill+"</td>");
				var td3 = $("<td>"+result.list[i].position+"</td>");
				/*if(result.list[i].hsbcDept.hsbcDeptName == null){
					var td4 = $("<td></td>");
				}else{
					var td4 = $("<td>"+result.list[i].hsbcDept.hsbcDeptName+"</td>");
				}
				if(result.list[i].hsbcDept.hsbcSubDeptName == null){
					var td5 = $("<td></td>");
				}else{
					var td5 = $("<td>"+result.list[i].hsbcDept.hsbcSubDeptName+"</td>");
				}*/
				var td4 = $("<td>"+result.list[i].status+"</td>");
				var td5 = $("<td>"+result.list[i].bgvCleared+"</td>");
				var td6 = $("<td>"+result.list[i].csSubDept+"</td>");
//				if(userType=='5' || userType=='6'){
//					var td8 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=demandDetail('"+demandId+"')>Detail</a></td>");
//				}else{
//					var td8 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=demandDetail('"+demandId+"')>Detail</a><a href='javascript:void(0); ' class='btn btn-info btn-small' onclick=demandDetailUpdate('"+demandId+"')>Edit</a></td>");
//				}
				td0.appendTo(tr);
				td1.appendTo(tr);
				tdd.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
//				td8.appendTo(tr);
				$("#demandList").append(tr);
				
				//gkf add 防止页面list刷新所选的checkbox失去焦点
				for(var index in candidateIdArray){
					if(candidateId == candidateIdArray[index]){
						$('#ls' + candidateId + '').attr(
								"checked", 'true');
					}
				}
			}
			$("#pageCount").html(result.pageCondition.totalPage);
			$("#currentPage").html(result.pageCondition.currPage);
			
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
				$("#fristPage").parent("li").removeClass("disabled");
				$("#previousPage").parent("li").removeClass("disabled");
			}
			if(result.pageCondition.currPage==1){
				$("#fristPage").parent("li").addClass("disabled");
				$("#fristPage").removeAttr('onclick');
				$("#previousPage").parent("li").addClass("disabled");
				$("#previousPage").removeAttr('onclick');
				$("#nextPage").parent("li").removeClass("disabled");
				$("#lastPage").parent("li").removeClass("disabled");
			}
			$("ul.pagination-centered li a").each(function(){
				if( 1 < result.pageCondition.currPage && result.pageCondition.currPage < result.pageCondition.totalPage){
					$(this).parent("li").siblings("li").removeClass("disabled");
				}
			});
            loadCSSubDept(result);			
			loadCSBu(result);
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

//gkf 选择填充处理
function checkCand(candidateId,candidateName){
	if($('#ls'+candidateId+'').is(':checked')){
		candidateIdArray.push(candidateId);
		candidateNameArray.push(candidateName);
		$('#candidateId').val(candidateIdArray);
		$('#candidateName').val(candidateNameArray);
		$('#backMaintain').css('display','block');
	}else{
		for(var i = 0;i < candidateIdArray.length; i++){
			if(candidateId == candidateIdArray[i]){
				candidateIdArray.splice(i,1);
				$('#candidateId').val(candidateIdArray);
			}
		}
		
		for(var i = 0;i < candidateNameArray.length; i++){
			if(candidateName == candidateNameArray[i]){
				candidateNameArray.splice(i,1);
				$('#candidateName').val(candidateNameArray);
			}
		}
		if($('#candidateId').val()==""){
			$('#backMaintain')[0].style.display='none';
		}
	}
}

function demandDetail(demandId){
	$("#demandId").val(demandId);
	var url = path+'/service/demand/demandDetail';
	$("#detailForm").attr("action",url);
	$("#detailForm").submit();
}

//add by jama
function demandDetailUpdate(demandId){
	$("#demandId").val(demandId);
	var url = path+'/service/demand/demandDetailUpdate';
	$("#detailForm").attr("action",url);
	$("#detailForm").submit();
}

//gkf
function backgroundOpe(){
	if($('#candidateName').val()==""){
		alert("Please choose candidate names.");
		return;
	}
	$('#feedBackDialog').modal('show');
	$('#candidatePeople').val(candidateNameArray);
}

$('#interviewForm').bootstrapValidator({
	feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
	},
	fields : {
		
		bgvCleared : {
			group : '.group',
			validators : {
				notEmpty : {
					message : 'please select.'
				},
			}
		},
	}
}).on('success.form.bv', function(e) {
    e.preventDefault();

    var $form = $(e.target);
        validator = $form.data('bootstrapValidator');
    if(validator){
    	updateBackForCandidate(e.target)
    }
    return false;
}) ;

//gkf 
function updateBackForCandidate(e){
	var candidateIds = $("#candidateId").val();
	var bgvCleared = $("#bgvCleared").val();
	
	$.ajax({
		url : path + '/service/demand/updateBackForCandidate',
		dataType : "json",
		data : {
			"candidateIds" : candidateIds,
			"bgvCleared" : bgvCleared,
		},
		async : true,
		cache : false,
		type : "post",
		success : function(resultFlag) {
			if (resultFlag) {
				alert("Information modified succesffully.")
				$('#candidateId').val("");
				$('#candidateName').val("");
				$('#candidatePeople').val("");
				candidateIdArray.splice(0,candidateIdArray.length);
				candidateNameArray.splice(0,candidateNameArray.length);
				$('#feedBackDialog').modal('hide');
				loadDemandList();
			}
		}
	})
}
