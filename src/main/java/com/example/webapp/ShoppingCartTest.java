package com.example.webapp;

import com.example.webapp.model.Product;
import com.example.webapp.model.User;

public class ShoppingCartTest {
    public static void main(String[] args) {
        // Create a new user
        User user = new User(1, "john_doe", "password123", "customer");

        // Create some products
        Product product1 = new Product(1, "Laptop", 1200.99, 5);
        Product product2 = new Product(2, "Phone", 799.49, 10);

        // Add products to the user's cart
        user.getCart().addProduct(product1);
        user.getCart().addProduct(product2);

        // Display cart contents
        System.out.println("User's cart contains:");
        for (Product product : user.getCart().getProducts()) {
            System.out.println(product.getName() + " - Price: $" + product.getPrice());
        }

        // Calculate the total price of the cart
        System.out.println("Total price: $" + user.getCart().getTotalPrice());
    }
}
