package com.lcz.shop.controller.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lcz.shop.entity.vo.CategoryVO;
import com.lcz.shop.service.CategoryService;

@Controller
public class IndexController {

	@Autowired
	CategoryService categoryService;
	
	/**
	 * 首页
	 * @RequestParam session
	 * @return
	 */
	@RequestMapping("index")
	public String index(HttpSession session) {
		List<CategoryVO> categories = categoryService.getindexCategory();
		session.setAttribute("categories", categories);
		return "/webfront/index";
	}
}
