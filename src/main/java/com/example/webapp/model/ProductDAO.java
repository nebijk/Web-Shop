package com.example.webapp.model;

import com.example.webapp.Config.DatabaseConfig;
import java.sql.*;
import java.util.Properties;

public class ProductDAO {
    private Connection jdbcConnection;

    public void connect() throws SQLException {
        DatabaseConfig config = new DatabaseConfig();
        Properties props = config.loadProperties();
        String jdbcURL = props.getProperty("db.url");
        String jdbcUsername = props.getProperty("db.username");
        String jdbcPassword = props.getProperty("db.password");

        jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // Exempel på CRUD-operationer:
    public void insertProduct(Product product) {
        // Logik för att infoga en produkt i databasen
    }

    public void updateProduct(Product product) {
        // Logik för att uppdatera en produkt
    }

    public void deleteProduct(int productId) {
        // Logik för att ta bort en produkt
    }
}