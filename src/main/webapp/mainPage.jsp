<%--
  Created by IntelliJ IDEA.
  User: Жевновы
  Date: 13.11.2020
  Time: 00:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница</title>
</head>
<body>

<%@ page import="classes.Person" %>
<%@ page import="classes.StorageOfUsers" %>
<% Person person = (Person) session.getAttribute("currentUser"); %>
<% if(StorageOfUsers.findPersonInStorageByLogin(person.getLogin()) == null){
    session.setAttribute("currentUser", null);
    application.getRequestDispatcher("/Error").forward(request,response);
}%>
<p> Привет, <%= person.getName() %> </p>
<div><h2 align="center">Main page</h2></div>
<div align="left">
<form>
    <br/>
    <p><a href="/myPage">Мой аккаунт</a></p>
    <p><a href="/shop">Магазин</a></p>
    <p><a href="/login">Выйти из учётной записи</a></p>
</form>
</div>
</body>
</html>