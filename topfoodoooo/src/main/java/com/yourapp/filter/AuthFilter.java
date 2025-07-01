package com.yourapp.filter;

import java.io.IOException;
import java.sql.*;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yourapp.util.DBUtil;
@WebFilter(urlPatterns = {"/member.jsp"})
public class AuthFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);
        String user = null;

        if (session != null) {
            user = (String) session.getAttribute("user");
        }

        // 如果 session 有 user，直接放行
        if (user != null) {
            chain.doFilter(req, res);
            return;
        }

        // 如果沒登入，檢查 rememberToken cookie
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("rememberToken".equals(c.getName())) {
                    token = c.getValue();
                    break;
                }
            }
        }

        if (token != null) {
            try (Connection conn = DBUtil.getConnection()) {
                PreparedStatement ps = conn.prepareStatement("SELECT username FROM members WHERE remember_token = ?");
                ps.setString(1, token);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    // 自動登入，建立 session
                    user = rs.getString("username");
                    session = request.getSession(true);
                    session.setAttribute("user", user);
                    chain.doFilter(req, res); // 放行
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // 都沒有驗證通過 → 轉向登入頁
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}
