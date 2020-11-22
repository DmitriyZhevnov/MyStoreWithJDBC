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
    <title>Редактировать хранилище</title>
</head>
<body>
<%@ page import="classes.StorageOfProducts, classes.StorageOfUsers, classes.Person" %>
<% Person person = (Person) session.getAttribute("currentUser");
    if (!person.getStatus().equals("admin") || StorageOfUsers.findPersonInStorageByLogin(person.getLogin()) == null) {
        session.setAttribute("currentUser", null);
        application.getRequestDispatcher("/Error").forward(request, response);
    }
    String message = (String) session.getAttribute("message");%>
<p align="center"><%= message%>
</p>
<table border="1" align="left" cellpadding="4">
    <tr>
        <th>
            <p>Добавить товары на склад</p>
            <form action="/admin" method="post">
                <select name="idProductToAdd">
                    <% for (int t = 0; t < StorageOfProducts.returnStorage().size(); t++) { %>
                    <option value="<%= StorageOfProducts.returnStorage().get(t).getId() %>">
                        <%= StorageOfProducts.returnStorage().get(t).getId()%>
                        - <%= StorageOfProducts.returnStorage().get(t).getName()%>
                    </option>
                    <% } %>
                </select>
                <input type="hidden" name="operation" value="addProduct">
                <input type="text" name="countOfProductToAdd">
                <input type="submit" value="Добавить"/>
            </form>
        </th>
        <th><p>Отсотрировать хранилище</p>
            <form action="/admin" method="post">
                <input type="hidden" name="operation" value="sortStorage">
                <input type="submit" value="Сортировать"/>
            </form>
        </th>
        <th><p>Добавить новый товар</p>
            <form action="/adminAddProduct.jsp">
                <input type="submit" value="Добавить"/>
            </form>
        </th>
    </tr>
</table>
</br>
<table border="1" width="100%" cellpadding="5">
    <tr>
        <th width="5%">ID товара</th>
        <th width="20%">Название</th>
        <th width="25%">Описание</th>
        <th width="5%">Цена</th>
        <th width="5%">Количество</th>
        <th width="20%">Редактировать</th>
        <th width="20%">Удалить</th>
    </tr>
    <%
        for (int i = 0; i < StorageOfProducts.returnStorage().size(); i++) {
    %>
    <tr>
        <td><%= StorageOfProducts.returnStorage().get(i).getId()%>
        </td>
        <td><%= StorageOfProducts.returnStorage().get(i).getName()%>
        </td>
        <td><%= StorageOfProducts.returnStorage().get(i).getDescription()%>
        </td>
        <td><%= String.format("%.2f", StorageOfProducts.returnStorage().get(i).getPrice())%>
        </td>
        <td><%= StorageOfProducts.returnStorage().get(i).getCount()%>
        </td>
        <td>
            <form action='/admin' method='POST'>
                <input type="hidden" name="operation" value="modifyProduct">
                <input type="hidden" name="idProductToModify"
                       value="<%=StorageOfProducts.returnStorage().get(i).getId() %>"/>
                <input type='submit' value='Редактировать'/>
            </form>
        </td>
        <td>
            <form action='/admin' method='POST'>
                <input type="hidden" name="operation" value="deleteProduct">
                <input type="hidden" name="idProductForDelete"
                       value="<%=StorageOfProducts.returnStorage().get(i).getId() %>"/>
                <input type='submit' value='Удалить'/>
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
<p><a href="/admin">Назад</a></p>
<p><a href="/mainPage.jsp">На главную страницу</a></p>
</body>
</html>