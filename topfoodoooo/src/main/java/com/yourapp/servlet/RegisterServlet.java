package com.yourapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yourapp.util.DBUtil;
import com.yourapp.util.MailUtil;

import com.yourapp.util.UrlUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String token = UUID.randomUUID().toString();

        try (Connection conn = DBUtil.getConnection()) {
            // 🔍 先檢查 email 是否已存在
            String checkSql = "SELECT COUNT(*) FROM members WHERE email = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            checkStmt.close();

            if (count > 0) {
                // ❌ 重複註冊，導向提示頁面
                response.sendRedirect("register_duplicate.jsp");
                return;
            }

            // ✅ 寫入新資料
            String insertSql = "INSERT INTO members (username, email, password, member, token, token_expiry) " +
                               "VALUES (?, ?, ?, 'n', ?, NOW() + INTERVAL 48 HOUR)";
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            insertStmt.setString(1, name);
            insertStmt.setString(2, email);
            insertStmt.setString(3, password);
            insertStmt.setString(4, token);
            insertStmt.executeUpdate();
            insertStmt.close();

         // 呼叫工具方法動態生成驗證連結
            String verifyLink = UrlUtil.buildVerificationLink(request, token);

            // 發送驗證信
            MailUtil.sendVerificationEmail(email, name, verifyLink);

            response.sendRedirect("register_success.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("register_error.jsp");
        }
    }
}




