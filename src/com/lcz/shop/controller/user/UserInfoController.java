package com.lcz.shop.controller.user;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lcz.shop.common.PageInfo;
import com.lcz.shop.common.Result;
import com.lcz.shop.entity.Address;
import com.lcz.shop.entity.User;
import com.lcz.shop.entity.vo.AddressVO;
import com.lcz.shop.service.AddressService;
import com.lcz.shop.service.UserService;

@Controller
@RequestMapping(value = "/uc/user")
public class UserInfoController {
	
	@Autowired
	AddressService addressService;
	@Autowired
	UserService userService;

	/**
	 * 个人中心
	 * @return
	 */
	@RequestMapping(value = "/portal")
    public String portal() {
        return "/usercenter/user_portal";
    }
	
	@RequestMapping(value = "/address")
    public String address(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "limit", required = false, defaultValue = "3") Integer limit,
            Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Address> addresses = addressService.pageAddressInfo(page, limit, user.getUserId());

        PageInfo info = new PageInfo(page.intValue(), limit.intValue(), "", "");
        info.setTotal((int) addressService.getL().getTotal());
        model.addAttribute("addresses", addresses);
        model.addAttribute("pageInfo", info);

        return "/usercenter/user_address";
    }
	
	/**
	 * 新增收货地址
	 * @RequestParam address
	 * @RequestParam session
	 * @return
	 */
	@RequestMapping(value = "/address/insert" , method = RequestMethod.POST)
    @ResponseBody
	public Object addAddress(Address address,HttpSession session ) {
		User user = (User) session.getAttribute("user");
		address.setUserId(user.getUserId());
		Integer count = addressService.addAddress(address);
		if (count>0) {
			return new Result(1,"新增收货地址成功");
		}else {
			return new Result(0,"新增收货地址失败");
		}
	}
	
	/**
     * PUT 更新收货地址
     * @return
     */
    @RequestMapping(value = "/address/update", method = RequestMethod.POST)
    @ResponseBody
    public Object addressUpdate(AddressVO addressvo, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Address address = new Address();
        BeanUtils.copyProperties(addressvo, address);
        address.setUserId(user.getUserId());
        int count = addressService.updatetAddress(address);
        if(count == 1) {
            return new Result(1, "更新收货地址成功");
        } else {
            return new Result(0, "更新收货地址失败");
        }
    }
    /**
     * DELETE 删除收货地址
     * @return
     */
    @RequestMapping(value = "/address/{addressId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object addressDelete(@PathVariable("addressId") Long addressId, HttpSession session) {
        Integer count = addressService.deleteByAddressId(addressId);
        if(count == 1) {
            return new Result(1, "删除收货地址成功");
        } else {
            return new Result(0, "删除收货地址失败");
        }
    }
    
    @RequestMapping("/userInfo")
    public String userInfo(HttpSession session,Model model) {
    	User user = (User) session.getAttribute("user");
    	model.addAttribute("userInfo", user);
		return "/usercenter/userInfo";
	}
    
    @RequestMapping(value="/modifyuserInfo",method=RequestMethod.GET)
    public String modifuserInfo(HttpSession session,Model model) {
    	User user = (User) session.getAttribute("user");
    	model.addAttribute("user", user);
		return "/usercenter/modifyuserInfo";
	}
    
    @RequestMapping(value="/modifyuserInfo",method=RequestMethod.POST)
    @ResponseBody
    public Object modifUserInfo(@RequestParam("realName")String realName,@RequestParam("sex")Byte sex,
    		@RequestParam("age")Byte age,HttpSession session) {
    	User sessionUser = (User) session.getAttribute("user");
    	Long id=sessionUser.getUserId();
    	User user=new User();
    	user.setUserNumber(sessionUser.getUserNumber());
    	user.setRealName(realName);
    	user.setSex(sex);
    	user.setAge(age);
    	Integer modifyUserInfo = userService.modifyUserInfo(user);
    	if (modifyUserInfo>0) {
			user=userService.findById(id);
			session.setAttribute("user", user);
			return new Result(1, "修改成功");
		}else {	
			return new Result(0, "修改失败");
		}
	}
    
    @RequestMapping(value="/password",method=RequestMethod.GET)
    public String password() {
		return "/usercenter/modifypassword";
	}
    
    @RequestMapping(value="/password",method=RequestMethod.POST)
    @ResponseBody
    public Object modifyPassword(@RequestParam("oldPassword")String oldPassword,@RequestParam("newPassword")String newPassword,HttpSession session) {
    	User user=(User) session.getAttribute("user");
    	Long number = user.getUserNumber();
    	Integer modifyPassword = userService.modifyPassword(number, oldPassword, newPassword);
		return new Result(modifyPassword, "");
	}
    
    @RequestMapping(value="avatar",method=RequestMethod.POST)
    @ResponseBody
    public Object avatar(@RequestParam("avatar")MultipartFile avatar,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			String path=session.getServletContext().getRealPath("/uploads/default/avatar");
			String pic=null;
			if (!avatar.isEmpty()) {
				String filename=avatar.getOriginalFilename();
				String suffix=filename.substring(filename.lastIndexOf("."));
				if (filename.endsWith(".jpg")||filename.endsWith(".png")||filename.endsWith("gif")) {
					pic=user.getUserNumber()+suffix;
					File file=new File(path, pic);
					try {
						avatar.transferTo(file);
					} catch (Exception e) {
						e.printStackTrace();
						return new Result(0, "上传到服务器失败");
					}
				}else {
					return new Result(0, "格式不支持");
				}
			}
			user.setPicImg("default/avatar/"+pic);
			user.setUpdateTime(new Date());
			user.setLoginPassword(null);
			userService.modifyUserInfo(user);
			return new Result(1, user.getPicImg().toString());
		}else {
			return "redirect:/user/login";
		}
		
	}
}
