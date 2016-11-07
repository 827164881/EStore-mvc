package com.geng.servlet;

import java.io.IOException;
import java.util.Map;

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
 * Servlet implementation class DelCartServlet
 */
@WebServlet("/DelCartServlet")
public class DelCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdService service=BasicFactory.getFactory().getInstence(ProdService.class);
		//��ȡ��Ҫɾ����Ʒ��id
		String id=request.getParameter("id");
		//����service�еķ���ɾ������Ʒ
		Product prod=service.findProdById(id);
		//����session���е���Ϣ
		Map<Product,Integer> cartmap=(Map<Product, Integer>) request.getSession().getAttribute("cartmap");
		cartmap.remove(prod);
		//�����ض���cartList.jsp
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
