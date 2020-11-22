<%--
  Created by IntelliJ IDEA.
  User: Жевновы
  Date: 20.11.2020
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактировать продукт</title>
</head>
<body>
<%@ page import="classes.Product, classes.StorageOfProducts, classes.Person, classes.StorageOfUsers" %>
<% Person person = (Person) session.getAttribute("currentUser");
    if (!person.getStatus().equals("admin") || StorageOfUsers.findPersonInStorageByLogin(person.getLogin()) == null) {
        session.setAttribute("currentUser", null);
        application.getRequestDispatcher("/Error").forward(request, response);
    }
    Product product = StorageOfProducts.getProductInStorage((Product) session.getAttribute("productToModify")); %>

<form action="/admin" method="post">
    ID товара: <input type="text" name="id" value=<%= product.getId() %>>
    <br/>
    Название: <input type="text" name="name" value=<%= product.getName() %>>
    <br/>
    Описание: <input type="text" name="description" value=<%= product.getDescription() %>>
    <br/>
    Цена: <input type="text" name="price" value=<%= product.getPrice() %>>
    <br/>
    Количество: <input type="text" name="count" value=<%= product.getCount() %>>
    <br/>
    <input type="hidden" name="operation" value="modifyProductWithValues">
    <p><a href="/myPage">Назад</a>
        <input type="submit" value="Сохранить"/></p>
</form>
</body>
</html>
