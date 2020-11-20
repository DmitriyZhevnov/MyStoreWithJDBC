<%@ page import="classes.Person" %><%--
  Created by IntelliJ IDEA.
  User: Жевновы
  Date: 20.11.2020
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заказ принят</title>
</head>
<body>
<%@ page import="classes.Person" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
    <% Person person = (Person) session.getAttribute("currentUser");%>
<h1 align="center"> Спасибо</h1>
<h2 align="center"> Ваш заказ № <%= person.getOrderHistory().getOrderHistory().get(person.getOrderHistory()
        .getOrderHistory().size()-1).getNumber()%></h2>
<p><a href="/myPage">Мой аккаунт</a></p>
<p><a href="mainPage.jsp">На главную страницу</a></p>
</body>
</html>
