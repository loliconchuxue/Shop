package com.lcz.shop.mapper;

import com.lcz.shop.entity.SpecificationAttribute;

public interface SpecificationAttributeMapper {

	/**
	 * 根据商品规格属性ID删除商品规格属性
	 * @param specAttrId
	 * @return
	 */
	int deleteByPrimaryKey(Long specAttrId);

	/**
	 * 添加商品规格属性
	 * @param record
	 * @return
	 */
    int insert(SpecificationAttribute record);

    /**
     * 根据商品规格属性ID获取商品规格属性
     * @param specAttrId
     * @return
     */
    SpecificationAttribute selectByPrimaryKey(Long specAttrId);

    /**
     * 根据商品规格属性ID更新商品规格属性
     * @param record
     * @return
     */
    int updateByPrimaryKey(SpecificationAttribute record);
}
