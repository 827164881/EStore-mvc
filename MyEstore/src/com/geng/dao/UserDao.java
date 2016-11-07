package com.geng.dao;

import com.geng.domain.User;

public interface UserDao {

	/**
	 * 根据用户名查找用户
	 * @param user
	 * @return
	 */
	User findUserByName(User user);

	/**
	 * 向数据库中注册用户
	 * @param user
	 */
	void addUser(User user);

	/**
	 * 根据各种信息查询用户
	 * @param findU
	 * @return 
	 */
	User find(User findU);

	/**
	 * 根据用户名和密码查找用户
	 * @param username
	 * @param password
	 * @return
	 */
	User findUserByNameAndPwd(String username, String password);
		
}
