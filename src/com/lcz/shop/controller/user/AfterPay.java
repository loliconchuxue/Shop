package com.lcz.shop.controller.user;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lcz.shop.entity.Order;
import com.lcz.shop.entity.vo.OrderVO;
import com.lcz.shop.service.OrderService;

@Controller
public class AfterPay {
	
	@Autowired
	OrderService orderService;

	@RequestMapping("/buy/payResult")
	public void payResult(@RequestParam("r1_Code")String r1_Code,@RequestParam("p1_MerId")String p1_MerId,HttpServletResponse response,
			@RequestParam("r3_Amt")String r3_Amt,@RequestParam("r6_Order")String r6_Order,@RequestParam("rp_PayDate") String rp_PayDate) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		if ("1".equals(r1_Code)) {
			response.getWriter().println("支付成功！<br/>"
					+ "商户编号："+p1_MerId+"<br/>"
					+ "支付金额："+r3_Amt+"<br/>"
					+"商户订单号："+r6_Order+"<br/>"
					+"支付成功时间："+rp_PayDate);
			Order order = orderService.getOrderByorderNumber(Long.parseLong(r6_Order));
			order.setOrderStatus((byte) 2);
			order.setUpdateTime(new Date());
			Boolean updateOrder = orderService.updateOrder(order);
			response.addHeader("refresh", "2;url=/Shop/uc/order/list");
		}else {
			response.getWriter().println("支付失败！");
		}
	}
}
