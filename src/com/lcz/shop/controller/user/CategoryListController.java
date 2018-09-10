package com.lcz.shop.controller.user;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lcz.shop.common.PageInfo;
import com.lcz.shop.entity.Category;
import com.lcz.shop.entity.Product;
import com.lcz.shop.service.CategoryService;
import com.lcz.shop.service.ProductService;

@Controller
public class CategoryListController {

	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/list")
    public String list(
            @RequestParam(value = "categoryId", required = false, defaultValue = "1") String reqCategoryId,
            @RequestParam(value = "sort", required = false, defaultValue = "0") String reqSort,
            @RequestParam(value = "page", required = false, defaultValue = "1") String reqPage,
            @RequestParam(value = "limit", required = false, defaultValue = "4") Integer limit,
            Model model) {
		
		System.err.println("categoryId:"+reqCategoryId+","+"sort:"+reqSort+","+"page:"+reqPage+","+"limit:"+limit);
        // 请求参数:类目ID,如果类目ID不存在或者不为Long类型,则默认1/全部商品
        Long categoryId = StringUtils.isNumeric(reqCategoryId) ? Long.valueOf(reqCategoryId) : 1;
        // 请求参数:排序方式,如果排序方式不存在或者不为Integer类型,则默认0/推荐排序
        Integer sort = StringUtils.isNumeric(reqSort) ? Integer.valueOf(reqSort) : 0;
        // 请求参数:分页,如果分页不存在或者不为Integer类型,则默认1/默认页数
        Integer page = StringUtils.isNumeric(reqPage) ? Integer.valueOf(reqPage) : 1;

        // 查找当前类目信息
        Category category = categoryService.getByCategoryId(categoryId);
        if (category != null) {

            // 通过类目ID、排序、分页查找商品列表
            List<Product> products = productService.pageProductInfo(categoryId,sort,page,limit);

            // 根据类目ID查找子类目
            List<Category> lowerCategories = categoryService.listLowerCategories(categoryId);

            // 根据类目ID查找上级类目列表
            List<Category> upperCategories = categoryService.listUpperCategories(categoryId);
            PageInfo info = new  PageInfo(page.intValue(), limit.intValue(), "", "");

            info.setTotal((int) productService.getL().getTotal());


            model.addAttribute("sort", reqSort);
            model.addAttribute("category", category);
            model.addAttribute("products", products);
            model.addAttribute("pageInfo", info);
            model.addAttribute("lowerCategories", lowerCategories);
            model.addAttribute("upperCategories", upperCategories);
        }
        return "/product/product_list";
    }
	@RequestMapping("search")
	public String search(
		@RequestParam(value = "search", required = false, defaultValue = "") String search,
        @RequestParam(value = "sort", required = false, defaultValue = "0") String reqSort,
        @RequestParam(value = "page", required = false, defaultValue = "1") String reqPage,
        @RequestParam(value = "limit", required = false, defaultValue = "4") Integer limit,
        Model model) {
	
	System.err.println("categoryId:"+search+","+"sort:"+reqSort+","+"page:"+reqPage+","+"limit:"+limit);
    // 请求参数:类目ID,默认1/全部商品
    Long categoryId=1l;
    // 请求参数:排序方式,如果排序方式不存在或者不为Integer类型,则默认0/推荐排序
    Integer sort = StringUtils.isNumeric(reqSort) ? Integer.valueOf(reqSort) : 0;
    // 请求参数:分页,如果分页不存在或者不为Integer类型,则默认1/默认页数
    Integer page = StringUtils.isNumeric(reqPage) ? Integer.valueOf(reqPage) : 1;

    // 查找当前类目信息
    Category category = categoryService.getByCategoryId(categoryId);
    if (category != null) {

        // 通过类目ID、排序、分页查找商品列表
        List<Product> products = productService.pageSearchProduct(search, sort, page, limit);

        // 根据类目ID查找子类目
        List<Category> lowerCategories = categoryService.listLowerCategories(categoryId);

        // 根据类目ID查找上级类目列表
        List<Category> upperCategories = categoryService.listUpperCategories(categoryId);
        PageInfo info = new  PageInfo(page.intValue(), limit.intValue(), "", "");

        info.setTotal((int) productService.getL().getTotal());

        model.addAttribute("sort", reqSort);
        model.addAttribute("category", category);
        model.addAttribute("products", products);
        model.addAttribute("pageInfo", info);
        model.addAttribute("lowerCategories", lowerCategories);
        model.addAttribute("upperCategories", upperCategories);
        model.addAttribute("search",search);
    }
		return "/product/product_search";
	}
}
