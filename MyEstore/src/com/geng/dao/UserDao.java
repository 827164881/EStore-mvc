package com.geng.dao;

import com.geng.domain.User;

public interface UserDao {

	/**
	 * �����û��������û�
	 * @param user
	 * @return
	 */
	User findUserByName(User user);

	/**
	 * �����ݿ���ע���û�
	 * @param user
	 */
	void addUser(User user);

	/**
	 * ���ݸ�����Ϣ��ѯ�û�
	 * @param findU
	 * @return 
	 */
	User find(User findU);

	/**
	 * �����û�������������û�
	 * @param username
	 * @param password
	 * @return
	 */
	User findUserByNameAndPwd(String username, String password);
		
}
