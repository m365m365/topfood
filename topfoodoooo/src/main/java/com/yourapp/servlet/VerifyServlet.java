package com.yourapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yourapp.util.DBUtil;

/**
 * Servlet implementation class VerifyServlet
 */
@WebServlet("/verify")
public class VerifyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        String token = request.getParameter("token");

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "UPDATE members SET member='y', token=NULL, token_expiry=NULL " +
                         "WHERE token=? AND token_expiry > NOW()";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, token);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                response.sendRedirect("verify_success.jsp");
            } else {
                response.sendRedirect("verify_failed.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("verify_failed.jsp");
        }
    }
}

