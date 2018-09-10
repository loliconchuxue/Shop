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

import com.lcz.shop.entity.User;
import com.lcz.shop.service.UserService;
import com.lcz.shop.util.CheckAdmin;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
	
	@Autowired
	UserService userService;

	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edituser(@RequestParam("id") Long id,HttpSession session,Model model) {
		if (CheckAdmin.checkAdmin(session)) {
			User user = userService.findById(id);
			model.addAttribute("user", user);
			return "/admin/user/edit";
		}else {
			return "redirect:/admin/login";
		}
	}
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String userlist(HttpSession session,Model model) {
		if (CheckAdmin.checkAdmin(session)) {
			List<User> allUser = userService.getAllUser();
			model.addAttribute("users", allUser);
			return "/admin/user/list";
		}else {
			return "redirect:/admin/login";
		}
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String editUser(@RequestParam("userNumber") Long userNumber,@RequestParam("userName") String userName,@RequestParam("loginPassword") String loginPassword,
			@RequestParam("realName") String realName,@RequestParam("sex") Byte sex,@RequestParam("age") Byte age,
			@RequestParam("telephone") String telephone,@RequestParam("email") String email,HttpSession session) {
		if (CheckAdmin.checkAdmin(session)) {
			User user=new User();
			user.setUserNumber(userNumber);
			user.setUserName(userName);
			user.setLoginPassword(loginPassword);
			user.setRealName(realName);
			user.setSex(sex);
			user.setAge(age);
			user.setTelephone(telephone);
			user.setEmail(email);
			user.setUpdateTime(new Date());
			userService.modifyUserInfo(user);
			return "redirect:/admin/user/list";
		}else {
			return "redirect:/admin/login";
		}
	}
	@RequestMapping("/del")
	public String deleteUser(@RequestParam("id") Long id,HttpSession session) {
		if (CheckAdmin.checkAdmin(session)) {
			userService.deleteUser(id);
			return "redirect:/admin/user/list";
		}else {
			return "redirect:/admin/login";
		}
	}
}
