<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.yourapp.info.AiRestaurantInfo"%>
<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<jsp:include page="/includes/header.jsp" flush="true" />

<div class="container mt-5" style="max-width: 500px;">
	<h2 class="mb-4 text-center">會員註冊</h2>

	<form action="register" method="post">
		<!-- Email -->
		<div class="mb-3">
			<label for="email" class="form-label">EMAIL</label> <input
				type="email" class="form-control" id="email" name="email" required>
		</div>

		<!-- 密碼 -->
		<div class="mb-3">
			<label for="password" class="form-label">密碼</label> <input
				type="password" class="form-control" id="password" name="password"
				required>
		</div>

		<!-- 名字 -->
		<div class="mb-3">
			<label for="name" class="form-label">名字</label> <input type="text"
				class="form-control" id="name" name="name" required>
		</div>

		<!-- 確定按鈕 -->
		<div class="d-grid">
			<button type="submit" class="btn btn-success">確定</button>
		</div>
	</form>

	<!-- 補充說明 -->
	<div class="form-text mt-3 text-muted">註冊後我們將寄送驗證信到你填寫的
		Email，請點擊信件連結後即可登入。</div>
</div>

<jsp:include page="/includes/footer.jsp" flush="true" />
</body>
</html>
