package com.example.webapp.ui;

import com.example.webapp.bo.*;
import com.example.webapp.db.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/create-order")
public class OrderController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if session exists and user is logged in
        if (session == null || session.getAttribute("userId") == null) {
            System.out.println("ssasda");
            response.sendRedirect("login.jsp");
            return;
        }
        System.out.println("ssadsasdadasdasd");

        try {
            // Retrieve userId and cart from session
            Integer userId = (Integer) session.getAttribute("userId");
            Cart cart = (Cart) session.getAttribute("cart");

            if (cart == null || cart.getProducts().isEmpty()) {
                response.sendRedirect("cart-view.jsp");  // Redirect if cart is invalid or empty
                return;
            }

            // Place the order using OrderHandler
            OrderHandler.placeOrder(userId, cart);

            // Clear the cart after placing the order
            session.removeAttribute("cart");

            // Redirect to product list after placing the order
            response.sendRedirect("product-list");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing the order.");
        }
    }

}

