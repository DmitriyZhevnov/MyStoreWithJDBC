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
<%@ page import="classes.Person, classes.StorageOfUsers, classes.OrderHistory, java.time.format.DateTimeFormatter" %>
<% Person person = (Person) session.getAttribute("currentUser");
    if (StorageOfUsers.findPersonInStorageByLogin(person.getLogin()) == null) {
        session.setAttribute("currentUser", null);
        application.getRequestDispatcher("/Error").forward(request, response);
    }%>
<p><a href="/myPage">Назад</a></p>
<% OrderHistory orderHistory = person.getOrderHistory();
    if (orderHistory.getOrderHistory().size() == 0) {
        request.getServletContext().getRequestDispatcher("/orderHistoryEmpty.jsp").forward(request, response);
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    for (int i = orderHistory.getOrderHistory().size() - 1; i >= 0; i--) { %>
<h2 align="left">Заказ № <%= orderHistory.getOrderHistory().get(i).getNumber()%>
</h2>
<table align="center" border="1" width="50%" cellpadding="5">
    <tr>
        <th width="20%">Дата заказа</th>
        <th width="40%">Адрес</th>
        <th width="20%">Телефон</th>
        <th width="10%"> Итоговая цена</th>
    </tr>
    <tr>
        <td><%= orderHistory.getOrderHistory().get(i).getDateTime().format(formatter)%>
        </td>
        <td><%= orderHistory.getOrderHistory().get(i).getAddress()%>
        </td>
        <td><%= orderHistory.getOrderHistory().get(i).getPhoneNumber()%>
        </td>
        <td><%= String.format("%.2f", orderHistory.getOrderHistory().get(i).getTotalCostOfOrder())%>
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
            for (int j = 0; j < orderHistory.getOrderHistory().get(i).getListOfProducts().size(); j++) {
        %>
        <td><%= orderHistory.getOrderHistory().get(i).getListOfProducts().get(j).getName()%>
        </td>
        <td><%= orderHistory.getOrderHistory().get(i).getListOfProducts().get(j).getDescription()%>
        </td>
        <td><%= orderHistory.getOrderHistory().get(i).getListOfProducts().get(j).getCount()%>
        </td>
        <td><%= String.format("%.2f", orderHistory.getOrderHistory().get(i).getListOfProducts().get(j).getPrice())%>
        </td>
    </tr>
    <%}%>
</table>
</br>
<%}%>
<p><a href="/myPage">Назад</a></p>
</body>
</html>
