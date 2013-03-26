<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../module/global/taglib.jsp"%>
<html>
<head>
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/common/scripts/jquery-1.8.3.min.js"></script>
<%@include file="../../module/global/globalNavigatorDecorate.jsp"%> 
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/chatroom/scripts/chatroom.js"></script>
<link href="${pageContext.request.contextPath}/resources/chatroom/style/chatroom.css" 
    type="text/css" rel="stylesheet"/>
</head>
<body>
	<%@include file="../../module/global/globalNavigator.jsp"%>
	<div id="div_banner" style="text-align: center">
		<h2>wendellup小站的聊天室</h2>	
	</div>
	<div id="div_body" style="margin:0px auto; height: 600px;width: 1000px;">
		<table id="t1">
			<tr>
				<td>
					<div id="div_msg_content" style="height: 450px;width: 800px; overflow-y: scroll;overflow: auto" >
						
					</div>
				</td>
				<td rowspan="2">
					<div id="div_onlinePerson" style="height: 600px;width: 200px;">
						<jsp:include page="onlineAccountList.jsp" flush="true" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div id="div_msg_send">
						<textarea id="text_msg_send" rows="6" cols="60"></textarea>
						<input id="btn_msg_send" type="button" value="发言">
					</div>
				</td>
			</tr>
		</table>
	</div>

</body>
</html>