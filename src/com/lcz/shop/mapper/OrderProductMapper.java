package com.lcz.shop.mapper;

import java.util.List;

import com.lcz.shop.entity.OrderProduct;


public interface OrderProductMapper {

	int deleteByPrimaryKey(Long orderProductId);

    int insert(OrderProduct record);

    OrderProduct selectByPrimaryKey(Long orderProductId);

    List<OrderProduct> selectByOrderId(Long orderId);

    int updateByPrimaryKey(OrderProduct record);
}
