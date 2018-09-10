<%@ page language="java" import="java.util.*,com.lcz.shop.entity.User" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/static/css/Style1.css" type="text/css" rel="stylesheet">
	</HEAD>
	
	<body>
		<form action="${pageContext.request.contextPath}/admin/user/edit" method="post" >
			&nbsp;
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<strong><STRONG>编辑用户</STRONG>
						</strong>
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">头像：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<img src="${pageContext.request.contextPath}/uploads/${ user.picImg }" name="pic" class="bg" style="width: 100px;"/></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">用户编号：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="hidden" name="userNumber" value="${ user.userNumber }"/>
					<input type="text" disabled="disabled" value="${ user.userNumber }" class="bg" style="width: 500px;"></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">用户名：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="userName" value="${ user.userName }" class="bg"  style="width: 500px;"/></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">用户密码：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="loginPassword" value="${ user.loginPassword }" class="bg" style="width: 500px;"/></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">真实姓名：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="realName" value="${ user.realName }" class="bg" style="width: 500px;"/></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">性别(1:男 2:女 0:保密)：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="sex" value="${ user.sex }" class="bg" style="width: 500px;"/></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">年龄：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="age" value="${ user.age }" class="bg" style="width: 500px;"/></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">电话号码：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input style= "width: 500px;" name="telephone" class="bg" value="${ user.telephone }" /></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">邮箱：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="email" value="${ user.email }" class="bg" style="width: 500px;"/></td>
				</tr>
				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgColor="#f5fafe" colSpan="4">
						<input type="submit" value="确定" class="button_ok" />
						
						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<input type="reset" value="重置" class="button_cancel" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</HTML>