package com.lcz.shop.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lcz.shop.entity.Category;
import com.lcz.shop.service.CategoryService;
import com.lcz.shop.util.CheckAdmin;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {
	
	@Autowired
	CategoryService categoryService;

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addcategory(HttpSession session) {
		if (CheckAdmin.checkAdmin(session)) {
			return "/admin/category/add";
		}else {
			return "redirect:/admin/login";
		}
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addCategory(@RequestParam("parentId") Long parentId,@RequestParam("name") String name,
			@RequestParam("sort") Integer sort,@RequestParam("type") Byte type,@RequestParam("pageTitle") String pageTitle,
			@RequestParam("pageDescription") String pageDescription,@RequestParam("pageKeyword") String pageKeyword,@RequestParam("remarks") String remarks,
			HttpSession session) {
		if (CheckAdmin.checkAdmin(session)) {
			Category category=new Category();
			category.setParentId(parentId);
			category.setName(name);
			category.setSort(sort);
			category.setType(type);
			category.setPageTitle(pageTitle);
			category.setPageDescription(pageDescription);
			category.setPageKeyword(pageKeyword);
			category.setRemarks(remarks);
			category.setCreateTime(new Date());
			categoryService.addCategory(category);
			
			return "redirect:/admin/category/list";
		}else {
			return "redirect:/admin/login";
		}
	}
	
	@RequestMapping("del")
	public String deleteCategory(@RequestParam("id")Long id,HttpSession session) {
		if (CheckAdmin.checkAdmin(session)) {
			categoryService.deleteCategory(id);
			
			return "redirect:/admin/category/list";
		}else {
			return "redirect:/admin/login";
		}
	}
	
	
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String editcategory(@RequestParam("id") Long id,HttpSession session,Model model) {
		if (CheckAdmin.checkAdmin(session)) {
			Category category = categoryService.getByCategoryId(id);
			model.addAttribute("category", category);
			return "/admin/category/edit";
		}else {
			return "redirect:/admin/login";
		}
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String editCategory(@RequestParam("categoryId") Long categoryId,@RequestParam("parentId") Long parentId,@RequestParam("name") String name,
			@RequestParam("sort") Integer sort,@RequestParam("type") Byte type,@RequestParam("pageTitle") String pageTitle,
			@RequestParam("pageDescription") String pageDescription,@RequestParam("pageKeyword") String pageKeyword,@RequestParam("remarks") String remarks,
			HttpSession session) {
		if (CheckAdmin.checkAdmin(session)) {
			Category category=new Category();
			category.setCategoryId(categoryId);
			category.setParentId(parentId);
			category.setName(name);
			category.setSort(sort);
			category.setType(type);
			category.setPageTitle(pageTitle);
			category.setPageDescription(pageDescription);
			category.setPageKeyword(pageKeyword);
			category.setRemarks(remarks);
			category.setUpdateTime(new Date());
			categoryService.updateCategory(category);
			return "redirect:/admin/category/list";
		}else {
			return "redirect:/admin/login";
		}
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String listcategory(HttpSession session,Model model) {
		if (CheckAdmin.checkAdmin(session)) {
			List<Category> allCategories = categoryService.selectAllCategories();
			model.addAttribute("categories", allCategories);
			return "/admin/category/list";
		}else {
			return "redirect:/admin/login";
		}
	}
}
