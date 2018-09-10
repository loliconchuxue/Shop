package com.lcz.shop.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lcz.shop.entity.CmsUser;
import com.lcz.shop.util.CheckAdmin;

@Controller
@RequestMapping("/admin")
public class AdminRequestController {
	

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "/admin/admin_login";
	}
	@RequestMapping(value="/bottom",method=RequestMethod.GET)
	public String bottom(HttpSession session) {
		if (CheckAdmin.checkAdmin(session)) {
				return "/admin/bottom";
			}else {
				return "redirect:/admin/login";
			}
		}
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String home(HttpSession session) {
		if (CheckAdmin.checkAdmin(session)) {
				return "/admin/home";
			}else {
				return "redirect:/admin/login";
			}
	}
	@RequestMapping(value="/left",method=RequestMethod.GET)
	public String left(HttpSession session) {
		if (CheckAdmin.checkAdmin(session)) {
				return "/admin/left";
			}else {
				return "redirect:/admin/login";
			}
	}
	@RequestMapping(value="/top",method=RequestMethod.GET)
	public String top(HttpSession session) {
		if (CheckAdmin.checkAdmin(session)) {
			return "/admin/top";
		}else {
			return "redirect:/admin/login";
		}
	}
	@RequestMapping(value="/welcome",method=RequestMethod.GET)
	public String welcome(HttpSession session) {
		if (CheckAdmin.checkAdmin(session)) {
			return "/admin/welcome";
		}else {
			return "redirect:/admin/login";
		}
	}
	
}
