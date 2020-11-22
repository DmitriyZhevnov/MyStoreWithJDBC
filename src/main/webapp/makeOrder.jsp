<%--
  Created by IntelliJ IDEA.
  User: Жевновы
  Date: 21.11.2020
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Оформление заказа</title>
</head>
<body>
<%@ page import="classes.Person, classes.StorageOfUsers" %>
<% Person person = (Person) session.getAttribute("currentUser");
    if (StorageOfUsers.findPersonInStorageByLogin(person.getLogin()) == null) {
        session.setAttribute("currentUser", null);
        application.getRequestDispatcher("/Error").forward(request, response);
    }%>
<%= person.getName()%>, для оформления заказа на
сумму <%= String.format("%.2f", person.getBasket().getTotalCostOfBasket())%> укажите свои
данные:
</br>
</br>
<form action="/basket" method="post">
    Адрес: <input type="text" name="address" value=<%= person.getAddress() %>>
    <br/>
    Телефон: <input type="text" name="phoneNumber" value=<%= person.getPhoneNumber() %>>
    <br/>
    <input type="hidden" name="operationInBasket" value="makeOrder">
    <br><input type="submit" value="Оформить"/> </br></br>
    <a href="/basket">Назад к корзине</a> </br></br>
    <a href="/shop">Вернуться в магазин</a>
</form>
</body>
</html>
