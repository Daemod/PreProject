<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserManager</title>
</head>
<body>
<form action="addUser" method="post">
    <input type="text" name="name">
    <input type="text" name="work">
    <input type="number" name="age">
    <button type="submit" name="submit">Отправить</button>
</form>
<table>
    Список пользователей.
    <tr>
        <td>id</td>
        <td>Имя:</td>
        <td>Работа:</td>
        <td>Возвраст:</td>
    </tr>
    <c:forEach items="${requestScope.users}" var="user">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <form action="editUser" method="post">
                <input type="hidden" name="id" value="${user.id}">
                <td><input type="text" name="name" value="${user.name}"></td>
                <td><input type="text" name="work" value="${user.work}"></td>
                <td><input type="number" name="age" value="${user.age}"></td>
                <td>
                    <button type="submit" name="submit">Редактировать</button>
                </td>
            </form>
            <form action="deleteUser" method="post">
                <input type="hidden" name="id" value="${user.id}">
                <td>
                    <button type="submit" name="submit">Удалить</button>
                </td>
            </form>
        </tr>
    </c:forEach>
</table>
</body>
</html>
