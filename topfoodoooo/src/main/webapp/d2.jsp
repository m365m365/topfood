<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.yourapp.info.AiRestaurantInfo"%>
<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<jsp:include page="/includes/header.jsp" flush="true" />

<div class="container mt-5" style="max-width: 500px;">
	<h2 class="mb-4 text-center">會員登入</h2>

	<!-- 登入表單 -->
	<form action="login" method="post">
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

		<!-- 按鈕區 -->
		<div class="d-flex justify-content-between">
			<button type="submit" class="btn btn-primary">登入</button>
			<a href="register.jsp" class="btn btn-outline-secondary">註冊</a>
		</div>
	</form>
</div>

<jsp:include page="/includes/footer.jsp" flush="true" />
</body>
</html>
