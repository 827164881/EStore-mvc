<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>商品信息展示</h1><hr>
<div align="center">
<table width="80%">
	<tr>
		<td width="60%"><img  src="${pageContext.request.contextPath }/ImageServlet?imgurl=${prod.imgurl}"></td>
		<td style="text-align: center;" width="20%">
			商品名称：${prod.name }<br>
			商品价格：${prod.price }<br>
			商品种类：${prod.category }<br>
			库存数量：${prod.pnum }<br>
			商品描述：${prod.description }<br><br><br>
			<a href="${pageContext.request.contextPath }/AddCartServlet?id=${prod.id}"><img  src="${pageContext.request.contextPath }/img/buy.bmp"></a>
		</td>
	</tr>
</table>
</div>
</body>
</html>