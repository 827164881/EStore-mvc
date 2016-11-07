package com.geng.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geng.domain.User;
import com.geng.factory.BasicFactory;
import com.geng.service.UserService;


public class ActiveServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�û�����
		UserService service=BasicFactory.getFactory().getInstence(UserService.class);
		//1.��ȡ������
		String activeCode=request.getParameter("activecode");
		//2.����service�еķ������м���
		User user=service.active(activeCode);//Ӧ��������try-catchһ���쳣������
		if(user==null){
			response.getWriter().write("�ü������Թ��ڣ�������ע��");}
		else{
			request.getSession().setAttribute("user", user);
			response.getWriter().write("����ɹ�");
		}
		
		

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
