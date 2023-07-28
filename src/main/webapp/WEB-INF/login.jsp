<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 25/05/2023
  Time: 09:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link type="text/css" rel="stylesheet" href="./CSS/style.css">


</head>
<body>
<div class="container_add">
<form action="LoginServlet" method="post"  >
    <table>
        <tr>
            <td>
                <label for="username">User Name:</label>
            </td>
            <td>
                <input type="text" name="userName" id="userName" value="${userName}" required><br><br>
            </td>
        </tr>
        <tr>
            <td>
                <label for="password">Password:</label>
            </td>
            <td>
                <input type="password" name="password" id="password" required><br><br>
            </td>
        </tr>
    </table>
    <input type="submit"  class="btnAdd" value="  Login  ">
</form>
</div>
</body>
</html>
