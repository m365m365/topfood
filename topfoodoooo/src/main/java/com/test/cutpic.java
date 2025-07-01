package com.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import org.apache.commons.io.FileUtils;

public class cutpic {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/search?q=鼎泰豐");

        Thread.sleep(3000);  // 等待網頁載入

        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("webpage_screenshot.png"));

        driver.quit();
        System.out.println("✅ 截圖完成！");
    }
}
