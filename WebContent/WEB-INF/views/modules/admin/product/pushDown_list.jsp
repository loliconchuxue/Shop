<%@ page language="java" import="java.util.*,com.lcz.shop.entity.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="${pageContext.request.contextPath}/static/admin/css/Style1.css"
	rel="stylesheet" type="text/css" />
</HEAD>
<body>
	<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
		<TBODY>
			<tr><td class="ta_01" align="center" bgColor="#afd1f3"><strong>商品列表</strong></td></tr>
			<tr><td align="left">
			<select name="category">
				<c:forEach items="${categories }" var="category">				
				<option value="${category.categoryId }">${category.name }</option>
				</c:forEach>
			</select>
				</tr>
				<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="20%">
										序号
									</td>
									<td align="center" width="20%">
										商品图片
									</td>
									<td align="center" width="15%">
										商品名称
									</td>
									<td align="center" width="15%">
										商品价格
									</td>
									<td width="15%" align="center">
										上架
									</td>
								</tr>
								<c:forEach items="${products }" var="product">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="20%">
												${product.productNumber }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="20%">
												<img src="${pageContext.request.contextPath}/uploads/${product.picImg }" width="100px" height="100px" >
											</td>
											<td align="center" style="HEIGHT: 22px" width="15%">
												${product.name }
											</td>
											<td align="center" style="HEIGHT: 22px" width="15%">
												${product.showPrice }
											</td>
											<td align="center" style="HEIGHT: 22px" width="15%">
												<a class="btn" href="${pageContext.request.contextPath}/admin/product/onShelves?id=${product.productId}">上架</a>
											</td>
										</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
			</TBODY>
		</table>
	</form>
</body>
</HTML>

