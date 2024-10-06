package com.example.webapp.bo;

import com.example.webapp.db.DbOrder;
import com.example.webapp.db.DbOrderItem;
import com.example.webapp.db.DbProduct;
import com.example.webapp.ui.ProductInfo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderHandler {

    // Method to create a new order
    public static int createOrder(Order order) throws SQLException {
        return DbOrder.addOrder(order);
    }

    // Method to get all orders for a specific user
    public static List<Order> getOrdersByUserId(int userId) throws SQLException {
        return DbOrder.getOrdersByUserId(userId);
    }


    public static void placeOrder(int userId, Cart cart) throws SQLException {
        Order order = new Order(0, userId, cart.getTotalPrice());

        int orderId = DbOrder.addOrder(order);

        for (Map.Entry<ProductInfo, Integer> entry : cart.getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            OrderItem orderItem = new OrderItem(0, orderId, product.getId(), quantity);
            DbOrderItem.addOrderItem(orderItem);

            DbProduct.updateStock(product.getId(), product.getStock() - quantity);
        }
    }
}
