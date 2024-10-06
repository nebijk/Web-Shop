package com.example.webapp.bo.handlers;

import com.example.webapp.bo.Cart;
import com.example.webapp.bo.Order;
import com.example.webapp.bo.OrderProduct;
import com.example.webapp.bo.Product;
import com.example.webapp.ui.ProductInfo;

import java.sql.SQLException;
import java.util.Map;

public class OrderHandler {
    public static void placeOrder(int userId, Cart cart) throws SQLException {
        Order order = new Order(0, userId, cart.getTotalPrice());

        int orderId = Order.addOrder(order);

        for (Map.Entry<ProductInfo, Integer> entry : cart.getProducts().entrySet()) {
            ProductInfo product = entry.getKey();
            int quantity = entry.getValue();

            OrderProduct orderProduct = new OrderProduct(0, orderId, product.getId(), quantity);
           OrderProduct.addOrderProduct(orderProduct);

            Product.updateStock(product.getId(), product.getStock() - quantity);
        }
    }
}
