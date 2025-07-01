package com.yourapp.servlet;

import com.yourapp.dao.UserDAO;
import com.yourapp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet {
  private UserDAO userDAO = new UserDAO();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");

    User user = userDAO.findByUsernameAndPassword(username, password);
    if (user != null) {
      // 登入成功
      HttpSession session = req.getSession();
      session.setAttribute("user", user);
      resp.sendRedirect("index.jsp");
    } else {
      // 登入失敗
      req.setAttribute("error", "帳號或密碼錯誤");
      req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
  }
}
