package com.example.webapp.bo;

import com.example.webapp.db.DbProduct;
import java.sql.SQLException;
import java.util.List;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    public Product() {}

    protected Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public static Product getProductById(int id) throws SQLException {
        return DbProduct.getProductById(id);
    }

    public static List<Product> getAllProducts() throws SQLException {
        return DbProduct.getAllProducts();
    }

    public static void updateStock(int id, int stock) throws SQLException {
        DbProduct.updateStock(id, stock);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
