<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<html>
	<head>
		<title>Insert title here</title>
	</head>
	<body>
		当前国际化语言编码:${pageContext.response.locale}
		<br />
		Language :
		<a href="?lang=en">English</a>|
		<a href="?lang=zh_CN">Chinese</a>
		<br />
		==================================================
		<br />
		<div id="div_wrapper">
			<div id="div_header">
				<h2>
					wendllup小站的账号
				</h2>
				<h2>
					登录wendellup小站
				</h2>
			</div>
			<div id="div_content">
				<form:form action="${pageContext.request.contextPath}/login?redirectURL=${redirectURL}"
					method="post" commandName="accountVo" modelAttribute="accountVo">
					<table id="t1">
						<tr>
							<td width="100px" class="td1">
								<spring:message code="login.username"/>:
							</td>
							<td width="160px">
								<!--<form:input path="accountName" id="accountName"/>-->
								<input name="accountName" value="${accountVo.accountName}"/>
							</td>
							<td width="160px">
								<form:errors path="accountName"></form:errors>
							</td>
						</tr>
						<tr>
							<td class="td1">
								<spring:message code="login.password"/>:
							</td>
							<td>
								<form:password path="accountPassword" />
							</td>
							<td width="160px">
								<form:errors path="accountPassword" />
							</td>
						</tr>
						<tr>
							<td class="td1">
								<spring:message code="login.validateCode"/>:
							</td>
							<td width="160px" colspan="2">
								<img src="kaptcha.jpg"
									onclick="this.src='${pageContext.request.contextPath}/kaptcha.jpg?'+new Date()" />
							</td>
						</tr>
						<tr>
							<td class="td1">
								<spring:message code="login.inputNumber"/>:
							</td>
							<td>
								<form:input path="validateCode" id="validateCode" />
							</td>
							<td>
								<form:errors path="validateCode" />
							</td>
						</tr>
						<tr>
							<td colspan="3" align="center">
								<input type="submit" value="登录">
							</td>
						</tr>

					</table>
				</form:form>
			</div>
			<div id="div_footer">
				<a href="${pageContext.request.contextPath}/register">还没有账号,去注册</a>
			</div>
		</div>
	</body>
</html>