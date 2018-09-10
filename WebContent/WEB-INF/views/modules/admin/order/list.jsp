<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/static/admin/css/Style1.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="${pageContext.request.contextPath}/static/admin/js/public.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/common/jquery/jquery-3.2.0.min.js"></script>
<script type="text/javascript">
$(function () {
	/* $("input[name='search']").click(function () { */
		var date=$("input[name='date']").val();
		var status=$("input[name='status']").val();
		//alert(status);
		var url="${ pageContext.request.contextPath }/admin/order/list";
		$.ajax({
			type : "post",
			url : url ,
			data : {
				'date' : date,
				'status' : status
			},
			dataType : "json",
			success : function(data) {
				var msg=data.message;
				var json=eval("("+msg+")");
				var arr=json.list;
				var time=json.time;
				var s=arr[1].orderStatus;
				var statu="";
				//alert(s);
				if (s==1) {
					statu="待付款";
				}else if (s==2) {
					statu="已付款";
				}else if (s==3) {
					statu="出库中";
				}else if (s==4||s==5) {
					statu="运输中";
				}else if (s==6) {
					ststu="已收货";
				}
				var str="";
				$.each(arr,function(index,obj){
					//alert(time[index]);
					str+="<tr onmouseover='this.style.backgroundColor = 'white';' onmouseout='this.style.backgroundColor = '#F5FAFE';'>"+
					"<td style='CURSOR: hand; HEIGHT: 22px' align='center' width='15%'>"+obj.orderId+"</td>"+
					"<td style='CURSOR: hand; HEIGHT: 22px' align='center' width='14%'>"+obj.orderNumber+"</td>"+
					"<td style='CURSOR: hand; HEIGHT: 22px' align='center' width='14%'>"+obj.orderAmount+"</td>"+
					"<td style='CURSOR: hand; HEIGHT: 22px' align='center' width='14%'>"+time[index]+"</td>"+
					"<td style='CURSOR: hand; HEIGHT: 22px' align='center' width='14%'>"+obj.invoiceTitle+"</td>"+
					"<td style='CURSOR: hand; HEIGHT: 22px' align='center' width='14%'>"+statu+"</td>"+
					"<td style='CURSOR: hand; HEIGHT: 22px' align='center' width='14%'>"+
					"<a href='${ pageContext.request.contextPath }/admin/order/detail?orderNumber="+obj.orderNumber+"'>详情</a></td></tr>";
				$("tbody").empty();
				$("tbody").append(str);
		});
		}
	})
/* 	}) */
});
</script>
</HEAD>
<body>
	<br>
		<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
			<thead>
				<tr><td class="ta_01" colspan="7" align="center" bgColor="#afd1f3"><strong>订单列表</strong></td></tr>
				<tr><td class="ta_01" colspan="7" align="center" bgColor="#afd1f3">
					<input type="date" name="date"><input type="button" name="search" value="查询"><input type="hidden" name="status" value="${status }">
				</td></tr>
				<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
					<td align="center" width="10%">序号</td>
					<td align="center" width="10%">订单编号</td>
					<td align="center" width="10%">订单金额</td>
					<td align="center" width="10%">订单日期</td>
					<td align="center" width="10%">收货人</td>
					<td align="center" width="10%">订单状态</td>
					<td align="center" width="40%">订单详情</td>
				</tr>
			</thead>
			<tbody >
				<tr onmouseover="this.style.backgroundColor = 'white';" onmouseout="this.style.backgroundColor = '#F5FAFE';">
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">xxx</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">xxx</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">xxx</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">xxx</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">xxx</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">xxx</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">xxx</td>
				</tr>
			</tbody>
		</table>
	<div style="margin-left: 650px;">
		<span><a href="javacript:void(0);">上一页</a></span>&nbsp;<span id="page">1</span>/<span id="total">1</span>&nbsp;<span><a href="javacript:void(0);">下一页</a></span>
	</div>
</body>
</HTML>

