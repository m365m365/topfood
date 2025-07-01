package com.yourapp.servlet;

import java.io.IOException;
import java.sql.*;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yourapp.util.DBUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("into loginservlet");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember"); // 是否勾選記住我

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT * FROM members WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // 登入成功：建立 session
                HttpSession session = request.getSession();
                session.setAttribute("user", username);

                if ("on".equals(remember)) {
                    // 勾選了記住我，產生 token
                    String token = UUID.randomUUID().toString();
                    System.out.println("SET remember_token");

                    // 存入資料庫
                    PreparedStatement ps2 = conn.prepareStatement("UPDATE members SET remember_token = ? WHERE username = ?");
                    ps2.setString(1, token);
                    ps2.setString(2, username);
                    ps2.executeUpdate();

                    // 設定 cookie（例如 7 天有效）
                    Cookie cookie = new Cookie("rememberToken", token);
                    cookie.setMaxAge(7 * 24 * 60 * 60);
                    cookie.setHttpOnly(true);
                    response.addCookie(cookie);
                }

                response.sendRedirect("member.jsp");
            } else {
                request.setAttribute("error", "帳號或密碼錯誤");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("DB error: " + e.getMessage());
        }
    }
}
