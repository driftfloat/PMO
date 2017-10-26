$(function(){
	loadMyCandidate();
	dateType();
})

function loadMyCandidate(currPage){
	$("#rmCandidateList  tr:not(:first)").html("");
	$.ajax({
		url:path + '/service/rmCandidate/getMyCandidate',
		dateType:'json',
		type:'post',
		data:{"currPage":currPage},
		success:function(result){
			if(result.candidatelist.length <= 0){
				$("#rmCandidateList").append("<tr><td colspan='13' style='text-align:center'>暂无数据！</td></tr>");
				return;
			}
			//$.each(result,function(candidatePush){
			for (var i = 0; i < result.candidatelist.length; i++) {
				var tr = $("<tr id='"+result.candidatelist[i].pushId+"'></tr>");
				var td1 = $("<td>"+result.candidatelist[i].candidateInfo.candidateName+"</td>");
				var tmp = result.candidatelist[i].interviewList[0].interviewId;  
				if (!tmp && typeof(tmp)!="undefined" && tmp!=0){  
					var td2 = $("<td>未安排面试</td>"); 
				}else{
					if(result.candidatelist[i].candidateInfo.interviewStatus == '3'){
						var td2 = $("<td>面试通过</td>");
					}else if(result.candidatelist[i].candidateInfo.interviewStatus == '4'){
						var td2 = $("<td>面试失败</td>");
					}else if(result.candidatelist[i].candidateInfo.interviewStatus == '2'){
						var td2 = $("<td>面试中</td>");
					}else{
						var td2 = $("<td></td>");
					}
				}
				if(result.candidatelist[i].candidateInfo.candidateSex == '0'){
					var td3 = $("<td>女</td>");
				}else{
					var td3 = $("<td>男</td>");
				}
				var td4 = $("<td>"+result.candidatelist[i].candidateInfo.candidateAge+"</td>");
				var td5 = $("<td>"+result.candidatelist[i].candidateInfo.skill+"</td>");
				var td6 = $("<td>"+result.candidatelist[i].candidateInfo.education+"</td>");
				var td7 = $("<td>"+result.candidatelist[i].candidateInfo.experienceYears+"</td>");
				var td8 = $("<td>"+result.candidatelist[i].candidateInfo.candidateTel+"</td>");
				var td9 = $("<td>"+result.candidatelist[i].candidateInfo.email+"</td>");
				var td10 = $("<td>"+result.candidatelist[i].candidateInfo.majorStatus+"</td>");
				var td11 = $("<td>"+result.candidatelist[i].csDept.csSubDeptName+"</td>");
				var td12 = $("<td>"+result.candidatelist[i].user.userName+"</td>");
				var status = result.candidatelist[i].interviewList[0].interviewId; 
				if(!status){
					var td13 = $("<td><div class='btn-group btn-group-sm' style='width: 380px;'>" +
							"<ul >" +
							"<li style='display:inline;'><a href='javascript:void(0);' id='"+result.candidatelist[i].pushId+"' class='btn btn-info btn-small' onclick='scheduleInterview(this.id)'>New Turn</a></li>&nbsp;" +
							"<li style='display:inline;'><a href='javascript:void(0);' id='"+result.candidatelist[i].pushId+"' class='btn btn-info btn-small ' onclick='nextInterview(this.id)'>Next Interview</a></li>&nbsp;" +
							"<li style='display:inline;'><a href='javascript:void(0);' id='"+result.candidatelist[i].pushId+"' class='btn btn-info btn-small' onclick='interviewBack(this.id)'>Back</a></li>&nbsp;" +
							"<li style='display:inline;'><a href='javascript:void(0);' id='"+result.candidatelist[i].pushId+"' class='btn btn-info btn-small '  onclick='offerInterview(this.id)'>Offer</a></li></div>"+
					"</ul></td>");
				}else{
					$("#addInterviewer").removeAttr("disabled");
					var td13 = $("<td><div class='btn-group btn-group-sm' style='width: 380px;'>" +
							"<ul >" +
							"<li style='display:inline;'><a href='javascript:void(0);' id='"+result.candidatelist[i].pushId+"' class='btn btn-info btn-small ' onclick='scheduleInterview(this.id)'>New Turn</a></li>&nbsp;" +
							"<li style='display:inline;'><a href='javascript:void(0);' id='"+result.candidatelist[i].pushId+"' class='btn btn-info btn-small' onclick='nextInterview(this.id)'>Next Interview</a></li>&nbsp;" +
							"<li style='display:inline;'><a href='javascript:void(0);' id='"+result.candidatelist[i].pushId+"' class='btn btn-info btn-small' onclick='interviewBack(this.id)'>Back</a></li>&nbsp;" +
							"<li style='display:inline;'><a href='javascript:void(0);' id='"+result.candidatelist[i].pushId+"' class='btn btn-info btn-small' onclick='offerInterview(this.id)'>Offer</a></li></div>"+
					"</ul></td>");
				}
							
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				td7.appendTo(tr);
				td8.appendTo(tr);
				td9.appendTo(tr);
				td10.appendTo(tr);
				td11.appendTo(tr);
				td12.appendTo(tr);
				td13.appendTo(tr);
				$("#rmCandidateList").append(tr);
			}
			$("#pageCount").html(result.pageCondition.totalPage);
			$("#currentPage").html(result.pageCondition.currPage);
			$("#fristPage").attr("onclick","loadMyCandidate(1)");
			if(result.pageCondition.currPage <= result.pageCondition.totalPage){
				$("#previousPage").attr("onclick","loadMyCandidate("+(result.pageCondition.currPage - 1)+")");
				$("#nextPage").attr("onclick","loadMyCandidate("+(result.pageCondition.currPage + 1)+")");
				$("#lastPage").attr("onclick","loadMyCandidate("+(result.pageCondition.totalPage)+")");
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
		}
	})
}

function dateType(){
	$('.form_datetime').datetimepicker({
		weekStart: 1,
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
		language:'zh-CN',
		format: 'yyyy-mm-dd hh:ii:00',
		pickerPosition: 'bottom-left',
		showMeridian: 1
	});
}
/*新一轮面试*/
function scheduleInterview(pushId){
	$('#myModal').modal('show');
	loadInterviewer();
	$("#addInterviewer").click(function(){
		var interviewDate = $("#interviewDate").val();
		var interviewerId = $("#interviewer").val();
		$.ajax({
			url:path+'/service/rmCandidate/addInterview',
			dataType:"json",
			async:true,
			cache:false,
			type:"post",
			data:{"pushId":pushId,"interviewDate":interviewDate,"interviewerId":interviewerId},
			success:function(data){
				if(data == "1"){
					$('#myModal').modal('hide');
					//BootstrapDialog.alert('安排面试成功!');
					BootstrapDialog.show({
				        title: '面试安排',
				        message: '新一轮面试安排成功!',
				        size: BootstrapDialog.SIZE_NORMAL,
				        buttons: [{
				            label: '确认',
				            action: function(dialog) {
				            	loadMyCandidate();
				            	dialog.close();
				            }
				        }]
				    });
				}else{
					BootstrapDialog.alert('安排面试失败!');
				}
			}
		})
		$('#graduationDate1').val("");
		$("#interviewer").val("");
	})
}
/*下一次面试*/
function nextInterview(pushId){
	$('#myModal').modal('show');
	loadInterviewer();
	$("#addInterviewer").click(function(){
		var interviewDate = $("#interviewDate").val();
		var interviewerId = $("#interviewer").val();
		$.ajax({
			url:path+'/service/rmCandidate/addNextInterview',
			dataType:"json",
			async:true,
			cache:false,
			type:"post",
			data:{"pushId":pushId,"interviewDate":interviewDate,"interviewerId":interviewerId},
			success:function(data){
				if(data == "1"){
					$('#myModal').modal('hide');
					BootstrapDialog.show({
				        title: '面试安排',
				        message: '下一次面试安排成功!',
				        size: BootstrapDialog.SIZE_NORMAL,
				        buttons: [{
				            label: '确认',
				            action: function(dialog) {
				            	loadMyCandidate();
				            	dialog.close();
				            }
				        }]
				    });
				}else{
					BootstrapDialog.alert('安排面试失败!');
				}
			}
		})
		$('#graduationDate1').val("");
		$("#interviewer").val("");
	})
}
/*退回*/
function interviewBack(pushId){
	/*BootstrapDialog.show({
        title: 'Default Title',
        message: '确认退回？',
        size: BootstrapDialog.SIZE_NORMAL
	});*/
	BootstrapDialog.show({
        title: '面试安排',
        message: '确认退回此候选人？',
        size: BootstrapDialog.SIZE_NORMAL,
        buttons: [{
            label: '确认',
            action: function(dialog) {
            	$.ajax({
            		url:path+'/service/rmCandidate/interviewBack',
            		dataType:"json",
            		async:true,
            		cache:false,
            		type:"post",
            		data:{"pushId":pushId},
            		success:function(data){
            			loadMyCandidate();
            		}
            	});
            	dialog.close();
            }
        }, {
            label: '取消',
            action: function(dialog) {
                dialog.close();
            }
        }]
    });
	
}

function loadInterviewer(){
	$("#interviewer").empty();
	$("#interviewer").append("<option value=''>-- select --</option>");
	$.ajax({
		url:path+'/service/rmCandidate/loadInterviewer',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(data){
			$.each(data, function(i,item){
				$("#interviewer").append("<option value='"+item.employeeId+"'>"+item.staffName+"</option>")
			})
		}
	})
}
/* offer */
function offerInterview(pushId){
	var str = '<table id="demandList" class="table table-bordered table-hover">'+
	'<thead><tr><th></th><th>RR</th><th>job Code</th><th>Tech/Skill</th>'+
	'<th>Position</th><th>Department</th><th>Sub - Department</th>'+
	'<th>Status</th><th>交付部</th><th>详情</th></tr></thead></table>';
	$("#table_area").append(str);

	$("#demandList").delegate("tr","click",function(e){
		$(this).find("input[type=radio]").prop("checked",true);
	});
	
	$.ajax({
		url:path+'/service/rmCandidate/offerDemandList',
		dataType:'json',
		async:false,
		cache:false,
		type:'post',
		data:{"pushId":pushId},
		success:function(result){
			if(result.list.length < 0){
				$("#demandList").append("<tr><td colspan='9' style='text-align:center'>暂无数据！</td></tr>");
				return;
			}
			for (var i = 0; i < result.list.length; i++) {
				var tr = $("<tr id='"+result.list[i].demandId+"'></tr>");
				var td1 = $("<td><input type='radio' name='checkAll' value='"+result.list[i].demandId+"'/></td>");
				var td2 = $("<td>"+result.list[i].rr+"</td>");
				var td3 = $("<td>"+result.list[i].jobCode+"</td>");
				var td4 = $("<td>"+result.list[i].skill+"</td>");
				var td5 = $("<td>"+result.list[i].position+"</td>");
				if(result.list[i].hsbcDept == null){
					var td6 = $("<td></td>");
					var td7 = $("<td></td>");
				}else{
					var td6 = $("<td>"+result.list[i].hsbcDept.hsbcDeptName+"</td>");
					var td7 = $("<td>"+result.list[i].hsbcDept.hsbcSubDeptName+"</td>");
				}
				var td8 = $("<td>"+result.list[i].status+"</td>");
				var td9 = $("<td>"+result.list[i].csSubDept+"</td>");
				var td10 = $("<td><div class='btn-group btn-group-sm'><a href='javascript:void(0);' class='btn btn-primary' onclick='demandDetail("+result.list[i].demandId+")'>详情</a></div></td>");
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				td7.appendTo(tr);
				td8.appendTo(tr);
				td9.appendTo(tr);
				td10.appendTo(tr);
				$("#demandList").append(tr);
			}
		}
		
	});
	
	var demandTable = $("#demandList");
	var demandId1 = $("input[name=checked]:checked").val();
	BootstrapDialog.show({
        title: 'DemandList',
        message: demandTable,
        size: BootstrapDialog.SIZE_WIDE,
        buttons: [{
            label: '确认',
            action: function(dialog) {
            	var demandId = $("input[name=checkAll]:checked").val();
            	$.ajax({
            		url:path+'/service/rmCandidate/offerInterview',
            		dataType:"json",
            		async:false,
            		cache:false,
            		type:"post",
            		data:{"pushId":pushId,"demandId":demandId},
            		success:function(data){
            			if(data == "1"){
            				BootstrapDialog.show({
            					title:'面试安排',
            					message:'offer成功！',
            					size: BootstrapDialog.SIZE_SMALL
            				})
            			}
            			loadMyCandidate();
            		}
            	});
            	dialog.close();
            }
        }, {
            label: '取消',
            action: function(dialog) {
                dialog.close();
            }
        }],
        onshown:function(e){
        	$(".bootstrap-dialog-message").css("maxHeight",window.innerHeight-240+"px")
        }
    });
}
