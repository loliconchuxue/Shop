<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>个人中心 - 宅米商城</title>

<link rel="stylesheet" href="${ctxsta}/os/css/address.css">
<link rel="stylesheet" href="${ctxsta}/os/area/css/select2.css" />
<script src="${ctxsta}/common/jquery/jquery-3.2.0.min.js"></script>
  <script src="${ctxsta}/common/layer/layer.js"></script> 
<script type="text/javascript">
	$(function () {
		var baselocation='${ctx}';
		$("#avatar").change(function () {
			var form=new FormData(document.getElementById("form"));
			if("undefined" != typeof(form) && form != null && form != ""){
				$.ajax({
					url:baselocation+'/uc/user/avatar',
					type:'POST',
					data: form,
					async: false,
					cache: false,
					contentType: false,
					processData: false,
					success:function(data){
						if (data.code==1) {
							layer.msg("上传成功", {
								icon : 1,
								time : 1000
							});
							$("#head").attr("src","${ctx}/uploads/"+data.message);
						}else {
							layer.alert(result.message, {
								icon : 2
							});
						}
					},
					error:function(){
						layer.alert("上传失败", {
							icon : 2
						});
					}
				})
			}
			
		})
	})
</script>
<style type="text/css">
.file {
	width:90px;
	height:30px;
    position: relative;
    display: block;
    background: #D0EEFF;
    border: 1px solid #99D3F5;
    border-radius: 4px;
    padding: 4px 12px;
    overflow: hidden;
    color: #1E88C7;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
}
.file input {
	width:90px;
	height:30px;
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.file:hover {
    background: #AADFFD;
    border-color: #78C3F3;
    color: #004974;
    text-decoration: none;
}
</style>
</head>
<body>
<div class="span16">
  <div class="uc-box uc-main-box">
    <div class="uc-content-box portal-content-box">
      <div class="box-bd">
        <div class="portal-main clearfix">
          <div class="user-card">
            <h2 class="username">${sessionScope.user.userName}</h2>
            <p class="tip">你好</p>
            <a class="link" href="${ctx}/uc/user/modifyuserInfo" target="_blank">修改个人信息 &gt;</a> 
            <img id="head" class="avatar" src="${ctximg}/${sessionScope.user.picImg}" width="160" height="160" alt="${sessionScope.user.userName}">
            <p>
            <form id="form" method="post" enctype="multipart/form-data"><a id="avatar" href="javascript:;" class="file">上传头像<input type="file" name="avatar"></a></form>
            </p>
          </div>
          <div class="user-actions">
            <ul class="action-list">
              <li>账户安全：<span class="level level-3">较高</span></li>
              <li>绑定手机：<span class="tel">${sessionScope.user.telephone}</span></li>
            </ul>
          </div>
        </div>
        <div class="portal-sub">
          <ul class="info-list clearfix">
            <li>
              <h3>待支付的订单：<span class="num">0</span></h3>
              <a href="${ctx}/uc/order/list?type=1">查看待支付订单&nbsp;<i class="glyphicon glyphicon-chevron-right"></i></a><img src="${ctxsta}/os/images/portal-icon-1.png" alt=""></li>
            <li>
              <h3>待收货的订单：<span class="num">0</span></h3>
              <a href="${ctx}/uc/order/list?type=2">查看待收货订单&nbsp;<i class="glyphicon glyphicon-chevron-right"></i></a> <img src="${ctxsta}/os/images/portal-icon-2.png" alt=""> </li>
          </ul>
        </div>
        
      </div>
    </div>
  </div>
</div>
</body>
</html>