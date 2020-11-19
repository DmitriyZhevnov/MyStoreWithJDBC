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
    <title>Магазин</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<%@page import="classes.StorageOfProducts" %>
<%@ page import="classes.Product" %>
<table border="1" width="100%" cellpadding="5">
    <tr>
        <th width="25%">Название</th>
        <th width="25%">Описание</th>
        <th width="5%">Цена</th>
        <th width="20%">Добавить в корзину</th>
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
            <form action='/shop' method='POST'>
                <input name="count" />
                <input type="hidden" name="product" value="<%=StorageOfProducts.returnStorage().get(i) %>" />
                <input type='submit' value='Добавить' />
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>


<%--    <c:forEach items="${storage}" var="product">--%>
<%--        <tr><td> <c:out value="product.getName()"></c:out></td><td>3,5</td><td>36</td><td>23</td></tr>--%>
<%--        <c:out value="${product}"/>--%>
<%--    </c:forEach>--%>

<%--    <%= for (Product product : StorageOfProducts.returnStorage()){--%>
<%--    "<tr>" +--%>
<%--        "<td>" + product.getName() + "</td>" +--%>
<%--        "<td>" + product.getDescription() + "</td>" +--%>
<%--        "<td>" + product.getPrice() + "</td>"+--%>
<%--    "</tr>"--%>
<%--    }%>--%>