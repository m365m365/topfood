<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.yourapp.info.AiRestaurantInfo"%>
<html>
<head>
<meta charset="UTF-8">
<title>台北美食推薦</title>
</head>
<body>
	<h2>台北 8 家熱門美食餐廳</h2>
	<table border="1">
		<tr>
			<th>名稱</th>
			<th>網址</th>
			<th>預覽圖</th>
		</tr>
		<%
		List<AiRestaurantInfo> aiList = (List<AiRestaurantInfo>) request.getAttribute("aiList");
		if (aiList != null) {
			for (AiRestaurantInfo info : aiList) {
		%>
		<tr>
			<td><%=info.getName()%></td>
			<td><a href="<%=info.getUrl()%>" target="_blank"><%=info.getUrl()%></a></td>
			<td><img src="<%=info.getPhotoUrl()%>" width="200"></td>
		</tr>
		<%
		}
		}
		%>
	</table>
</body>
</html>
