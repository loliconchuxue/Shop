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
<script type="text/javascript" src="${ctxsta}/os/user/js/modifyuserInfo.js"></script>
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
        		<div class="item col-xs-12"> <span class="intelligent-label f-fl"><b class="ftx04">*</b>真实姓名：</span>
            		<div class="f-fl item-ifo">
            			<c:if test="${empty user.realName }"><input type="text" name="realName" id="realName" /></c:if>
             			<c:if test="${not empty user.realName }"><input type="text" name="realName" disabled="disabled" id="realName" value="${user.realName }" /></c:if>
              			<span class="clearfix"></span>
            		</div>
          		</div>
         		<div class="item col-xs-12"> <span class="intelligent-label f-fl"><b class="ftx04">*</b>性别：</span>
            		<div class="f-fl item-ifo">
             			<select name="sex" id="sex">
             			<option value="0">保密</option>
             			<option selected="selected" value="1">男</option>
             			<option value="2">女</option>
             			</select>
              			<span class="clearfix valid" id="sex"></span>
            		</div>
          		</div>
          		<div class="item col-xs-12"> <span class="intelligent-label f-fl"><b class="ftx04">*</b>年龄：</span>
            		<div class="f-fl item-ifo">
             		 	<input type="number" name="age" id="age" value="${user.age }"/>
             		 	<span class="clearfix valid" id="ageMSG"></span>
            		</div>
          		</div>
          		<div class="item col-xs-12 message" style="height:auto"> <span class="intelligent-label f-fl">&nbsp;</span>
            		<label class="valid" ></label>
          		</div>
          		<div class="item col-xs-12"> <span class="intelligent-label f-fl">&nbsp;</span>
            		<div class="f-fl item-ifo"> <a href="javascript:;" class="btn btn-blue f-r3" id="btn_modifyUserInfo">确定</a> </div>
          		</div>
        	</div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>