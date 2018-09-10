package com.lcz.shop.mapper;

import com.lcz.shop.entity.ProductDetail;

public interface ProductDetailMappe {

	/**
	 * 通过商品细节ID删除商品细节
	 * @param productDetailId
	 * @return
	 */
	int deleteByPrimaryKey(Long productDetailId);

	/**
	 * 添加商品细节
	 * @param record
	 * @return
	 */
    int insert(ProductDetail record);

    /**
     * 通过商品细节ID获取商品细节
     * @param productDetailId
     * @return
     */
    ProductDetail selectByPrimaryKey(Long productDetailId);

    int updateByPrimaryKeyWithBLOBs(ProductDetail record);

    /**
     * 更新商品细节
     * @param record
     * @return
     */
    int updateByPrimaryKey(ProductDetail record);

    /**
     * 通过商品ID获取商品细节
     * @param productId
     * @return
     */
    ProductDetail selectByProductId(Long productId);
}
