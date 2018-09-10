<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>账户注册 | 猫宁网 - 但行好事，莫问前程</title>
<meta name="author" content="猫宁Morning. - 但行好事，莫问前程。" />
<meta name="keywords" content="猫宁商城,猫宁Morning,猫宁公益商城,公益,电子商城,猫宁社区,公益商城,在线购物,社会责任">
<meta name="description" content="猫宁Morning公益商城是中国公益性在线电子商城，以商城B2C模式运营的公益在线商城，是一家致力于将传统公益商城互联网化的创新公益商城。">
<link rel="shortcut icon" href="${ctximg}/default/ico/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="${ctxsta}/common/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctxsta}/os/user/css/gloab.css" />
<link rel="stylesheet" href="${ctxsta}/os/user/css/index.css" />
<script> var t1 = new Date().getTime(); baselocation='${ctx}';</script>
</head>
<body class="bgf4">
<script zIndex="-1" src="${ctxsta}/os/user/js/canvas-nest.min.js"></script>
<div class="login-box">
  <div class="container-nav"> <a class="navbar-brand">但行好事,莫问前程.</a>
  </div>
  <div class="main">
    <div class="reg-box-pan display-inline">
      <div class="reg-box login" id="verifyCheck" style="margin-top:20px;">
        <div class="part1">
          <form >
            <div class="item col-xs-12">
              <div class="f-fl item-ifo">
                <input type="text" name="loginName" maxlength="20" placeholder="管理员用户" class="txt03 f-r3 required loginPage" data-error="请输入帐号" id="adminNo" />
                <span class="ie8 icon-close close hide"></span>
                <label class="icon-sucessfill blank hide"></label>
                <label class="focus"><span></span></label>
                <label class="focus valid"></label>
              </div>
            </div>
            <div class="item col-xs-12">
              <div class="f-fl item-ifo">
                <input type="password" name="loginPassword" id="password" maxlength="20" placeholder="密码" class="txt03 f-r3 required loginPage" />
                <span class="ie8 icon-close close hide" style="right:55px"></span> <span class="showpwd" data-eye="password"></span>
                <label class="icon-sucessfill blank hide"></label>
                <label class="focus"></label>
                <label class="focus valid"></label>
                <span class="clearfix" id="pwdMSG"></span>
              </div>
            </div>
          	<div class="item col-xs-12">
            	<div class="f-fl item-ifo"> <a href="javascript:;" class="btn btn-blue f-r3" id="btn_login" >立即登录</a> </div>
          	</div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="${ctxsta}/common/jquery/jquery-2.1.4.min.js"></script> 
<script src="${ctxsta}/common/bootstrap/js/bootstrap.min.js"></script> 
<script src="${ctxsta}/common/security/security.js"></script>
<script type="text/javascript">
$(function () {

	$("#btn_login").click(function () {
		var loginName=$("input[name='loginName']").val();
		var loginPassword=$("input[name='loginPassword']").val();
		$.ajax({
			type : "POST",
			url : baselocation + '/admin/login',
			data : {
				'loginName' : loginName,
				'loginPassword' :loginPassword
			},
			dataType : "json",
			success : function(result) {
				if (result.code == 1) {
					window.location.href = baselocation + '/admin/home';
				} else {
					$("#pwdMSG").html(result.message);
				}
			}
		})
	})
	
})

</script>
</body>
</html>
