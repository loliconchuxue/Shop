package com.lcz.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.lcz.shop.entity.Product;
import com.lcz.shop.entity.ProductCategory;
import com.lcz.shop.entity.ProductDetail;
import com.lcz.shop.entity.ProductImage;
import com.lcz.shop.entity.ProductParameter;
import com.lcz.shop.entity.ProductSpecification;
import com.lcz.shop.entity.vo.HotCategoryVO;
import com.lcz.shop.entity.vo.KindVO;

public interface ProductService {

	/**
	 * 查询热门分类商品
	 * @return
	 */
	public ArrayList<HotCategoryVO> getHotCategory();
	
	/**
	 * 获取商品详情
	 * @param productNumber
	 * @return
	 */
	public Product getProduct(Long productNumber);
	
	/**
	 * 获取商品图片
	 * @param productId
	 * @return
	 */
	public List<ProductImage> getProductImages(Long productId);
	
	/**
	 * 获取商品配件信息
	 * @param productId
	 * @return
	 */
	public List<ProductParameter> getProductParameter(Long productId);
	
	/**
	 * 获取商品详细信息
	 * @param productId
	 * @return
	 */
	public ProductDetail getProductDetail(Long productId);
	
	/**
	 * 获取销量
	 * @param productId
	 * @return
	 */
	public Integer getSalesVolume(Long productId);
	
	public Boolean addProductSpecification(ProductSpecification productSpecification);
	
	public Boolean updateProductSpecification(List<ProductSpecification> productSpecifications);
	
	
	/**
	 * 获取商品规格参数
	 * @param productId
	 * @return
	 */
	public Map<String, Object> getProductSpecification(Long productId);
	
	/**
	 * 获取分类规格组合
	 * @param productId
	 * @return
	 */
	public List<KindVO> getProductKind(Long productId);
	
	/**
	 * 根据规格ID获取商品信息
	 * @param productSpecNumber
	 * @return
	 */
	public Product getProductBySpecNumber(Long productSpecNumber);
	
	/**
	 * 分页查询商品信息
	 * @param categoryId
	 * @param sort
	 * @param page
	 * @param limit
	 * @return
	 */
	public List<Product> pageProductInfo(Long categoryId, Integer sort, Integer page, Integer limit);
	
	/**
	 * 查询&分页显示
	 * @param search
	 * @param sort
	 * @param page
	 * @param limit
	 * @return
	 */
	public List<Product> pageSearchProduct(String search, Integer sort, Integer page, Integer limit);
	/**
	 * 获取所有上架商品
	 * @param categoryId
	 * @return
	 */
	public List<Product> allProductOnShelves(Long categoryId);
	/**
	 * 获取所有下架商品
	 * @param categoryId
	 * @return
	 */
	public List<Product> allProductOutShelves(Long categoryId);
	
	/**
	 * 更新商品（上架下架）
	 * @param productSpecification
	 * @return
	 */
	public Boolean shelvesProduct(ProductSpecification productSpecification);
	
	
	public Boolean addProduct(Product product);
	public Boolean addProductCategory(ProductCategory productCategory);
	public Boolean addProductDetail(ProductDetail productDetail);
	public Boolean addProductImages(List<ProductImage> list);
	public Boolean addProductParameter(List<ProductParameter> list);
	
	public Page<Product> getL();
	public void setL(Page<Product> l);
	
	
}
