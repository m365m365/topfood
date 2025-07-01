package com.test;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class MailTest {
    public static void main(String[] args) {
        final String from = "mm365day@gmail.com";
        final String to = "o365day@yahoo.com.tw";
        final String password = "lfbp joyb zirn rcpd"; // 你的 Gmail 應用程式密碼

        // 設定 Gmail SMTP 伺服器
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // 🔒 新增：略過憑證驗證（僅限測試使用）
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.ssl.checkserveridentity", "false");

        // 登入驗證
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // 建立郵件內容
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from, "測試發信者"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Java Mail 測試");
            message.setText("這是使用 JavaMail API 傳送的測試信件！");

            // 寄出
            Transport.send(message);

            System.out.println("✅ 郵件已成功寄出！");
        } catch (Exception e) {
            System.err.println("❌ 郵件傳送失敗！");
            e.printStackTrace();
        }
    }
}
