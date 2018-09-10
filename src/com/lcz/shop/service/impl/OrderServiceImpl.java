package com.lcz.shop.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lcz.shop.entity.Order;
import com.lcz.shop.entity.OrderProduct;
import com.lcz.shop.entity.OrderShipment;
import com.lcz.shop.entity.OrderStatus;
import com.lcz.shop.entity.vo.OrderVO;
import com.lcz.shop.mapper.OrderMapper;
import com.lcz.shop.mapper.OrderProductMapper;
import com.lcz.shop.mapper.OrderShipmentMapper;
import com.lcz.shop.mapper.OrderStatusMapper;
import com.lcz.shop.service.OrderService;
import com.lcz.shop.util.StatusToRemarks;

@Service
public class OrderServiceImpl implements OrderService {

	private Page<OrderVO> l;
	private Page<Order> ord;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderProductMapper orderProductMapper;
	@Autowired
	private OrderStatusMapper orderStatusMapper;
	@Autowired
	private OrderShipmentMapper orderShipmentMapper;
	
	@Override
	public Long saveOder(Order order) {
		orderMapper.insert(order);
		return order.getOrderId();
	}

	@Override
	public void saveOrderShioment(OrderShipment orderShipment) {
		orderShipmentMapper.insert(orderShipment);
	}

	@Override
	public void saveOrderProduct(OrderProduct orderProduct) {
		orderProductMapper.insert(orderProduct);

	}

	@Override
	public void saveOrderStatus(OrderStatus orderStatus) {
		orderStatusMapper.insert(orderStatus);

	}

	@Override
	public Order getByOrderNumber(Long orderNumber, Long userId) {
		Order order=new Order();
		order.setOrderNumber(orderNumber);
        order.setUserId(userId);
        order=orderMapper.selectByOrderNumber(order);
		return order;
	}

	@Override
	public List<OrderProduct> getOrderProductByOrderNumber(Long orderId) {
		List<OrderProduct> orderProducts = orderProductMapper.selectByOrderId(orderId);
		return orderProducts;
	}

	@Override
	public OrderShipment getOrderShipmentByOrderId(Long orderId) {
		OrderShipment shipment = orderShipmentMapper.selectByOderId(orderId);
		return shipment;
	}

	@Override
	public List<OrderVO> getPageOrderByUserId(Long userId,Integer type, Integer page, Integer limit) {
		PageHelper.startPage(page, limit);
		List<OrderVO> list = orderMapper.selectByUserId(userId,type);
		this. l = (Page<OrderVO>)list;
		return list;
	}

	@Override
	public OrderVO getOrderByOrderNumber(Long orderNumber) {
		OrderVO orderVO = orderMapper.selectOrderVOByOrderNumber(orderNumber);
		return orderVO;
	}

	@Override
	public Page<OrderVO> getL() {
		return this.l;
	}

	@Override
	public void setL(Page<OrderVO> l) {
		this.l=l;
	}

	@Override
	public BigDecimal getValueOfOrder(List<OrderProduct> list) {
		BigDecimal total=new BigDecimal(0);
		for (OrderProduct orderProduct : list) {
			total=total.add(orderProduct.getPrice());
		}
		System.out.println(total);
		return total;
	}

	@Override
	public Boolean cancelOrder(OrderVO orderVO) {
		Long orderId = orderVO.getOrderId();
		Order order=new Order();
		order.setOrderId(orderId);
		order.setOrderStatus((byte) 12);
		order.setUpdateTime(new Date());
		
		OrderStatus orderStatus=new OrderStatus();
		orderStatus.setOrderStatus((byte) 12);
		orderStatus.setOrderId(orderId);
		orderStatus.setRemarks("手动取消订单");
		int updateOrder = orderMapper.updateByPrimaryKey(order);
		int updateOrderStatus = orderStatusMapper.updateByOrderId(orderStatus);
		
		return (updateOrder>0&&updateOrderStatus>0);
	}

	@Override
	public Boolean modifyShipmentTime(Order order) {
		int updateByOrderNumber = orderMapper.updateByOrderNumber(order);
		return updateByOrderNumber>0;
	}

	@Override
	public Boolean modifyShipmentMSG(OrderShipment orderShipment) {
		int updateByPrimaryKey = orderShipmentMapper.updateByPrimaryKey(orderShipment);
		return updateByPrimaryKey>0;
	}

	@Override
	public List<Order> getPageOrderByStatus(Integer status, Integer page, Integer limit) {
		//PageHelper.startPage(page, limit);
		List<Order> list = orderMapper.getAllByStatus(status);
		//this. ord = (Page<Order>)list;
		return list;
	}

	@Override
	public Boolean updateOrder(Order order1) {
		Long orderId=order1.getOrderId();
		Byte status = order1.getOrderStatus();
		Order order=new Order();
		order.setOrderId(orderId);
		order.setOrderStatus(status);
		order.setUpdateTime(new Date());
		int updateOrder = orderMapper.updateByPrimaryKey(order);
		String remarks=StatusToRemarks.statusToRemarks(status);
		OrderStatus orderStatus=new OrderStatus();
		orderStatus.setOrderId(orderId);
		orderStatus.setOrderStatus(status);
		orderStatus.setRemarks(remarks);
		int updateOrderStatus = orderStatusMapper.updateByOrderId(orderStatus);
		return (updateOrder>0&&updateOrderStatus>0);
	}

	@Override
	public Order getOrderByorderNumber(Long orderNumber) {
		Order order = orderMapper.getOrderByOrderNumber(orderNumber);
		return order;
	}

	@Override
	public Boolean updateOrderStatus(OrderStatus orderStatus) {
		int updateByOrderId = orderStatusMapper.updateByOrderId(orderStatus);
		return updateByOrderId>0;
	}

}
