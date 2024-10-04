package com.example.webapp.controller;

import com.example.webapp.model.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {


    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Skicka användaren till inloggningssidan om GET-anrop görs
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validera inloggningsuppgifter via service-lagret
        if (userService.validateLogin(username, password)) {
            // Om inloggningen lyckas, skicka vidare till en välkomstsida
            response.sendRedirect("product.jsp");
        } else {
            // Om inloggningen misslyckas, skicka tillbaka till inloggningssidan med ett felmeddelande
            response.sendRedirect("login.jsp?error=true");
        }
    }
}

