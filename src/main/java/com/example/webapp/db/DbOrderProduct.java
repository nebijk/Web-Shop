package com.example.webapp.db;

import com.example.webapp.bo.OrderProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbOrderProduct extends OrderProduct {
    private static Connection jdbcConnection;

    static {
        DbManager config = new DbManager();
        jdbcConnection = config.getConnection();
        if (jdbcConnection == null) {
            System.out.println("Failed to establish a database connection in OrderItemDAO.");
        }
    }

    public DbOrderProduct(int orderProductId, int orderId, int productId, int quantity) {
        super(orderProductId, orderId, productId, quantity);
    }

    // Method to add an order item
    public static void addOrderProducts(OrderProduct orderProduct) throws SQLException {
        String query = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = jdbcConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, orderProduct.getOrderId());
            stmt.setInt(2, orderProduct.getProductId());
            stmt.setInt(3, orderProduct.getQuantity());
            stmt.executeUpdate();
        }
    }
}