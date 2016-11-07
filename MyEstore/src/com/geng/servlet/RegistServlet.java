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
		//验证码
		UserService service=BasicFactory.getFactory().getInstence(UserService.class);
		try{
		String viliStr =req.getParameter("vilistr");
		String viliStr2 =(String) req.getSession().getAttribute("valistr");
		if(viliStr==null || viliStr2==null || !viliStr.equals(viliStr2)){
			resp.getWriter().write("验证码错误");
		}
		
		//封装数据校验数据
		User user=new User();
		BeanUtils.populate(user, req.getParameterMap());
		
		//调用service注册用户
		service.regist(user);
		
		//回到主页
		resp.getWriter().write("注册成功，请到邮箱中激活");
		
		}catch(Exception e){e.printStackTrace();}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
	

}
