package dao;

import java.sql.*;

public class DBConnection {
 public static Connection getConnection() {
     Connection connection = null;
     try {
         Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
         connection = DriverManager.getConnection("jdbc:derby:D:\\2831825\\MyDB;create=true");
     } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
     }
     return connection;
 }
}
