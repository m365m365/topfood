<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.yourapp.util.DBUtil"%>
<jsp:include page="/includes/header.jsp" />

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<div class="container mt-5">
	<h2 class="mb-4">會員管理後台</h2>

	<!-- 新增會員表單 -->
	<form action="admin_member.jsp" method="post" class="row g-3 mb-5">
		<h5>新增會員</h5>
		<div class="col-md-2">
			<input type="text" name="name" class="form-control" placeholder="姓名"
				required>
		</div>
		<div class="col-md-3">
			<input type="email" name="email" class="form-control"
				placeholder="Email" required>
		</div>
		<div class="col-md-2">
			<input type="text" name="password" class="form-control"
				placeholder="密碼" required>
		</div>
		<div class="col-md-2">
			<select name="member" class="form-select">
				<option value="y">啟用</option>
				<option value="n">未啟用</option>
			</select>
		</div>
		<div class="col-md-2">
			<button type="submit" name="action" value="insert"
				class="btn btn-primary">新增</button>
		</div>
	</form>

	<%
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            try (Connection conn = DBUtil.getConnection()) {
                PreparedStatement ps = conn.prepareStatement("DELETE FROM members WHERE id=?");
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String member = request.getParameter("member");
            try (Connection conn = DBUtil.getConnection()) {
                PreparedStatement ps = conn.prepareStatement(
                    "UPDATE members SET username=?, email=?, password=?, member=? WHERE id=?"
                );
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, password);
                ps.setString(4, member);
                ps.setInt(5, id);
                ps.executeUpdate();
            }
        } else if ("insert".equals(action)) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String member = request.getParameter("member");
            try (Connection conn = DBUtil.getConnection()) {
                PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO members (username, email, password, member) VALUES (?, ?, ?, ?)"
                );
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, password);
                ps.setString(4, member);
                ps.executeUpdate();
            }
        }
    %>

	<!-- 顯示所有會員資料 -->
	<table class="table table-bordered align-middle">
		<thead class="table-light">
			<tr>
				<th>ID</th>
				<th>姓名</th>
				<th>Email</th>
				<th>密碼</th>
				<th>狀態</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<%
            try (Connection conn = DBUtil.getConnection()) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM members");
                while (rs.next()) {
        %>
			<form action="admin_member.jsp" method="post">
				<tr>
					<td><%= rs.getInt("id") %></td>
					<td><input type="text" name="name"
						value="<%= rs.getString("username") %>" class="form-control"></td>
					<td><input type="email" name="email"
						value="<%= rs.getString("email") %>" class="form-control"></td>
					<td><input type="text" name="password"
						value="<%= rs.getString("password") %>" class="form-control"></td>
					<td><select name="member" class="form-select">
							<option value="y"
								<%= rs.getString("member").equals("y") ? "selected" : "" %>>啟用</option>
							<option value="n"
								<%= rs.getString("member").equals("n") ? "selected" : "" %>>未啟用</option>
					</select></td>
					<td><input type="hidden" name="id"
						value="<%= rs.getInt("id") %>">
						<button type="submit" name="action" value="update"
							class="btn btn-success btn-sm">修改</button> <a
						href="admin_member.jsp?action=delete&id=<%= rs.getInt("id") %>"
						class="btn btn-danger btn-sm"
						onclick="return confirm('確定要刪除這筆會員資料嗎？');">刪除</a></td>
				</tr>
			</form>
			<%
                }
                rs.close();
            }
        %>
		</tbody>
	</table>
</div>

<jsp:include page="/includes/footer.jsp" />
