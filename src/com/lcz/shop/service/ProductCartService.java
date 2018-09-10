package com.lcz.shop.service;

import javax.servlet.http.HttpSession;

import com.lcz.shop.entity.vo.CartVO;

public interface ProductCartService {

	/**
	 * 添加商品到购物车
	 * @param productSpecNumber
	 * @param session
	 * @return
	 */
	public CartVO insertProductCart(Long productSpecNumber, HttpSession session);
	/**
	 * 查询购物车是否存在该商品
	 * @param productSpecNumber
	 * @param session
	 * @return
	 */
	public Boolean getProductExsit(Long productSpecNumber, HttpSession session);
	/**
	 * 增加购物车商品
	 * @param productSpecNumber
	 * @param session
	 * @param buyNumber
	 * @return
	 */
	public CartVO insertProductCart(Long productSpecNumber, HttpSession session, Integer buyNumber);
	/**
	 * 减少购物车商品
	 * @param productSpecNumber
	 * @param session
	 * @param buyNumber
	 * @return
	 */
	public CartVO deProductCart(Long productSpecNumber, HttpSession session, Integer buyNumber);
	/**
	 * 清空购物车
	 * @param productSpecNumber
	 * @param session
	 * @return
	 */
	public CartVO deleteProductCart(Long productSpecNumber, HttpSession session);
	
	/**
	 * 获取购物车商品数量
	 * @param session
	 * @return
	 */
	/*public Long getProductNumber(HttpSession session);*/
}
