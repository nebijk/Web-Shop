package com.example.webapp.controller;

import com.example.webapp.model.Cart;
import com.example.webapp.model.Product;
import com.example.webapp.model.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CartController", value = "/cart")
public class CartController extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get productId from the request
        int productId = Integer.parseInt(request.getParameter("productId"));

        // Fetch the product from the database
        Product product = null;
        try {
            product = productDAO.getProductById(productId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Get or create a cart from the session
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        // Add the product to the cart (increment quantity)
        if (product != null) {
            cart.addProduct(product);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product");
        }

        // Redirect back to the product listing page
        response.sendRedirect("product-list");
    }
}
