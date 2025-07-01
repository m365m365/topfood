package com.yourapp.util;

import com.yourapp.info.AiRestaurantInfo;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BatchScreenshotUtil3 {

    public static List<AiRestaurantInfo> captureScreenshots(List<AiRestaurantInfo> list, String screenshotDir) {
        List<AiRestaurantInfo> successList = new ArrayList<>();

        // 1. 建立 screenshots 資料夾
        File dir = new File(screenshotDir);
        if (!dir.exists()) {
            dir.mkdirs();
            System.out.println("📂 建立 screenshots 目錄: " + dir.exists());
        }

        // 2. 清除舊的截圖
        try {
            FileUtils.cleanDirectory(dir);
        } catch (IOException e) {
            System.err.println("❌ 無法清除 screenshots 目錄: " + e.getMessage());
        }

        // 3. Selenium 設定（headless）
        System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--disable-gpu", "--window-size=1280,800");

        WebDriver driver = new ChromeDriver(options);

        int count = 0;
        for (AiRestaurantInfo info : list) {
            if (count >= 6) break;

            String url = info.getUrl();
            if (isBlacklistedUrl(url)) {
                System.out.println("❌ 黑名單跳過: " + url);
                continue;
            }

            try {
                driver.get(url);
                Thread.sleep(3000); // 等載入

                if (isSuspiciousPage(driver)) {
                    System.out.println("❌ 可疑網站略過（內容異常）: " + url);
                    continue;
                }

                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String fileName = "restaurant_" + (count + 1) + ".png";
                File dest = new File(dir, fileName);
                FileUtils.copyFile(src, dest);

                info.setPhotoUrl("screenshots/" + fileName);
                successList.add(info);
                System.out.println("✅ 截圖完成: " + info.getName());
                count++;
            } catch (Exception e) {
                System.out.println("❌ 截圖失敗: " + url + "（" + e.getMessage() + "）");
            }
        }

        driver.quit();
        return successList;
    }

    // ✅ 判斷是否黑名單網址
    private static boolean isBlacklistedUrl(String url) {
        if (url == null) return true;
        url = url.toLowerCase();
        return url.contains("facebook.com") ||
               url.contains("instagram.com") ||
               url.contains("robuchon.com.tw") ||
               url.startsWith("http://") || // 可依需求移除這條
               url.contains("expired-cert") ||
               url.contains("gov.tw/login");
    }

    // ✅ 判斷是否異常錯誤頁面（例如 SSL 錯誤、憑證錯誤、404 等）
    private static boolean isSuspiciousPage(WebDriver driver) {
        String title = driver.getTitle().toLowerCase();
        String pageSource = driver.getPageSource().toLowerCase();

        return title.contains("不安全") ||
               title.contains("error") ||
               title.contains("404") ||
               pageSource.contains("您的連線不是私人連線") ||
               pageSource.contains("net::err_cert") ||
               pageSource.contains("page not found") ||
               pageSource.contains("connection is not private") ||
               pageSource.contains("安全性憑證") ||
               pageSource.contains("attackers might be trying");
    }
}
