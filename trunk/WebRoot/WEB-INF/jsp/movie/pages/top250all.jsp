<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Bootstrap 101 Template</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- Bootstrap -->
		<link href="${pageContext.request.contextPath}/resources/common/style/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="${pageContext.request.contextPath}/resources/common/style/bootstrap-responsive.min.css" rel="stylesheet"
			media="screen">
		<link href="${pageContext.request.contextPath}/resources/movie/style/top250.css" rel="stylesheet" type="text/css">
		<script src="http://code.jquery.com/jquery.js"></script>
		<script src="${pageContext.request.contextPath}/resources/common/scripts/bootstrap.min.js"></script>
	</head>
	<body style="margin-top: 40px">
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container">
					<a class="btn btn-navbar" data-toggle="collapse"
						data-target=".navbar-inverse-collapse"> <span class="icon-bar"></span>
						<span class="icon-bar"></span> <span class="icon-bar"></span> </a>
					<div class="nav-collapse collapse navbar-inverse-collapse">
						<ul class="nav">
							<li class="active">
								<a href="#">首页</a>
							</li>
							<li>
								<a href="#">聊天室</a>
							</li>
							<li>
								<a href="#">电影</a>
							</li>
							<li>
								<a href="#">论坛</a>
							</li>
						</ul>
						<ul class="nav pull-right">
							<li>
								<a>提醒</a>
							</li>
							<li>
								<a>邮件</a>
							</li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									wendellup的账号<b class="caret"></b> </a>
								<ul class="dropdown-menu">
									<li>
										<a href="#">我的账号</a>
									</li>
									<li>
										<a href="#">退出</a>
									</li>
								</ul>
							</li>
						</ul>
					</div>
					<!-- /.nav-collapse -->
				</div>
			</div>
			<!-- /navbar-inner -->
		</div>
		<div class="well">
				<div class="row-fluid">
					<div class="span2 offset1">
						<h3>
							搜索电影
						</h3>
					</div>
					<div class="span9">
						<form class="form" style="margin-top: 15px">
							<div class="input-append">
								<input type="text" class="input-xxlarge"
									placeholder="电影、影人、影院、电视剧">
								<button class="btn" type="button">
									查询
								</button>
							</div>
						</form>
					</div>
				</div>
				<div class="row">
					<div class="span6 offset1">
						<ul class="inline">
							<li>
								<a>我看</a>
							</li>
							<li>
								<a>影讯&购票</a>
							</li>
							<li>
								<a>电视剧</a>
							</li>
							<li>
								<a>排行榜</a>
							</li>
							<li>
								<a>分类</a>
							</li>
							<li>
								<a>影评</a>
							</li>
						</ul>
					</div>
				</div>
			</div>

		<!-- 电影列表开始 -->
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span1">
					<!--Sidebar content-->
				</div>
					<div class="span8">
						<c:forEach items="${movieList}" var="movie" varStatus="status">
							<div class="media">
								<span class="pull-left">
									${movie.movie250Rate}
								</span>
								<a class="pull-left" href="#">
									<img class="media-object"
										data-src="holder.js/105x150" alt="105*150"
										style="width: 105px; height: 150px;"
										src="${pageContext.request.contextPath}/fileStore/movie/${movie.moviePicUrl}">
										
								</a>
								<div class="media-body">
									<h4 class="media-heading">
										${movie.movieName}
									</h4>
									Cras sit amet nibh libero, in gravida nulla. Nulla vel metus
									scelerisque ante sollicitudin commodo. Cras purus odio,
									vestibulum in vulputate at, tempus viverra turpis. Fusce
									condimentum nunc ac nisi vulputate fringilla. Donec lacinia
									congue felis in faucibus.
									<br />
									<span class="rating${movie.movieScoreRate}-t"><em>${movie.movieScore}</em></span>
								</div>
							</div>
							<hr>
						</c:forEach>
				  
					<!-- 分页开始 -->
					<div class="pagination">
					  <ul>
					    <li><a href="#">Prev</a></li>
					    <li><a href="#">1</a></li>
					    <li><a href="#">2</a></li>
					    <li><a href="#">3</a></li>
					    <li><a href="#">4</a></li>
					    <li><a href="#">5</a></li>
					    <li><a href="#">Next</a></li>
					  </ul>
					</div>
					<!-- 分页结束 -->
				</div>
				<div class="span3">
					<!--Body content-->
				</div>
			</div>
		</div>
		<!-- 电影列表结束 -->
	</body>
</html>