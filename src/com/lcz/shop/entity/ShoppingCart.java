package com.lcz.shop.entity;

import java.io.Serializable;
import java.util.Date;

public class ShoppingCart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long shoppingcartId;
	private Long userId;
	private Long productId;
	private Long productNum;
	private String productName;
	private String productPic;
	private String price;
	private Byte status;
	private Date createTime;
	private String createUser;
	public Long getShoppingcartId() {
		return shoppingcartId;
	}
	public void setShoppingcartId(Long shoppingcartId) {
		this.shoppingcartId = shoppingcartId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getProductNum() {
		return productNum;
	}
	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPic() {
		return productPic;
	}
	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
}
