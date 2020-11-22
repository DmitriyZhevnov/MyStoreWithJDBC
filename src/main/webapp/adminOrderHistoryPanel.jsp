<%--
  Created by IntelliJ IDEA.
  User: Жевновы
  Date: 22.11.2020
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Истории заказов</title>
</head>
<body>
<p><a href="/myPage">Назад</a></p>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="classes.StorageOfOrders" %>
<%@ page import="java.util.Set" %>
<%@ page import="classes.Order" %>
<%DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    for (int i = StorageOfOrders.getOrderStorage().size() - 1; i >= 0; i--) {
%>
<h2 align="left">Заказ № <%= StorageOfOrders.getOrderStorage().get(i).getNumber()%>
</h2>
<table align="center" border="1" width="50%" cellpadding="5">
    <tr>
        <th width="20%">Дата заказа</th>
        <th width="40%">Логин</th>
        <th width="40%">Адрес</th>
        <th width="20%">Телефон</th>
        <th width="10%"> Итоговая цена</th>
    </tr>
    <tr>
        <td><%= StorageOfOrders.getOrderStorage().get(i).getDateTime().format(formatter)%>
        </td>
        <td><%= StorageOfOrders.getOrderStorage().get(i).getLogin()%>
        </td>
        <td><%= StorageOfOrders.getOrderStorage().get(i).getAddress()%>
        </td>
        <td><%= StorageOfOrders.getOrderStorage().get(i).getPhoneNumber()%>
        </td>
        <td><%= String.format("%.2f", StorageOfOrders.getOrderStorage().get(i).getTotalCostOfOrder())%>
        </td>
    </tr>
</table>
</br>
<table align="center" border="1" width="70%" cellpadding="5">
    <tr>
        <th colspan="4">Содержимое заказа</th>
    </tr>
    <tr>
        <th width="40%">Название</th>
        <th width="40%">Описание</th>
        <th width="10%">Количество</th>
        <th width="10%">Цена</th>
    </tr>
    <tr>
        <%
            for (int j = 0; j < StorageOfOrders.getOrderStorage().get(i).getListOfProducts().size(); j++) {
        %>
        <td><%= StorageOfOrders.getOrderStorage().get(i).getListOfProducts().get(j).getName()%>
        </td>
        <td><%= StorageOfOrders.getOrderStorage().get(i).getListOfProducts().get(j).getDescription()%>
        </td>
        <td><%= StorageOfOrders.getOrderStorage().get(i).getListOfProducts().get(j).getCount()%>
        </td>
        <td><%= String.format("%.2f", StorageOfOrders.getOrderStorage().get(i).getListOfProducts().get(j).getPrice())%>
        </td>
    </tr>
    <%}%>
</table>
</br>
<%}%>
<p><a href="/myPage">Назад</a></p>
</body>
</html>