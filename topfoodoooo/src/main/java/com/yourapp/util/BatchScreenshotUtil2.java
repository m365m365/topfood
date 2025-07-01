package com.yourapp.util;

import com.yourapp.info.AiRestaurantInfo;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BatchScreenshotUtil2 {

    private static final List<String> DOMAIN_BLACKLIST = List.of(
        "facebook.com",
        "instagram.com",
        "line.me",
        "twitter.com",
        "linkedin.com",
        "ssr.com.tw"
    );

    private static boolean isBlacklistedUrl(String url) {
        if (url.startsWith("http://")) return true;  // 非 HTTPS
        for (String domain : DOMAIN_BLACKLIST) {
            if (url.contains(domain)) return true;
        }
        return false;
    }

    public static List<AiRestaurantInfo> captureScreenshots(List<AiRestaurantInfo> list, String screenshotDir) {
        List<AiRestaurantInfo> successList = new ArrayList<>();

        // 1. 建立 screenshots 資料夾
        File dir = new File(screenshotDir);
        if (!dir.exists()) {
            dir.mkdirs();
            System.out.println("📂 建立 screenshots 目錄: " + dir.exists());
        }

        // 2. 清空資料夾
        try {
            FileUtils.cleanDirectory(dir);
        } catch (IOException e) {
            System.err.println("❌ 無法清除 screenshots 目錄: " + e.getMessage());
        }

        // 3. Selenium 設定
        System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--disable-gpu", "--window-size=1280,800");

        WebDriver driver = new ChromeDriver(options);

        int count = 0;
        for (AiRestaurantInfo info : list) {
            if (count >= 6) break;

            String url = info.getUrl().toLowerCase();
            if (isBlacklistedUrl(url)) {
                System.out.println("❌ 跳過不支援網站: " + url);
                continue;
            }

            try {
                driver.get(info.getUrl());
                Thread.sleep(3000); // 等網頁載入

                // 原始截圖暫存檔
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String fileName = "restaurant_" + (count + 1) + ".png";
                File dest = new File(dir, fileName);

                // 進行縮圖
                Thumbnails.of(src)
                          .width(500)
                          .outputFormat("png")
                          .toFile(dest);

                info.setPhotoUrl("screenshots/" + fileName);
                successList.add(info);
                System.out.println("✅ 截圖完成: " + info.getName());
                count++;
            } catch (Exception e) {
                System.out.println("❌ 截圖失敗: " + info.getUrl());
            }
        }

        driver.quit();
        return successList;
    }
}
