package com.example.webapp.db;

import com.example.webapp.bo.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbOrderItem extends OrderItem {
    private static Connection jdbcConnection;

    static {
        DbManager config = new DbManager();
        jdbcConnection = config.getConnection();
        if (jdbcConnection == null) {
            System.out.println("Failed to establish a database connection in OrderItemDAO.");
        }
    }

    public DbOrderItem(int orderItemId, int orderId, int productId, int quantity) {
        super(orderItemId, orderId, productId, quantity);
    }

    // Method to add an order item
    public static void addOrderItem(OrderItem orderItem) throws SQLException {
        String query = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = jdbcConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, orderItem.getOrderId());
            stmt.setInt(2, orderItem.getProductId());
            stmt.setInt(3, orderItem.getQuantity());
            stmt.executeUpdate();
        }
    }

    // Method to get all order items by order ID
    public static List<OrderItem> getOrderItemsByOrderId(int orderId) throws SQLException {
        List<OrderItem> orderItems = new ArrayList<>();
        String query = "SELECT * FROM order_items WHERE order_id = ?";
        try (PreparedStatement stmt = jdbcConnection.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrderItem orderItem = new OrderItem(
                        rs.getInt("order_item_id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity")
                );
                orderItems.add(orderItem);
            }
        }
        return orderItems;
    }
}