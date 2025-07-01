<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%
    String user = (String) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員主頁</title>
<!-- Bootstrap CDN（你也可以換成本地版本） -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="container mt-4">
	<h2 class="mb-4">
		歡迎，<%= user %>！
	</h2>

	<!-- 會員功能選單 -->
	<div class="btn-group mb-4" role="group">
		<a href="index.jsp" class="btn btn-outline-primary">回首頁</a> <a
			href="recommend.jsp" class="btn btn-outline-success">我要推薦</a> <a
			href="favorites.jsp" class="btn btn-outline-warning">我的收藏</a>
	</div>

	<br>
	<a href="logout.jsp" class="btn btn-danger">登出</a>
</body>
</html>
