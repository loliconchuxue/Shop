<%@ page language="java" pageEncoding="UTF-8"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/static/admin/css/Style1.css" type="text/css" rel="stylesheet">
	</HEAD>
	
	<body>
		<form action="${pageContext.request.contextPath}/admin/category/add" method="post">
			&nbsp;
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<STRONG>添加分类</STRONG>
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">父类ID：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="parentId" class="bg"  style="width: 500px;"/></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">分类名称：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="name" class="bg" style="width: 500px;"/></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">排序：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="sort" class="bg" style="width: 500px;"/></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">分类级别：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="type" class="bg" style="width: 500px;"/></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">分类标题：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="pageTitle" class="bg" style="width: 500px;"/></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">分类描述：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<textarea rows="5" cols="6" style= "overflow:hidden; width: 500px;height: 150px; " name="pageDescription" class="bg"></textarea></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">分类关键字：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="pageKeyword" class="bg" style="width: 500px;"/></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">分类备注：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="remarks" class="bg" style="width: 500px;"/></td>
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