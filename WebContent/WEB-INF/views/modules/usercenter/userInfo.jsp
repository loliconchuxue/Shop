<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>个人中心 - 宅米商城</title>
</head>
<body>
<div class="span16">
  <div class="uc-box uc-main-box">
    <div class="uc-content-box">
      <div class="box-hd">
        <h2 class="title">个人信息</h2>
      </div>
      <div class="box-bd">
        <div class="user-card portal-main clearfix">
        <center>
        <table style="font-size: 20px;">
        <tbody>
        <tr><td colspan="2"><img class="avatar" src="${ctximg}/${userInfo.picImg }" width="150px" height="150px" /></td></tr>
        <tr><td>用户编号 </td><td>${userInfo.userNumber }</td></tr>
        <tr><td>用户名 </td><td>${userInfo.userName }</td></tr>
        <tr><td>真实姓名 </td><td><c:if test="${empty userInfo.realName }">请完善</c:if><c:if test="${not empty userInfo.realName }">${userInfo.realName }</c:if></td></tr>
        <tr><td>性别 </td><td><c:if test="${empty userInfo.sex }">请完善</c:if><c:if test="${userInfo.sex eq 0}">保密</c:if><c:if test="${userInfo.sex eq 1}">男</c:if><c:if test="${userInfo.sex eq 2}">女</c:if></td></tr>
        <tr><td>年龄 </td><td><c:if test="${empty userInfo.age }">请完善</c:if><c:if test="${not empty userInfo.age }">${userInfo.age }</c:if></td></tr>
        <tr><td>电话 </td><td><c:if test="${empty userInfo.telephone }">请完善</c:if><c:if test="${not empty userInfo.telephone }">${userInfo.telephone }</c:if></td></tr>
        <tr><td>Email </td><td><c:if test="${empty userInfo.email }">请完善</c:if><c:if test="${not empty userInfo.email }">${userInfo.email }</c:if></td></tr>
        </tbody>
        </table>
        </center>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>