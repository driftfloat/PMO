$(function () {
	$('#myModal').modal('hide')
    loadSkillList();
    loadCSSubDept();
    loadRole();
    loadSkill();
    
    $("#officialAccreditation").change(function() { 
    	if($(this).is(':checked')){
    		$("#officialAccreditation").val('1');
    	}else{
    		$("#officialAccreditation").val('');
    	}
    });
});

function loadCSSubDept(result){
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
//			$('#csSubDept').val(result.pageInfo.du);
		}
	})
}

function loadSkill(result){
	$.ajax({
		url:path+'/service/skill/skills',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			$("#paramName").empty();
			$("#paramName").append("<option value=''>--Option--</option>");
			for(var i = 0;i<list.length;i++){
				$("#paramName").append("<option>"+list[i]+"</option>");
			}
//			$('#csSubDept').val(result.pageInfo.du);
		}
	})
}

function loadRole(result){
	var url = path+'/json/role.json'
	$.getJSON(url,  function(data) {
		   $("#role").empty();
		   $("#role").append("<option value=''>--Option--</option>");
	       $.each(data, function(i, item) {
	    	   $("#role").append("<option>"+item.name+"</option>");
	       })
//		   $('#csBu').val(result.pageInfo.bu);
	});
}

function loadCSBu(result){
	var url = path+'/json/csBuName.json'
	$.getJSON(url,  function(data) {
		   $("#csBu").empty();
		   $("#csBu").append("<option value=''>--Option--</option>");
	       $.each(data, function(i, item) {
	    	   $("#csBu").append("<option>"+item.name+"</option>");
	       })
//		   $('#csBu').val(result.pageInfo.bu);
	});
}

//function majorcateIds(result){
//	$.ajax({
//		url:path+'/service/skill/majorcateIds',
//		dataType:"json",
//		async:true,
//		cache:false,
//		type:"post",
//		success:function(list){
//			$("#majorcateId").empty();
//			$("#majorcateId").append("<option value=''>--Option--</option>");
//			for(var i = 0;i<list.length;i++){
//				$("#majorcateId").append("<option value='"+list[i].majorcateId+"'>"+list[i].name+"</option>");
//			}
//		}
//	})
//}

function loadSkillList(){
    var queryUrl = path+'/service/skill/query';
	//var queryUrl = path+'/json/test.json';
    var table = $('#skillList').bootstrapTable({
        url: queryUrl,                      //请求后台的URL（*）
        method: 'GET',                      //请求方式（*）
        toolbar: '#toolbar',              //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        fixedColumns: true,
        fixedNumber: 0,
        singleSelect : true,                // 单选checkbox 
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: true,
        showColumns: false,                  //是否显示所有的列（选择显示的列）
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        //得到查询的参数
        queryParams : function (params) {
        	return {
        		pageSize: params.limit,
        		pageNumber: params.offset/params.limit+1,
            };
        },
        columns: [
        {
        	checkbox: true,  
            visible: true                 //是否显示复选框  
        }, 
        {
            field: 'eHr',
            title: 'E-HR',
            sortable: true
        },
        {
            field: 'staffId',
            title: 'Staff ID',
            sortable: true
        },
        {
            field: 'lob',
            title: 'LOB',
            sortable: true
        }, 
        {
            field: 'staffName',
            title: 'Staff Name',
            sortable: true
        },
        {
            field: 'csSubDept',
            title: 'DU',
            sortable: true
        },
        {
            field: 'role',
            title: 'Role',
            sortable: true
        },
        {
            field: 'paramName',
            title: 'Major Skill',
            sortable: true
        }
        ,{
            field: 'officialAccreditation',
            title: 'Official Certification',
            sortable: true
            ,formatter:function(value,row,index){
            	if('1'==value){
            		return 'Yes';
            	}
            	return 'No';
            }
        }
        ,{
            field: 'workExperience',
//            title: 'work Experience(Years)',
            title: 'work',
            sortable: true
        }
        ,{
//            field: 'Operate',
            title: 'Operate'
//            ,sortable: true
            ,formatter:function(value,row,index){
            	return "<a href='javascript:void(0);' class='btn btn-info btn-small' onclick=detail('"+row.eHr+"')>Detail</a>"
            	+"<a href='javascript:void(0);' class='btn btn-info btn-small' onclick=editSkill('"+row.eHr+"')>Edit</a>"
            	;
            }
        }
        /*{
            field:'id',
            title: 'Operation',
            width: 120,
            align: 'center',
            valign: 'middle'
//        }*/
        
        ]
        ,onLoadSuccess: function () {
        	var data = $('#skillList').bootstrapTable('getData');
//        	loadOfflineSummary(data);
        },
        onLoadError: function () {
        	
        },
        onDblClickRow: function (row, $element) {
           
        }
        
    });
}

function search(){
//	//获取查询条件
	var eHr = $("#eHr").val();
	var staffId = $("#staffId").val();
	var staffName = $("#staffName").val();
	var lob = $("#lob").val();
	var role = $("#role").val();
	var csSubDept = $("#csSubDept").val();
	var paramName = $("#paramName").val();
	var officialAccreditation = $("#officialAccreditation").val();
	var workExperience = $("#workExperience").val();
	
	var queryParams = { 
		query: {  
			eHr:eHr
			,staffId:staffId
			,staffName:staffName
			,lob:lob
			,role:role
			,csSubDept:csSubDept
			,paramName:paramName
			,officialAccreditation:officialAccreditation
			,workExperience:workExperience
        }
    }  
	//刷新表格  
    $('#skillList').bootstrapTable('refresh',queryParams);  
}

function editSkill(id){
//	$("#editForm").attr("action",path+"/service/capability/editPage.html");
//	$("#id").val(id);
//	$("#editForm").submit();
	var url= path+"/service/skill/editPage.html"+"?id="+id
	location=url;
}

function detail(id){
	var queryUrl = path+'/service/skill/detail/'+id;
	$.ajax({
		url:queryUrl,
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(data){
			$("#detailBtn").click();
//			$("#accordion tbody").remove();
			$("#accordion > div:gt(0)").remove();
			
//			var _firstDiv = $("#accordion > div");
			
			var tbody ;
			var last_majorcate = '' ;
			if(data.length>0){
//				last_majorcate = data[0].majorcateId;= 
				var t = "View Detail Information of "+data[0].employeeId ;
				$("#myModalLabel").text(t);
				
//				console.log($("#myModalLabel").val());
//				console.log($("#myModalLabel").text($("#myModalLabel").text()+" "+data[0].employeeId);
			}
			for (var i = 0; i <data.length; i++) {
//				if(i>1) return;
				if(last_majorcate!=data[i].majorcateId){
					var div_id='div_'+data[i].majorcateId;
					var a_id='a_'+data[i].majorcateId;
//					$("#accordion > div:first-child").clone().insertAfter($("#accordion > div:last-child")); 
					$("#accordion > div:first-child").clone().appendTo($("#accordion")); 
					$("#accordion > div:last-child").attr('id',div_id);
					
					$("#"+div_id +" a").attr('href','#'+a_id);
					$("#"+div_id +" a").text(data[i].fatherName);
					$("#"+div_id +" > div").eq(1).attr('id', a_id);
					
					$("#accordion > div:last-child").css("display","block");
					tbody = $("#accordion tbody:last-child");
					div_id = '';
				}
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				
				var td1 = $("<td>"	+data[i].paramName + "</td>");
				var td2 = $("<td>"	+(data[i].abilityLevel==null?'':data[i].abilityLevel)	+ "</td>");
				var td3 = $("<td>"	+( data[i].mainAbility==null?'': ('1'==data[i].mainAbility?'<input CHECKED type="radio"/>':'' )	)+ "</td>");
				var td4 = $("<td>"	+( data[i].officialAccreditation==null?'':('1'==data[i].officialAccreditation?'Yes':'No' ))		+ "</td>");
				var td5 = $("<td>"	+( data[i].authenticationName==null?'':data[i].authenticationName)	+ "</td>");
				var td6 = $("<td>"	+( data[i].workExperience==null?'':data[i].workExperience)	+ "</td>");
				
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				last_majorcate = data[i].majorcateId ;
			}
		}
	})
}
	
function toEdit(id){
	var queryUrl = path+'/service/skill/detail/'+id;
	$.ajax({
		url:queryUrl,
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(data){
			$("#detailBtn").click();
//			$("#accordion tbody").remove();
			$("#accordion > div:gt(0)").remove();
			
//			var _firstDiv = $("#accordion > div");
			
			var tbody ;
			var last_majorcate = '' ;
			if(data.length>0){
//				last_majorcate = data[0].majorcateId;= 
				var t = "View Detail Information of "+data[0].employeeId ;
				$("#myModalLabel").text(t);
				
//				console.log($("#myModalLabel").val());
//				console.log($("#myModalLabel").text($("#myModalLabel").text()+" "+data[0].employeeId);
			}
			for (var i = 0; i <data.length; i++) {
//				if(i>1) return;
				if(last_majorcate!=data[i].majorcateId){
					var div_id='div_'+data[i].majorcateId;
					var a_id='a_'+data[i].majorcateId;
//					$("#accordion > div:first-child").clone().insertAfter($("#accordion > div:last-child")); 
					$("#accordion > div:first-child").clone().appendTo($("#accordion")); 
					$("#accordion > div:last-child").attr('id',div_id);
					
					$("#"+div_id +" a").attr('href','#'+a_id);
					$("#"+div_id +" a").text(data[i].fatherName);
					$("#"+div_id +" > div").eq(1).attr('id', a_id);
					
					$("#accordion > div:last-child").css("display","block");
					tbody = $("#accordion tbody:last-child");
					div_id = '';
				}
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				
				var td1 = $("<td>"	+data[i].paramName + "</td>");
				var td2 = $("<td>"	+(data[i].abilityLevel==null?'':data[i].abilityLevel)	+ "</td>");
				var td3 = $("<td>"	+( data[i].mainAbility==null?'': ('1'==data[i].mainAbility?'<input CHECKED type="radio"/>':'' )	)+ "</td>");
				var td4 = $("<td>"	+( data[i].officialAccreditation==null?'':('1'==data[i].officialAccreditation?'Yes':'No' ))		+ "</td>");
				var td5 = $("<td>"	+( data[i].authenticationName==null?'':data[i].authenticationName)	+ "</td>");
				var td6 = $("<td>"	+( data[i].workExperience==null?'':data[i].workExperience)	+ "</td>");
				
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				last_majorcate = data[i].majorcateId ;
			}
		}
	})
}
	
	


