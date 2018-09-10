package com.lcz.shop.mapper;

import java.util.List;

import com.lcz.shop.entity.Category;
import com.lcz.shop.entity.vo.CategoryVO;


public interface CategoryMapper {
	/**
	 * 根据类目ID删除类目
	 * @param categoryId
	 * @return
	 */
	int deleteByPrimaryKey(Long categoryId);

	/**
	 * 添加类目
	 * @param record
	 * @return
	 */
    int insert(Category record);

    /**
     * 根据类目ID获取类目信息
     * @param categoryId
     * @return
     */
    Category selectByPrimaryKey(Long categoryId);

    /**
     * 根据类目ID更新类目信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Category record);
    
    /**
     * 获取所有类目
     * @return
     */
    List<CategoryVO> selectIndexCategory();

    /**
     * 根据商品ID获取类目
     * @param productId
     * @return
     */
    Category selectParentCategoryByProductId(Long productId);

    /**
     *  根据类目ID查找子类目
     * @param categoryId
     * @return
     */
    List<Category> listLowerCategories(Long categoryId);
    
    /**
     *  根据类目ID查找上级类目列表
     * @param categoryId
     * @return
     */
    List<Category> listUpperCategories(Long categoryId);
    
    List<Category> selectAllCategories();
}
