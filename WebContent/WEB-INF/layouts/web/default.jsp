<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title><sitemesh:write property='title' />宅米</title>
	<meta name="author" content="宅米" />  
	<meta name="keywords" content="宅米">
	<meta name="description" content="宅米">
	<link rel="shortcut icon" href="${ctximg}/default/ico/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="${ctxsta}/common/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${ctxsta}/os/css/base.css">
	<script> var t1 = new Date().getTime(); baselocation='${ctx}';</script>
	<sitemesh:write property='head' />
  </head>
  <body>

	<jsp:include page="/WEB-INF/layouts/web/header.jsp" />
	
	<sitemesh:write property='body' />
	
	<jsp:include page="/WEB-INF/layouts/web/footer.jsp" />
	
	<!-- 全局js -->
	<script src="${ctxsta}/common/jquery/jquery-3.2.0.min.js"></script>
	<script src="${ctxsta}/common/bootstrap/js/bootstrap.min.js"></script>
	<!-- 自定义js -->
	<script src="${ctxsta}/os/js/zySearch.js"></script>	
	<script src="${ctxsta}/os/js/jump.js"></script>
	<script src="${ctxsta}/os/js/base.js"></script>
	<!-- 第三方插件 -->
	<sitemesh:write property='myfooter' />

  </body>
</html>
