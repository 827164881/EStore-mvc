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
		//用户激活
		UserService service=BasicFactory.getFactory().getInstence(UserService.class);
		//1.获取激活码
		String activeCode=request.getParameter("activecode");
		//2.调用service中的方法进行激活
		User user=service.active(activeCode);//应该是在这try-catch一堆异常来处理
		if(user==null){
			response.getWriter().write("该激活码以过期，请重新注册");}
		else{
			request.getSession().setAttribute("user", user);
			response.getWriter().write("激活成功");
		}
		
		

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
