package com.geng.service;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.geng.dao.UserDao;
import com.geng.domain.User;
import com.geng.factory.BasicFactory;

public class UserServiceImpl implements UserService{

	UserDao userDao=BasicFactory.getFactory().getInstence(UserDao.class);
	@Override
	public void regist(User user) {
		// 查看用户名是否已经存在
		if(userDao.findUserByName(user)!=null){
			throw new RuntimeException("用户名存在");
		}
		//调用到注册
		user.setRole("user");
		user.setActivecode(UUID.randomUUID().toString());
		user.setState(0);
		userDao.addUser(user);
		//发送激活邮件
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");//协议
		prop.setProperty("mail.smtp.host", "smtp.163.com");//主机名
		prop.setProperty("mail.smtp.auth", "true");//是否开启权限控制
		prop.setProperty("mail.debug", "true");//如果设置为true则在发送邮件时会打印发送时的信息
		//创建程序到邮件服务器之间的一次会话
		try {				
			Session session = Session.getInstance(prop);
			//获取邮件对象
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("ts_BYSJ@163.com"));
			msg.setRecipients(RecipientType.TO, new InternetAddress[]{new InternetAddress(user.getEmail())});
			msg.setSubject("你问我答账户激活");
			msg.setText("您好，欢迎使用\"你问我答\"app,请点击下面的连接完成激活\n"+" <a href=\"http://localhost:8271/MyEstore/ActiveServlet?activecode="+user.getActivecode()+"\">激活帐户</a>"
			+"\n如果不能完成，请将网址复制到浏览器中完成激活");
			//找到邮递员
			Transport trans = session.getTransport();
			trans.connect("ts_BYSJ", "gqtsbysj123");
			trans.sendMessage(msg, msg.getAllRecipients());
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public User active(String activeCode) {
		// TODO Auto-generated method stub
		User findU=new User();
		findU.setActivecode(activeCode);
		findU=userDao.find(findU);
		//激活码不正确，不给激活
		if(findU==null){
			throw new RuntimeException("激活码不正确");
		}else if(findU.getState()!=1){
			//如果已经激活，则不能重复激活
			throw new RuntimeException("该用户已经激活");
		}
		return findU;
	}
	@Override
	public User findUserByUNAndPWD(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.findUserByNameAndPwd(username,password);
		
	}

}
