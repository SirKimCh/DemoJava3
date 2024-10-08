<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 08/10/2024
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>
<body>
<form action="register" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <label for="role">Role:</label>
    <select id="role" name="role">
        <option value="USER">User</option>
        <option value="ADMIN">Admin</option>
    </select>
    <button type="submit">Register</button>
</form>
</body>
</html>