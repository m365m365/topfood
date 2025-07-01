package com.yourapp.util;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ChromeDriverUtil {

    public static void setupChromeDriver() {
        String os = System.getProperty("os.name").toLowerCase();
        String driverFileName = os.contains("win") ? "chromedriver.exe" : "chromedriver";

        try {
            // 嘗試從 resources/drivers/ 讀取對應的 chromedriver
            InputStream input = ChromeDriverUtil.class.getClassLoader().getResourceAsStream("drivers/" + driverFileName);
            if (input == null) {
                throw new RuntimeException("找不到 chromedriver: drivers/" + driverFileName);
            }

            // 複製到系統暫存目錄
            File tempDriver = new File(System.getProperty("java.io.tmpdir"), driverFileName);
            Files.copy(input, tempDriver.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // 非 Windows 需要給予執行權限
            if (!os.contains("win")) {
                tempDriver.setExecutable(true);
            }

            // 設定 WebDriver 屬性
            System.setProperty("webdriver.chrome.driver", tempDriver.getAbsolutePath());

            System.out.println("✔ 成功設定 ChromeDriver 路徑: " + tempDriver.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("載入 ChromeDriver 失敗: " + e.getMessage(), e);
        }
        URL testUrl = ChromeDriverUtil.class.getClassLoader().getResource("drivers/chromedriver.exe");
        System.out.println("✔ 驗證資源路徑: " + (testUrl != null ? testUrl.getPath() : "❌ 找不到 chromedriver.exe"));

        
        
    }
}
