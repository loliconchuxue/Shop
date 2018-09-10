<%@ page language="java" import="java.util.*,com.lcz.shop.entity.User" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/static/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/static/admin/js/public.js"></script>
		
	</HEAD>
	<body>
		<br>
		<form action="" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>用户列表</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="20%">
										用户编号
									</td>
									<td align="center" width="15%">
										用户名
									</td>
									<td align="center" width="15%">
										真实姓名
									</td>
									<td width="10%" align="center">
										性别
									</td>
									<td width="20%" align="center">
										电话
									</td>
									<td width="10%" align="center">
										编辑
									</td>
									<td width="10%" align="center">
										删除
									</td>
								</tr>
								<c:forEach items="${users}" var="user">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="20%">
												${user.userNumber }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="15%">
												${user.userName }
											</td>
											<td align="center" style="HEIGHT: 22px" width="15%">
												${user.realName }
											</td>
											<td align="center" style="HEIGHT: 22px" width="10%">
												${user.sex }
											</td>
											<td align="center" style="HEIGHT: 22px" width="20%">
												${user.telephone }
											</td>
											<td align="center" style="HEIGHT: 22px" width="10%">
												<a class="btn" href="${pageContext.request.contextPath}/admin/user/edit?id=${user.userId}">编辑</a>
											</td>
											<td align="center" style="HEIGHT: 22px" width="10%">
												<a class="btn" href="${pageContext.request.contextPath}/admin/user/del?id=${user.userId}">删除</a>
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

