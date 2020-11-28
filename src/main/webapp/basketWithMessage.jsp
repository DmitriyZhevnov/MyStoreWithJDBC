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
<%@page import="classes.Basket, classes.Person, classes.StorageOfUsers" %>
<% Person person = (Person) session.getAttribute("currentUser");
    if (StorageOfUsers.findPersonInStorageByLogin(person.getLogin()) == null) {
        session.setAttribute("currentUser", null);
        application.getRequestDispatcher("/Error").forward(request, response);
    }
    Basket basket = person.getBasket();
    String message = (String) session.getAttribute("shopMessage");%>
<p align="center"><%= message%>
</p>
<table border="1" width="100%" cellpadding="5">
    <tr>
        <th width="25%">Название</th>
        <th width="25%">Описание</th>
        <th width="5%">Цена</th>
        <th width="5%">Количество</th>
        <th width="5%">Итого</th>
        <th width="20%">Изменить количество</th>
        <th width="5%">Удалить</th>
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
        <td><%= String.format("%.2f", basket.getTotalCostOfProduct(basket.getBasket().get(i)))%>
        </td>
        <td>
            <form action='/basket' method='POST'>
                <input type="hidden" name="operationInBasket" value="modifyProduct">
                <input name="count" value="<%= basket.getBasket().get(i).getCount()%>"/>
                <input type="hidden" name="idProduct" value="<%=basket.getBasket().get(i).getSerialNumber() %>"/>
                <input type='submit' value='Изменить'/>
            </form>
        </td>
        <td>
            <form action='/basket' method='POST'>
                <input type="hidden" name="operationInBasket" value="deleteProduct">
                <input type="hidden" name="idProduct" value="<%=basket.getBasket().get(i).getSerialNumber() %>"/>
                <input type='submit' value='Удалить'/>
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
<h2 align="right">Итого к оплате: <%= String.format("%.2f", basket.getTotalCostOfBasket()) %>
</h2>
<h2 align="right">
    <form action='/makeOrder.jsp'>
        <input type="hidden" name="operationInBasket" value="pay">
        <input type='submit' value='Оформить заказ'/>
    </form>
</h2>
<p><a href="/shop">В магазин</a></p>
<p><a href="/mainPage.jsp">На главную страницу</a></p>
</body>
</html>