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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
@WebServlet("/t5")
public class AiRestaurantServlet extends HttpServlet {
    private static final String PROMPT =
        "請列出台北16家熱門美食餐廳，回傳 JSON 陣列，每筆包含 name 和 url。";

    private static final String API_KEY = "your gpt金鑰"; // ← 使用你自己的
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/event-stream;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Connection", "keep-alive");

        PrintWriter writer = response.getWriter();

        String gptResponseJson = ChatGPTUtil.askGpt(PROMPT, API_KEY);
        List<AiRestaurantInfo> aiList = JsonUtil.parseAiRestaurantList(gptResponseJson);

        String screenshotDir = getServletContext().getRealPath("/screenshots");
        List<AiRestaurantInfo> successList = new ArrayList<>();

        BatchScreenshotUtil.captureScreenshots(aiList, screenshotDir, (info, index) -> {
            successList.add(info);

            String json = String.format(
                "{\"name\":\"%s\",\"url\":\"%s\",\"photoUrl\":\"%s\",\"count\":%d}",
                info.getName(), info.getUrl(), info.getPhotoUrl(), index + 1
            );

            writer.println("data: " + json);
            writer.println();
            writer.flush();
        });

        if (successList.size() < 6) {
            List<AiRestaurantInfo> fallback = getFallbackRestaurants();
            int needed = 6 - successList.size();
            for (int i = 0; i < needed; i++) {
                AiRestaurantInfo info = fallback.get(i);
                successList.add(info);

                String json = String.format(
                    "{\"name\":\"%s\",\"url\":\"%s\",\"photoUrl\":\"%s\",\"count\":%d}",
                    info.getName(), info.getUrl(), info.getPhotoUrl(), successList.size()
                );

                writer.println("data: " + json);
                writer.println();
                writer.flush();
            }
        }

        writer.println("event: done");
        writer.println("data: done");
        writer.println();
        writer.flush();
        writer.close();
    }

    private List<AiRestaurantInfo> getFallbackRestaurants() {
        List<AiRestaurantInfo> fallback = new ArrayList<>();
        fallback.add(new AiRestaurantInfo("RAW", "https://www.raw.com.tw/", "images/backup1.jpg"));
        fallback.add(new AiRestaurantInfo("金色三麥", "https://www.taps.com.tw/", "images/backup2.jpg"));
        fallback.add(new AiRestaurantInfo("MUME", "https://www.mume.tw/", "images/backup3.jpg"));
        fallback.add(new AiRestaurantInfo("陶板屋", "https://www.tokiya.com.tw/", "images/backup1.jpg"));
        fallback.add(new AiRestaurantInfo("Impromptu", "https://www.impromptu.tw/", "images/backup2.jpg"));
        fallback.add(new AiRestaurantInfo("大腕燒肉", "https://www.da-wan.com.tw/", "images/backup3.jpg"));
        return fallback;
    }
}
