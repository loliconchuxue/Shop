package com.lcz.shop.controller.admin;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.xml.soap.Detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lcz.shop.common.Result;
import com.lcz.shop.entity.Category;
import com.lcz.shop.entity.Product;
import com.lcz.shop.entity.ProductCategory;
import com.lcz.shop.entity.ProductDetail;
import com.lcz.shop.entity.ProductImage;
import com.lcz.shop.entity.ProductParameter;
import com.lcz.shop.entity.ProductSpecification;
import com.lcz.shop.service.CategoryService;
import com.lcz.shop.service.ProductService;
import com.lcz.shop.util.CheckAdmin;
import com.lcz.shop.util.GetRandom;
import com.sun.org.apache.xpath.internal.operations.Mod;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {
	
		@Autowired
		CategoryService categoryService;
		@Autowired
		ProductService productService;
	
		@RequestMapping(value="/add",method=RequestMethod.GET)
		public String addproduct(HttpSession session,Model model) {
			if (CheckAdmin.checkAdmin(session)) {
				List<Category> allCategories = categoryService.selectAllCategories();
				model.addAttribute("categories", allCategories);
				return "/admin/product/add";
			}else {
				return "redirect:/admin/login";
			}
		}
		@RequestMapping(value="/edit",method=RequestMethod.GET)
		public String editproduct(HttpSession session) {
			if (CheckAdmin.checkAdmin(session)) {
				return "/admin/product/edit";
			}else {
				return "redirect:/admin/login";
			}
		}
		@RequestMapping(value="/list",method=RequestMethod.GET)
		public String listproduct(@RequestParam(value="categoryId",required=false,defaultValue="0")Long categoryId,
				@RequestParam(value="page",required=false,defaultValue="1")Integer page,
				HttpSession session,Model model) {
			if (CheckAdmin.checkAdmin(session)) {
				List<Category> categories = categoryService.selectAllCategories();
				List<Product> products = productService.allProductOnShelves(categoryId);
				model.addAttribute("categories", categories);
				model.addAttribute("products", products);
				return "/admin/product/list";
			}else {
				return "redirect:/admin/login";
			}
		}
		
		@RequestMapping(value="/pushDown_list",method=RequestMethod.GET)
		public String pushDownproduct(@RequestParam(value="categoryId",required=false,defaultValue="0")Long categoryId,
				@RequestParam(value="page",required=false,defaultValue="1")Integer page,
				HttpSession session,Model model) {
			if (CheckAdmin.checkAdmin(session)) {
				List<Category> categories = categoryService.selectAllCategories();
				List<Product> products = productService.allProductOutShelves(categoryId);
				model.addAttribute("categories", categories);
				model.addAttribute("products", products);
				return "/admin/product/pushDown_list";
			}else {
				return "redirect:/admin/login";
			}
		}
		
		@RequestMapping(value="/addproduct",method=RequestMethod.POST)
		@ResponseBody
		public Object addProduct(@RequestParam("productName")String productName,@RequestParam("category") Long category,
				@RequestParam("showPrice")BigDecimal showPrice,@RequestParam("mainPic") MultipartFile mainPic,
				@RequestParam("introduce")String introduce,@RequestParam("pageTitle") String pageTitle,
				@RequestParam("pageDescription")String pageDescription,@RequestParam("remarks")String remarks,
				@RequestParam("stock")Integer stock,HttpSession session) {
			String pic=null;
			if (CheckAdmin.checkAdmin(session)) {
			if (!mainPic.isEmpty()) {
				String path=session.getServletContext().getRealPath("/uploads/images/goods");
				String filename=mainPic.getOriginalFilename();
				String suffix=filename.substring(filename.lastIndexOf("."));
				if (filename.endsWith(".jpg")||filename.endsWith(".png")||filename.endsWith("gif")) {
					pic=UUID.randomUUID()+suffix;
					File file=new File(path,pic);
					try {
						mainPic.transferTo(file);
					} catch (Exception e) {
						e.printStackTrace();
						return new Result(0, "图片上传服务器失败");
					}
				}else {
					return new Result(0, "该后缀文件不支持");
				}
			}
				Product product=new Product();
				product.setProductNumber(GetRandom.getNumber());
				product.setName(productName);
				product.setShowPrice(showPrice);
				product.setPicImg("images/goods/"+pic);
				product.setIntroduce(introduce);
				product.setPageTitle(pageTitle);
				product.setPageDescription(pageDescription);
				product.setRemarks(remarks);
				product.setCreateTime(new Date());
				product.setUpdateTime(new Date());
				productService.addProduct(product);
				System.out.println("产品ID:"+product.getProductId());
				ProductCategory productCategory=new ProductCategory();
				productCategory.setProductId(product.getProductId());
				productCategory.setCategoryId(category);
				productCategory.setCreateTime(new Date());
				productService.addProductCategory(productCategory);
				
				ProductSpecification productSpecification=new ProductSpecification();
				productSpecification.setProductId(product.getProductId());
				productSpecification.setPrice(showPrice);
				productSpecification.setProductSpecNumber(GetRandom.getNumber());
				productSpecification.setStock(stock);
				productSpecification.setSalesVolume(0);
				productSpecification.setDefaultStatus((byte) 1);
				productSpecification.setStatus((byte) 1);
				productSpecification.setCreateTime(new Date());
				productSpecification.setUpdateTime(new Date());
				productService.addProductSpecification(productSpecification);
				return new Result(1,product.getProductId().toString());
			}else {
				return "redirect:/admin/login";
			}
		}
		
		@RequestMapping(value="/addproductdetail",method=RequestMethod.POST)
		@ResponseBody
		public Object addProductDetail(@RequestParam("productId")Long productId,
				@RequestParam("detail") MultipartFile[] pics,HttpSession session) {
			String path=session.getServletContext().getRealPath("/uploads/detail");
			//String[] picName = new String[20];
			List<String> picName=new ArrayList<>();
			if (CheckAdmin.checkAdmin(session)) {
				for (int i=0;i<pics.length;i++) {
					if (!pics[i].isEmpty()) {
						String filename=pics[i].getOriginalFilename();
						String suffix=filename.substring(filename.lastIndexOf("."));
						if (filename.endsWith(".jpg")||filename.endsWith(".png")||filename.endsWith("gif")) {
							String pic=UUID.randomUUID()+suffix;
							picName.add(pic);
							File file=new File(path,pic);
							try {
								pics[i].transferTo(file);
							} catch (Exception e) {
								e.printStackTrace();
								return new Result(0, "图片上传服务器失败");
							}
						}else {
							return new Result(0, "该后缀文件不支持");
						}
					}
				}
				StringBuffer detail=new StringBuffer("<p>");
				for (int i = 0; i < picName.size(); i++) {
					detail.append("<img src='/Shop/uploads/detail/").append(picName.get(i)).append("'>");
				}
				detail.append("</p>");
				ProductDetail productDetail=new ProductDetail();
				productDetail.setProductId(productId);
				productDetail.setDescription(detail.toString());
				productDetail.setCreateTime(new Date());
				productDetail.setUpdateTime(new Date());
				productService.addProductDetail(productDetail);
				return new Result(1, productId.toString());
			}else {
				return "redirect:/admin/login";
			}
		}
		
		
		@RequestMapping(value="/addproductpic",method=RequestMethod.POST)
		@ResponseBody
		public Object addProductPic(@RequestParam("productId")Long productId,
				@RequestParam("pic") MultipartFile[] pics,HttpSession session) {
			String path=session.getServletContext().getRealPath("/uploads/images/goods");
			//String[] picName = new String[20];
			List<ProductImage> list=new ArrayList<>();
			if (CheckAdmin.checkAdmin(session)) {
				for (int i=0;i<pics.length;i++) {
					if (!pics[i].isEmpty()) {
						ProductImage productImage=new ProductImage();
						String filename=pics[i].getOriginalFilename();
						String suffix=filename.substring(filename.lastIndexOf("."));
						if (filename.endsWith(".jpg")||filename.endsWith(".png")||filename.endsWith("gif")) {
							String pic=UUID.randomUUID()+suffix;
							productImage.setPicImg("images/goods/"+pic);
							File file=new File(path,pic);
							try {
								pics[i].transferTo(file);
							} catch (Exception e) {
								e.printStackTrace();
								return new Result(0, "图片上传服务器失败");
							}
						}else {
							return new Result(0, "该后缀文件不支持");
						}
						productImage.setProductId(productId);
						productImage.setStatus((byte) 1);
						productImage.setSort((byte) (i+1));
						productImage.setCreateTime(new Date());
						list.add(productImage);
					}
				}
				productService.addProductImages(list);
				return new Result(1, productId.toString());
			}else {
				return "redirect:/admin/login";
			}
		}
		
		

		@RequestMapping(value="/addproductparameter",method=RequestMethod.POST)
		@ResponseBody
		public Object addProductParameter(@RequestParam("productId")Long productId,
				@RequestParam("parametername")String[] parametername,@RequestParam("parametervalue") String[] parametervalue,
				HttpSession session) {
			if (CheckAdmin.checkAdmin(session)) {
			List<ProductParameter> list=new ArrayList<>();
			for (int i = 0; i < parametername.length; i++) {
				ProductParameter productParameter=new ProductParameter();
				productParameter.setProductId(productId);
				productParameter.setName(parametername[i]);
				productParameter.setValue(parametervalue[i]);
				productParameter.setSort(i+1);
				productParameter.setCreateTime(new Date());
				productParameter.setUpdateTime(new Date());
				list.add(productParameter);
			}
			try {
				productService.addProductParameter(list);
			} catch (Exception e) {
				e.printStackTrace();
				return new Result(0, "添加失败");
			}
				return new Result(1, "添加成功");
			}else {
				return "redirect:/admin/login";
			}
		}
		
		@RequestMapping(value="detail",method=RequestMethod.GET)
		public String Detail(@RequestParam("id")Long id,HttpSession session,Model model) {
			if (CheckAdmin.checkAdmin(session)) {
				//信息
				Product product = productService.getProduct(id);
				//商品详细介绍
				ProductDetail detail = productService.getProductDetail(product.getProductId());
				//参数
				List<ProductParameter> productParameter = productService.getProductParameter(product.getProductId());
				
				model.addAttribute("product", product);
				model.addAttribute("detail", detail);
				model.addAttribute("parameters", productParameter);
				return "/admin/product/detail";
			}else {
				return "redirect:/admin/login";
			}
		}
		
		@RequestMapping(value="outShelves",method=RequestMethod.GET)
		public String OutShelves(@RequestParam("id")Long id,HttpSession session) {
			if (CheckAdmin.checkAdmin(session)) {
				ProductSpecification productSpecification=new ProductSpecification();
				productSpecification.setProductId(id);
				productSpecification.setStatus((byte) 2);
				productSpecification.setUpdateTime(new Date());
				productService.shelvesProduct(productSpecification);
				return "redirect:/admin/product/list";
			}else {
				return "redirect:/admin/login";
			}
		}
		
		@RequestMapping(value="onShelves",method=RequestMethod.GET)
		public String OnShelves(@RequestParam("id")Long id,HttpSession session) {
			if (CheckAdmin.checkAdmin(session)) {
				ProductSpecification productSpecification=new ProductSpecification();
				productSpecification.setProductId(id);
				productSpecification.setStatus((byte) 1);
				productSpecification.setUpdateTime(new Date());
				productService.shelvesProduct(productSpecification);
				return "redirect:/admin/product/list";
			}else {
				return "redirect:/admin/login";
			}
		}
		
}
