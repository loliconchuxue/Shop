package com.lcz.shop.mapper;

import com.lcz.shop.entity.ProductCategory;

public interface ProductCategoryMapper {

	/**
	 * 通过商品种类ID删除商品种类
	 * @param productCategoryId
	 * @return
	 */
	int deleteByPrimaryKey(Long productCategoryId);

	/**
	 * 添加商品种类
	 * @param record
	 * @return
	 */
    int insert(ProductCategory record);

    /**
     * 通过商品种类ID获取商品种类信息
     * @param productCategoryId
     * @return
     */
    ProductCategory selectByPrimaryKey(Long productCategoryId);

    /**
     * 通过商品种类ID更新商品种类信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(ProductCategory record);
}
