package com.lcz.shop.mapper;

import java.util.List;

import com.lcz.shop.entity.Address;

public interface AddressMapper {
	
	/**
	 *根据地址ID删除地址 
	 * @param addressId
	 * @return
	 */
	int deleteByPrimaryKey(Long addressId);

	/**
	 * 添加地址
	 * @param record
	 * @return
	 */
    int insert(Address record);

    /**
     * 根据用户ID获取地址
     * @param userId
     * @return
     */
    List<Address> selectByUserId(Long userId);

    /**
     * 根据地址ID获取地址
     * @param addressId
     * @return
     */
    Address selectByAddressId(Long addressId);

    /**
     * 根据地址ID更新地址
     * @param record
     * @return
     */
    int updateByPrimaryKey(Address record);
}
