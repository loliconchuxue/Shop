package com.lcz.shop.mapper;

import com.lcz.shop.entity.ProductDetail;

public interface ProductDetailMapper {

	/**
	 * 根据商品详情ID删除商品详情
	 * @param productDetailId
	 * @return
	 */
	int deleteByPrimaryKey(Long productDetailId);

	/**
	 * 添加商品详情
	 * @param record
	 * @return
	 */
    int insert(ProductDetail record);

    /**
     * 根据商品详情ID获取商品详情
     * @param productDetailId
     * @return
     */
    ProductDetail selectByPrimaryKey(Long productDetailId);

    int updateByPrimaryKeyWithBLOBs(ProductDetail record);

    /**
     * 根据商品详情ID更新商品详情
     * @param record
     * @return
     */
    int updateByPrimaryKey(ProductDetail record);

    /**
     * 根据商品ID获取商品详情
     * @param productId
     * @return
     */
    ProductDetail selectByProductId(Long productId);
}
