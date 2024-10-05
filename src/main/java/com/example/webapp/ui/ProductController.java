package com.example.webapp.ui;

import com.example.webapp.bo.Product;
import com.example.webapp.db.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/product-list")
public class ProductController extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Kontrollera om användaren är inloggad
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            // Om användaren inte är inloggad, skicka tillbaka till login-sidan
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // Hämta alla produkter från databasen
            List<Product> productList = productDAO.getAllProducts();
            request.setAttribute("products", productList);

            // Skicka vidare till JSP-sidan som visar produktlistan
            request.getRequestDispatcher("product-list.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching product list");
        }
    }
}
