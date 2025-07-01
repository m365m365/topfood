<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.yourapp.info.AiRestaurantInfo"%>
<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/includes/header.jsp" flush="true" />

<div class="container mt-5">
	<h2 class="mb-4">新增個人推薦</h2>
	<form action="submitInfluencer" method="post"
		enctype="multipart/form-data">

		<!-- 上傳圖片 -->
		<div class="mb-3">
			<label for="photo" class="form-label">上傳圖片</label> <input type="file"
				class="form-control" id="photo" name="photo" required>
		</div>

		<!-- 店家名稱 -->
		<div class="mb-3">
			<label for="restaurantName" class="form-label">店家名稱</label> <input
				type="text" class="form-control" id="restaurantName"
				name="restaurantName" required>
		</div>

		<!-- 推薦理由 -->
		<div class="mb-3">
			<label for="reason" class="form-label">推薦理由</label>
			<textarea class="form-control" id="reason" name="reason" rows="3"
				required></textarea>
		</div>

		<!-- 星星評分 -->
		<div class="mb-3">
			<label class="form-label">推薦評分</label><br>
			<c:forEach var="i" begin="1" end="5">
				<input type="radio" class="btn-check" name="rating" id="star${i}"
					value="${i}" required>
				<label class="btn btn-outline-warning" for="star${i}">★</label>
			</c:forEach>
		</div>

		<!-- 地址 -->
		<div class="mb-3">
			<label for="address" class="form-label">地址</label> <input type="text"
				class="form-control" id="address" name="address" required>
		</div>

		<!-- 按鈕 -->
		<div class="d-flex gap-2">
			<button type="submit" class="btn btn-primary">送出</button>
			<button type="reset" class="btn btn-secondary">再推薦</button>
			<a href="index.jsp" class="btn btn-outline-dark">返回首頁</a>
		</div>

		<!-- 提示文字 -->
		<div class="form-text mt-3">
			使用者需登入才能啟用送出功能，才能點擊再推薦按鈕。<br> 送出資料會導向首頁且資料會同步加入會員頁。
		</div>

	</form>
</div>

<jsp:include page="/includes/footer.jsp" flush="true" />
</body>
</html>
