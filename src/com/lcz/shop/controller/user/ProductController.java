package com.lcz.shop.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lcz.shop.entity.Product;
import com.lcz.shop.entity.ProductDetail;
import com.lcz.shop.entity.ProductImage;
import com.lcz.shop.entity.ProductParameter;
import com.lcz.shop.entity.vo.HotCategoryVO;
import com.lcz.shop.entity.vo.KindVO;
import com.lcz.shop.service.ProductService;

import net.sf.json.JSONObject;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	// 首页热门分类
		@RequestMapping("/recommend/hot")
		public String recommendTop(Model model) {
			ArrayList<HotCategoryVO> categorys = productService.getHotCategory();
			model.addAttribute("categories", categorys);
			return "/recommend/recommend_hot";
		}

		// 商品详情
		@RequestMapping("/detail/{productNumber}")
		public String productDetail(Model model, @PathVariable Long productNumber) {
			//信息
			Product osProduct = productService.getProduct(productNumber);
			//商品详细介绍
			ProductDetail detail = productService.getProductDetail(osProduct.getProductId());
			//图片
			List<ProductImage> productImages = productService.getProductImages(osProduct.getProductId());
			//参数
			List<ProductParameter> productParameter = productService.getProductParameter(osProduct.getProductId());
			//规格
			List<KindVO> kindVOs = productService.getProductKind(osProduct.getProductId());
			Map<String, Object> map =  productService.getProductSpecification(osProduct.getProductId());
			model.addAttribute("product",osProduct);
			model.addAttribute("detail",detail);
			model.addAttribute("productImages",productImages);
			model.addAttribute("productParameter",productParameter);
			model.addAttribute("kindVOs",kindVOs);
			model.addAttribute("productSpecifications", JSONObject.fromObject(map));
			return "/product/product_detail";
		}
}
