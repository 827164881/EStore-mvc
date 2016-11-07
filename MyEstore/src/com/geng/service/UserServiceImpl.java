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
		// �鿴�û����Ƿ��Ѿ�����
		if(userDao.findUserByName(user)!=null){
			throw new RuntimeException("�û�������");
		}
		//���õ�ע��
		user.setRole("user");
		user.setActivecode(UUID.randomUUID().toString());
		user.setState(0);
		userDao.addUser(user);
		//���ͼ����ʼ�
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");//Э��
		prop.setProperty("mail.smtp.host", "smtp.163.com");//������
		prop.setProperty("mail.smtp.auth", "true");//�Ƿ���Ȩ�޿���
		prop.setProperty("mail.debug", "true");//�������Ϊtrue���ڷ����ʼ�ʱ���ӡ����ʱ����Ϣ
		//���������ʼ�������֮���һ�λỰ
		try {				
			Session session = Session.getInstance(prop);
			//��ȡ�ʼ�����
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("ts_BYSJ@163.com"));
			msg.setRecipients(RecipientType.TO, new InternetAddress[]{new InternetAddress(user.getEmail())});
			msg.setSubject("�����Ҵ��˻�����");
			msg.setText("���ã���ӭʹ��\"�����Ҵ�\"app,���������������ɼ���\n"+" <a href=\"http://localhost:8271/MyEstore/ActiveServlet?activecode="+user.getActivecode()+"\">�����ʻ�</a>"
			+"\n���������ɣ��뽫��ַ���Ƶ����������ɼ���");
			//�ҵ��ʵ�Ա
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
		//�����벻��ȷ����������
		if(findU==null){
			throw new RuntimeException("�����벻��ȷ");
		}else if(findU.getState()!=1){
			//����Ѿ���������ظ�����
			throw new RuntimeException("���û��Ѿ�����");
		}
		return findU;
	}
	@Override
	public User findUserByUNAndPWD(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.findUserByNameAndPwd(username,password);
		
	}

}
