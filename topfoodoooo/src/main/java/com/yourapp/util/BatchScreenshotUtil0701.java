package com.yourapp.util;

import com.yourapp.info.AiRestaurantInfo;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class BatchScreenshotUtil0701 {

    // 原始版本：無 callback
    public static List<AiRestaurantInfo> captureScreenshots(List<AiRestaurantInfo> list, String screenshotDir) {
        return captureScreenshots(list, screenshotDir, null);
    }

    // 擴充版本：支援 callback
    public static List<AiRestaurantInfo> captureScreenshots(
            List<AiRestaurantInfo> list,
            String screenshotDir,
            BiConsumer<AiRestaurantInfo, Integer> onSuccess
    ) {
        // ✅ 設定 ChromeDriver
        ChromeDriverUtil.setupChromeDriver();

        // ✅ Headless 瀏覽器選項
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--disable-gpu", "--window-size=1280,800");

        // ✅ 建立服務，手動指定 chromedriver 路徑（避免被 Selenium 忽略）
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(System.getProperty("webdriver.chrome.driver")))
                .usingAnyFreePort()
                .build();

        WebDriver driver = new ChromeDriver(service, options);

        List<AiRestaurantInfo> successList = new ArrayList<>();

        // ✅ 建立 screenshots 資料夾
        File dir = new File(screenshotDir);
        if (!dir.exists()) {
            dir.mkdirs();
            System.out.println("📂 建立 screenshots 目錄: " + dir.getAbsolutePath());
        }

        // ✅ 清除舊圖檔
        try {
            FileUtils.cleanDirectory(dir);
        } catch (IOException e) {
            System.err.println("❌ 無法清除 screenshots 目錄: " + e.getMessage());
        }

        int count = 0;
        for (AiRestaurantInfo info : list) {
            if (count >= 1) break;

            String url = info.getUrl();
            if (isBlacklistedUrl(url)) {
                System.out.println("❌ 黑名單跳過: " + url);
                continue;
            }

            try {
                driver.get(url);
                Thread.sleep(3000);

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

                if (onSuccess != null) {
                    onSuccess.accept(info, count);
                }

                count++;
            } catch (Exception e) {
                System.out.println("❌ 截圖失敗: " + url + "（" + e.getMessage() + "）");
            }
        }

        driver.quit();
        return successList;
    }

    private static boolean isBlacklistedUrl(String url) {
        if (url == null) return true;
        url = url.toLowerCase();
        return url.contains("facebook.com") ||
               url.contains("instagram.com") ||
               url.contains("robuchon.com.tw") ||
               url.startsWith("http://") ||
               url.contains("expired-cert") ||
               url.contains("gov.tw/login");
    }

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
