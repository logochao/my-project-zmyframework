<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/account/regist" method="post">
		<table id="t1">
			<tr>
				<td>账户名</td>
				<td><input type="text" name="accountName"></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="text" name="accountPassword"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="注册"></td>
			</tr>
		</table>
		
	</form>
</body>
</html>