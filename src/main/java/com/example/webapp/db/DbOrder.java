package com.example.webapp.db;

import com.example.webapp.bo.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbOrder extends Order {

    protected DbOrder(int orderId, int userId, double totalAmount) {
        super(orderId, userId, totalAmount);
    }

    // Method to add an order
    public static int addOrder(Order order) throws SQLException {
        String query = "INSERT INTO orders (user_id, total_amount) VALUES (?, ?)";

        // Dynamically get connection for each operation
        try (Connection connection = DbManager.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            // Set query parameters
            stmt.setInt(1, order.getUserId());
            stmt.setDouble(2, order.getTotalAmount());
            stmt.executeUpdate();

            // Retrieve the generated order ID
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); // Return the generated order ID
            } else {
                throw new SQLException("Failed to retrieve order ID.");
            }
        }
    }

    // Method to retrieve all orders
    public static List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";

        // Dynamically get connection for each operation
        try (Connection connection = DbManager.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Iterate through the result set and create Order objects
            while (rs.next()) {
                Order order = new DbOrder(
                        rs.getInt("order_id"),
                        rs.getInt("user_id"),
                        rs.getDouble("total_amount")
                );
                orders.add(order);
            }
        }

        return orders;
    }
}
