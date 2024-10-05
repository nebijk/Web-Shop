package com.example.webapp.bo;

import com.example.webapp.db.UserDAO;

public class UserService {
    private UserDAO userDAO = new UserDAO();


    public boolean validateLogin(String username, String password) {
        return userDAO.validateUser(username, password);
    }
}
