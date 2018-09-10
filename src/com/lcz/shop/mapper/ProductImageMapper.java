package com.lcz.shop.mapper;

import java.util.List;

import com.lcz.shop.entity.ProductImage;

public interface ProductImageMapper {

	/**
	 * 通过图片ID删除图片
	 * @param picImgId
	 * @return
	 */
	int deleteByPrimaryKey(Long picImgId);

	/**
	 * 插入图片信息
	 * @param record
	 * @return
	 */
    int insert(ProductImage record);

    /**
     * 根据图片ID获取图片信息
     * @param picImgId
     * @return
     */
    ProductImage selectByPrimaryKey(Long picImgId);

    /**
     * 根据图片ID更新图片信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(ProductImage record);
    
    /**
     * 根据产品ID获取该商品所有图片
     * @param productId
     * @return
     */
    List<ProductImage> selectByProductId(Long productId);
    
    /**
     * 批量插入图片
     * @param list
     * @return
     */
    Integer insertImages(List<ProductImage> list);
}
