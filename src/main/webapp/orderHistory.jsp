<%@ page import="classes.OrderHistory" %><%--
  Created by IntelliJ IDEA.
  User: Жевновы
  Date: 20.11.2020
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>История заказов</title>
</head>
<body>
<%@page import="classes.OrderHistory" %>
<%@ page import="classes.Person" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<% Person person = (Person) session.getAttribute("currentUser");
    OrderHistory orderHistory = person.getOrderHistory();
    if (orderHistory.getOrderHistory().size() == 0){
        request.getServletContext().getRequestDispatcher("/orderHistoryEmpty.jsp").forward(request, response);
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");%>

<table border="1" width="100%" cellpadding="5">
    <tr>
        <th width="20%" colspan="2">Информация</th>
        <th width="70%" colspan="3">Содержимое заказа</th>
        <th width="10%"> Итоговая цена </th>
    </tr>
    <tr>
        <th width="10%">Номер заказа</th>
        <th width="10%">Дата заказа</th>
        <th width="40%">Название</th>
        <th width="10%">Количество</th>
        <th width="10%">Цена</th>
        <th></th>
    </tr>
    <%
        for (int i = orderHistory.getOrderHistory().size()-1; i >= 0; i--) {
    %>
    <tr>
            <%
        for (int j = 0; j < orderHistory.getOrderHistory().get(i).getListOfProducts().size(); j++) {
    %>
        <td><%= orderHistory.getOrderHistory().get(i).getNumber()%></td>
        <td></td>
        <td><%= orderHistory.getOrderHistory().get(i).getListOfProducts().get(j).getName()%></td>
        <td><%= orderHistory.getOrderHistory().get(i).getListOfProducts().get(j).getCount()%></td>
        <td><%= String.format("%.2f",orderHistory.getOrderHistory().get(i).getListOfProducts().get(j).getPrice())%></td>
        <td></td>
    </tr>
    <%}%>
    </tr>
    <tr>
        <td><%= orderHistory.getOrderHistory().get(i).getNumber()%></td>
        <td><%= orderHistory.getOrderHistory().get(i).getDateTime().format(formatter)%></td>
        <td></td>
        <td></td>
        <td></td>
        <td><%= String.format("%.2f",orderHistory.getOrderHistory().get(i).getTotalCostOfOrder())%></td>

    </tr>
    <%= orderHistory.getOrderHistory().get(i).getAddress()%>
    <%= orderHistory.getOrderHistory().get(i).getPhoneNumber()%>

    <%}%>
</table>
<p><a href="/myPage">Назад</a></p>
</body>
</html>
