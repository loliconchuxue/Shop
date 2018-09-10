<%@ page language="java" pageEncoding="UTF-8"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/static/admin/css/Style1.css" type="text/css" rel="stylesheet">
		<script src="${pageContext.request.contextPath}/static/common/jquery/jquery-2.1.4.min.js"></script> 
	</HEAD>
	
	<body>
		<form action="" method="post">

			&nbsp;
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<STRONG>编辑分类</STRONG>
						
					</td>
				</tr>

				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">分类ID：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="hidden" name="categoryId" value="${ category.categoryId }"/>
					<input type="text" disabled="disabled" value="${ category.categoryId }" class="bg" style="width: 500px;"></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">父类ID：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="parentId" value="${ category.parentId }" class="bg"  style="width: 500px;"/></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">分类名称：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="name" value="${ category.name }" class="bg" style="width: 500px;"/></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">排序：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="sort" value="${ category.sort }" class="bg" style="width: 500px;"/></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">分类级别：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="type" value="${ category.type }" class="bg" style="width: 500px;"/></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">分类标题：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="pageTitle" value="${ category.pageTitle }" class="bg" style="width: 500px;"/></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">分类描述：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<textarea rows="5" cols="6" style= "overflow:hidden; width: 500px;height: 150px; " name="pageDescription" class="bg">${ category.pageDescription }</textarea></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">分类关键字：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="pageKeyword" value="${ category.pageKeyword }" class="bg" style="width: 500px;"/></td>
				</tr>
				<tr>
					<td width="40%" align="center" bgColor="#f5fafe" class="ta_01">分类备注：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3" width="60%">
					<input type="text" name="remarks" value="${ category.remarks }" class="bg" style="width: 500px;"/></td>
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