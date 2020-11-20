<%--
  Created by IntelliJ IDEA.
  User: Жевновы
  Date: 20.11.2020
  Time: 01:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Моя корзина</title>
</head>
<body>
<%@page import="classes.Basket" %>
<%@ page import="classes.Person" %>
<%String message = (String) session.getAttribute("shopMessage");%>

<% Person person = (Person) session.getAttribute("currentUser");
    Basket basket = person.getBasket(); %>
<p align="center"><%= message%></p>
<table border="1" width="100%" cellpadding="5">
    <tr>
        <th width="25%">Название</th>
        <th width="25%">Описание</th>
        <th width="5%">Цена</th>
        <th width="5%">Количество</th>
        <th width="5%">Итого</th>
        <th width="20%">Изменить количество</th>
    </tr>
    <%
        for (int i = 0; i < basket.getBasket().size(); i++) {
    %>
    <tr>
        <td><%= basket.getBasket().get(i).getName()%>
        </td>
        <td><%= basket.getBasket().get(i).getDescription()%>
        </td>
        <td><%= basket.getBasket().get(i).getPrice()%>
        </td>
        <td><%= basket.getBasket().get(i).getCount()%>
        </td>
        <td><%= String.format("%.2f",basket.getBasket().get(i).getCount() * basket.getBasket().get(i).getPrice())%>
        </td>
        <td>
            <form action='/basket' method='POST'>
                <input name="count" value="<%= basket.getBasket().get(i).getCount()%>" />
                <input type="hidden" name="idProduct" value="<%=basket.getBasket().get(i).getId() %>" />
                <input type='submit' value='Изменить' />
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
<p><a href="/shop">В магазин</a></p>
</body>
</html>
