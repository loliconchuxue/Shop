package com.lcz.shop.mapper;

import com.lcz.shop.entity.CmsUser;

public interface CmsUserMapper {

	CmsUser findByLoginName(String loginName);
}
