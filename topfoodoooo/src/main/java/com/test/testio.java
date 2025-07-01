package com.test;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileWriter;

public class testio {
    public static void main(String[] args) throws Exception {
        File src = new File("source.txt");
        
        // 如果檔案不存在，就先建立並寫入內容
        if (!src.exists()) {
            FileWriter writer = new FileWriter(src);
            writer.write("這是自動建立的檔案內容");
            writer.close();
            System.out.println("🔧 建立 source.txt 完成");
        }

        File dest = new File("copy.txt");
        FileUtils.copyFile(src, dest);
        System.out.println("✅ 複製成功！");
    }
}
