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
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<STRONG>订单详情</STRONG>
						
					</td>
				</tr>

				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">订单编号：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<%-- <input type="hidden" name="categoryId" value="${ orderVo.orderId }"/> --%>
					<input type="text" disabled="disabled" value="${ orderVo.orderNumber }" class="bg" style="width: 500px;"></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">收货人：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="parentId" value="${ orderVo.orderShipment.userName }" class="bg"  style="width: 500px;"/></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">收货地址：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="sort" value="${ orderVo.orderShipment.userAdress }" class="bg" style="width: 500px;"/></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">订单总额：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="name" value="${ orderVo.payAmount }" class="bg" style="width: 500px;"/></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">订单商品：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<table>
					<thead><tr><th>图片</th><th>名字</th><th>数量</th><th>价格</th></tr></thead>
					<tbody>
					<c:forEach items="${orderVo.orderProducts }" var="product">
						<tr><td><img src="${pageContext.request.contextPath}/uploads/${product.picImg }" width="50px" height="50px" /></td>
						<td>${product.name }</td><td>${product.buyNumber }</td><td>${product.productAmount }</td></tr>
					</c:forEach>
					</tbody>
					</table>
					</td>
				</tr>
				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe">
						<c:if test="${orderVo.orderStatus eq 2 }">
						<a href="${pageContext.request.contextPath}/admin/order/outstorage?orderNumber=${orderVo.orderNumber}" class="button_ok" >出库</a>
						</c:if>
						<c:if test="${orderVo.orderStatus eq 3 }">
						<a href="${pageContext.request.contextPath}/admin/order/send?orderNumber=${orderVo.orderNumber}" class="button_ok" >配送</a>
						</c:if>
					</td>
					<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe">
						<c:if test="${orderVo.orderStatus eq 2 ||orderVo.orderStatus eq 3}">
						<a href="${pageContext.request.contextPath}/admin/order/cancelOrder?orderNumber=${orderVo.orderNumber}" class="button_ok" >取消订单</a>
						</c:if>
					</td>
				</tr>
			</table>
		</form>
	</body>
</HTML>