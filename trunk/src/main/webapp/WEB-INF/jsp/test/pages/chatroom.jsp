<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>  
<html>
<head>
<title>Insert title here</title>
<style type="text/css">
div {
	border: 1px solid black;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/test/scripts/test.js"></script>
</head>
<body>
	<div style="margin:0px auto; height: 80px;width: 1106px;">
		<div style="height: 80px;width: 1106px;">
			网站历史访问次数:${visitTimes}
			<br /> <a href="${pageContext.request.contextPath}/test/logout">退出系统</a>
		</div>
		<div style="float: left;height: 500px;width: 150px;">navigator</div>
		<div style="float: left;height: 500px;width: 800px;">
			${content}
		</div>
		<div id="div_onlinePerson"
			style="float: left;height: 500px;width: 150px;">
			<%-- <jsp:include page="../../module/global/onlinePersonList.jsp" flush="true"></jsp:include> --%>
		</div>
	</div>

</body>
</html>