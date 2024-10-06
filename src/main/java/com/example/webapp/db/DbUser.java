package com.example.webapp.db;

import com.example.webapp.bo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUser extends User {

    private static Connection jdbcConnection;

    static {
        DbManager config = new DbManager();
        jdbcConnection = config.getConnection();
        if (jdbcConnection == null) {
            System.out.println("Failed to establish a database connection in UserDAO.");
        } else {
            System.out.println("Database connection established in UserDAO.");
        }
    }

    public DbUser(int id, String username, String password, String role) {
        super(id, username, password, role);
    }

    public static boolean validateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = jdbcConnection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static User getUserByUsername(String username) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = jdbcConnection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
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
