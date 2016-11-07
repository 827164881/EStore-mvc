package com.geng.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.geng.domain.User;
import com.geng.factory.BasicFactory;
import com.geng.service.UserService;


public class RegistServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��֤��
		UserService service=BasicFactory.getFactory().getInstence(UserService.class);
		try{
		String viliStr =req.getParameter("vilistr");
		String viliStr2 =(String) req.getSession().getAttribute("valistr");
		if(viliStr==null || viliStr2==null || !viliStr.equals(viliStr2)){
			resp.getWriter().write("��֤�����");
		}
		
		//��װ����У������
		User user=new User();
		BeanUtils.populate(user, req.getParameterMap());
		
		//����serviceע���û�
		service.regist(user);
		
		//�ص���ҳ
		resp.getWriter().write("ע��ɹ����뵽�����м���");
		
		}catch(Exception e){e.printStackTrace();}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
	

}
