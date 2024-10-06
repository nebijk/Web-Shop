package com.example.webapp.bo;

import com.example.webapp.db.OrderItemDAO;

import java.sql.SQLException;
import java.util.List;

public class OrderItemHandler {

    // Method to add order items to an order
    public static void addOrderItems(List<OrderItem> orderItems) throws SQLException {
        for (OrderItem item : orderItems) {
            OrderItemDAO.addOrderItem(item);
        }
    }

    // Method to get order items by order ID
    public static List<OrderItem> getOrderItemsByOrderId(int orderId) throws SQLException {
        return OrderItemDAO.getOrderItemsByOrderId(orderId);
    }
}
