package com.yourapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil2 {

    private static final String URL = "jdbc:mysql://localhost:3306/aaa?useSSL=false&serverTimezone=Asia/Taipei&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "abc123";
	public static Object java;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 8.x 以上使用 cj 驅動
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
