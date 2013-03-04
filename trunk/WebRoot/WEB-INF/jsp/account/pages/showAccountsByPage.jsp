<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytag" uri="http://wendellup/jsp/mytag"%>
<%@ page import="org.wendellup.core.support.QueryParams"%>
<html>
<head>
<title>Insert title here</title>
<link
	href="${pageContext.request.contextPath}/resources/account/style/showAccounts.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/account/scripts/showAccountsByPage.js"></script>
	
<script>
function funX(event,img){
	var e = window.event || arguments[0];
	//alert(e.type);  
    //target = e.srcElement || e.target;  
	//alert(target.tagName);
	var divImg = $("#div_img").get(0);
	divImg.style.display="";
 	divImg.style.left=event.clientX+10;
 	divImg.style.top=event.clientY;
 	$("#div_img img").get(0).src=img;
}
function funH()
{$("#div_img").get(0).style.display='none';}

</script>
</head>
<body>
	<div id="div_search">
		<form
			id="form1"
			action="${pageContext.request.contextPath}/account/showAccountsByPage"
			method="get">
			<input id="currentPage" type="hidden" name="currentPage" value="${queryParams.paging.currentPage}">
			<table>
				<tr>
					<td>账户名</td>
					<td><input type="text" name="name" id="search_name"
						value="${queryParams.entity.name}"></td>
					<td>注册时间段(开始)</td>
					<td><input type="text" name="startTime" id="search_startTime"
						value="<fmt:formatDate value='${queryParams.entity.startTime}'
										pattern='yyyy-MM-dd' type='Date' />">
					</td>
					<td>注册时间段(结束)</td>
					<td><input type="text" name="endTime" id="search_endTime"
						value="<fmt:formatDate value='${queryParams.entity.endTime}'
										pattern='yyyy-MM-dd' type='Date' />">
					</td>
					<td><input id="btn_search" type="button" value="查询"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="div_content">
		<div id="div_content_value">
			<div id="div_img" style="position:absolute;left:10;display:none">
				<img/>
			</div>
			<table id="t1">
				<tbody id="t1_title">
					<tr>
						<td width="10">头像</td>
						<td width="8%">账户名</td>
						<td width="15%">密码</td>
						<td width="25%">头像路径</td>
						<td width="25%">注册时间</td>
						<td width="10%">操作</td>
					</tr>
				</tbody>
				<tbody id="t1_content">
					<c:forEach items="${accountList}" var="account" varStatus="status">
						<tr class="row${status.index%2+1}">
							<td>
								<img onmouseover="funX(event,'${pageContext.request.contextPath}/fileStore/${account.accountPicture}')" 
								     onmouseout="funH()"  
								     height="20px" src="${pageContext.request.contextPath}/fileStore/${account.accountPicture}">
							</td>
							<td>${account.accountName}</td>
							<td width="100px">${account.accountPassword}</td>
							<td width="100px">${account.accountPicture}</td>
							<td>&nbsp;<fmt:formatDate value="${account.accountCreateTime}"
									pattern="yyyy-MM-dd hh:mm:ss" type="Date" />
							</td>
							<td><a
								href="${pageContext.request.contextPath}/account/delete?id=${account.id}">删除</a>
								|<a
								href="${pageContext.request.contextPath}/account/update?id=${account.id}">修改</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a href="${pageContext.request.contextPath}/account/regist">添加新账户</a>
		</div>
		<div id="div_paging">
			<!-- 显示分页信息标签 -->
			<mytag:page />
		</div>
	</div>

</body>
</html>