package com.example.webapp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private static Connection jdbcConnection;

    static {
        DBManager config = new DBManager();
        jdbcConnection = config.getConnection();  // Anslutning till databasen
        if (jdbcConnection == null) {
            System.out.println("Failed to establish a database connection in UserDAO.");
        } else {
            System.out.println("Database connection established in UserDAO.");
        }
    }

    public static boolean validateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = jdbcConnection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();  // Om en rad returneras betyder det att användaren finns och uppgifterna stämmer
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
