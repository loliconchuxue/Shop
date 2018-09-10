<%@ page language="java" import="java.util.*,com.lcz.shop.entity.Category" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/static/admin/css/Style1.css" type="text/css" rel="stylesheet">
		<script src="${pageContext.request.contextPath}/static/common/jquery/jquery-2.1.4.min.js"></script> 
		<script type="text/javascript">
			$(function(){
				var count_detail=1;
				$("#addDetail").click(function(){
					count_detail++;
					$("#detail").append("<p>图片"+count_detail+"：<input type='file' name='detail' style='width: 250px;' /></p>");
				});
				var count_parameter=1;
				$("#addParameter").click(function(){
					count_parameter++;
					$("#parameter").append("<tr><td><input type='text' name='parametername' style='width: 200px;'/></td><td><input type='text' name='parametervalue' style='width: 200px;' /></td></tr>");
				});
			})
			$(function () {
				/* 第一步 */
				$("input[name='btn_1']").click(function () {
					var form1=new FormData(document.getElementById("addproduct"));
				//	alert(form1);
					$.ajax({
						url: '${pageContext.request.contextPath}/admin/product/addproduct',
						type: 'POST',
						data: form1,
						async: false,
						cache: false,
						contentType: false,
						processData: false,
						success: function (data) {
								if (data.code==1) {
									alert("添加成功");
									$("input[name='productId']").val(data.message);
									$("#addproduct").hide();
									$("#addproductdetail").show();
								}else{
									alert(data.message);
								}
							}
						});
				})
				/* 第二步 */
				$("input[name='btn_2']").click(function () {
					var form2=new FormData(document.getElementById("addproductdetail"));
				//	alert(form2);
					$.ajax({
						url: '${pageContext.request.contextPath}/admin/product/addproductdetail',
						type: 'POST',
						data: form2,
						async: false,
						cache: false,
						contentType: false,
						processData: false,
						success: function (data) {
								if (data.code==1) {
									alert("添加成功");
									$("input[name='productId']").val(data.message);
									$("#addproductdetail").hide();
									$("#addproductpic").show();
								}else{
									alert(data.message);
								}
							}
						});
				})
				/* 第三步 */
				$("input[name='btn_3']").click(function () {
					var form3=new FormData(document.getElementById("addproductpic"));
					alert(form3);
					$.ajax({
						url: '${pageContext.request.contextPath}/admin/product/addproductpic',
						type: 'POST',
						data: form3,
						async: false,
						cache: false,
						contentType: false,
						processData: false,
						success: function (data) {
								if (data.code==1) {
									alert("添加成功");
									$("input[name='productId']").val(data.message);
									$("#addproductpic").hide();
									$("#addproductparameter").show();
								}else{
									alert(data.message);
								}
							}
						});
				})
				
				/* 第四步 */
				$("input[name='btn_4']").click(function () {
					var form4=new FormData(document.getElementById("addproductparameter"));
					alert(form4);
					$.ajax({
						url: '${pageContext.request.contextPath}/admin/product/addproductparameter',
						type: 'POST',
						data: form4,
						async: false,
						cache: false,
						contentType: false,
						processData: false,
						success: function (data) {
								if (data.code==1) {
									alert("添加成功");
									location.href="${pageContext.request.contextPath}/admin/product/list";
								}else{
									alert(data.message);
								}
							}
						});
				})
			})
		</script>
	</HEAD>
	
	<body>
		<!--  -->
		<center>
		<h1 align="center">添加商品</h1>
		<form id="addproduct" method="post" enctype="multipart/form-data">
				<fieldset style="width: 400px; text-align: left;">
				<legend>商品简述</legend>
				<p>商品名称:<input type="text" name="productName" style="width: 250px;" ></p>
				<p>商品分类:<select name="category" style="width: 250px;">
					<c:forEach items="${categories}" var="category">
					<option value="${category.categoryId}">${category.name}</option>
					</c:forEach>
				</select></p>
				<p>商品价格:<input type="number" min="0" step="0.01" name="showPrice" style="width: 250px;">
				<p>商品库存:<input type="number" min="0" name="stock" style="width: 250px;">
				<p>商品图片:<input type="file" name="mainPic" style="width: 250px;"></p>
				<p>商品简介:<textarea rows="4" cols="4" name="introduce" style= "overflow:hidden;width: 250px;height: 50px;"></textarea></p>
				<p>页面标题:<input type="text" name="pageTitle" style="width: 250px;"></p>
				<p>页面描述:<textarea rows="4" cols="4" name="pageDescription" style= "overflow:hidden;width: 250px;height: 50px;"></textarea></p>
				<p>商品备注:<textarea rows="4" cols="4" name="remarks" style= "overflow:hidden;width: 250px;height: 50px;"></textarea></p>
				<span style="margin-left: 150px;"><input type="button" name="btn_1" value="下一步"></span>
				</fieldset>
		</form>
		<form id="addproductdetail" style="display: none;">
				<fieldset style="width: 400px; text-align: left;">
				<legend>商品详情</legend>
				<button type="button" id="addDetail">+</button>
				<input type="hidden" name="productId">
				<div id="detail">
					<p>图片1：<input type="file" name="detail" style="width: 250px;" /></p>
				</div>
				<span><input type="button" name="btn_2" value="下一步"></span>
				</fieldset>				
		</form>
		<form id="addproductpic" style="display: none;">
				<fieldset style="width: 400px; text-align: left;">
				<legend>商品图片</legend>
				<input type="hidden" name="productId">
				<p>商品图片1:<input type="file" name="pic" style="width: 250px;"></p>
				<p>商品图片2:<input type="file" name="pic" style="width: 250px;"></p>
				<p>商品图片3:<input type="file" name="pic" style="width: 250px;"></p>
				<p>商品图片4:<input type="file" name="pic" style="width: 250px;"></p>
				<p>商品图片5:<input type="file" name="pic" style="width: 250px;"></p>
				<p>商品图片6:<input type="file" name="pic" style="width: 250px;"></p>
				<span><input type="button" name="btn_3" value="下一步"></span>
				</fieldset>
		</form>
		<form id="addproductparameter" style="display: none;">
				<fieldset style="width: 400px; text-align: left;">
				<legend>规格参数</legend>
				<input type="hidden" name="productId">
				<button type="button" id="addParameter">+</button>
				<table>
					<thead><tr><th>参数名称</th><th>参数值</th></tr></thead>
					<tbody id="parameter">
						<tr><td><input type="text" name="parametername" style="width: 200px;"/></td><td><input type="text" name="parametervalue" style="width: 200px;" /></td></tr>
					</tbody>
				</table>
				<span><input type="button" name="btn_4" value="下一步"></span>
				</fieldset>
		</form>		
		
		</center>
	</body>
</HTML>