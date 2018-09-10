$(function() {	
	
	$("input").blur(function() {
		checkPWD();
	})
$("body").on("click","#btn_ModifyPWD", function() {
	var oldPassword = $("input[name='oldPassword']").val();
	var newPassword = $("input[name='newPassword']").val();
	if (!checkPWD()) {
		return false;
	}
	$.ajax({
		type : "POST",
		url : baselocation + '/uc/user/password',
		data : {
			"oldPassword" : oldPassword,
			"newPassword" : newPassword
		},
		dataType : "json",
		success : function(result) {
			if (result.code == 1) {
				layer.alert('修改成功!', {
					icon : 1,
					time : 2000
				});
				window.location.href = baselocation + '/user/login'
			} else if (result.code == 2) {
				layer.msg('原密码错误!', {
					icon : 2,
					time : 2000
				});
			} else {
				layer.msg('修改失败!', {
					icon : 2,
					time : 2000
				});
			}
		}
	})
})	

function checkPWD() {
	var oldPassword = $("input[name='oldPassword']").val();
	var newPassword = $("input[name='newPassword']").val();
	var rePassword = $("input[name='rePassword']").val();
	$("#repwdMSG").html("");
	$("#pwdMSG").html("");
	if (newPassword=="") {
		$("#pwdMSG").html("密码不能为空");
		return false;
	}
	if (newPassword.length<6) {
		$("#pwdMSG").html("密码长度最少6位");
		return false;
	}
	if (newPassword!=rePassword) {
		$("#pwdMSG").html("");
		$("#repwdMSG").html("前后密码不一致");
		return false;
	}
	return true;
}

})