<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="/includes/header.jsp" flush="true" />


<div class="container my-4">
	<h1 class="mb-3">首頁</h1>

	<!-- AI 搜尋按鈕t1t2t5t8 -->
	<form action="t8" method="get" class="mb-4">
		<button type="submit" class="btn btn-outline-primary">AI 搜尋</button>
	</form>

	<!-- 推薦 / 個人推薦 按鈕（登入後顯示） -->
	<c:if test="${not empty sessionScope.user}">
		<a href="recommend.jsp" class="btn btn-primary me-2">推薦</a>
		<a href="personalRecommend.jsp" class="btn btn-secondary">個人推薦</a>
	</c:if>

	<div class="row mt-5">
		<!-- 左側：AI 推薦 -->
		<div class="col-md-6">
			<div class="card mb-4 shadow-sm">
				<div class="card-header">AI 推薦</div>
				<div class="card-body">
					<div class="row g-3">
						<c:forEach var="r" items="${aiList}">
							<div class="col-12">
								<a
									href="https://www.google.com/maps/search/?api=1&query=${r.lat},${r.lng}"
									target="_blank" class="text-decoration-none text-dark">
									<div class="card">
										<c:if test="${not empty r.photoUrl}">
											<img src="gcBGM3N.jpeg" class="card-img-top" alt="${r.name}">
										</c:if>

										<div class="card-body p-2">
											<h6 class="card-title mb-1">${r.name}</h6>
											<p class="card-text small text-muted">${r.address}</p>
										</div>
									</div>
								</a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>

		<!-- 右側：網紅推薦 -->
		<div class="col-md-6">
			<div class="card mb-4 shadow-sm">
				<div class="card-header">網紅推薦</div>
				<div class="card-body">
					<div class="row g-3">
						<c:forEach var="r" items="${influencerList}">
							<div class="col-12">
								<div class="card">
									<div class="d-flex align-items-center p-2">
										<!-- 網紅名稱點擊進入網紅頁 -->
										<a href="influencerProfile?id=${r.influencer.id}"
											class="me-3 fw-bold">${r.influencer.name}</a>
										<!-- 星星評分 -->
										<div>
											<c:forEach begin="1" end="5" var="i">
												<i class="bi bi-star${i <= r.rating ? "-filltext-warning" : ""}"></i>
											</c:forEach>
										</div>
									</div>
									<a href="restaurantDetail?id=${r.id}"
										class="text-decoration-none text-dark"> <img
										src="${r.photoUrl}" class="card-img-top" alt="${r.name}">
										<div class="card-body p-2">
											<h6 class="card-title mb-1">${r.name}</h6>
											<p class="card-text small text-muted">${r.address}</p>
										</div>
									</a>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<jsp:include page="/includes/footer.jsp" flush="true" />
<!-- 引入 bootstrap.js & icons -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
