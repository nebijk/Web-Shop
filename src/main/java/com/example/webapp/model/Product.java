package com.example.webapp.model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    // Standardkonstruktor
    public Product() {
    }

    // Konstruktor med parametrar
    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id == product.id;  // Products are equal if their ids are equal
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);  // Use the product's id for the hash code
    }

    // Getter och setter för id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter och setter för name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter och setter för price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Getter och setter för quantity
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // toString() metod för enkel utskrift av produktinformationen
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + stock +
                '}';
    }
}