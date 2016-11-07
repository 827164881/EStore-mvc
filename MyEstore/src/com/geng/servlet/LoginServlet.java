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
		//获取用户名密码
		UserService service=BasicFactory.getFactory().getInstence(UserService.class);
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		//调用service中的方法查找用户
		User user=service.findUserByUNAndPWD(username,password);
		//检查是否激活
		if(user==null){
			req.setAttribute("msg", "您还没有注册，请先去注册用户");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		else if(user.getState()==0){
			req.setAttribute("msg", "您还没有激活，请到邮箱中激活");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}else{
			//记住用户名和密码
			if("true".equals(req.getParameter("remname"))){
			Cookie remnameC=new Cookie("remname", URLEncoder.encode(user.getUsername(),"utf-8"));
			remnameC.setPath("/");
			remnameC.setMaxAge(3600*24*30);
			resp.addCookie(remnameC);
			}
			//重定向回主页
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
