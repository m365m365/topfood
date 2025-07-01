package com.yourapp.dao;

import com.yourapp.model.User;
import com.yourapp.util.DBUtil;

import java.sql.*;

public class UserDAO {
  public User findByUsernameAndPassword(String user, String pass) {
    try (Connection conn = DBUtil.getConnection();
         PreparedStatement ps = conn.prepareStatement(
           "SELECT id, username FROM users WHERE username=? AND password=SHA2(?,256)")) {
      ps.setString(1, user);
      ps.setString(2, pass);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          User u = new User();
          u.setId(rs.getInt("id"));
          u.setUsername(rs.getString("username"));
          return u;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
