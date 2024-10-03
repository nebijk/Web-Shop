package com.example.webapp.model;

import com.example.webapp.Config.DatabaseConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private Connection jdbcConnection;

    public UserDAO() {
        DatabaseConfig config = new DatabaseConfig();
        this.jdbcConnection = config.getConnection();
    }

    // Metod för att kontrollera användaruppgifterna
    public boolean validateUser(String username, String password) {
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
