package com.example.webapp.db;

import com.example.webapp.bo.OrderProduct;

import java.sql.*;

public class DbOrderProduct extends OrderProduct {

    public DbOrderProduct(int orderProductId, int orderId, int productId, int quantity) {
        super(orderProductId, orderId, productId, quantity);
    }

    // Method to add an order item
    public static void addOrderProducts(OrderProduct orderProduct) throws SQLException {
        String query = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";

        // Dynamically get connection for each operation
        try (Connection connection = DbManager.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            // Set query parameters
            stmt.setInt(1, orderProduct.getOrderId());
            stmt.setInt(2, orderProduct.getProductId());
            stmt.setInt(3, orderProduct.getQuantity());

            // Execute the query to insert the order product
            stmt.executeUpdate();
        }
    }
}
