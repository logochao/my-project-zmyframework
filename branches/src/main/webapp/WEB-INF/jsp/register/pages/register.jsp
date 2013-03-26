<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>欢迎加入wendellup的小站</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/common/scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/register/scripts/register.js"></script>
<!-- <link
	href="${pageContext.request.contextPath}/resources/register/style/register.css"
	type="text/css" rel="stylesheet" /> -->
</head>
<body>
	<div id="div_wrapper">
		<div id="div_header">
			<h2>wendllup小站的账号</h2>
			<h2>欢迎加入wendellup小站</h2>
		</div>
		<div id="div_content">
			<form:form action="${pageContext.request.contextPath}/register" method="post" commandName="accountVo">
				<table id="t1">
					<tr>
						<td width="100px" class="td1">用户名:</td>
						<td width="160px">
							<form:input path="accountName" id="accountName" onblur="checkName()"/>
						</td>
						<td width="160px">
							<div id="accountName_info"></div>
							<form:errors path="accountName"></form:errors>
						</td>
					</tr>
					<tr>
						<td class="td1">密码:</td>
						<td>
							<form:password path="accountPassword"/>
						</td>
						<td width="160px">
							<form:errors path="accountPassword"/>
						</td>
					</tr>
					<tr>
						<td class="td1">密码确认:</td>
						<td>
							<form:password path="confirmPassword"/>
						</td>
						<td width="160px">
							<form:errors path="confirmPassword"/>
						</td>
					</tr>
					<tr>
						<td class="td1">验证码:</td>
						<td width="160px" colspan="2">
							<img src="kaptcha.jpg" onclick="this.src='${pageContext.request.contextPath}/kaptcha.jpg?'+new Date()"/>
						</td>
					</tr>
					<tr>
						<td class="td1">输入图中数字:</td>
						<td>
							<form:input path="validateCode" id="validateCode"/>
						</td>
						<td>
							<form:errors path="validateCode"/>
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							<input type="submit" value="注册">
						</td>
					</tr>
					
				</table>
			</form:form>
		</div>
		<div id="div_footer">
			<a href="${pageContext.request.contextPath}/login">已有账号,去登录</a>
		</div>
	</div>
</body>
</html>