<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>个人中心 - 宅米商城</title>
<link rel="stylesheet" href="${ctxsta}/os/user/css/gloab.css" />
<link rel="stylesheet" href="${ctxsta}/os/user/css/index.css" />
<script src="${ctxsta}/common/jquery/jquery-2.1.4.min.js"></script> 
<script src="${ctxsta}/common/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctxsta}/common/layer/layer.js"></script> 
<script type="text/javascript" src="${ctxsta}/os/user/js/password.js"></script>
</head>
<body>
<div class="span16">
  <div class="uc-box uc-main-box">
    <div class="uc-content-box">
      <div class="box-hd">
        <h2 class="title">个人信息</h2>
      </div>
      <div class="box-bd">
        <div class="user-card portal-main clearfix reg-box retrieve-psw">
        	<div class="part3 display-inline" >
        		<div class="item col-xs-12"> <span class="intelligent-label f-fl"><b class="ftx04">*</b>原密码：</span>
            		<div class="f-fl item-ifo">
             			<input type="password" name="oldPassword" id="oldpassword" maxlength="20" class="txt03 f-r3 required btn_Pswpart3" tabindex="3" style="ime-mode:disabled;" onpaste="return  false" autocomplete="off" data-valid="isNonEmpty||between:6-20||level:2" data-error="密码不能为空||密码长度6-20位||该密码太简单，有被盗风险，建议字母+数字的组合" />
             		 	<span class="ie8 icon-close close hide" style="right:55px"></span> <span class="showpwd" data-eye="password"></span>
              			<label class="icon-sucessfill blank hide"></label>
              			<label class="focus">6-20位英文（区分大小写）、数字、字符的组合</label>
              			<label class="focus valid"></label>
              			<span class="clearfix"></span>
              			<label class="strength"> <span class="f-fl f-size12">安全程度：</span> <b><i>弱</i><i>中</i><i>强</i></b> </label>
            		</div>
          		</div>
         		<div class="item col-xs-12"> <span class="intelligent-label f-fl"><b class="ftx04">*</b>密码：</span>
            		<div class="f-fl item-ifo">
             			<input type="password" name="newPassword" id="password" maxlength="20" class="txt03 f-r3 required btn_Pswpart3" tabindex="3" style="ime-mode:disabled;" onpaste="return  false" autocomplete="off" data-valid="isNonEmpty||between:6-20||level:2" data-error="密码不能为空||密码长度6-20位||该密码太简单，有被盗风险，建议字母+数字的组合" />
             		 	<span class="ie8 icon-close close hide" style="right:55px"></span> <span class="showpwd" data-eye="password"></span>
              			<label class="icon-sucessfill blank hide"></label>
              			<label class="focus">6-20位英文（区分大小写）、数字、字符的组合</label>
              			<label class="focus valid"></label>
              			<span class="clearfix valid" id="pwdMSG"></span>
              			<label class="strength"> <span class="f-fl f-size12">安全程度：</span> <b><i>弱</i><i>中</i><i>强</i></b> </label>
            		</div>
          		</div>
          		<div class="item col-xs-12"> <span class="intelligent-label f-fl"><b class="ftx04">*</b>确认密码：</span>
            		<div class="f-fl item-ifo">
             		 	<input type="password" name="rePassword"  maxlength="20" class="txt03 f-r3 required btn_Pswpart3" tabindex="4" style="ime-mode:disabled;" onpaste="return  false" autocomplete="off" data-valid="isNonEmpty||between:6-16||isRepeat:password" data-error="密码不能为空||密码长度6-16位||两次密码输入不一致" id="rePassword" />
              			<span class="ie8 icon-close close hide" style="right:55px"></span> <span class="showpwd" data-eye="rePassword"></span>
              			<label class="icon-sucessfill blank hide"></label>
              			<label class="focus">请再输入一遍上面的密码</label>
              			<label class="focus valid"></label>
              			<span class="clearfix valid" id="repwdMSG"></span>
            		</div>
          		</div>
          		<div class="item col-xs-12 message" style="height:auto"> <span class="intelligent-label f-fl">&nbsp;</span>
            		<label class="valid" ></label>
          		</div>
          		<div class="item col-xs-12"> <span class="intelligent-label f-fl">&nbsp;</span>
            		<div class="f-fl item-ifo"> <a href="javascript:;" class="btn btn-blue f-r3" id="btn_ModifyPWD">确定</a> </div>
          		</div>
        	</div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>