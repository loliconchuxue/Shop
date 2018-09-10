package com.lcz.shop.util;

public class StatusToRemarks {
	public static String statusToRemarks(Byte status) {
		String remarks=null;
		switch (status) {
		case 1:
			remarks="待付款";
			break;
		case 2:
			remarks="待发货";
			break;
		case 3:
			remarks="待揽件";
			break;
		case 4:
			remarks="运输中";
			break;
		case 5:
			remarks="已收货";
			break;
		case 11:
			remarks="商家关闭订单";
			break;
		case 12:
			remarks="手动取消订单";
			break;

		default:
			remarks="未知，status="+status;
			break;
		}
		return remarks;
	}
}
