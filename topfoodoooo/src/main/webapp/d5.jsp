<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.yourapp.info.AiRestaurantInfo"%>
<!DOCTYPE html>
<html>
<head>
<%
  class DummyRec {
    public String getPhotoUrl() { return "https://via.placeholder.com/300x200"; }
    public String getRestaurantName() { return "測試餐廳"; }
    public String getAddress() { return "台北市大安區"; }
    public int getRating() { return 4; }
    public int getId() { return 123; }
  }
  DummyRec rec = new DummyRec();
%>
<div class="col">
	<div class="card h-100 shadow-sm">
		<a
			href="https://www.google.com/maps/search/?api=1&query=<%= rec.getAddress() %>"
			target="_blank"> <img src="<%= rec.getPhotoUrl() %>"
			class="card-img-top" alt="店家圖片">
		</a>
		<div class="card-body">
			<h5 class="card-title"><%= rec.getRestaurantName() %></h5>
			<p class="card-text"><%= rec.getAddress() %></p>
			<p>
				<%
          for (int i = 0; i < rec.getRating(); i++) {
        %>
				<span class="text-warning">★</span>
				<%
          }
          for (int i = rec.getRating(); i < 5; i++) {
        %>
				<span class="text-secondary">☆</span>
				<%
          }
        %>
			</p>
		</div>
		<div class="card-footer d-flex justify-content-between">
			<form action="editRecommendation" method="get">
				<input type="hidden" name="id" value="<%= rec.getId() %>">
				<button type="submit" class="btn btn-sm btn-outline-primary">編輯</button>
			</form>
			<form action="deleteRecommendation" method="post"
				onsubmit="return confirm('確定刪除？');">
				<input type="hidden" name="id" value="<%= rec.getId() %>">
				<button type="submit" class="btn btn-sm btn-outline-danger">刪除</button>
			</form>
		</div>
	</div>
</div>
</body>
</html>
