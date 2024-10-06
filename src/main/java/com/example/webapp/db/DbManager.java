package com.example.webapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {

    private static DbManager dbManager = null;  // Singleton instance

    // Private constructor to prevent external instantiation
    private DbManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Load MySQL driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Singleton instance getter
    public static synchronized DbManager getInstance() {
        if (dbManager == null) {
            dbManager = new DbManager();
        }
        return dbManager;
    }

    // Method to get a new connection for each operation
    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost/webshop";
        String user = "root";
        String password = "Dekemhare145.";  // You should use environment variables for security reasons
        return DriverManager.getConnection(url, user, password);
    }

    // Method to close a connection (optional for external use)
    public void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
