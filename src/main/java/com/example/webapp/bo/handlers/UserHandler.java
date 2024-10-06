package com.example.webapp.bo.handlers;

import com.example.webapp.bo.User;
import com.example.webapp.db.DbUser;

import java.sql.SQLException;

public class UserHandler {


    public static User getUserByName(String username) throws SQLException {
        return User.getUserByName(username);
    }
    public static User validateUser(String username, String password) {
        try {
            User user = DbUser.getUserByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
