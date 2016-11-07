package com.geng.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.taglibs.standard.tag.common.sql.DataSourceUtil;

import com.geng.domain.User;
import com.geng.utils.DaoUtils;

public class UserDaoImpl implements UserDao{

	@Override
	public User findUserByName(User user) {
		// TODO Auto-generated method stub
		String sql="select * from users where username=?";
		QueryRunner runner=new QueryRunner(DaoUtils.getSource());
		try {
			return runner.query(sql, new BeanHandler<User>(User.class),user.getUsername());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		String sql="insert into USERS VALUES(SEQ_USER.NEXTVAL,?,?,?,?,?,?,(select sysdate from dual),?)";
		QueryRunner runner=new QueryRunner(DaoUtils.getSource());
		try {
			int result=runner.update( sql,user.getUsername(),user.getPassword(),user.getNickname(),user.getEmail(),user.getState(),user.getActivecode(),user.getRole());
		    System.out.println(result+"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public User find(User findU) {
		// TODO Auto-generated method stub
		try {
		String sql="select * from users where 1=1";
		List<String> list=new ArrayList<>();
		if(findU.getId()!=0){
			sql=sql+"and id=?";
			list.add(findU.getId()+"");
		}
		if(findU.getUsername()!=null || !findU.getUsername().equals("")){
			sql=sql+"and username=?";
			list.add(findU.getUsername());
		}
		if(findU.getPassword()!=null || !findU.getPassword().equals("")){
			sql=sql+"and password=?";
			list.add(findU.getPassword());
		}
		if(findU.getNickname()!=null || !findU.getNickname().equals("")){
			sql=sql+"and nickname=?";
			list.add(findU.getNickname());
		}
		if(findU.getEmail()!=null || !findU.getEmail().equals("")){
			sql=sql+"and email=?";
			list.add(findU.getEmail());
		}
		if(findU.getActivecode()!=null || !findU.getActivecode().equals("")){
			sql=sql+"and activecode=?";
			list.add(findU.getActivecode());
		}
		if(findU.getRole()!=null || !findU.getRole().equals("")){
			sql=sql+"and activecode=?";
			list.add(findU.getRole());
		}
		 QueryRunner runner=new QueryRunner(DaoUtils.getSource());
		 return	runner.query(sql, new BeanHandler<User>(User.class),list.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
		
	}

	@Override
	public User findUserByNameAndPwd(String username, String password) {
		// TODO Auto-generated method stub
		try{
		String sql="select * from users where username=? and password=?";
		QueryRunner runner=new QueryRunner(DaoUtils.getSource());
		return runner.query(sql, new BeanHandler<User>(User.class),username,password);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

}
