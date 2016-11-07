package com.geng.service;

import com.geng.domain.User;

public interface UserService {

	/**
	 * �����ݿ���ע����û�
	 * @param user
	 */
	void regist(User user);

	/**
	 * �����û������ص�ǰ�û�
	 * @param activeCode
	 * @return
	 */
	User active(String activeCode);

	/**
	 * �����û������룬���Ҷ�Ӧ���û�
	 * @param username
	 * @param password
	 * @return
	 */
	User findUserByUNAndPWD(String username, String password);
	
}
