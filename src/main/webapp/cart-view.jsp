<%@ page import="com.example.webapp.bo.Cart" %>
<%@ page import="com.example.webapp.bo.Product" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your Cart</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            height: 100vh;
            margin: 0;
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
        }

        table {
            width: 80%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        table, th, td {
            border: 1px solid #ccc;
        }

        th, td {
            padding: 12px;
            text-align: center;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        td {
            background-color: white;
        }

        .total-row {
            font-weight: bold;
            background-color: #f9f9f9;
        }

        .empty-cart {
            color: red;
            font-weight: bold;
        }

        a, input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }

        a:hover, input[type="submit"]:hover {
            background-color: #45a049;
        }

        .container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 1000px;
        }

    </style>
</head>
<body>
<div class="container">
    <h1>Your Cart</h1>
    <%
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && !cart.getProducts().isEmpty()) {
    %>
    <table>
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
        <tr class="total-row">
            <td colspan="3">Total Price:</td>
            <td><%= cart.getTotalPrice() %></td>
        </tr>
    </table>
    <%
    } else {
    %>
    <p class="empty-cart">Your cart is empty.</p>
    <%
        }
    %>

    <a href="product-list">Continue Shopping</a>
</div>
</body>
</html>
