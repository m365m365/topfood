<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.yourapp.info.AiRestaurantInfo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/includes/header.jsp" flush="true" />
<div class="container my-4">
	<h1 class="mb-3">首頁</h1>

	<!-- AI 搜尋按鈕 -->
	<form action="t8" method="get" class="mb-4">
		<button type="submit" class="btn btn-outline-primary">AI 搜尋</button>
	</form>

	<h2 class="mb-4">台北 6 家熱門美食推薦</h2>

	<!-- 主區塊分為左右兩側 -->
	<div class="row">
		<!-- 左：AI 推薦 -->
		<div class="col-md-6">
			<div class="card-container">
				<div class="row row-cols-1 row-cols-sm-2 row-cols-md-2 g-2">
					<%
            List<AiRestaurantInfo> aiList = (List<AiRestaurantInfo>) request.getAttribute("aiList");
            if (aiList != null) {
              for (AiRestaurantInfo info : aiList) {
          %>
					<div class="col">
						<div class="card shadow-sm">
							<a href="<%= info.getUrl() %>" target="_blank"> <img
								src="<%= info.getPhotoUrl() %>" class="card-img-top" alt="預覽圖">
							</a>
							<div class="card-body p-2">
								<h6 class="card-title"><%= info.getName() %></h6>
							</div>
						</div>
					</div>
					<%
              }
            } else {
            	 // 以下是預設的 6 張卡片
                String[] defaultNames = {
                  "阜杭豆漿", "牛肉麵一條街", "阿宗麵線", "永康牛肉麵", "天廚菜館", "士林夜市豪大雞排"
                };
                String[] defaultUrls = {
                  "https://www.facebook.com/pages/%E8%8F%AF%E5%B1%B1%E5%B8%82%E5%A0%B4-%E9%98%9C%E6%9D%AD%E8%B1%86%E6%BC%BF/154088941341874",
                  "https://www.facebook.com/taiwanfoodeat/posts/%E7%89%9B%E8%82%89%E9%BA%B5%E4%B8%80%E6%A2%9D%E8%A1%97%E9%9B%86%E9%83%B5%E5%AE%8C%E7%95%A2%E5%90%83%E5%BE%97%E5%B7%AE%E4%B8%8D%E5%A4%9A%E4%BA%86%E5%85%B6%E5%AF%A6%E6%AF%8F%E9%96%93%E6%B5%81%E6%B4%BE%E9%83%BD%E5%A4%A7%E5%90%8C%E5%B0%8F%E7%95%B0%E6%B9%AF%E9%A0%AD%E6%8E%A5%E8%BF%91%E4%B8%80%E6%A8%A3%E6%8F%90%E4%BE%9B%E7%89%9B%E6%B2%B9%E8%BE%A3%E7%89%9B%E6%B2%B9%E9%85%B8%E8%8F%9C%E7%94%9A%E8%87%B3%E9%82%84%E6%9C%89%E5%85%A9%E9%96%93%E9%96%8B%E9%9A%94%E5%A3%81%E5%BA%97%E5%90%8D%E4%B8%8D%E5%90%8C/542891211835573/",
                  "https://www.klook.com/zh-TW/activity/10451-ay-chung-flour-rice-noodle-taipei/", 
                  "https://www.facebook.com/p/%E6%B0%B8%E5%BA%B7%E7%89%9B%E8%82%89%E9%BA%B5%E9%A4%A8-100063524573126/?locale=zh_TW",
                  "https://www.facebook.com/TianChuCaiGuan/?locale=zh_TW",
                  "http://www.hotstar.com.tw/tw/AboutUs/ugC_HotStar.asp"
                };
                String[] defaultPhotos = {
                		"images/food01.webp", "images/food02.jpg","images/food03.webp","images/food04.webp","images/food05.webp","images/food06.jpg"
                };

                for (int i = 0; i < 6; i++) {
            %>
					<div class="col">
						<div class="card shadow-sm">
							<a href="<%= defaultUrls[i] %>" target="_blank"> <img
								src="<%= defaultPhotos[i] %>" class="card-img-top" alt="預設圖">
							</a>
							<div class="card-body p-2">
								<h6 class="card-title"><%= defaultNames[i] %></h6>
							</div>
						</div>
					</div>
					<%
                }
              }
            %>


				</div>
			</div>
		</div>

		<!-- 右：網紅推薦 -->
		<div class="col-md-6">
			<!-- 兩個平行按鈕 -->
			<div class="d-flex justify-content-between mb-3">
				<a href="member.jsp" class="btn btn-outline-success">新增推薦</a> <a
					href="member.jsp" class="btn btn-outline-primary">我的推薦</a>
			</div>

			<h4 class="mb-3">網紅推薦</h4>

			<div class="card mb-3">
				<div class="card-body">
					<h5 class="card-title">網紅名稱：美食阿宏</h5>
					<img src="https://via.placeholder.com/300x150"
						class="img-fluid mb-2" alt="店家圖片">
					<p class="mb-1">
						<strong>店家名稱：</strong>金雞園
					</p>
					<p class="mb-1">
						<strong>地址：</strong>台北市大安區永康街xx號
					</p>
					<p class="mb-1">
						推薦評分： <span class="text-warning">★ ★ ★ ★ ☆</span>
					</p>
					<a href="userRecommendations.jsp"
						class="btn btn-sm btn-outline-secondary">查看個人推薦頁</a>
				</div>
			</div>

			<div class="card mb-3">
				<div class="card-body">
					<h5 class="card-title">網紅名稱：吃貨小梅</h5>
					<img src="https://via.placeholder.com/300x150"
						class="img-fluid mb-2" alt="店家圖片">
					<p class="mb-1">
						<strong>店家名稱：</strong>鼎泰豐
					</p>
					<p class="mb-1">
						<strong>地址：</strong>台北市信義區信義路xx號
					</p>
					<p class="mb-1">
						推薦評分： <span class="text-warning">★ ★ ★ ★ ★</span>
					</p>
					<a href="userRecommendations.jsp"
						class="btn btn-sm btn-outline-secondary">查看個人推薦頁</a>
				</div>
			</div>
		</div>


		<jsp:include page="/includes/footer.jsp" flush="true" />

		<!-- 引入 bootstrap.js & icons -->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
		<link rel="stylesheet"
			href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">

		<style>
.card-container {
	max-width: 100%;
	padding: 1rem;
	border: 1px solid #ccc;
	border-radius: 10px;
}

.card img {
	width: 100%;
	height: 100px;
	object-fit: cover;
}

.card-title {
	font-size: 1rem;
	font-weight: bold;
}
</style>