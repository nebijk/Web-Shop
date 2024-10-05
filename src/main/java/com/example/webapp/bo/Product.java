package com.example.webapp.bo;

import com.example.webapp.db.ProductDAO;
import java.sql.SQLException;
import java.util.List;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    // Standardkonstruktor
    public Product() {}

    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Statiska metoder som anropar ProductDAO
    public static Product getProductById(int id) throws SQLException {
        return ProductDAO.getProductById(id);
    }

    public static List<Product> getAllProducts() throws SQLException {
        return ProductDAO.getAllProducts();
    }

    // Getter och setter för id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter och setter för name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter och setter för price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Getter och setter för quantity
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
