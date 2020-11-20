<%@ page import="classes.StorageOfProducts" %><%--
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
<%@page import="classes.StorageOfProducts" %>
<table border="1" width="100%" cellpadding="5">
    <tr>
        <th width="25%">Название</th>
        <th width="25%">Описание</th>
        <th width="5%">Цена</th>
        <th width="20%">Редактировать</th>
    </tr>
    <%
        for (int i = 0; i < StorageOfProducts.returnStorage().size(); i++) {
    %>
    <tr>
        <td><%= StorageOfProducts.returnStorage().get(i).getName()%>
        </td>
        <td><%= StorageOfProducts.returnStorage().get(i).getDescription()%>
        </td>
        <td><%= StorageOfProducts.returnStorage().get(i).getPrice()%>
        </td>
        <td>
            <form action='/admin' method='POST'>
                <input name="count"/>
                <input type="hidden" name="operation" value="modifyProduct">
                <input type="hidden" name="idProduct" value="<%=StorageOfProducts.returnStorage().get(i).getId() %>"/>
                <input type='submit' value='Редактировать'/>
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
<p><a href="/basket">В корзину</a></p>
<p><a href="/mainPage.jsp">На главную страницу</a></p>
</body>
</html>