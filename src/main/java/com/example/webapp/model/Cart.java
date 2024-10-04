package com.example.webapp.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> products;  // Map to hold Product objects and their quantities

    public Cart() {
        this.products = new HashMap<>();  // Initialize the map
    }

    // Method to add a product to the cart (increments quantity if already present)
    public void addProduct(Product product) {
        products.put(product, products.getOrDefault(product, 0) + 1);  // Increment quantity by 1
    }

    // Method to remove a product from the cart
    public void removeProduct(Product product) {
        products.remove(product);
    }

    // Method to get the total price of products in the cart
    public double getTotalPrice() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();  // Price * quantity
        }
        return total;
    }

    // Method to get the map of products and their quantities in the cart
    public Map<Product, Integer> getProducts() {
        return products;  // Return the map of products and quantities
    }

    // Method to clear the cart
    public void clear() {
        products.clear();
    }
}
