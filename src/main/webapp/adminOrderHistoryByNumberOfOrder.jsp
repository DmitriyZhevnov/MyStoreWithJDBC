<%--
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
<%@ page import="classes.Order, java.time.format.DateTimeFormatter, classes.StorageOfUsers, classes.Person" %>
<% Person checkPerson = (Person) session.getAttribute("currentUser");
    if (!checkPerson.getStatus().equals("admin") || StorageOfUsers.findPersonInStorageByLogin(checkPerson.getLogin()) == null) {
        session.setAttribute("currentUser", null);
        application.getRequestDispatcher("/Error").forward(request, response);
    }
    Order order = (Order) session.getAttribute("orderToShow");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
%>
<p><a href="/adminOrderHistoryPanel.jsp">Назад</a></p>
<h2 align="left">Заказ № <%= order.getNumber()%>
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
        <td><%= order.getDateTime().format(formatter)%>
        </td>
        <td><%= order.getLogin()%>
        </td>
        <td><%= order.getAddress()%>
        </td>
        <td><%= order.getPhoneNumber()%>
        </td>
        <td><%= String.format("%.2f", order.getTotalCostOfOrder())%>
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
            for (int j = 0; j < order.getListOfProducts().size(); j++) {
        %>
        <td><%= order.getListOfProducts().get(j).getName()%>
        </td>
        <td><%= order.getListOfProducts().get(j).getDescription()%>
        </td>
        <td><%= order.getListOfProducts().get(j).getCount()%>
        </td>
        <td><%= String.format("%.2f", order.getListOfProducts().get(j).getPrice())%>
        </td>
    </tr>
    <%}%>
</table>
</br>
<p><a href="/adminOrderHistoryPanel.jsp">Назад</a></p>
</body>
</html>
