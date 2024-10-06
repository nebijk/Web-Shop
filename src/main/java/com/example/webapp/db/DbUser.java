package com.example.webapp.db;

import com.example.webapp.bo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUser extends User {

    protected DbUser(int id, String username, String password, String role) {
        super(id, username, password, role);
    }

    // Method to validate user credentials
    public static boolean validateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        // Dynamically get connection each time a query is executed
        try (Connection connection = DbManager.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Set query parameters
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Execute the query and check if any result matches the username and password
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get user by username
    public static User getUserByUsername(String username) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ?";

        // Dynamically get connection for each operation
        try (Connection connection = DbManager.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Set query parameter
            stmt.setString(1, username);

            // Execute the query and map the result to a User object
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new DbUser(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            } else {
                return null;
            }
        }
    }
}
