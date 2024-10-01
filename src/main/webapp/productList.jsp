<%@ page import="com.example.webapp.model.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: nebil
  Date: 10/1/2024
  Time: 6:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<h1>Product List</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Stock Quantity</th>
    </tr>
    <%
        List<com.example.webapp.model.Product> products = (List<Product>) request.getAttribute("products");

        if (products != null && !products.isEmpty()) {
            for (com.example.webapp.model.Product product : products) {
    %>
    <tr>
        <td><%= product.getId() %></td>
        <td><%= product.getName() %></td>
        <td><%= product.getPrice() %></td>
        <td><%= product.getStock() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="4">No products found</td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>