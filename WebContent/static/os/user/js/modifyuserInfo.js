/**
 * 
 */

$(function() {
	
$("body").on("click","#btn_modifyUserInfo", function() {
	
		var realName=$("input[name='realName']").val();
		var sex=$("select[name='sex']").val();
		var age = $("input[name='age']").val();
		
	if (!checkAge()) {
		return false;
	}
	$.ajax({
		type : "POST",
		url : baselocation + '/uc/user/modifyuserInfo',
		data : {
			"realName" : realName,
			"sex" : sex,
			"age" : age
		},
		dataType : "json",
		success : function(result) {
			if (result.code == 1) {
				layer.alert('修改成功!', {
					icon : 1,
					time : 2000
				});
				window.location.href = baselocation + '/uc/user/userInfo'
			} else {
				layer.msg('修改失败!', {
					icon : 2,
					time : 2000
				});
			}
		}
	})
})

function checkAge() {
		var age = $("input[name='age']").val();
		$("#ageMSG").html("");
		if (age<6||age>150) {
			$("#ageMSG").html("年龄必须在6-150之间");
			return false;
		}
		return true;
	}


})