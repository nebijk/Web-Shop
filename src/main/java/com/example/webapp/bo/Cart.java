package com.example.webapp.bo;

import com.example.webapp.ui.ProductInfo;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<ProductInfo, Integer> products;  // Map to hold Product objects and their quantities

    public Cart() {
        this.products = new HashMap<>();  // Initialize the map
    }

    // Method to add a product to the cart (increments quantity if already present)
    public void addProduct(ProductInfo product, int quantity) {
        if (products.containsKey(product)) {
            int currentQuantity = products.get(product);
            products.put(product, currentQuantity + quantity);
        } else {
            products.put(product, quantity);
        }
    }

    // Method to remove a product from the cart
    public void removeProduct(Product product) {
        products.remove(product);
    }

    // Method to get the total price of products in the cart
    public double getTotalPrice() {
        double total = 0;
        for (Map.Entry<ProductInfo, Integer> entry : products.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();  // Price * quantity
        }
        return total;
    }

    // Method to get the map of products and their quantities in the cart
    public Map<ProductInfo, Integer> getProducts() {
        return products;  // Return the map of products and quantities
    }

    // Method to clear the cart
    public void clear() {
        products.clear();
    }
}
