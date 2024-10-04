package com.example.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;  // List to hold Product objects

    public Cart() {
        this.products = new ArrayList<>();  // Initialize the list
    }

    // Method to add a product to the cart
    public void addProduct(Product product) {
        products.add(product);
    }

    // Method to remove a product from the cart
    public void removeProduct(Product product) {
        products.remove(product);
    }

    // Method to get the total price of products in the cart
    public double getTotalPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();  // Add each product's price to the total
        }
        return total;
    }

    // Method to get the list of products in the cart
    public List<Product> getProducts() {
        return products;
    }

    // Method to clear the cart
    public void clear() {
    }
    }
