package com.lcz.shop.service;

import java.util.List;

import com.lcz.shop.entity.Category;
import com.lcz.shop.entity.vo.CategoryVO;


public interface CategoryService {

	/**
	 * 首页导航栏商品列表
	 * @return
	 */
	public List<CategoryVO> getindexCategory();
	
	/**
	 * 获取所有分类
	 * @return
	 */
	public List<Category> selectAllCategories();
	
	/**
	 * 根据类目ID查找类目
	 * @param categoryId
	 * @return
	 */
	public Category getByCategoryId(Long categoryId);
	
	/**
	 * 根据类目ID查找子类
	 * @param categoryId
	 * @return
	 */
	public List<Category> listLowerCategories(Long categoryId);
	
	/**
	 *根据类目ID查找上级类目列表 
	 * @param categoryId
	 * @return
	 */
	public List<Category> listUpperCategories(Long categoryId);
	
	/**
	 * 更新分类
	 * @param category
	 * @return
	 */
	public Boolean updateCategory(Category category);
	
	/**
	 * 添加分类
	 * @param category
	 * @return
	 */
	public Boolean addCategory(Category category);
	
	/**
	 * 删除分类
	 * @param category
	 * @return
	 */
	public Boolean deleteCategory(Long categoryId);
	
}
