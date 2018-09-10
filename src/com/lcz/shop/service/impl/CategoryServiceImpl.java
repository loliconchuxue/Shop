package com.lcz.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcz.shop.entity.Category;
import com.lcz.shop.entity.vo.CategoryVO;
import com.lcz.shop.mapper.CategoryMapper;
import com.lcz.shop.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryMapper categoryMapper;
	
	@Override
	public List<CategoryVO> getindexCategory() {
		List<CategoryVO> categorys = categoryMapper.selectIndexCategory();
		return categorys;
	}

	@Override
	public Category getByCategoryId(Long categoryId) {
		Category category = categoryMapper.selectByPrimaryKey(categoryId);
		return category;
	}

	@Override
	public List<Category> listLowerCategories(Long categoryId) {
		List<Category> lowerCategories = categoryMapper.listLowerCategories(categoryId);
		return lowerCategories;
	}

	@Override
	public List<Category> listUpperCategories(Long categoryId) {
		List<Category> upperCategories = categoryMapper.listUpperCategories(categoryId);
		return upperCategories;
	}

	@Override
	public List<Category> selectAllCategories() {
		List<Category> allCategories = categoryMapper.selectAllCategories();
		return allCategories;
	}

	@Override
	public Boolean updateCategory(Category category) {
		int updateByPrimaryKey = categoryMapper.updateByPrimaryKey(category);
		return updateByPrimaryKey>0;
	}

	@Override
	public Boolean addCategory(Category category) {
		int insert = categoryMapper.insert(category);
		return insert>0;
	}

	@Override
	public Boolean deleteCategory(Long categoryId) {
		int deleteByPrimaryKey = categoryMapper.deleteByPrimaryKey(categoryId);
		return deleteByPrimaryKey>0;
	}

}
