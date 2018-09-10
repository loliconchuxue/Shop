package com.lcz.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lcz.shop.entity.User;

public interface UserMapper {
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public Integer insert(User user);
	
	public Integer delete(Long id);
	
	/**
	 * 根据Id查找
	 * @param id
	 * @return
	 */
	public User findById(Long id);
	
	/**
	 * 根据Id查找
	 * @param id
	 * @return
	 */
	public User findByNumber(Long number);
	/**
	 * 根据用户名查找
	 * @param username
	 * @return
	 */
	public User findByUserName(@Param("userName")String userName,@Param("loginPassword")String loginPassword);
	/**
	 * 根据电话号码查找
	 * @param telephone
	 * @return
	 */
	public User findByPhone(@Param("telephone")String telephone,@Param("loginPassword")String loginPassword);
	/**
	 * 根据邮箱查找
	 * @param email
	 * @return
	 */
	public User findByEmail(@Param("email")String email);
	
	/**
	 * 重置密码
	 * @param email
	 * @param loginPassword
	 * @return
	 */
	public Long resetPasswordByEmail(@Param("email")String email,@Param("loginPassword")String loginPassword);
	
	/**
	 * 更新用户表
	 * @param user
	 * @return
	 */
	public Integer updateUser(User user);
	
	/**
	 * 获取所有用户信息
	 * @return
	 */
	public List<User> getAllUser();
}
