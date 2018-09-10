package com.lcz.shop.controller.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.naming.HandlerRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lcz.shop.common.Result;
import com.lcz.shop.entity.Order;
import com.lcz.shop.entity.vo.OrderVO;
import com.lcz.shop.service.OrderService;
import com.lcz.shop.util.CheckAdmin;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {
	
	@Autowired
	OrderService orderService;

	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String orderlist(@RequestParam(value="status",required=false,defaultValue="1") Integer status,HttpSession session,Model model) {
		if (CheckAdmin.checkAdmin(session)) {
			model.addAttribute("status", status);
			return "/admin/order/list";
		}else {
			return "redirect:/admin/login";
		}
	}
	@RequestMapping(value="list",method=RequestMethod.POST)
	@ResponseBody
	public Object orderList(@RequestParam(value="status",required=false,defaultValue="1") Integer status,
			@RequestParam(value="page",required=false,defaultValue="0") Integer page,
			HttpSession session) {
		if (CheckAdmin.checkAdmin(session)) {
			List<Order> list = orderService.getPageOrderByStatus(status, page, 20);
			List<String> time=new ArrayList<>();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (Order order : list) {
				time.add(formatter.format(order.getCreateTime()));
			}
			JSONObject json=new JSONObject();
			json.put("list", list);
			json.put("time", time);
			return new Result(1, json.toString());
		}else {
			return "redirect:/admin/login";
		}
	}
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String orderItem(@RequestParam("orderNumber") Long orderNumber,HttpSession session,Model model) {
		if (CheckAdmin.checkAdmin(session)) {
			OrderVO orderVO = orderService.getOrderByOrderNumber(orderNumber);
			model.addAttribute("orderVo", orderVO);
			return "/admin/order/detail";
		}else {
			return "redirect:/admin/login";
		}
	}
	
	@RequestMapping(value="outstorage",method=RequestMethod.GET)
	public String outStorage(@RequestParam("orderNumber")Long orderNumber,HttpSession session) {
		if (CheckAdmin.checkAdmin(session)) {
			System.err.println(orderNumber);
			Order order = orderService.getOrderByorderNumber(orderNumber);
			System.err.println(order);
			order.setOrderStatus((byte) 3);
			order.setUpdateTime(new Date());
			Boolean updateOrder = orderService.updateOrder(order);
			return "redirect:/admin/order/list?status=3";
		}else {
			return "redirect:/admin/login";
		}
	}
	
	@RequestMapping(value="send",method=RequestMethod.GET)
	public String send(@RequestParam("orderNumber")Long orderNumber,HttpSession session) {
		if (CheckAdmin.checkAdmin(session)) {
			Order order = orderService.getOrderByorderNumber(orderNumber);
			System.err.println(order);
			order.setOrderStatus((byte) 4);
			order.setUpdateTime(new Date());
			Boolean updateOrder = orderService.updateOrder(order);
			return "redirect:/admin/order/list?status=4";
		}else {
			return "redirect:/admin/login";
		}
	}
	
	@RequestMapping(value="cancelOrder",method=RequestMethod.GET)
	public String cancelOrder(@RequestParam("orderNumber")Long orderNumber,HttpSession session) {
		if (CheckAdmin.checkAdmin(session)) {
			Order order = orderService.getOrderByorderNumber(orderNumber);
			order.setOrderStatus((byte) 11);
			order.setUpdateTime(new Date());
			Boolean updateOrder = orderService.updateOrder(order);
			return "redirect:/admin/order/list?status=11";
		}else {
			return "redirect:/admin/login";
		}
	}
}
