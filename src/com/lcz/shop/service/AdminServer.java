package com.lcz.shop.service;

import com.lcz.shop.entity.CmsUser;

public interface AdminServer {

	public Boolean login(String loginName,String loginPassword);
	
	public CmsUser findByUserName(String loginName);
}
