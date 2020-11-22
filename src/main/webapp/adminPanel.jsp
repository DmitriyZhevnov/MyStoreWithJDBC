<%@ page import="classes.Person" %><%--
  Created by IntelliJ IDEA.
  User: Жевновы
  Date: 13.11.2020
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Админ-панель</title>
</head>
<body>
<%@ page import="classes.Person" %>
<%@ page import="classes.StorageOfUsers" %>
<% Person person = (Person) session.getAttribute("currentUser"); %>
<% if(!person.getStatus().equals("admin") || StorageOfUsers.findPersonInStorageByLogin(person.getLogin()) == null){
    session.setAttribute("currentUser", null);
    application.getRequestDispatcher("/Error").forward(request,response);
}%>
<a href="/adminPanelChangeStatus.jsp">Сделать пользователя Администратором</a>
<br/>
<a href="/adminPanelDeleteUser.jsp">Удалить пользователя</a>
<br/> <br/>
<a href="/adminEditStorageOfProducts.jsp">Редактировать хранилище</a>
<br/>
<a href="/adminOrderHistoryPanel.jsp">Операции с Историей заказов</a>
<p><a href="personalPageForAdmin.jsp">Назад</a></p>
</body>
</html>
