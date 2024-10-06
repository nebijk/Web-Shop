package com.example.webapp.db;

import com.example.webapp.bo.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbOrder extends Order {
    private static Connection jdbcConnection;

    static {
        DbManager config = new DbManager();
        jdbcConnection = config.getConnection();
        if (jdbcConnection == null) {
            System.out.println("Failed to establish a database connection in OrderDAO.");
        }
    }

    public DbOrder(int orderId, int userId, double totalAmount) {
        super(orderId, userId, totalAmount);
    }

    // Method to add an order
    public static int addOrder(Order order) throws SQLException {
        String query = "INSERT INTO orders (user_id, total_amount) VALUES (?, ?)";
        try (PreparedStatement stmt = jdbcConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
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

    public static List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";
        try (Statement stmt = jdbcConnection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Order order = new Order(
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
