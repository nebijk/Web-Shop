package com.example.webapp.db;

import com.example.webapp.bo.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends Product{

    public ProductDAO(int id, String name, double price, int stock) {
        super(id, name, price, stock);
    }

    private static Connection jdbcConnection;

    static {
        DBManager config = new DBManager();
        jdbcConnection = config.getConnection();  // Anslutning till databasen
        if (jdbcConnection == null) {
            System.out.println("Failed to establish a database connection in ProductDAO.");
        } else {
            System.out.println("Database connection established in ProductDAO.");
        }
    }

    public static Product getProductById(int id) throws SQLException {
        Product product = null;
        String query = "SELECT * FROM products WHERE id = ?";

        try (PreparedStatement stmt = jdbcConnection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                product = new ProductDAO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
            }
        }
        return product;
    }

    public static List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        try (Statement stmt = jdbcConnection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Product product = new ProductDAO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
                products.add(product);
            }
        }
        return products;
    }
    public static void updateStock(int productId, int newStock) throws SQLException {
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
            connection.setAutoCommit(false);  // Disable auto-commit

            String query = "UPDATE products SET stock = ? WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, newStock);
                stmt.setInt(2, productId);
                stmt.executeUpdate();
            }

            connection.commit();  // Commit transaction
            System.out.println("Stock updated successfully.");
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();  // Rollback transaction in case of error
                System.out.println("Transaction rolled back.");
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);  // Re-enable auto-commit
            }
        }
    }



}
