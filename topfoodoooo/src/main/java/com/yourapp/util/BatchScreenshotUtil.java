package com.yourapp.util;

import com.yourapp.info.AiRestaurantInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class BatchScreenshotUtil {

    // 簡化版：不執行 Selenium，只回傳原始資料
    public static List<AiRestaurantInfo> captureScreenshots(List<AiRestaurantInfo> list, String screenshotDir) {
        return captureScreenshots(list, screenshotDir, null);
    }

    public static List<AiRestaurantInfo> captureScreenshots(
            List<AiRestaurantInfo> list,
            String screenshotDir,
            BiConsumer<AiRestaurantInfo, Integer> onSuccess
    ) {
        System.out.println("⚠️ Screenshot 功能已停用（因為部署環境無法使用 Chrome/Selenium）");
        
        // 模擬處理成功回呼
        int count = 0;
        for (AiRestaurantInfo info : list) {
            if (onSuccess != null) {
                onSuccess.accept(info, count++);
            }
        }

        // 原樣回傳
        return list;
    }
}


