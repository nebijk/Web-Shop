<%@ page import="com.example.webapp.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
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

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 8px 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        a {
            text-decoration: none;
            color: #4CAF50;
            font-size: 16px;
            margin-top: 10px;
        }

        a:hover {
            color: #45a049;
        }

        .out-of-stock {
            color: red;
            font-weight: bold;
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
    <h1>Product List</h1>

    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Stock Quantity</th>
            <th>Add to Cart</th>
        </tr>
        <%
            List<Product> products = (List<Product>) request.getAttribute("products");

            if (products != null && !products.isEmpty()) {
                for (Product product : products) {
        %>
        <tr>
            <td><%= product.getId() %></td>
            <td><%= product.getName() %></td>
            <td><%= product.getPrice() %></td>
            <td><%= product.getStock() %></td>
            <td>
                <% if (product.getStock() > 0) { %>
                <form action="cart" method="post">
                    <input type="hidden" name="productId" value="<%= product.getId() %>">
                    <input type="submit" value="Add to Cart">
                </form>
                <% } else { %>
                <span class="out-of-stock">Out of Stock</span>
                <% } %>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="5">No products found</td>
        </tr>
        <%
            }
        %>
    </table>

    <a href="cart-view.jsp">View Cart</a>
</div>
</body>
</html>
