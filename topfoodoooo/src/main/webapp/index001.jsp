<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.yourapp.info.AiRestaurantInfo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/includes/header.jsp" flush="true" />
<div class="container my-4">
	<h1 class="mb-3">首頁</h1>

	<!-- AI 搜尋按鈕t1t2t5t8 -->
	<form action="t8" method="get" class="mb-4">
		<button type="submit" class="btn btn-outline-primary">AI 搜尋</button>
	</form>


	<meta charset="UTF-8">
	<title>台北美食推薦卡片</title>
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
	<style>
.card-container {
	max-width: 40vw; /* 左半邊略縮小 */
	margin-left: 0; /* 靠左 */
	margin-right: auto;
	padding: 1rem;
	resize: horizontal;
	overflow: auto;
	border: 1px solid #ccc;
	border-radius: 10px;
}

.card {
	max-width: 180px; /* 控制單卡寬度 */
	margin: auto;
}

.card img {
	width: 100%;
	height: 100px;
	object-fit: cover;
	border-bottom: 1px solid #ddd;
}

.card-title {
	margin-top: 0.5rem;
	text-align: center;
	font-size: 0.95rem;
	font-weight: 600;
}

h2 {
	font-size: 1.5rem;
	font-weight: bold;
}
</style>
	</head>
	<body>
		<div class="container mt-4">
			<h2 class="mb-4">台北 6 家熱門美食推薦</h2>
			<div class="card-container">
				<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-2">
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
            %>
					<div class="col-12">
						<p>找不到餐廳資料。</p>
					</div>
					<%
                }
            %>
				</div>
			</div>
			<jsp:include page="/includes/footer.jsp" flush="true" />
			<!-- 引入 bootstrap.js & icons -->
			<script
				src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
			<link rel="stylesheet"
				href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">