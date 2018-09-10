package com.lcz.shop.mapper;

import java.util.List;

import com.lcz.shop.entity.ProductSpecification;
import com.lcz.shop.entity.vo.KindVO;


public interface ProductSpecificationMapper {

	/**
	 * 根据商品规格ID删除商品规格
	 * @param productSpecId
	 * @return
	 */
	int deleteByPrimaryKey(Long productSpecId);

	/**
	 * 添加商品规格信息
	 * @param record
	 * @return
	 */
    int insert(ProductSpecification record);

    /**
     * 根据商品规格ID获取商品规格信息
     * @param productSpecId
     * @return
     */
    ProductSpecification selectByPrimaryKey(Long productSpecId);

    /**
     * 根据商品规格编号获取商品规格信息
     * @param productSpecNumber
     * @return
     */
    ProductSpecification selectByProductSpecNumber(Long productSpecNumber);
    
    /**
     * 根据产品ID获取销量
     * @param productId
     * @return
     */
    Integer getSaleVolumeByProductId(Long productId);

    /**
     * 根据商品规格编号更新商品规格信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(ProductSpecification record);
    
    /**
     * 根据商品编号更新
     * @param productSpecification
     * @return
     */
    Integer updateByProductId(ProductSpecification productSpecification);
    
    /**
     * 根据商品编号获取所有商品规格信息
     * @param productNumber
     * @return
     */
    List<ProductSpecification> selectByProductNumber(Long productNumber);

    /**
     * 根据商品ID获取所有商品规格信息
     * @param productId
     * @return
     */
    List<ProductSpecification> selectByProductId(Long productId);

    /**
     * 根据种类ID获取所有种类信息
     * @param categoryId
     * @return
     */
    List<KindVO> selectSpecByCategoryId(Long categoryId);
}
