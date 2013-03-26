<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<html>
<head>
<title>Insert title here</title>
</head>
<body>
 	当前国际化语言编码:${pageContext.response.locale}<br /> 
	Language : <a href="?lang=en">English</a>|<a href="?lang=zh_CN">Chinese</a>
	<br />
	==================================================
	<br />
	<form action="${pageContext.request.contextPath}/test/chatroom" method="post">
		<spring:message code="login.username"></spring:message><br />
		<fmt:message key="login.username"/>:<input type="text" name="name"> 
		<br />
		<fmt:message key="login.password"/>:<input type="text" name="password">
		<input type="submit" value="提交">
	</form>
</body>
</html>