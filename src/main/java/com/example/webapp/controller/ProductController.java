package com.example.webapp.controller;


import com.example.webapp.model.ProductDAO;
import com.example.webapp.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/product-list")
public class ProductController extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Hämta alla produkter från databasen
            List<Product> productList = productDAO.getAllProducts();
            // Skicka listan med produkter till JSP-sidan
            request.setAttribute("products", productList);
            // Skicka vidare till JSP-sidan (view)
            request.getRequestDispatcher("productList.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching product list");
        }
    }
}
