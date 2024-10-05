package com.example.webapp.bo;

public class UserHandler {

    public static boolean validateUser(String username, String password) {
        return User.validateLogin(username, password);  // Anropar statisk metod i User
    }
}
