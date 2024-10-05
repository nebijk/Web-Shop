package com.example.webapp.bo;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> products;  // Map to hold Product objects and their quantities

    public Cart() {
        this.products = new HashMap<>();  // Initialize the map
    }

    // Method to add a product to the cart (increments quantity if already present)
    public void addProduct(Product product) {
        // Check if the product is already in the cart and update its quantity
        if (products.containsKey(product)) {
            int currentQuantity = products.get(product);
            products.put(product, currentQuantity + 1);  // Increment the quantity
        } else {
            products.put(product, 1);  // Add the product with a quantity of 1 if it's not already in the cart
        }
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
