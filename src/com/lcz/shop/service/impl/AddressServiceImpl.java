package com.lcz.shop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lcz.shop.entity.Address;
import com.lcz.shop.mapper.AddressMapper;
import com.lcz.shop.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressMapper addressMapper;
	
	private Page<Address>l;
	
	@Override
	public List<Address> listAddress(Long userId) {
		List<Address> addresses = addressMapper.selectByUserId(userId);
		return addresses;
	}

	@Override
	public Address getAddress(Long addressId) {
		Address address = addressMapper.selectByAddressId(addressId);
		return address;
	}

	@Override
	public List<Address> pageAddressInfo(Integer page, Integer limit, Long userId) {
		PageHelper.startPage(page, limit);
        List<Address> list = addressMapper.selectByUserId(userId);
        this. l = (Page<Address>)list;
        return list;
	}

	@Override
	public int addAddress(Address address) {
		address.setCreateTime(new Date());
		int count = addressMapper.insert(address);
		return count;
	}

	@Override
	public int updatetAddress(Address address) {
		address.setUpdateTime(new Date());
		int count = addressMapper.updateByPrimaryKey(address);
		return count;
	}

	@Override
	public int deleteByAddressId(Long addressId) {
		int count = addressMapper.deleteByPrimaryKey(addressId);
		return count;
	}

	@Override
	public Page<Address> getL() {
		return this.l;
	}

	@Override
	public void setL(Page<Address> l) {
		this.l=l;
	}

}
