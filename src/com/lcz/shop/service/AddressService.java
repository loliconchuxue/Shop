package com.lcz.shop.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.lcz.shop.entity.Address;

public interface AddressService {
	
	/**
	 * 获取用户所有收货地址
	 * @param userId
	 * @return
	 */
	public List<Address> listAddress(Long userId);
	/**
	 * 根据地址ID获取地址
	 * @param addressId
	 * @return
	 */
	public Address getAddress(Long addressId);
	/**
	 * 分页查询收货地址
	 * @param page
	 * @param limit
	 * @param userId
	 * @return
	 */
	public List<Address> pageAddressInfo(Integer page, Integer limit, Long userId);
	/**
	 * 新增收货地址
	 * @param osAddress
	 * @return
	 */
	public int addAddress(Address address);
	/**
	 * 修改收货地址
	 * @param address
	 * @return
	 */
	public int updatetAddress(Address address);
	/**
	 * 根据地址ID删除收货地址
	 * @param addressId
	 * @return
	 */
	public int deleteByAddressId(Long addressId);
	
	public Page<Address> getL();
	public void setL(Page<Address> l);
}
