package com.yourapp.info;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    public static AiRestaurantInfo parseAiRestaurantInfo(String jsonText) {
        List<AiRestaurantInfo> list = parseAiRestaurantList(jsonText);
        return list.isEmpty() ? null : list.get(0);
    }

    public static List<AiRestaurantInfo> parseAiRestaurantList(String jsonText) {
        List<AiRestaurantInfo> result = new ArrayList<>();

        try {
            // Step 1: parse outer response
            JSONObject root = new JSONObject(jsonText);
            JSONArray choices = root.getJSONArray("choices");
            JSONObject message = choices.getJSONObject(0).getJSONObject("message");
            String content = message.getString("content");  // content 是 JSON 字串，但可能有 markdown 包裝

            // Step 2: 清除 GPT 包起來的 markdown 語法（例如 ```json ... ```）
            String pureJsonArray = extractJsonArray(content);

            // Step 3: 轉換為 JSONArray 並處理每筆資料
            JSONArray arr = new JSONArray(pureJsonArray);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                AiRestaurantInfo info = new AiRestaurantInfo();
                info.setName(obj.getString("name"));
                info.setUrl(obj.getString("url"));
                result.add(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String extractJsonArray(String raw) {
        // 嘗試擷取 ```json ... ``` 區段，或直接回傳本體
        int start = raw.indexOf("[");
        int end = raw.lastIndexOf("]");
        if (start >= 0 && end >= 0 && end > start) {
            return raw.substring(start, end + 1);
        }
        return "[]";  // fallback
    }
}
