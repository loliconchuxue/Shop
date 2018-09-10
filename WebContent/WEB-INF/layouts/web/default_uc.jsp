<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>
<sitemesh:write property='title' />宅米</title>
<meta name="author" content="宅米" />
<meta name="keywords" content="宅米">
<meta name="description" content="宅米">
<link rel="shortcut icon" href="${ctximg}/default/ico/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="${ctxsta}/common/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctxsta}/os/css/base.css">
<link rel="stylesheet" href="${ctxsta}/os/css/main.css">
<script> var t1 = new Date().getTime(); baselocation='${ctx}';</script>
<sitemesh:write property='head' />
</head>
<body>
<jsp:include page="/WEB-INF/layouts/web/header.jsp" />

<!-- 轮播top菜单导航引入 -->
<jsp:include page="/WEB-INF/views/modules/common/site_header.jsp" />
<!-- 轮播top菜单导航引入 --> 

<!--     导航栏 begin       -->
<div class="breadcrumbs">
  <div class="container-fluid"> <a href="${ctx}/index">首页</a><span class="sep">&gt;</span><span class="title"></span> </div>
</div>
<!--     导航栏 begin       --> 

<!--     个人中心 begin       -->
<div class="page-main user-main">
  <div class="container-fluid">
    <div class="row">
      <div class="span4">
        <div class="uc-box uc-sub-box">
          <div class="uc-nav-box">
            <div class="box-hd">
              <h3 class="title">订单中心</h3>
            </div>
            <div class="box-bd">
              <ul class="uc-nav-list">
                <li><a href="${ctx}/uc/order/list">我的订单</a></li>
              </ul>
            </div>
          </div>
          <div class="uc-nav-box">
            <div class="box-hd">
              <h3 class="title">个人中心</h3>
            </div>
            <div class="box-bd">
              <ul class="uc-nav-list">
                <li><a href="${ctx}/uc/user/portal">我的个人中心</a></li>
                <li><a href="${ctx}/uc/user/address">收货地址</a></li>
              </ul>
            </div>
          </div>
          <div class="uc-nav-box">
            <div class="box-hd">
              <h3 class="title">账户管理</h3>
            </div>
            <div class="box-bd">
              <ul class="uc-nav-list">
                <li><a href="${ctx}/uc/user/userInfo">个人信息</a></li>
                <li><a href="${ctx}/uc/user/password">修改密码</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <sitemesh:write property='body' />
    </div>
  </div>
</div>
<!--     个人中心 end       -->

<jsp:include page="/WEB-INF/layouts/web/footer.jsp" />

<!-- 全局js --> 
<script src="${ctxsta}/common/jquery/jquery-3.2.0.min.js"></script> 
<script src="${ctxsta}/common/bootstrap/js/bootstrap.min.js"></script> 
<!-- 自定义js --> 
<script src="${ctxsta}/os/js/zySearch.js"></script> 
<script src="${ctxsta}/os/js/jump.js"></script> 
<script src="${ctxsta}/os/js/base.js"></script> 
<script src="${ctxsta}/os/js/main.js"></script> 
<!-- 第三方插件 -->
<sitemesh:write property='myfooter' />
</body>
</html>