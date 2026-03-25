package br.com.esteves.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
  private final String url = "jdbc:mysql://localhost:3306/estoque_db";
  private final String user = "root";
  private final String pass = "";

  public Connection getConnection() {
    try {
      return DriverManager.getConnection(url, user, pass);
    } catch (SQLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }
}
