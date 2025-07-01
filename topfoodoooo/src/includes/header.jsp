<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 引入 Bootstrap CSS 或自訂 CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="css/styles.css">
<title>餐廳推薦</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm">
		<div class="container">
			<a class="navbar-brand" href="index.jsp">餐廳推薦</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<!-- 左側空位 / 或放其他導覽連結 -->
				<ul class="navbar-nav me-auto"></ul>
				<!-- 右側：登入／下拉會員選單 -->
				<ul class="navbar-nav">
					<c:choose>
						<c:when test="${not empty sessionScope.user}">
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="userMenu"
								role="button" data-bs-toggle="dropdown">
									${sessionScope.user} </a>
								<ul class="dropdown-menu dropdown-menu-end"
									aria-labelledby="userMenu">
									<li><a class="dropdown-item" href="personalRecommend.jsp">個人推薦</a>
									</li>
									<li><hr class="dropdown-divider"></li>
									<li><a class="dropdown-item" href="logout.jsp">登出</a></li>
								</ul></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link" href="login.jsp">登入</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="d3.jsp">註冊</a>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>