package com.example.webapp.bo.handlers;

import com.example.webapp.bo.Cart;
import com.example.webapp.bo.Order;
import com.example.webapp.bo.OrderItem;
import com.example.webapp.bo.Product;
import com.example.webapp.db.DbOrder;
import com.example.webapp.db.DbOrderItem;
import com.example.webapp.db.DbProduct;
import com.example.webapp.ui.ProductInfo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderHandler {
    public static void placeOrder(int userId, Cart cart) throws SQLException {
        Order order = new Order(0, userId, cart.getTotalPrice());

        int orderId = Order.addOrder(order);

        for (Map.Entry<ProductInfo, Integer> entry : cart.getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            OrderItem orderItem = new OrderItem(0, orderId, product.getId(), quantity);
            DbOrderItem.addOrderItem(orderItem);

            DbProduct.updateStock(product.getId(), product.getStock() - quantity);
        }
    }
}
