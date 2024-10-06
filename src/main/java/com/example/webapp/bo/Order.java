package com.example.webapp.bo;

import com.example.webapp.db.DbOrder;

import java.sql.SQLException;
import java.util.List;

public class Order {
    private int orderId;
    private int userId;
    private double totalAmount;

    // Constructor
    public Order(int orderId, int userId, double totalAmount) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalAmount = totalAmount;
    }
    public static int addOrder(Order order) throws SQLException {
        return DbOrder.addOrder(order);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
