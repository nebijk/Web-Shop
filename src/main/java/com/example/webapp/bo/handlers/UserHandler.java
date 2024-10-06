package com.example.webapp.bo.handlers;

import com.example.webapp.bo.User;
import com.example.webapp.db.DbUser;
import com.example.webapp.ui.UserInfo;

import java.sql.SQLException;

public class UserHandler {

    public static UserInfo validateUser(String username, String password) {
        try {
            User user = User.getUserByName(username);
            if (user != null && user.getPassword().equals(password)) {
                return new UserInfo(user.getId(), user.getUsername(), "", user.getRole());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
