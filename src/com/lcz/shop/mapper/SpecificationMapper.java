package com.lcz.shop.mapper;

import com.lcz.shop.entity.Specification;

public interface SpecificationMapper {

	/**
	 * 根据规格ID删除规格信息
	 * @param specificationId
	 * @return
	 */
	int deleteByPrimaryKey(Long specificationId);

	/**
	 * 添加规格信息
	 * @param record
	 * @return
	 */
    int insert(Specification record);

    /**
     * 根据规格ID获取规格信息
     * @param specificationId
     * @return
     */
    Specification selectByPrimaryKey(Long specificationId);

    /**
     * 根据规格ID更新规格信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Specification record);
}
