<%@ page language="java" import="java.util.*,com.lcz.shop.entity.Category" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/static/admin/css/Style1.css" type="text/css" rel="stylesheet">
		<script src="${pageContext.request.contextPath}/static/common/jquery/jquery-2.1.4.min.js"></script> 
	</HEAD>
	
	<body>
		<!--  -->
		<h1 align="center">商品详情</h1>
				<fieldset style="width: 400px; text-align: left;">
				<legend>商品简述</legend>
				<table>
				<tbody>
					<tr><td>商品图片</td><td>${product.picImg }</td>
					<tr><td>商品编号</td><td>${product.productNumber }</td>
					<tr><td>商品名称</td><td>${product.name }</td>
					<tr><td>商品价格</td><td>${product.showPrice }</td>
					<tr><td>商品简介</td><td>${product.introduce }</td>
				</tbody>
				</table>
				</fieldset>
				
				<fieldset style="width: 400px; text-align: left;">
				<legend>商品详情</legend>
				<div id="detail">
					${detail.description }
				</div>
				</fieldset>	
				
				<fieldset style="width: 400px; text-align: left;">
				<legend>规格参数</legend>
				<table>
					<thead><tr><th>参数名称</th><th>参数值</th></tr></thead>
					<tbody id="parameter">
					<c:forEach items="${parameters }" var="parameter">					
						<tr><td>${parameter.name }</td><td>${parameter.value }</td></tr>
					</c:forEach>
					</tbody>
				</table>
				</fieldset>
		
	</body>
</HTML>