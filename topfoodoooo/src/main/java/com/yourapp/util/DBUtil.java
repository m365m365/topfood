package com.yourapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtil {

    // 1) jdbc:mysql://<Endpoint>:3306/<DB_NAME>
    // 2) useSSL=false （測試用；正式環境請開啟並配置憑證）
    // 3) serverTimezone 設成你要的時區
    private static final String URL =
        "jdbc:mysql://"
      + "database-1.c3owigyicdva.ap-northeast-2.rds.amazonaws.com"
      + ":3306/"
      + "topfood"
      + "?useSSL=false"
      + "&serverTimezone=Asia/Taipei"
      + "&characterEncoding=utf8";

    // Lightsail 上的 Master username
    private static final String USER = "admin";
    // Lightsail 上你設定的 Master password
    private static final String PASS = "pa$$1234";
    public static Object java;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 8.x 以上使用 cj 驅動
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
