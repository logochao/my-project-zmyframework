<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../module/global/taglib.jsp"%>
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
当前在线人数:${fn:length(onlineAccountList)}
<br />
当前在线人列表
<ul>
	<c:forEach items="${onlineAccountList}" var="account" varStatus="status">
		<li>
			<div id="div_img" style="position:absolute;left:10;display:none">
				<img/>
			</div>
			<img height="20px" src="${pageContext.request.contextPath}/fileStore${account.accountPicture}">
			<a href="${pageContext.request.contextPath}/account/update?id=${account.id}">${account.accountName}</a>
		</li>
	</c:forEach>
</ul>
