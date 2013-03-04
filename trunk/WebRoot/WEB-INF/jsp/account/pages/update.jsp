<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/common/scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/common/scripts/ajaxfileupload.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/account/scripts/update.js"></script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/account/update"
		method="post">
		<input type="hidden" id="hidden_id" name="id" value="${updateAccount.id}">
		<table id="t1">
			<tr>
				<td>照片</td>
				<td><img
					src="${pageContext.request.contextPath}/fileStore/${updateAccount.accountPicture}">
				</td>
			</tr>
			<tr>
				<td>账户名</td>
				<td><input type="text" name="accountName"
					value="${updateAccount.accountName}"></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="text" name="accountPassword"
					value="${updateAccount.accountPassword}"></td>
			</tr>
			<tr>
				<td>照片路径</td>
				<td><input id="input_accountPicture" type="text" name="accountPicture"
					value="${updateAccount.accountPicture}"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="确认"></td>
			</tr>
		</table>
	</form>
	<div id="div_fileupdate">
		<form action="${pageContext.request.contextPath}/account/imageUpload"
			method="post" encType="multipart/form-data">
			<input type="hidden" name="id" value="${updateAccount.id}">
			<table>
				<tr>
					<td>选择照片</td>
					<td><input type="file" name="imageFile"> <br></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="上传照片"></td>
				</tr>
			</table>
		</form>
		ajax方式实现文件上传: <br />
		<!-- <input type="hidden" name="id" value="${updateAccount.id}"> -->
		<table>
			<tr>
				<td colspan="2">
					<div id="div_upload_result"></div>
				</td>
			</tr>
			<tr>
				<td>选择照片</td>
				<td><input id="imageFileAjax" type="file" name="imageFileAjax">
					<br></td>
			</tr>
			<tr>
				<td colspan="2"><input id="btn_upload_ajax" type="button"
					value="ajax方式上传照片" onclick="uploadImage()"></td>
			</tr>
		</table>

	</div>
</body>
</html>