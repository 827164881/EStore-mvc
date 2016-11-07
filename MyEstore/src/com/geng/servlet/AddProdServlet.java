package com.geng.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.geng.domain.Product;
import com.geng.factory.BasicFactory;
import com.geng.service.ProdService;
import com.geng.utils.IOUtils;
import com.geng.utils.PicUtils;

import sun.nio.ch.IOUtil;




/**
 * Servlet implementation class AddProdServlet
 */
@WebServlet("/AddProdServlet")
public class AddProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdService service = BasicFactory.getFactory().getInstence(ProdService.class);
		Map<String, String> paramMap = new HashMap<String,String>();
		String encode=this.getServletContext().getInitParameter("encode");
		//1.上传图片
		DiskFileItemFactory factory=new DiskFileItemFactory();
		factory.setRepository(new File(this.getServletContext().getRealPath("WEB-INF/temp")));
		factory.setSizeThreshold(1024*100);
		
		ServletFileUpload fileUpload=new ServletFileUpload(factory);
		fileUpload.setHeaderEncoding(encode);
		fileUpload.setSizeMax(1024*1024*1);
		fileUpload.setFileSizeMax(1024*1024*10);
		
		if(!fileUpload.isMultipartContent(request)){
			throw new RuntimeException("请用正确的表单提交");
		}
		
		try {
			List<FileItem> list=fileUpload.parseRequest(request);
			for (FileItem item : list) {
				if(item.isFormField()){
					//普通字段想
					String name=item.getFieldName();
					String value=item.getString(encode);
					paramMap.put(name, value);
				}else{
					//文件上传项
					String realname=item.getName();
					String uuidname=UUID.randomUUID().toString()+"_"+realname;
					
					String hash=Integer.toHexString(uuidname.hashCode());
					String upload=this.getServletContext().getRealPath("WEB-INF/upload");
					String imgurl="/WEB-INF/upload";
					for (char c : hash.toCharArray()) {
						upload+="/"+c;
						imgurl+="/"+c;
					}
					imgurl+="/"+uuidname;
					paramMap.put("imgurl", imgurl);
					File file=new File(upload);
					if(!file.exists()){
						file.mkdirs();
					}
					System.out.println(upload+"~~"+uuidname);
					
					InputStream in=item.getInputStream();
					OutputStream out=new FileOutputStream(new File(upload,uuidname));
					IOUtils.In2Out(in, out);
					IOUtils.close(in, out);
					//生成缩略图
					PicUtils pu=new PicUtils(this.getServletContext().getRealPath(imgurl));
					pu.resizeByHeight(140);
					item.delete(); 
				}
			}
			
			
		
		//2.调用service中的方法，在数据库中添加商品
		Product prod=new Product();
		BeanUtils.populate(prod, paramMap);
		service.addProd(prod);
		
		response.getWriter().write("添加商品成功，三秒回到主页");
		response.setHeader("refresh", "3;url="+request.getContextPath()+"/index.jsp");
		//
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
