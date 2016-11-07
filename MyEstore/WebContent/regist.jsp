<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"	prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function changeImg(img) {
		img.src=img.src+"?time="+Date().getTime();
	}
</script>
</head>
<div align="center">
<body>
		<h1>用户注册</h1><hr>
		<form action="${pageContext.request.contextPath }/RegistServlet" method="post">
			<table>
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="username" ></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td>确认密码：</td>
					<td><input type="password" name="password2"></td>
				</tr>
				<tr>
					<td>昵称：</td>
					<td><input type="text" name="nickname"></td>
				</tr>
				<tr>
					<td>邮箱：</td>
					<td><input type="text" name="email"></td>
				</tr>
				<tr>
					<td>验证码：</td>
					<td><input type="text" name="vilistr"></td>
				</tr>
				<tr>
					<td><input type="submit" value="提交"></td>
					<td><img  src="${pageContext.request.contextPath }/ValiImg" onclick="changeImg(this)" style="cursor: pointer;" ></td>
				</tr>
			</table>
		
		</form>
</body>
</div>
</html>