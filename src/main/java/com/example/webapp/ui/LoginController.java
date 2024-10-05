package com.example.webapp.ui;

import com.example.webapp.bo.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
        boolean isValid = userService.validateLogin(username, password);  // boolean returvärde

        if (isValid) {
            // Om inloggningen lyckas, skapa en session och lagra användarnamnet
            HttpSession session = request.getSession();
            session.setAttribute("username", username);  // Spara användarnamn i sessionen

            // Omdirigera till produktlistan efter inloggningen
            response.sendRedirect("product-list");
        } else {
            // Om inloggningen misslyckas, skicka tillbaka till inloggningssidan med ett felmeddelande
            response.sendRedirect("login.jsp?error=true");
        }
    }
}
