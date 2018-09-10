package com.lcz.shop.entity.vo;

import java.util.ArrayList;

public class HotCategoryVO {
	
	private String name;
	private ArrayList<CategoryVO> childrenCategorys;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<CategoryVO> getChildrenCategorys() {
		return childrenCategorys;
	}
	public void setChildrenCategorys(ArrayList<CategoryVO> childrenCategorys) {
		this.childrenCategorys = childrenCategorys;
	}
}
