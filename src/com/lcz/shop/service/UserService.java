package com.lcz.shop.service;

import java.util.List;

import com.lcz.shop.entity.User;

public interface UserService {
	/**
	 * 添加用户
	 * @param user
	 */
	public void insertUser(User user);
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public Boolean deleteUser(Long id);
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 */
	public User findById(Long id);
	
	
	/**
	 * 修改密码
	 * @param number
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public Integer modifyPassword (Long number,String oldPassword,String newPassword);
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public Integer modifyUserInfo(User user);
	
	/**
	 * 登录
	 * @param loginName
	 * @param loginPassword
	 * @return
	 */
	public User selectUser(String loginName, String loginPassword);
	
	/**
	 * 查找邮箱
	 * @param email
	 * @return
	 */
	public User findByEmail(String email);
	
	/**
	 * 重置密码
	 * @param email
	 * @param loginPassword
	 * @return
	 */
	public Long resetPasswordByEmail(String email,String loginPassword);
	
	public List<User> getAllUser();
}
