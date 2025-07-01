<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*"%>
<%
    session = request.getSession(false); // 不需再次宣告 HttpSession
    if (session != null) {
        session.invalidate();
    }

    // 清除 Cookie
    Cookie cookie = new Cookie("rememberToken", "");
    cookie.setMaxAge(0); // 立即過期
    response.addCookie(cookie);

    response.sendRedirect("login.jsp");
%>
