package com.lcz.shop.entity.vo;

import java.io.Serializable;
import java.util.List;

import com.lcz.shop.entity.Product;



public class CategoryVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long categoryId;

    private String name;

    List<Product> products;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
