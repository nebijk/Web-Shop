<%@ page import="com.example.webapp.model.Cart" %>
<%@ page import="com.example.webapp.model.Product" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your Cart</title>
</head>
<body>
<h1>Your Cart</h1>
<%
    Cart cart = (Cart) session.getAttribute("cart");
    if (cart != null && !cart.getProducts().isEmpty()) {
%>
<table border="1">
    <tr>
        <th>Product</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Total</th>
    </tr>
    <%
        for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
    %>
    <tr>
        <td><%= product.getName() %></td>
        <td><%= product.getPrice() %></td>
        <td><%= quantity %></td>
        <td><%= quantity * product.getPrice() %></td>
    </tr>
    <%
        }
    %>
    <tr>
        <td colspan="3">Total Price:</td>
        <td><%= cart.getTotalPrice() %></td>
    </tr>
</table>
<%
} else {
%>
<p>Your cart is empty.</p>
<%
    }
%>

<a href="product-list">Continue Shopping</a> <!-- Link to go back to the product list -->
</body>
</html>
