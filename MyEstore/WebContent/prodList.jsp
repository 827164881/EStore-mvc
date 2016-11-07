<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Estore_商品列表</h1>
	<table width="100%">
		<c:forEach items="${requestScope.list }" var="prod">
			<tr>
				<td width="40%"><a href="${pageContext.request.contextPath }/ProdInfoServlet?id=${prod.id}"><img src="${pageContext.request.contextPath }/ImageServlet?imgurl=${prod.imgurls}" style="cursor: pointer;"/></a></td>
				<td width="40%">
						商品名称：${prod.name }<br>
						商品价格：${prod.price }<br>
						商品种类：${prod.category }<br>
						库存数量：${prod.pnum }<br>
						商品描述：${prod.description }<br>
				</td>
				<td width="20%">
				 	<c:if test="${prod.pnum>0 }">
				 		<font color="blue">有货</font>
				 	</c:if>
				 	<c:if test="${prod.pnum<=0 }">
				 		<font color="red">缺货</font>
				 	</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2"><hr></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>