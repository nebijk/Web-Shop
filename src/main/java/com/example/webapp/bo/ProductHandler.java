package com.example.webapp.bo;

import java.sql.SQLException;
import java.util.List;

public class ProductHandler {

    public static List<Product> getProducts() throws SQLException {
        // Anropar Product-klassen för att få alla produkter
        return Product.getAllProducts();
    }

    public static Product getProduct(int productId) throws SQLException {
        // Anropar Product-klassen för att få en specifik produkt
        return Product.getProductById(productId);
    }
}
