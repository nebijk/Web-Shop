package com.example.webapp.model;

import com.example.webapp.Config.DatabaseConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class ProductDAO {

    private Connection jdbcConnection;

    public ProductDAO() {
        DatabaseConfig config = new DatabaseConfig();
        this.jdbcConnection = config.getConnection();  // Använd hårdkodad anslutning från DatabaseConfig
        if (jdbcConnection == null) {
            System.out.println("Failed to establish a database connection in ProductDAO.");
        } else {
            System.out.println("Database connection established in ProductDAO.");
        }
    }

    // Metod för att hämta alla produkter från databasen
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        try (Statement stmt = jdbcConnection.createStatement()) {  // Här används jdbcConnection
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
                products.add(product);
            }

        }
        System.out.println("Number of products fetched: " + products.size());
        return products;
    }
}


