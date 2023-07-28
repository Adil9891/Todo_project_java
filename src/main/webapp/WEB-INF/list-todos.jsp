<%@ page import="java.util.*" %>

<%@ page import="com.example.Todo.model.Todo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Todo list</title>
    <link type="text/css" rel="stylesheet" href="./CSS/style.css">

</head>
<body>

<div class="container_add">

    <h1>Bienvenue<span> ${sessionScope.userName}</span> , voici votre todolist !</h1>
<form action="LogoutServlet" method="get">
    <input type="submit" class="btnAdd" value="logout"/>
</form>


<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Description</th>
        <th>Status</th>
        <th></th>


    </tr>
    </thead>
    <tbody>




    <c:forEach var="tempTodo" items="${requestScope.todos}">
        <c:url var="EditLink" value= "EditTodoServlet">
            <c:param name="todoId" value="${tempTodo.id}"/>
        </c:url>
        <tr>
            <td>${tempTodo.id}</td>
            <td>${tempTodo.description}</td>
            <td>${tempTodo.status}</td>
            <td> <a href="${EditLink}"> Done</a></td>


        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
</body>
</html>