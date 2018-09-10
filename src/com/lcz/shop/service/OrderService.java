package com.lcz.shop.service;

import java.math.BigDecimal;
import java.util.List;

import com.github.pagehelper.Page;
import com.lcz.shop.entity.Order;
import com.lcz.shop.entity.OrderProduct;
import com.lcz.shop.entity.OrderShipment;
import com.lcz.shop.entity.OrderStatus;
import com.lcz.shop.entity.vo.OrderVO;

public interface OrderService {
	/**
	 * 保存订单
	 * @param order
	 * @return
	 */
	public Long saveOder(Order order);
	
	public Boolean cancelOrder(OrderVO order);
	/**
	 * 保存订单配送表
	 * @param orderShipment
	 */
	public void saveOrderShioment(OrderShipment orderShipment);
	/**
	 * 保存订单商品表
	 * @param OrderProduct
	 */
	public void saveOrderProduct(OrderProduct OrderProduct);
	/**
	 * 保存订单状态表
	 * @param OrderStatus
	 */
	public void saveOrderStatus(OrderStatus OrderStatus);
	
	public Boolean updateOrderStatus(OrderStatus orderStatus);
	/**
	 * 通过订单号获取订单信息
	 * @param orderNumber
	 * @param userId
	 * @return
	 */
	public Order getOrderByorderNumber(Long orderNumber);
	
	public Order getByOrderNumber(Long orderNumber, Long userId);
	/**
	 * 根据订单ID获取订单内所有商品
	 * @param orderId
	 * @return
	 */
	public List<OrderProduct> getOrderProductByOrderNumber(Long orderId);
	/**
	 * 根据订单ID获取配送信息
	 * @param orderId
	 * @return
	 */
	public OrderShipment getOrderShipmentByOrderId(Long orderId);
	/**
	 * 分页显示用户中心用户个人订单
	 * @param userId
	 * @param page
	 * @param limit
	 * @return
	 */
	public List<OrderVO> getPageOrderByUserId(Long userId,Integer type, Integer page, Integer limit);
	
	/**
	 * 分页管理订单
	 * @param status
	 * @param page
	 * @param limit
	 * @return
	 */
	public List<Order> getPageOrderByStatus(Integer status,Integer page,Integer limit);
	/**
	 * 根据订单号查询用户个人订单
	 * @param orderNumber
	 * @return
	 */
	public OrderVO getOrderByOrderNumber(Long orderNumber);
	
	public BigDecimal getValueOfOrder(List<OrderProduct> list);
	
	public Boolean modifyShipmentTime(Order order);
	public Boolean modifyShipmentMSG(OrderShipment orderShipment);
	
	public Boolean updateOrder(Order order);
	
	public Page<OrderVO> getL();
	public void setL(Page<OrderVO> l);
	
	
	
}
