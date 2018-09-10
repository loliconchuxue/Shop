package com.lcz.shop.mapper;

import java.util.List;
import com.lcz.shop.entity.ShoppingCart;

public interface ShoppingCartMapper {

	int insertShoppingCart(ShoppingCart shoppingCart);
	int deleteShoppingCart(Long shoppingCartId);
	int updateShoppingCart(ShoppingCart shoppingCart);
	List<ShoppingCart> findShoppingCartByUserId(Long userId);
}
