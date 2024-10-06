package com.example.webapp.ui.controller;

import com.example.webapp.bo.*;
import com.example.webapp.bo.handlers.OrderHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/create-order")
public class OrderController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            Integer userId = (Integer) session.getAttribute("userId");
            Cart cart = (Cart) session.getAttribute("cart");

            if (cart == null || cart.getProducts().isEmpty()) {
                response.sendRedirect("cart-view.jsp");
                return;
            }

            OrderHandler.placeOrder(userId, cart);
            session.removeAttribute("cart");
            response.sendRedirect("product-list");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing the order.");
        }
    }

}

