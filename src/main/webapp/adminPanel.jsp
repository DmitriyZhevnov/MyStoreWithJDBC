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
<% Person person = (Person) session.getAttribute("currentUser"); %>
<% if(!person.getStatus().equals("admin")){
    application.getRequestDispatcher("/Error").forward(request,response);
}%>
<a href="/adminPanelChangeStatus.jsp">Сделать пользователя Администратором</a>
<br/>
<a href="/adminPanelDeleteUser.jsp">Удалить пользователя</a>
<p><a href="personalPageForAdmin.jsp">Назад</a></p>
</body>
</html>
