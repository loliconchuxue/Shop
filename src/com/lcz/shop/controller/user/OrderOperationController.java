package com.lcz.shop.controller.user;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lcz.shop.common.PageInfo;
import com.lcz.shop.common.Result;
import com.lcz.shop.entity.Order;
import com.lcz.shop.entity.OrderShipment;
import com.lcz.shop.entity.User;
import com.lcz.shop.entity.vo.OrderVO;
import com.lcz.shop.service.OrderService;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.DefaultValueLoaderDecorator;

@Controller
@RequestMapping("/uc/order")
public class OrderOperationController {

	@Autowired
	OrderService orderService;
	
	 @RequestMapping(value = "/list")
	    public String orderUI(HttpSession session, Model model,
	                          @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
	                          @RequestParam(value = "limit", required = false, defaultValue = "6") Integer limit,
	                          @RequestParam(value = "type", required = false, defaultValue = "0") Integer type) {
	        User user = (User) session.getAttribute("user");
	        List<OrderVO> orderVOs = orderService.getPageOrderByUserId(user.getUserId(),type, page, limit);
	        PageInfo info = new  PageInfo(page.intValue(), limit.intValue(), "", "");

	        info.setTotal((int) orderService.getL().getTotal());
	        model.addAttribute("pageInfo", info);
	        model.addAttribute("orderVOs", orderVOs);
	        model.addAttribute("type", type);
	        return "/usercenter/user_order";
	    }

	    @RequestMapping(value = "//{orderNumber}")
	    public String orderUI(@PathVariable Long orderNumber, HttpSession session, Model model) {
	        OrderVO orderVO = orderService.getOrderByOrderNumber(orderNumber);
	        model.addAttribute("orderVO", orderVO);
	        return "/usercenter/user_order_view";
	    }
	    
	    @RequestMapping(value="/cancelOrder",method=RequestMethod.POST)
		@ResponseBody
	    public Object cancelOrder(@RequestParam("orderNumber")String orderNumber) {
	    	System.out.println(orderNumber);
	    	OrderVO orderVO = orderService.getOrderByOrderNumber(Long.parseLong(orderNumber));
	    	
	    	
	    	Boolean cancelOrder = orderService.cancelOrder(orderVO);
	    	if (cancelOrder) {
				return new Result(1, "删除成功");
			}else {				
				return new Result(0, "删除失败");
			}
		}
	    
	    @RequestMapping(value="/time",method=RequestMethod.POST)
	    @ResponseBody
	    public Object time(@RequestParam("shipmentTime")String shipmentTime,@RequestParam("orderNumber")String orderNumber) {
	    	Order order=new Order();
	    	order.setOrderNumber(Long.parseLong(orderNumber));
	    	order.setShipmentTime(Byte.parseByte(shipmentTime));
	    	order.setUpdateTime(new Date());
	    	Boolean modifyShipmentTime = orderService.modifyShipmentTime(order);
	    	if (modifyShipmentTime) {
				return new Result(1, "修改成功");
			}
	    	else {
	    		return new Result(0, "修改失败");
			}
		}
	    
	    @RequestMapping(value="/shipment",method=RequestMethod.POST)
	    @ResponseBody
	    public Object shipment(@RequestParam("userName")String userName,@RequestParam("userPhone")String userPhone,
	    		@RequestParam("userAdress")String userAdress,@RequestParam("userZipcode")String userZipcode,
	    		@RequestParam("orderShipmentId")String orderShipmentId,@RequestParam("orderId")String orderId) {
	    	OrderShipment orderShipment=new OrderShipment();
	    	orderShipment.setOrderId(Long.parseLong(orderId));
	    	orderShipment.setOrderShipmentId(Long.parseLong(orderShipmentId));
	    	orderShipment.setUserName(userName);
	    	orderShipment.setUserPhone(userPhone);
	    	orderShipment.setUserAdress(userAdress);
	    	orderShipment.setUserZipcode(Integer.parseInt(userZipcode));
	    	orderShipment.setUpdateTime(new Date());
	    	Boolean modifyShipment = orderService.modifyShipmentMSG(orderShipment);
	    	if (modifyShipment) {
				return new Result(1, "修改成功");
			}
	    	else {
	    		return new Result(0, "修改失败");
			}
	    }
}
