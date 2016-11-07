package com.geng.servlet;

import java.io.IOException;
import java.util.HashMap;

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
 * Servlet implementation class ChangeCartServlet
 */
@WebServlet("/ChangeCartServlet")
public class ChangeCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdService service=BasicFactory.getFactory().getInstence(ProdService.class);
		//��ȡ��Ʒid��buyNum
		String id=request.getParameter("id");
		Integer buyNum=Integer.parseInt(request.getParameter("buyNum"));
		//������Ʒ
		Product prod=service.findProdById(id);
		//�޸�session�е���Ϣ
		HashMap<Product,Integer> cartmap=(HashMap<Product, Integer>) request.getSession().getAttribute("cartmap");
		cartmap.put(prod, buyNum);
		//�ض����cartList.jsp
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
