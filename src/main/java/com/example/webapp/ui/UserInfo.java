package com.example.webapp.ui;

import com.example.webapp.bo.Cart;
import com.example.webapp.db.DbUser;

import java.sql.SQLException;

public class UserInfo {
    private int id;
    private String username;
    private String password;
    private String role;
    private Cart cart;

    public UserInfo(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.cart = new Cart();
    }
    public UserInfo(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cart = new Cart();
    }

    public static boolean validateLogin(String username, String password) {
        return DbUser.validateUser(username, password);
    }

    public static com.example.webapp.bo.User getUserByName (String username) throws SQLException {
        return DbUser.getUserByUsername(username);
    }


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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
