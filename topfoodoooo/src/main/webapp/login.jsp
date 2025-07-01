<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:include page="/includes/header.jsp" flush="true" />

<div class="container my-5" style="max-width: 400px;">
	<h3>會員登入</h3>
	<form action="login" method="post">
		<div class="mb-3">
			<label class="form-label">使用者姓名</label> <input type="text"
				name="username" class="form-control" required>
		</div>
		<div class="mb-3">
			<label class="form-label">密碼</label> <input type="password"
				name="password" class="form-control" required>
		</div>
		<c:if test="${not empty error}">
			<div class="alert alert-danger">${error}</div>
		</c:if>
		<label><input type="checkbox" name="remember"> 記住我</label><br>
		<button type="submit" class="btn btn-primary">登入</button>
	</form>
</div>
<jsp:include page="/includes/footer.jsp" flush="true" />


