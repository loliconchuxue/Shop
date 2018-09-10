package com.lcz.shop.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lcz.shop.entity.Product;
import com.lcz.shop.entity.vo.CategoryVO;
import com.lcz.shop.entity.vo.HotCategoryVO;


public interface ProductMapper {

	/**
	 * 通过ID删除商品
	 * @param productId
	 * @return
	 */
	int deleteByPrimaryKey(Long productId);

	/**
	 * 增加商品
	 * @param record
	 * @return
	 */
    Long insert(Product record);

    /**
     * 通过ID查找商品
     * @param productId
     * @return
     */
    Product selectByPrimaryKey(Long productId);

    /**
     * 通过ID更新商品信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Product record);
    
    /**
     * 获取热门商品
     * @param vo
     * @return
     */
    List<Product> getHotProduct(CategoryVO vo);
    
    ArrayList<HotCategoryVO> getAllCategory();
    
    /**
     * 通过商品编号查找商品
     * @param productNumber
     * @return
     */
    Product selectByProductNumber(Long productNumber);

    /**
     * 通过商品ID查找商品编号
     * @param productId
     * @return
     */
    Long selectProductNumberByProductId(Long productId);

    /**
     * 通过商品ID获取商品名字
     * @param productId
     * @return
     */
    String selectNameByProductId(Long productId);

    /**
     * 通过商品ID获取商品图片
     * @param productId
     * @return
     */
    String selectPicImgByProductId(Long productId);

    /**
     * 通过商品规格编号获取商品
     * @param productSpecNumber
     * @return
     */
    Product getProductBySpecNumber(Long productSpecNumber);

    List<Product> listByPage(@Param("categoryIds" + "") List<String> categoryIds);
    List<Product> listByPageOrderByCreateTime(@Param("categoryIds" + "") List<String> categoryIds);
    List<Product> listByPageOrderByPriceDesc(@Param("categoryIds" + "") List<String> categoryIds);
    List<Product> listByPageOrderByPriceAsc(@Param("categoryIds" + "") List<String> categoryIds);
    List<Product> listByPageOrderBySaleVolumeDesc(@Param("categoryIds" + "") List<String> categoryIds);
    /**
     * 获取所有商品
     * @return
     */
    List<Product> selectAll();
    /**
     * 所有商品根据创建时间排序
     * @return
     */
    List<Product> selectAllOrderByCreateTime();
    /**
     * 所有商品根据价格降序排列
     * @return
     */
    List<Product> selectAllOrderByPriceDesc();
    /**
     * 所有商品根据价格升序排序
     * @return
     */
    List<Product> selectAllOrderByPriceAsc();
    /**
     * 根据销量降序排列
     * @return
     */
    List<Product> selectAllOrderBySaleVolumeDesc();
    /**
     * 根据评分降序排序
     * @return
     */
    List<Product> selectAllOrderByScore();
    
    /**
     * 搜索所有商品
     * @return
     */
    List<Product> searchAll(@Param("search")String search);
    /**
     * 搜索所有商品根据创建时间排序
     * @return
     */
    List<Product> searchAllOrderByCreateTime(@Param("search")String search);
    /**
     * 搜索所有商品根据价格降序排列
     * @return
     */
    List<Product> searchAllOrderByPriceDesc(@Param("search")String search);
    /**
     * 搜索所有商品根据价格升序排序
     * @return
     */
    List<Product> searchAllOrderByPriceAsc(@Param("search")String search);
    /**
     * 搜索根据销量降序排列
     * @return
     */
    List<Product> searchAllOrderBySaleVolumeDesc(@Param("search")String search);
    
    /**
     * 查找所有上架商品
     * @return
     */
    List<Product> selectAllOnShelvesProduct();
    /**
     * 查找所有下架商品
     * @return
     */
    List<Product> selectAllOutShelvesProduct();
    
}
