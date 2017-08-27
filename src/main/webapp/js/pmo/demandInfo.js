
$(function(){
	loadSkill();
	loadPosition();
	loadDemandStatus();
	
	loadDepartment();
	/*根据department加载subdepartment的ajax*/
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
	
	
})

function loadSkill(){
	var url = path+'/json/skill.json'
	$.getJSON(url, function(data){
		$.each(data, function(i, item){
			 $("#skill").append("<option value='"+item.name+"'>"+item.name+"</option>");
		})
	})
}
function loadPosition(){
	var url = path+'/json/position.json'
	$.getJSON(url, function(data){
		$.each(data, function(i, item){
			$("#position").append("<option value='"+item.name+"'>"+item.name+"</option>");
		})
	})
}
function loadDemandStatus(){
	var url = path+'/json/demandStatus.json'
	$.getJSON(url, function(data){
		$.each(data, function(i, item){
			$("#status").append("<option value='"+item.name+"'>"+item.name+"</option>");
		})
	})
}

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


$("#searchBtn").click(function(){
	var skill= $("#skill").val();
	var position= $("#position").val();
	var department= $("#department").val();
	var sub_department= $("#sub_department").val();
	var status= $("#status").val();
	var rr= $("#rr").val();
	$.ajax({
		url:path+'/service/demand/queryDemandList',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		data:{"skill":skill,"position":position,"hsbcDept.hsbcDeptName":department,
			"hsbcDept.hsbcSubDeptName":sub_department,"status":status,"rr":rr},
		success:function(list){
			$.each(list, function(i,demand){
				//console.log(demand.rr);
				var tr = $("<tr id='"+demand.rr+"'></tr>");
				var td1 = $("<td>"+demand.rr+"</td>");
				var td2 = $("<td>"+demand.skill+"</td>");
				var td3 = $("<td>"+demand.position+"</td>");
				var td4 = $("<td>"+demand.hsbcDept.hsbcDeptName+"</td>");
				var td5 = $("<td>"+demand.hsbcDept.hsbcSubDeptName+"</td>");
				var td6 = $("<td>"+demand.status+"</td>");
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				$("#demandList").append(tr);
			})
		}
	})
})