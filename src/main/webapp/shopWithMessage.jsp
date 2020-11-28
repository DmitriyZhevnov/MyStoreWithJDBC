<%--
  Created by IntelliJ IDEA.
  User: Жевновы
  Date: 19.11.2020
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Магазин</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<%@ page import="classes.StorageOfProducts, classes.Person, classes.StorageOfUsers" %>
<% Person person = (Person) session.getAttribute("currentUser");
    if (StorageOfUsers.findPersonInStorageByLogin(person.getLogin()) == null) {
        session.setAttribute("currentUser", null);
        application.getRequestDispatcher("/Error").forward(request, response);
    }
    String message = (String) session.getAttribute("shopMessage");%>
<p align="center"><%= message%>
</p>
<table border="1" width="100%" cellpadding="5">
    <tr>
        <th width="25%">Название</th>
        <th width="25%">Описание</th>
        <th width="5%">Цена</th>
        <th width="20%">Добавить в корзину</th>
    </tr>
    <%
        for (int i = 0; i < StorageOfProducts.returnStorage().size(); i++) {
            if (StorageOfProducts.returnStorage().get(i).getCount() > 0) {
    %>
    <tr>
        <td><%= StorageOfProducts.returnStorage().get(i).getName()%>
        </td>
        <td><%= StorageOfProducts.returnStorage().get(i).getDescription()%>
        </td>
        <td><%= String.format("%.2f", StorageOfProducts.returnStorage().get(i).getPrice())%>
        </td>
        <td>
            <form action='/shop' method='POST'>
                <input name="count"/>
                <input type="hidden" name="idProduct" value="<%=StorageOfProducts.returnStorage().get(i).getSerialNumber() %>"/>
                <input type='submit' value='Добавить'/>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
<p><a href="/basket">В корзину</a></p>
<p><a href="/mainPage.jsp">На главную страницу</a></p>
</body>
</html>