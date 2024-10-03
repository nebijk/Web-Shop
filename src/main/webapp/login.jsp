<%--
  Created by IntelliJ IDEA.
  User: nebil
  Date: 10/3/2024
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>

<%-- Visa felmeddelande om inloggningen misslyckades --%>
<%
    String error = request.getParameter("error");
    if (error != null && error.equals("true")) {
%>
<p style="color:red;">Invalid username or password, please try again.</p>
<%
    }
%>

<form action="login" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <br><br>

    <input type="submit" value="Login">
</form>

</body>
</html>