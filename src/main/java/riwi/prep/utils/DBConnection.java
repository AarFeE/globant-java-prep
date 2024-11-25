package riwi.prep.utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
 private static Connection connection;

 public static Connection getConnection() {
  if (connection == null) {
   try {
    Dotenv dotenv = Dotenv.load();

    String dbUrl = dotenv.get("DB_URL");
    String dbUsername = dotenv.get("DB_USERNAME");
    String dbPassword = dotenv.get("DB_PASSWORD");

    System.out.println("DB_URL: " + dbUrl + ", DB_USERNAME: " + dbUsername + ", DB_PASSWORD: " + dbPassword);

    connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    System.out.println("Database connection successful");
   } catch(SQLException e) {
    System.err.println("Failed to establish db connection: " + e.getMessage());
   }
  }
  return connection;
 }
}
