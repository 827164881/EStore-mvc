package com.geng.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geng.domain.Product;
import com.geng.factory.BasicFactory;
import com.geng.service.ProdService;

/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdService service=BasicFactory.getFactory().getInstence(ProdService.class);
		//将商品保存 到session中Cartmap中
		String id=request.getParameter("id");
		Product prod=service.findProdById(id);
		if(prod==null){
			response.getWriter().write("该商品以下架，请您重新挑选商品");
			response.setHeader("refresh", "2;url=/ProdListServlet");
		}
		//保存该商品
		LinkedHashMap<Product, Integer> cartmap = (LinkedHashMap<Product, Integer>) request.getSession().getAttribute("cartmap");
		cartmap.put(prod, cartmap.containsKey(prod)?cartmap.get(prod)+1:1);
		
		//带到cartList.jsp页面做展示
		response.sendRedirect(request.getContextPath()+"/cartList.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
