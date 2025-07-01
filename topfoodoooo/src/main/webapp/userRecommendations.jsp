<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<jsp:include page="/includes/header.jsp" flush="true" />

<div class="container mt-5">
	<h2 class="mb-4">王曉明推薦餐廳</h2>

	<!-- 新增按鈕 -->
	<div class="mb-3 text-end">
		<a href="influencerForm.jsp" class="btn btn-success">新增</a>
	</div>

	<!-- 靜態卡片列表 -->
	<div class="row row-cols-1 row-cols-md-3 g-4">
		<!-- 卡片 1 -->
		<div class="col">
			<div class="card h-100 shadow-sm">
				<a href="https://www.google.com/maps?q=台北市信義區" target="_blank">
					<img src="https://via.placeholder.com/300x200" class="card-img-top"
					alt="店家圖片">
				</a>
				<div class="card-body">
					<h5 class="card-title">鼎泰豐</h5>
					<p class="card-text">台北市信義區信義路5段</p>
					<p>
						<span class="text-warning">★</span> <span class="text-warning">★</span>
						<span class="text-warning">★</span> <span class="text-warning">★</span>
						<span class="text-secondary">☆</span>
					</p>
				</div>
				<div class="card-footer d-flex justify-content-between">
					<button class="btn btn-sm btn-outline-primary">編輯</button>
					<button class="btn btn-sm btn-outline-danger">刪除</button>
				</div>
			</div>
		</div>

		<!-- 卡片 2 -->
		<div class="col">
			<div class="card h-100 shadow-sm">
				<a href="https://www.google.com/maps?q=台北市大安區" target="_blank">
					<img src="https://via.placeholder.com/300x200" class="card-img-top"
					alt="店家圖片">
				</a>
				<div class="card-body">
					<h5 class="card-title">春水堂</h5>
					<p class="card-text">台北市大安區復興南路</p>
					<p>
						<span class="text-warning">★</span> <span class="text-warning">★</span>
						<span class="text-warning">★</span> <span class="text-secondary">☆</span>
						<span class="text-secondary">☆</span>
					</p>
				</div>
				<div class="card-footer d-flex justify-content-between">
					<button class="btn btn-sm btn-outline-primary">編輯</button>
					<button class="btn btn-sm btn-outline-danger">刪除</button>
				</div>
			</div>
		</div>

		<!-- 可加更多卡片 -->
	</div>
</div>

<jsp:include page="/includes/footer.jsp" flush="true" />
