package com.example.webapp.model;

import com.example.webapp.model.UserDAO;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public boolean validateLogin(String username, String password) {
        return userDAO.validateUser(username, password);
    }
}
