<%@ page import="com.example.webapp.model.Product" %>
<%@ page import="java.util.List" %>
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
            Out of Stock
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
<a href="cart-view.jsp">view cart</a>


</body>
</html>
