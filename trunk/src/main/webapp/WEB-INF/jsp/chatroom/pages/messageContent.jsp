<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../module/global/taglib.jsp"%>
	<c:forEach items="${messageVoList}" var="messageVo">
		${messageVo.accountName}&nbsp;&nbsp;
		<fmt:formatDate value='${messageVo.messageTime}'
				pattern='yyyy-MM-dd hh:mm:ss' type='Date' />
		<br />
		&nbsp;&nbsp;${messageVo.messageContent}
		<br />
		<br />
	</c:forEach>
