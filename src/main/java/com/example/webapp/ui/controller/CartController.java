package com.example.webapp.ui.controller;

import com.example.webapp.bo.Cart;
import com.example.webapp.bo.handlers.ProductHandler;

import com.example.webapp.ui.ProductInfo;
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

        int productId = Integer.parseInt(request.getParameter("productId"));

        List<ProductInfo> productList = null;
        try {
            productList = ProductHandler.getProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ProductInfo product = null;
        for (ProductInfo p : productList) {
            if (p.getId() == productId) {
                product = p;
                break;
            }
        }

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        if (product != null) {
            cart.addProduct(product,1);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product");
        }

        response.sendRedirect("product-list");
    }
}
