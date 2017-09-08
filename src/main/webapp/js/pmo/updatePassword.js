
	$("#editPwd").bind("click",function(){
	$("#editPassword").modal('show');
	})


function updatePassword(){
	var newPwd = $("#newPassword").val();
	var confPwd = $("#confPassword").val();
	if(newPwd==confPwd){
		$.ajax({
			url : path+"/service/user/updatePassword",
			type : "post",
			async : true,
			cache : false,
			dataType : "json",
			data : {'newPwd':newPwd},
			//timeout : 20000,
			success : function(data) {
				if(data=="0"){
					
					alert("修改成功");
				}
			}
		});
	}else{
		$("#updateAlert").html('确认密码与新密码不一致').show();
		$("#updateAlert").css({color:"red"});
	}
}