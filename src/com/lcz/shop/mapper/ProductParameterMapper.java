package com.lcz.shop.mapper;

import java.util.List;

import com.lcz.shop.entity.ProductParameter;


public interface ProductParameterMapper {

	/**
	 * 根据商品配件ID删除商品配件
	 * @param productParameterId
	 * @return
	 */
	int deleteByPrimaryKey(Long productParameterId);

	/**
	 * 添加商品配件参数
	 * @param record
	 * @return
	 */
    int insert(ProductParameter record);

    /**
     * 根据商品配件ID获取商品配件参数
     * @param productParameterId
     * @return
     */
    ProductParameter selectByPrimaryKey(Long productParameterId);

    /**
     * 根据商品配件ID更新商品配件参数
     * @param record
     * @return
     */
    int updateByPrimaryKey(ProductParameter record);
    
    /**
     * 根据商品ID获取所有该商品配件参数
     * @param productId
     * @return
     */
    List<ProductParameter> selectByProductId(Long productId);
    
    /**
     * 批量插入配件参数
     * @param list
     * @return
     */
    Integer insertParameters(List<ProductParameter> list);
}
