<%--
  Created by IntelliJ IDEA.
  User: Жевновы
  Date: 13.11.2020
  Time: 00:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Войти в аккаунт</title>
</head>
<body>
<form action="/homePage" method="POST" >
    Логин: <input type="text" size="25" name="login">
    <br/>
    Пароль: <input type="password" size="25" name="password"/>
    <br/>
    <p><input type="submit" value="Войти"/>
    <a href="registrationPage.jsp">Регистрация</a></p>
</form>
</body>
</html>
