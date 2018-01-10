$(function(){
	
	loadUserInfo();
});



function updateUser(){
	var bootstrapValidator = $("#updateUserForm").data('bootstrapValidator');
	   bootstrapValidator.validate();
	if(bootstrapValidator.isValid()){
		var userId = $('#userId').val();
		var eHr = $('#eHr').val();
		var nickName = $('#nickName').val();
		var bu = $('#buName').val();
		var du = $('#csSubDept option:selected').val();
		var userType = $('#userType').val();
		$.ajax({
			url:path+'/service/user/updateUser',
			dataType:"json",
			data:{"userId":userId,"eHr":eHr,"userName":nickName,"bu":bu,"du":du,"userType":userType},
			async:true,
			cache:false,
			type:"post",
			success:function(resultFlag){
				if(resultFlag){
					$("html,body").animate({scrollTop:0}, 500);
					$('#successAlert').html('用户'+nickName+'信息修改成功').show();
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



function loadUserInfo(){
	var userId = $('#userId').val();
	
	$.ajax({
		url:path+'/service/user/queryUserById',
		dataType:"json",
		data:{"userId":userId},
		async:true,
		cache:false,
		type:"post",
		success:function(user){
			
			loadCSBu(user);
			
			loadCSDept(user);
			
			loadUserType(user);
			
			
			$('#nickName').val(user.nickname);
			
			$('#eHr').val(user.userName);
			
		}
	})
}


function loadCSDept(user){
	$.ajax({
		url : path + '/service/csDept/queryAllCSSubDept',
		dataType : "json",
		async : true,
		cache : false,
		type : "post",
		success : function(result) {
			var csSubs = result.csSubDepts;

			for (var i = 0; i < result.data.length; i++) {
				$("#csSubDept").append(
						"<option value='" + result.data[i].csSubDeptId + "'>"
								+ result.data[i].csSubDeptName + "</option>");
			}
			$('#csSubDept').val(user.csdeptId);
		}
	})
}

function loadCSBu(user){
	var userType = user.user_type;
	var url = path+'/json/csBuName.json'
	$.getJSON(url,  function(data) {
		   $("#buName").empty();
		   $("#buName").append("<option value=''>--Option--</option>");
	       $.each(data, function(i, item) {
	    	   $("#buName").append("<option>"+item.name+"</option>");
	       })
		   $('#buName').val(user.bu);
	});
}


function loadUserType(user){
	var url = path+'/json/userType.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#userType").append("<option value='"+item.key+"'>"+item.name+"</option>");
	       })
	       $('#userType').val(user.userType);
	});
}