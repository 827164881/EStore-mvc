package com.geng.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodeFilter implements Filter{
	private FilterConfig filterConfig;
	private ServletContext context;
	private String encode;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig=filterConfig;
		context=filterConfig.getServletContext();
		this.encode=context.getInitParameter("encode");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding(encode);
		response.setContentType("text/html;charset="+encode); 
		chain.doFilter(new MyHttpServletRequest((HttpServletRequest) request), response);
		
	}
	
	class MyHttpServletRequest extends HttpServletRequestWrapper{
		private HttpServletRequest request;
		private boolean isNotEncode=true;
		public MyHttpServletRequest(HttpServletRequest request) {
			super(request);
			// TODO Auto-generated constructor stub
			this.request=request;
		}
		@Override
		public Map<String, String[]> getParameterMap() {
			// TODO Auto-generated method stub
			try {
			if(request.getMethod().equalsIgnoreCase("post")){
					request.setCharacterEncoding(encode);
					return request.getParameterMap();
			}else if(request.getMethod().equalsIgnoreCase("get")){
				Map<String, String[]> map=request.getParameterMap();
				if (isNotEncode) {
					for (Map.Entry<String, String[]> entry : map.entrySet()) {
						String[] vs = entry.getValue();
						for (int i = 0; i < vs.length; i++) {
							vs[i] = new String(vs[i].getBytes("iso8859-1"), encode);
						}

					} 
				}
				return map;
			}else{
				return request.getParameterMap();
			}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			return request.getParameterMap();
			
		}
		
		@Override
		public String[] getParameterValues(String name) {
			// TODO Auto-generated method stub
			return getParameterMap().get(name);
		}
		
		@Override
		public String getParameter(String name) {
			// TODO Auto-generated method stub
			return getParameterValues(name)==null?null:getParameterValues(name)[0];
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub 
	}


	
	

}
