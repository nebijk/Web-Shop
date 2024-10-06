package com.example.webapp.bo;

import com.example.webapp.db.UserDAO;

import java.sql.SQLException;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private Cart cart;  // Each user has a cart

    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.cart = new Cart();  // Initialize the cart when the user is created
    }

    // Statiska metoder för att validera användare
    public static boolean validateLogin(String username, String password) {
        return UserDAO.validateUser(username, password);  // Anropar statisk metod från UserDAO
    }


        public static User getUserByName (String username) throws SQLException {
        return UserDAO.getUserByUsername(username);
    }

    // Getters and setters for User attributes
    public  int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Getter and setter for Cart
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
