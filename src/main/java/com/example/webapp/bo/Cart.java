package com.example.webapp.bo;

import com.example.webapp.ui.ProductInfo;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<ProductInfo, Integer> products;

    public Cart() {
        this.products = new HashMap<>();
    }

    public void addProduct(ProductInfo product, int quantity) {
        if (products.containsKey(product)) {
            int currentQuantity = products.get(product);
            products.put(product, currentQuantity + quantity);
        } else {
            products.put(product, quantity);
        }
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public double getTotalPrice() {
        double total = 0;
        for (Map.Entry<ProductInfo, Integer> entry : products.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public Map<ProductInfo, Integer> getProducts() {
        return products;
    }

    public void clear() {
        products.clear();
    }
}
