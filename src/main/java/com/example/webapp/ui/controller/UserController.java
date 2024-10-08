package com.example.webapp.ui.controller;

import com.example.webapp.bo.handlers.UserHandler;

import com.example.webapp.ui.UserInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserInfo user = UserHandler.validateUser(username, password);

        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
        session.setAttribute("username", username);

        response.sendRedirect("product-list");
    }
}
