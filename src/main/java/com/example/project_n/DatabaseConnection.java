package com.example.project_n;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            String url = "jdbc:oracle:thin:@//localhost:1521/yourdatabase";
            String username = "yourUsername";
            String password = "yourPassword";

            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}
