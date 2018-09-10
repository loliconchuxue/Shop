package com.lcz.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lcz.shop.entity.Order;
import com.lcz.shop.entity.vo.OrderVO;

public interface OrderMapper {

	int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    Order selectByPrimaryKey(Long orderId);

    Order selectByOrderNumber(Order Order);

    int updateByPrimaryKey(Order record);
    
    int updateByOrderNumber(Order order);

    List<OrderVO> selectByUserId(@Param("userId")Long userId,@Param("type")Integer type);

    OrderVO selectOrderVOByOrderNumber(Long orderNumber);
    
    List<Order> getAllByStatus(Integer status);
    
    Order getOrderByOrderNumber(Long orderNumber);
}
