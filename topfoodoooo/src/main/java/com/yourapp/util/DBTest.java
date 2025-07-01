package com.yourapp.util;

import java.sql.*;

public class DBTest {
    public static void main(String[] args) {
        try (Connection conn = DBUtil.getConnection()) {
            System.out.println("✅ 資料庫連線成功！");

            String sql = "SELECT id, username, email, member FROM members";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("username");
                String email = rs.getString("email");
                String member = rs.getString("member");

                System.out.println("ID: " + id + ", 姓名: " + name + ", Email: " + email + ", 狀態: " + member);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("❌ 資料庫連線失敗！");
            e.printStackTrace();
        }
    }
}




    
    

