package com.geng.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.geng.domain.User;
import com.geng.factory.BasicFactory;
import com.geng.service.UserService;

public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡ�û�������
		UserService service=BasicFactory.getFactory().getInstence(UserService.class);
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		//����service�еķ��������û�
		User user=service.findUserByUNAndPWD(username,password);
		//����Ƿ񼤻�
		if(user==null){
			req.setAttribute("msg", "����û��ע�ᣬ����ȥע���û�");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		else if(user.getState()==0){
			req.setAttribute("msg", "����û�м���뵽�����м���");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}else{
			//��ס�û���������
			if("true".equals(req.getParameter("remname"))){
			Cookie remnameC=new Cookie("remname", URLEncoder.encode(user.getUsername(),"utf-8"));
			remnameC.setPath("/");
			remnameC.setMaxAge(3600*24*30);
			resp.addCookie(remnameC);
			}
			//�ض������ҳ
			req.getSession().setAttribute("user", user);
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
	

}
