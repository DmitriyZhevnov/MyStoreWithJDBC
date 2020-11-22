<%--
  Created by IntelliJ IDEA.
  User: Жевновы
  Date: 13.11.2020
  Time: 00:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<form action="/registration" method="POST">
    <%String error = (String) session.getAttribute("messageError");%>
    <p style="color:#bf3b3b"><%= error%>
    </p>
    Имя: <input type="text" name="name">
    <br/>
    Возраст: <input type="text" name="age"/>
    <br/>
    Логин: <input type="text" name="login"/>
    <br/>
    Пароль: <input type="text" name="password"/>
    <input type="submit" value="Зарегестрировать"/>
    <p><a href="loginPage.jsp">Назад</a></p>
    <br/>
</form>
</body>
</html>
