package com.lcz.shop.mapper;

import com.lcz.shop.entity.OrderShipment;

public interface OrderShipmentMapper {

	int deleteByPrimaryKey(Long orderShipmentId);

    int insert(OrderShipment record);

    OrderShipment selectByPrimaryKey(Long orderShipmentId);

    OrderShipment selectByOderId(Long orderId);

    int updateByPrimaryKey(OrderShipment record);
}
