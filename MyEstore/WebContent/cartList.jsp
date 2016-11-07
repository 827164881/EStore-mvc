<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function changeNum(id,obj,oldvalue) {
		if(!/^[1-9]\d*$/.test(obj.value)){
			alert("购买数量必须大于0");
			obj.value=oldvalue;
			return;}
		var contextPath=window.location.pathname.substring(0, window.location.pathname.lastIndexOf("/"));
		window.location.href=contextPath+"/ChangeCartServlet?id="+id+"&buyNum="+obj.value;
	} 
</script>
</head>
<div align="center">
<body>
<c:if test="${empty sessionScope.cartmap }">
<a href="${pageContext.request.contextPath }/ProdListServlet"><h2>购物车空空如也，快去挑选商品吧~~</h2></a>
</c:if>
<c:if test="${not empty sessionScope.cartmap }">
<h1 align="center">购物车信息列表</h1><hr>
<table width="90%" style="text-align: center;" border="1" >
<tr>
<div align="right">
<a href="${pageContext.request.contextPath }/ProdListServlet">继续购物</a>
<a href="${pageContext.request.contextPath }/ClearCartServlet">清空购物车</a>
</div>

<tr>
	<th>缩略图</th>
	<th>产品名称</th>
	<th>商品价格</th>
	<th>所属类型</th>
	<th>商品描述</th>
	<th>购买量</th>
	<th>库存信息</th>
	<th>价格</th>
</tr>
<c:set var="money" value="0"/>
<c:forEach items="${sessionScope.cartmap}" var="entry">
	<tr>
	 	<td><img src="${pageContext.request.contextPath }/ImageServlet?imgurl=${entry.key.imgurls }"></td>
		<td>${entry.key.name }</td>
		<td>${entry.key.price }元</td>
		<td>${entry.key.category }</td>
		<td>${entry.key.description }</td>
		<td><input type="text" value="${entry.value }" style="width: 25px;" onchange="changeNum(${entry.key.id },this,${entry.value })">件</td>
	

		
		<td>
			<c:if test="${entry.key.pnum>entry.value }">
				<font color="blue"><c:out value="有货"></c:out></font>
			</c:if>
			<c:if test="${entry.key.pnum<=entry.value }">
				<font color="red"><c:out value="缺货"/></font>
			</c:if>
    	</td> 
    	<td>${entry.value*entry.key.price }元
    			<c:set value="${money+entry.value*entry.key.price  }" var="money"></c:set></td>
    			<td><a href="${pageContext.request.contextPath }/DelCartServlet?id=${entry.key.id}"><font color="red">删除</font></a></td>
	</tr>
</c:forEach>
</table>
<div align="right">
	<font color="red" size="6" >总价：${money }元</font>
	<a href="${pageContext.request.contextPath }/addOrder.jsp"><img  src="${pageContext.request.contextPath }/img/gotoorder.bmp"></a>
</div>
</c:if>
</body>
</div>
</html>