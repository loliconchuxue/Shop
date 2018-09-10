package com.lcz.shop.controller.user;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lcz.shop.common.Result;
import com.lcz.shop.entity.User;
import com.lcz.shop.entity.vo.CartVO;
import com.lcz.shop.service.UserService;
import com.lcz.shop.util.GetRandom;
import com.lcz.shop.util.MailUtils;

import cn.dsna.util.images.ValidateCode;

@Controller
@RequestMapping("/user")
public class UserLoginController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/register")
	public String registerUI() {
		return "/user/user_register";
	}
	
	@RequestMapping("/login")
	public String loginUI() {
		return "/user/user_login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Object register(@ModelAttribute("user") User user, @RequestParam("registerCode") String registerCode, HttpSession session) {
        String code = (String) session.getAttribute("code");
        Long num = GetRandom.getNumber();
        user.setUserNumber(num);
        if (code.equals(registerCode)) {
            try {
                userService.insertUser(user);
                return new Result(1, user.getUserName());
            } catch (Exception e) {
                return new Result(0, "注册失败");
            }
        } else {
            return new Result(0, "验证码错误");
        }
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestParam("loginName") String loginName,
                        @RequestParam("loginPassword") String loginPassword, HttpSession session) {
        User user = userService.selectUser(loginName, loginPassword);
        
        if (user!=null) {
        	user.setLoginPassword("保密");
        	//保存到session
            session.setAttribute("user", user);
            CartVO cartVO = new CartVO();
            session.setAttribute("cart", cartVO);
            return new Result(1, "登录成功");
        } else {
            return new Result(0, "用户名或密码错误");
        }
    }
	
	@RequestMapping(value = "/code-image.jpg")
    @ResponseBody
    public void getImageCode(HttpServletResponse response, HttpSession session) throws IOException {
        //设置响应格式
        response.setContentType("image/jpeg");
        //禁止缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        session.removeAttribute("code");
        ValidateCode vCode = new ValidateCode(100, 30, 4, 8);
        System.out.println("code:" + vCode.getCode());
        session.setAttribute("code", vCode.getCode());
        vCode.write(response.getOutputStream());
    }
	
	/**
	 * 登出
	 * @RequestParam request
	 * @RequestParam session
	 * @return
	 */
	@RequestMapping("/logout")
    public String loginUI(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }
	
	@RequestMapping(value="forgetPassword",method=RequestMethod.GET)
	public String forgetPassword() {
		return "/user/user_forget_password";
	}
	/**
	 * 查找邮箱是否在数据库中存在
	 * @RequestParam email
	 * @RequestParam registerCode
	 * @RequestParam session
	 * @return
	 */
	@RequestMapping(value="forgetPassword",method=RequestMethod.POST)
	@ResponseBody
	public Object FindEmail(
			@RequestParam("email") String email,@RequestParam("registerCode") String registerCode,HttpSession session) {
		String code = (String) session.getAttribute("code");
		//System.out.println("email:"+email+",code:"+code);
		if (code.equalsIgnoreCase(registerCode)) {			
			User user = userService.findByEmail(email);
			if (user!=null) {
			    return new Result(1, email);
			}else {
				return new Result(0,"没有找到该邮箱");
			}
		}else {
			return new Result(0, "验证码错误");
		}
	}
	/**
	 * 发送验证码到邮箱
	 * @RequestParam email
	 * @return
	 */
	@RequestMapping("sendEmailRegister")
	@ResponseBody
	public Object sendEmailRegister(String email,HttpSession session) {
		String code = GetRandom.getCode(6);
		session.setAttribute("emailCode", code);
		try {
			MailUtils.sendMail(email, code);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(0, "发送失败");
		}
		return new Result(1, "nabudao");
	}
	/**
	 * 验证邮箱验证码
	 * @RequestParam email
	 * @RequestParam captcha
	 * @RequestParam emailSign
	 * @return
	 */
	@RequestMapping(value="verifyEmail",method=RequestMethod.POST)
	@ResponseBody
	public Object verifyEmail(@RequestParam("email")String email,@RequestParam("captcha")String captcha,HttpSession session) {
		String emailSign = (String) session.getAttribute("emailCode");
		if (emailSign.equals(captcha)) {
			session.setAttribute("EmailCheck", true);
			return new Result(1, email);
		}
		else {			
			return new Result(0, "验证码错误");
		}
	}
	
	@RequestMapping(value="resetPassword",method=RequestMethod.POST)
	@ResponseBody
	public Object resetPassword(@RequestParam("email")String email,@RequestParam("loginPassword")String loginPassword,HttpSession session) {
		Boolean check = (Boolean) session.getAttribute("EmailCheck");
		System.out.println(check);
		if (check==false) {
			return new Result(0,"非法操作");
		}
		Long Id =null;
		try {
			Id= userService.resetPasswordByEmail(email, loginPassword);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(0, "重置失败");
		}
		if (Id!=null) {
			User user = new User();
			try {
				user=userService.findById(Id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Result(1, user.getUserName());
		}else {			
			return new Result(0, "修改失败");
		}
		
	}
}
