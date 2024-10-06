package com.example.webapp.bo;

import com.example.webapp.db.OrderDAO;
import com.example.webapp.db.OrderItemDAO;
import com.example.webapp.db.ProductDAO;
import com.example.webapp.ui.ProductInfo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderHandler {

    // Method to create a new order
    public static int createOrder(Order order) throws SQLException {
        return OrderDAO.addOrder(order);
    }

    // Method to get all orders for a specific user
    public static List<Order> getOrdersByUserId(int userId) throws SQLException {
        return OrderDAO.getOrdersByUserId(userId);
    }


    public static void placeOrder(int userId, Cart cart) throws SQLException {
        // Create an order
        Order order = new Order(0, userId, cart.getTotalPrice());

        // Use OrderDAO to add the order and get its ID
        int orderId = OrderDAO.addOrder(order);
        System.out.println("Placing order for User ID: " + userId);
        System.out.println("Cart Total Price: " + cart.getTotalPrice());
        // Loop through the cart's products and add them as order items
        for (Map.Entry<ProductInfo, Integer> entry : cart.getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            // Create and add order item
            OrderItem orderItem = new OrderItem(0, orderId, product.getId(), quantity);
            OrderItemDAO.addOrderItem(orderItem);

            // Update product stock
            ProductDAO.updateStock(product.getId(), product.getStock() - quantity);
        }

    }


}
