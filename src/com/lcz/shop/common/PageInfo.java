package com.lcz.shop.common;

import java.io.Serializable;

public class PageInfo implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/** 总记�? */
	private int total;
	
	/** 总页�? */
	private int totalPage;
	
	/** 数据库中limit的参数，从第几条�?始取 */
	private int offset;
	
	/** 每页显示的记录数 */
	private int limit;
	
	/** 当前�? */
	private int current;
	
	/** 排序字段 */
	private String sort;
	
	/** ASC,DESC mybatis Order 关键�? */
	private String order;
	
    public PageInfo() {
		super();
	}
    
	public PageInfo(int limit, int current) {
		// 计算当前�?
		if (current < 0) {
			this.current = 1;
		} else {
			// 当前�?
			this.current = current;
		}
		// 记录每页显示的记录数
		if (limit < 0) {
			this.limit = limit;
		} else {
			this.limit = limit;
		}
		// 计算�?始的记录和结束的记录
		this.offset = (this.current - 1) * this.limit;
	}

	// 构�?�方�?
	public PageInfo(int current, int limit, String sort, String order) {
		// 计算当前�?
		if (current < 0) {
			this.current = 1;
		} else {
			// 当前�?
			this.current = current;
		}
		// 记录每页显示的记录数
		if (limit < 0) {
			this.limit = limit;
		} else {
			this.limit = limit;
		}
		// 计算�?始的记录和结束的记录
		this.offset = (this.current - 1) * this.limit;
		// 排序字段，正序还是反�?
		this.sort = sort;
		this.order = order;
	}

	public void count() {
		if (limit <= 0) {
			this.limit = 1;
		}
		// 计算总页�?
		int totalPageTemp = this.total / this.limit;
		int plus = (this.total % this.limit) == 0 ? 0 : 1;
		totalPageTemp = totalPageTemp + plus;
		if (totalPageTemp <= 0) {
			totalPageTemp = 1;
		}
		this.totalPage = totalPageTemp;
	}
	
	public void current() {
		if (limit <= 0) {
			this.limit = 1;
		}
		// 计算当前页数
		this.current = this.offset / this.limit + 1;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		count();
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
		current();
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}