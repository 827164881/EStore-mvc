<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function  checkPrice() {
		var price=document.getElementsByName("price")[0].value;
		
		if(isNaN(price)){
			alert("价格必须是数字");
			return false;
		}else if(price<0){
			alert("价格必须为正数");
			return false;
		}else{return true;}
	}
</script>
</head>
<body>
<div align="center">
<h1>Estore_添加商品</h1><hr>
<form action="${pageContext.request.contextPath }/AddProdServlet" method="post" enctype="multipart/form-data" onsubmit="return checkPrice()">
	<table>
		<tr>
			<td>商品名称</td>
			<td><input type="text" name="name"></td>
		</tr>
		<tr>
			<td>单价</td>
			<td><input type="text" name="price"></td>
		</tr>
		<tr>
			<td>商品种类</td>
			<td>
				<select name="category">
					<option value="电子数码">电子数码</option>
					<option value="日用百货">日用百货</option>
					<option value="床上用品">床上用品</option>
					<option value="休闲零食">休闲零食</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>库存数量</td>
			<td><input type="text" name="pnum"></td>
		</tr>
		<tr>
			<td>商品图片</td>
			<td><input type="file" name="file1"></td>
		</tr>
		<tr>
			<td>描述信息</td>
			<td>
				<textarea name="description">
					
				</textarea>
			</td>
		</tr>
		<tr>
			<td><input type="submit" value="提交商品"></td>
		</tr>
	</table>
	</div>
</form>
</body>
</html>