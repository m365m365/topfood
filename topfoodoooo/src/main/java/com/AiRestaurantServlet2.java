package com;

import com.yourapp.info.AiRestaurantInfo;
import com.yourapp.info.JsonUtil;
import com.yourapp.util.BatchScreenshotUtil;
import com.yourapp.util.ChatGPTUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/t8")
public class AiRestaurantServlet2 extends HttpServlet {

    private static final String PROMPT =
        "請輸出台北16家熱門美食餐廳，使用 JSON 陣列格式回傳，例如：\n" +
        "[{\"name\": \"鼎泰豐\", \"url\": \"https://www.dintaifung.com.tw/\"}]";

    private static final String API_KEY = "your gpt金鑰";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("/t8 Servlet 啟動");

        // 1. 呼叫 GPT 取得原始 JSON
        String gptResponseJson = ChatGPTUtil.askGpt(PROMPT, API_KEY);

        // 2. 解析 JSON 成 List<AiRestaurantInfo>
        List<AiRestaurantInfo> aiList = JsonUtil.parseAiRestaurantList(gptResponseJson);

        // 3. 擷取成功圖片（最多 6 張）
        String screenshotDir = getServletContext().getRealPath("/screenshots");
        List<AiRestaurantInfo> successList = BatchScreenshotUtil.captureScreenshots(aiList, screenshotDir);

        // 4. 若成功不足 6 家，補 fallback
        if (successList.size() < 6) {
        	System.out.println("啟用備用圖片");
            List<AiRestaurantInfo> fallback = getFallbackRestaurants();
            int needed = 6 - successList.size();
            successList.addAll(fallback.subList(0, needed));
        }
     

        // 5. 傳給 JSP 顯示卡片
        request.setAttribute("aiList", successList);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private List<AiRestaurantInfo> getFallbackRestaurants() {
        List<AiRestaurantInfo> fallback = new ArrayList<>();
        fallback.add(new AiRestaurantInfo("樂沐法式餐廳", "http://www.le-mout.com/", "images/backup1.jpg"));
        fallback.add(new AiRestaurantInfo("大腕燒肉", "https://www.da-wan.com.tw/", "images/backup2.jpg"));
        fallback.add(new AiRestaurantInfo("Impromptu by Paul Lee", "https://www.impromptu.tw/", "images/backup3.jpg"));
        fallback.add(new AiRestaurantInfo("金色三麥", "https://www.taps.com.tw/", "images/backup1.jpg"));
        fallback.add(new AiRestaurantInfo("RAW", "https://www.raw.com.tw/", "images/backup2.jpg"));
        fallback.add(new AiRestaurantInfo("MUME", "https://www.mume.tw/", "images/backup3.jpg"));
        return fallback;
    }
}
