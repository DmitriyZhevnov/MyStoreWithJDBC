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
<% Person person = (Person) session.getAttribute("currentUser");
OrderHistory orderHistory = person.getOrderHistory(); %>

<table border="1" width="100%" cellpadding="5">
    <tr>
        <th width="25%">1</th>
        <th width="25%">2</th>
        <th width="25%">3</th>
    </tr>
        <%
        for (int i = 0; i < orderHistory.getOrderHistory().size(); i++) {
    %>
    <td><%= orderHistory.getOrderHistory().get(i).getNumber()%>
    </td>
    <td><%= orderHistory.getOrderHistory().get(i).getDateTime()%>
    </td>
    <td><%= orderHistory.getOrderHistory().get(i).getListOfProducts().toString()%>
    </td>

   <%}%>
</table>
<p><a href="/myPage">Назад</a></p>
</body>
</html>
