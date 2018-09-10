package com.lcz.shop.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lcz.shop.common.Result;
import com.lcz.shop.entity.CmsUser;
import com.lcz.shop.service.AdminServer;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {
	
	@Autowired
	AdminServer adminServer;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Object AdminLogin(@RequestParam("loginName") String loginName,@RequestParam("loginPassword") String loginPassword,HttpSession session) {
		
		System.out.println("loginName:"+loginName+" loginPassword:"+loginPassword);
		
		Boolean login = adminServer.login(loginName, loginPassword);
		if (login) {
			
			CmsUser admin = adminServer.findByUserName(loginName);
			session.setMaxInactiveInterval(60*60);
			session.setAttribute("admin", admin);
			return new Result(1, "登录成功");
		}else {
			return new Result(0, "用户名或密码错误");
		}
		
	}
}
