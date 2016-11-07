package com.geng.service;

import com.geng.domain.User;

public interface UserService {

	/**
	 * 向数据库中注册该用户
	 * @param user
	 */
	void regist(User user);

	/**
	 * 激活用户，返回当前用户
	 * @param activeCode
	 * @return
	 */
	User active(String activeCode);

	/**
	 * 根据用户名密码，查找对应的用户
	 * @param username
	 * @param password
	 * @return
	 */
	User findUserByUNAndPWD(String username, String password);
	
}
