package com.example.webapp.bo;

import com.example.webapp.db.DbOrderProduct;

import java.sql.SQLException;

public class OrderProduct {
    private int orderProductId;
    private int orderId;
    private int productId;
    private int quantity;

    // Constructor
    public OrderProduct(int orderProductId, int orderId, int productId, int quantity) {
        this.orderProductId = orderProductId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }
    public static void addOrderProduct(OrderProduct orderProduct) throws SQLException {
        DbOrderProduct.addOrderProducts(orderProduct);
    }

    // Getters and setters
    public int getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(int orderProductId) {
        this.orderProductId = orderProductId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}