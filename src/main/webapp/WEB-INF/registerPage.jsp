<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
    <form action="/registration" method="post">
        <span>Введите логин:</span><input type="text" name="login">
        <span>Введите пароль:</span><input type="password" name="password">
        <span>Повторите пароль:</span><input type="password" name="rPassword">
        <p1>Дополнительная информация</p1>
        <span>Работа:</span><input type="text" name="work">
        <span>Возвраст:</span><input type="number" name="age">
        <button type="submit">Зарегистрироваться</button>
    </form>
</body>
</html>
