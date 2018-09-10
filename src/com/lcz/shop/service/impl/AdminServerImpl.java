package com.lcz.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcz.shop.entity.CmsUser;
import com.lcz.shop.mapper.CmsUserMapper;
import com.lcz.shop.service.AdminServer;

@Service
public class AdminServerImpl implements AdminServer {
	
	@Autowired
	CmsUserMapper cmsUserMapper;

	@Override
	public Boolean login(String loginName, String loginPassword) {
		CmsUser cmsUser = cmsUserMapper.findByLoginName(loginName);
		if (loginPassword.equals(cmsUser.getLoginPassword())) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public CmsUser findByUserName(String loginName) {
		CmsUser cmsUser = cmsUserMapper.findByLoginName(loginName);
		return cmsUser;
	}

}
