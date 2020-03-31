package com.revature.bankingProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

  private static final String URL = System.getenv("url");
  private static final String USERNAME = System.getenv("username");
  private static final String PASSWORD = System.getenv("password");

  public static Connection connect() throws SQLException {
    return DriverManager.getConnection(URL, USERNAME, PASSWORD);
  }


}
