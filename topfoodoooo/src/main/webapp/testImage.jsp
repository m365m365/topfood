<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>圖片測試</title>
</head>
<body>
	<h2>測試 screenshots/fallback1.jpeg 是否能顯示：</h2>

	<p>若成功，下面應該會看到圖片：</p>

	<img src="screenshots/mq2 (9).webp" width="400" alt="fallback1 測試圖">

	<hr>

	<p>測試其他可能副檔名：</p>
	<ul>
		<li><img src="screenshots/fallback1.jpg" width="200"
			alt="fallback1.jpg 測試"></li>
		<li><img src="screenshots/fallback1.png" width="200"
			alt="fallback1.png 測試"></li>
	</ul>

</body>
</html>
