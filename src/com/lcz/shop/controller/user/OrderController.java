package com.lcz.shop.controller.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.lcz.shop.common.Result;
import com.lcz.shop.entity.Address;
import com.lcz.shop.entity.Order;
import com.lcz.shop.entity.OrderProduct;
import com.lcz.shop.entity.OrderShipment;
import com.lcz.shop.entity.OrderStatus;
import com.lcz.shop.entity.Product;
import com.lcz.shop.entity.ProductSpecification;
import com.lcz.shop.entity.User;
import com.lcz.shop.entity.vo.CartVO;
import com.lcz.shop.entity.vo.ShoppingCartVO;
import com.lcz.shop.service.AddressService;
import com.lcz.shop.service.OrderService;
import com.lcz.shop.service.ProductService;
import com.lcz.shop.util.GetRandom;
import com.lcz.shop.util.PaymentUtil;

@Controller
@RequestMapping("/buy")
public class OrderController {
	
	@Autowired
	private AddressService addressService;
	@Autowired
	private OrderService orderService;
	
	private ProductService productService;

	/**
	 * 添加订单
	 * @RequestParam session
	 * @RequestParam model
	 * @return
	 */
	@RequestMapping(value = "/checkout")
    public String checkout(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        // 收货地址
        List<Address> addresses = addressService.listAddress(user.getUserId());
        model.addAttribute("addresses", addresses);

        // 购物车选中商品
        CartVO cartVO = (CartVO) session.getAttribute("cart");
        model.addAttribute("cart", cartVO);

        return "/order/order_buy_checkout";
    }
	
	@RequestMapping(value = "/confirm", method = RequestMethod.POST )
    @ResponseBody
    public Object confirm(Order order, @RequestParam(value = "addressId") Long addressId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        CartVO cart = (CartVO) session.getAttribute("cart");
        // 收货地址
        Address address = addressService.getAddress(addressId);
        if (address != null) {
            Long num = GetRandom.getNumber();
            order.setOrderNumber(num);
            order.setBuyNumber(cart.getTotalNumber());
            order.setCreateTime(new Date());
            order.setOrderAmount(cart.getTotalPrice());
            order.setInvoiceTitle(address.getUserName());
            order.setOrderStatus(new Byte("1"));//1 提交
            order.setUserId(user.getUserId());
            order.setPayAmount(cart.getTotalPrice());
            //保存order 拿到orderid
            Long oderId = orderService.saveOder(order);
            order.setOrderId(oderId);
            //保存订单商品
            List<ShoppingCartVO> shoppingCartVOs = cart.getShoppingCartVOs();
            for (ShoppingCartVO vo : shoppingCartVOs) {
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setOrderId(oderId);
                orderProduct.setProductNumber(vo.getProductNumber());
                orderProduct.setName(vo.getName());
                orderProduct.setBuyNumber(vo.getBuyNumber());
                orderProduct.setPicImg(vo.getPicImg());
                orderProduct.setProductSpecNumber(vo.getProductSpecNumber());
                List<String> l = vo.getSpecificationName();
                String s = "";
                if(l == null) {
                    s = "";
                } else {
                    for (String ll : l) {
                        s += ll + " ";
                    }
                }
                orderProduct.setProductSpecName(s);
                orderProduct.setPrice(vo.getPrice());
                orderProduct.setProductNumber(vo.getProductNumber());
                orderProduct.setProductAmount(vo.getProductAmount());

                orderService.saveOrderProduct(orderProduct);
            }

            //保存订单配送收货地址
            OrderShipment orderShipment = new OrderShipment();
            orderShipment.setOrderId(oderId);
            BeanUtils.copyProperties(address, orderShipment);
            orderService.saveOrderShioment(orderShipment);

            //保存订单状态
            OrderStatus OrderStatus = new OrderStatus();
            OrderStatus.setOrderId(oderId);
            OrderStatus.setOrderStatus(new Byte("1"));
            OrderStatus.setCreateTime(new Date());
            OrderStatus.setCreateStatus(new Byte("0"));
            OrderStatus.setRemarks("订单提交");
            orderService.saveOrderStatus(OrderStatus);

            return new Result(1, String.valueOf(num));
        }
        return null;
    }
	
	
	@RequestMapping(value = "/confirm/{orderNumber}")
    public String confirmShow( @PathVariable Long orderNumber, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Order order = orderService.getByOrderNumber(orderNumber, user.getUserId());

        if (order != null) {

            List<OrderProduct> orderProducts = orderService.getOrderProductByOrderNumber(order.getOrderId());

            OrderShipment orderShipment = orderService.getOrderShipmentByOrderId(order.getOrderId());
            session.setAttribute("orderId", order.getOrderNumber().toString());
            session.setAttribute("money",orderService.getValueOfOrder(orderProducts).toString());
            model.addAttribute("order", order); // 订单信息
            model.addAttribute("orderProducts", orderProducts);// 订单详情表
            model.addAttribute("orderShipment", orderShipment);// 订单配送表
            System.out.println(orderShipment);
            return "/order/order_buy_confirm";
        }

        return "/order/order_buy_confirm";
    }
	
	@RequestMapping("pay")
	public ModelAndView pay(HttpSession session) {
		String 	p0_Cmd="Buy",
				p1_MerId="10001126856",//这个是商户的编号
				p2_Order=(String) session.getAttribute("orderId"),
				//p3_Amt=(String) session.getAttribute("money"),                                                                                      
				p3_Amt="0.01",
				p4_Cur="CNY",
				p5_Pid="",
				p6_Pcat="",
				p7_Pdesc="",
				p8_Url="http://119.23.212.125:8080/Shop/buy/payResult",
				p9_SAF="",
				pa_MP="",
				pd_FrpId="CCB-NET-B2C",
				pr_NeedResponse="1";
		String keyValue="69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac=PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		String url="https://www.yeepay.com/app-merchant-proxy/node?"+
		"&p0_Cmd="+p0_Cmd+
		"&p1_MerId="+p1_MerId+
		"&p2_Order="+p2_Order+
		"&p3_Amt="+p3_Amt+
		"&p4_Cur="+p4_Cur+
		"&p5_Pid="+p5_Pid+
		"&p6_Pcat="+p6_Pcat+
		"&p7_Pdesc="+p7_Pdesc+
		"&p8_Url="+p8_Url+
		"&p9_SAF="+p9_SAF+
		"&pa_MP="+pa_MP+
		"&pd_FrpId="+pd_FrpId+
		"&pr_NeedResponse="+pr_NeedResponse+
		"&hmac="+hmac;
		System.out.println(url);
		return  new ModelAndView(new RedirectView(url));
	}
	
	@RequestMapping("/takedelivery/{orderNumber}")
	public String takeDelivery(@PathVariable Long orderNumber,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			Order order = orderService.getByOrderNumber(orderNumber, user.getUserId());
			order.setOrderStatus((byte) 5);
			order.setUpdateTime(new Date());
			orderService.updateOrder(order);
			OrderStatus orderStatus=new OrderStatus();
			orderStatus.setOrderId(order.getOrderId());
			orderStatus.setOrderStatus((byte) 5);
			orderStatus.setRemarks("已收货");
			orderService.updateOrderStatus(orderStatus);
			
			//销量+1
			/*List<OrderProduct> list = orderService.getOrderProductByOrderNumber(order.getOrderId());
			List<ProductSpecification> productSpecifications=new ArrayList<>();
			Long productNumber=null;
			for (OrderProduct orderProduct : list) {
				productNumber=orderProduct.getProductNumber();
				Product product = productService.getProduct(productNumber);
				ProductSpecification productSpecification=new ProductSpecification();
				productSpecification.setProductId(product.getProductId());
				Integer salesVolume = productService.getSalesVolume(product.getProductId());
				productSpecification.setSalesVolume(salesVolume+1);
				productSpecification.setUpdateTime(new Date());
				productSpecifications.add(productSpecification);
			}
			productService.updateProductSpecification(productSpecifications);*/
			return "redirect:/uc/order/list";
		}else {
			return "redirect:/user/login";
		}
	}
	
}
