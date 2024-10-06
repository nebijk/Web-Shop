package com.example.webapp.db;

import com.example.webapp.bo.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbProduct extends Product {

    public DbProduct(int id, String name, double price, int stock) {
        super(id, name, price, stock);
    }

    // Method to get a product by its ID
    public static Product getProductById(int id) throws SQLException {
        Product product = null;
        String query = "SELECT * FROM products WHERE id = ?";

        // Dynamically get connection for each operation
        try (Connection connection = DbManager.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                product = new DbProduct(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
            }
        }

        return product;
    }

    // Method to get all products
    public static List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";

        // Dynamically get connection for each operation
        try (Connection connection = DbManager.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Product product = new DbProduct(
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

    // Method to update stock for a product
    public static void updateStock(int productId, int newStock) throws SQLException {
        String query = "UPDATE products SET stock = ? WHERE id = ?";

        // Dynamically get connection for each operation
        try (Connection connection = DbManager.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            connection.setAutoCommit(false);  // Disable auto-commit

            stmt.setInt(1, newStock);
            stmt.setInt(2, productId);
            stmt.executeUpdate();

            connection.commit();  // Commit the transaction
            System.out.println("Stock updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
