package com.lcz.shop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcz.shop.entity.User;
import com.lcz.shop.mapper.UserMapper;
import com.lcz.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public void insertUser(User user) {
		user.setPicImg("default/avatar/default.jpg");
		user.setRegeistTime(new Date());
		userMapper.insert(user);
	}

	@Override
	public User selectUser(String loginName, String loginPassword) {
		User findByUserName = userMapper.findByUserName(loginName,loginPassword);
		User findByPhone = userMapper.findByPhone(loginName,loginPassword);
		if (findByPhone!=null) {
			return findByPhone;
		}
		else if (findByUserName!=null) {
			return findByUserName;
		}
		return null;
	}
	@Override
	public User findByEmail(String email) {
		User findByEmail = userMapper.findByEmail(email);
		return findByEmail;
	}

	@Override
	public Long resetPasswordByEmail(String email, String loginPassword) {
		Long Id = userMapper.resetPasswordByEmail(email, loginPassword);
		System.out.println("Id："+Id);
		
		return Id;
	}

	@Override
	public User findById(Long id) {
		User user = userMapper.findById(id);
		return user;
	}

	@Override
	public Integer modifyPassword(Long number, String oldPassword, String newPassword) {
		User findByNumber = userMapper.findByNumber(number);
		Integer updateUser =0;
		if (oldPassword.equals(findByNumber.getLoginPassword())) {
			User user =new User();
			user.setLoginPassword(newPassword);
			user.setUserNumber(number);
			try {
				updateUser= userMapper.updateUser(user);
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
			if (updateUser>0) {
				return 1;
			}else {				
				return 0;
			}
		}else {			
			return 2;
		}
	}

	@Override
	public Integer modifyUserInfo(User user) {
		Integer updateUser = userMapper.updateUser(user);
		
		return updateUser;
	}

	@Override
	public List<User> getAllUser() {
		List<User> users = userMapper.getAllUser();
		return users;
	}

	@Override
	public Boolean deleteUser(Long id) {
		Integer delete = userMapper.delete(id);
		return delete>0;
	}
}
