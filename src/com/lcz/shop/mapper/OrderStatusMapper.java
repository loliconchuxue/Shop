package com.lcz.shop.mapper;

import com.lcz.shop.entity.OrderStatus;

public interface OrderStatusMapper {

	int deleteByPrimaryKey(Long orderStatusId);

    int insert(OrderStatus record);

    OrderStatus selectByPrimaryKey(Long orderStatusId);

    int updateByPrimaryKey(OrderStatus record);
    int updateByOrderId(OrderStatus orderStatus);
}