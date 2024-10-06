package com.example.webapp.bo;

import com.example.webapp.db.UserDAO;

import java.sql.SQLException;

public class UserHandler {


    public static User getUserByName(String username) throws SQLException {
        return User.getUserByName(username);  // Call DAO to fetch user from database
    }
    public static User validateUser(String username, String password) {
        try {
            User user = UserDAO.getUserByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                return user;  // Return the User object if login is successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Return null if login fails
    }
}
