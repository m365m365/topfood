package com.yourapp.util;

import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class MailUtil {
    public static void sendVerificationEmail(String toEmail, String name, String verifyLink) {
        final String from = "mm365day@gmail.com"; // 你的 Gmail
        final String password = "lfbp joyb zirn rcpd"; // 從 Google App 密碼產生

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // 測試用 - 跳過 TLS 憑證驗證（若需要）
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.ssl.checkserveridentity", "false");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from, "TopFood.com 台北美食網"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("會員註冊確認信");

            String content = "<h3>親愛的 " + name + " 您好，</h3>"
                    + "<p>請點擊以下連結以完成註冊：</p>"
                    + "<p><a href='" + verifyLink + "'>立即完成註冊</a></p>"
                    + "<p>連結 48 小時內有效</p>";

            message.setContent(content, "text/html; charset=utf-8");
            Transport.send(message);

            System.out.println("✅ 驗證信已成功寄出");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
