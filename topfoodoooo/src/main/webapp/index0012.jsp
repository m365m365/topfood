<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.yourapp.info.AiRestaurantInfo"%>

<jsp:include page="/includes/header.jsp" flush="true" />
<div class="container my-4">
	<h1 class="mb-3">首頁</h1>

	<!-- ✅ 按下按鈕才開始 -->
	<button id="searchBtn" class="btn btn-outline-primary">AI 搜尋</button>

	<!-- ✅ 進度條（預設隱藏） -->
	<div class="mt-3" id="progressSection"
		style="width: 300px; display: none;">
		<div class="progress" style="height: 20px;">
			<div id="progressBar"
				class="progress-bar progress-bar-striped bg-success"
				role="progressbar" style="width: 0%">尚未開始</div>
		</div>
	</div>

	<h2 class="mt-4 mb-3">台北 6 家熱門美食推薦</h2>
	<div class="card-container">
		<div id="cardArea"
			class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-2"></div>
	</div>
</div>

<!-- ✅ 樣式 -->
<style>
.card-container {
	max-width: 40vw;
	margin-left: 0;
	margin-right: auto;
	padding: 1rem;
	resize: horizontal;
	overflow: auto;
	border: 1px solid #ccc;
	border-radius: 10px;
}

.card {
	max-width: 180px;
	margin: auto;
}

.card img {
	width: 100%;
	height: 100px;
	object-fit: cover;
	border-bottom: 1px solid #ddd;
}

.card-title {
	margin-top: 0.5rem;
	text-align: center;
	font-size: 0.95rem;
	font-weight: 600;
}

h2 {
	font-size: 1.5rem;
	font-weight: bold;
}
</style>

<!-- ✅ JavaScript：使用 SSE 即時接收資料並更新進度條與卡片 -->
<script>
document.getElementById("searchBtn").addEventListener("click", () => {
    const max = 6;
    let count = 0;

    const progressSection = document.getElementById("progressSection");
    const progressBar = document.getElementById("progressBar");
    const cardArea = document.getElementById("cardArea");

    // 顯示進度條
    progressSection.style.display = "block";
    progressBar.classList.remove("bg-info", "bg-danger");
    progressBar.classList.add("bg-success");
    progressBar.style.width = "0%";
    progressBar.innerText = "蒐集中...";
    cardArea.innerHTML = "";

    const source = new EventSource("sse-t8");

    source.onmessage = (e) => {
        const data = JSON.parse(e.data);
        if (!data.photoUrl || data.photoUrl.trim() === "") return;

        count = data.count;
        const percent = Math.min((count / max) * 100, 100);
        progressBar.style.width = percent + "%";
        progressBar.innerText = `已蒐集 ${count} 家`;

        const contextPath = "/" + window.location.pathname.split("/")[1];
        const imgUrl = data.photoUrl.startsWith("http") ? data.photoUrl : contextPath + "/" + data.photoUrl;

        const card = document.createElement("div");
        card.className = "col";
        card.innerHTML = `
          <div class="card shadow-sm">
            <a href="${data.url}" target="_blank">
              <img src="${imgUrl}" class="card-img-top" alt="圖" />
            </a>
            <div class="card-body p-2">
              <h6 class="card-title text-center">${data.name}</h6>
            </div>
          </div>`;
        cardArea.appendChild(card);
    };

    source.addEventListener("done", () => {
        progressBar.classList.remove("bg-success");
        progressBar.classList.add("bg-info");
        progressBar.innerText = "蒐集完成";
        source.close();
    });

    source.onerror = () => {
        progressBar.classList.remove("bg-success");
        progressBar.classList.add("bg-danger");
        progressBar.innerText = "錯誤：連線失敗";
        source.close();
    };
});
</script>

<!-- Bootstrap & Footer -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<jsp:include page="/includes/footer.jsp" flush="true" />
