<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.yourapp.info.AiRestaurantInfo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>台北美食推薦卡片</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<style>
.card-container {
	max-width: 50vw; /* 左半邊 */
	padding: 1rem;
	resize: horizontal;
	overflow: auto;
	border: 1px solid #ccc;
	border-radius: 10px;
}

.card img {
	height: auto;
	max-width: 100%;
	border-bottom: 1px solid #ddd;
}

.card-title {
	margin-top: 0.5rem;
	text-align: center;
	font-weight: 600;
}
</style>
</head>
<body>
	<div class="container mt-4">
		<h2 class="mb-4">台北 6 家熱門美食推薦</h2>
		<div class="card-container">
			<div class="row row-cols-1 row-cols-md-2 g-4">
				<%
                List<AiRestaurantInfo> aiList = (List<AiRestaurantInfo>) request.getAttribute("aiList");
                if (aiList != null) {
                    for (AiRestaurantInfo info : aiList) {
            %>
				<div class="col">
					<div class="card h-100 shadow-sm">
						<a href="<%= info.getUrl() %>" target="_blank"> <img
							src="<%= info.getPhotoUrl() %>" class="card-img-top" alt="預覽圖">
						</a>
						<div class="card-body">
							<h5 class="card-title"><%= info.getName() %></h5>
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
	</div>
</body>
</html>
