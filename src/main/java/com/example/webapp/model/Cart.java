package com.example.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;  // List to hold CartItem objects

    public Cart() {
        this.items = new ArrayList<>();  // Initialize the list
    }

    // Method to add a product to the cart
    public void addProduct(Product product, int quantity) {
        // Check if the product is already in the cart
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                // If the product is already in the cart, update the quantity
                item.setQuantity(item.getQuantity() + quantity);
                return;  // Exit method
            }
        }

        // If the product is not in the cart, add it as a new CartItem
        items.add(new CartItem(product, quantity));
    }

    // Method to remove a product from the cart
    public void removeProduct(Product product) {
        items.removeIf(item -> item.getProduct().getId() == product.getId());
    }

    // Method to get the total price of all items in the cart
    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();  // Add each item's total price to the overall total
        }
        return total;
    }

    // Method to get the list of CartItems in the cart
    public List<CartItem> getItems() {
        return items;
    }

    // Method to clear the cart
    public void clear() {
        items.clear();
    }


}
