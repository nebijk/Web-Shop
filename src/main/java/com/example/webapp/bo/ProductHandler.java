package com.example.webapp.bo;

import com.example.webapp.ui.ProductInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductHandler {

    public static List<ProductInfo> getProducts() throws SQLException {
        // Anropar Product-klassen för att få alla produkter
        List<Product> products = Product.getAllProducts();
        List<ProductInfo> productInfos = new ArrayList<>();
        for(Product product : products) {
            productInfos.add(new ProductInfo(product.getId(),product.getName(),product.getPrice(),product.getStock()));
        }
        return productInfos;
    }

    public static Product getProduct(int productId) throws SQLException {
        // Anropar Product-klassen för att få en specifik produkt
        return Product.getProductById(productId);
    }
}
