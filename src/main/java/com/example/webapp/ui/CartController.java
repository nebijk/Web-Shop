package com.example.webapp.ui;

import com.example.webapp.bo.Cart;
import com.example.webapp.bo.Product;
import com.example.webapp.bo.ProductHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CartController", value = "/cart")
public class CartController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get productId from the request
        int productId = Integer.parseInt(request.getParameter("productId"));

        // Fetch all products using ProductHandler
        List<ProductInfo> productList = null;
        try {
            productList = ProductHandler.getProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Find the product by productId using a simple loop
        ProductInfo product = null;
        for (ProductInfo p : productList) {
            if (p.getId() == productId) {
                product = p;
                break;
            }
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
            cart.addProduct(product,1);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product");
        }

        // Redirect back to the product listing page
        response.sendRedirect("product-list");
    }
}
