<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/account/style/showAccounts.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table id="t1">
		<tbody id="t1_title">
			<tr>
				<td width="18%">账户名</td>
				<td width="15%">密码</td>
				<td width="25%">头像路径</td>
				<td width="25%">注册时间</td>
				<td width="10%">操作</td>
			</tr>
		</tbody>
		<tbody id="t1_content">
			<c:forEach items="${accountList}" var="account" varStatus="status">
				<tr class="row${status.index%2+1}">
					<td >${account.accountName}</td>
					<td width="100px">${account.accountPassword}</td>
					<td width="100px">${account.accountPicture}</td>
					<td><fmt:formatDate value="${account.accountCreateTime}"
							pattern="yyyy-MM-dd hh:mm:ss" type="Date" />
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/account/delete?id=${account.id}">删除</a>
						|<a href="${pageContext.request.contextPath}/account/update?id=${account.id}">修改</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>