<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../module/global/taglib.jsp"%>
<div id="div_global_nav">
	<ul id="ul_left">
		<li>
			<a>主页</a>
		</li>
		<li>
			<a>电影</a>
		</li>
		<li>
			<a href="javascript:location.href='chatroom'">聊天室</a>
		</li>
		<li>
			<a>小站</a>
		</li>
	</ul>
	<ul id="ul_right">
		<c:choose>
			<c:when test='${sessionAccount!=null}'>
				<li>
					<a>提醒</a>
				</li>
				<li>
					<a>${sessionAccount.accountName} 的账号</a>
				</li>
				<li>
					<a href="javascript:location.href='login/exit'">登出</a>
				</li>
			</c:when>
			<c:otherwise>
				<li>
					<a href="javascript:location.href='login'">登录</a>
				</li>
				<li>
					<a href="javascript:location.href='register'">注册</a>
				</li>
			</c:otherwise>
		</c:choose>
	
		<c:if test="">
			
		</c:if>
	</ul>
</div>
