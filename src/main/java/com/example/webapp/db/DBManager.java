package com.example.webapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    // Singleton instance
    private static DBManager dbManager = null;
    private Connection connection = null;

    // Private constructor to prevent instantiation
    DBManager() {
        String user = "root";
        String pwd = "Coola145!";
        String server = "jdbc:mysql://127.0.0.1/webshop";
        System.out.println("hej");
        try {
            System.out.println("Connecting to database...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(server, user, pwd);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DBManager getInstance() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public static Connection getConnection() {
        return getInstance().connection;
    }


    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}