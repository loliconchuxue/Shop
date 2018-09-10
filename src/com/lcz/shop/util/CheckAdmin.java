package com.lcz.shop.util;

import javax.servlet.http.HttpSession;

import com.lcz.shop.entity.CmsUser;

public class CheckAdmin {

	public static Boolean checkAdmin(HttpSession session) {
		CmsUser admin = (CmsUser) session.getAttribute("admin");
		if (admin!=null) {			
			return true;
		}else {
			return false;
		}
	}
}
