<%--
  Created by IntelliJ IDEA.
  User: Жевновы
  Date: 13.11.2020
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Мой аккаунт</title>
</head>
<body>
<%@ page import="classes.Person, classes.StorageOfUsers" %>
<% Person person = (Person) session.getAttribute("currentUser");
    if (!person.getStatus().equals("admin") || StorageOfUsers.findPersonInStorageByLogin(person.getLogin()) == null) {
        session.setAttribute("currentUser", null);
        application.getRequestDispatcher("/Error").forward(request, response);
    }%>
<div align="center">
    <h2>Информация о Вас</h2>
    <p> Имя:  <%= person.getName() %>
    </p>
    <p> Логин  <%= person.getLogin() %>
    </p>
    <p> Возраст:  <%= person.getAge() %>
    </p>
</div>
<p><a href="/admin">Админ-панель</a></p>
<p><a href="personalPageForChangeParameters.jsp">Изменить данные</a></p>
<p><a href="orderHistory.jsp">История заказов</a></p>
<p><a href="mainPage.jsp">На главную страницу</a></p>
<p><a href="/login">Выйти из учётной записи</a></p>
</form>
</body>
</html>