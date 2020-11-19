<%--
  Created by IntelliJ IDEA.
  User: Жевновы
  Date: 13.11.2020
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменить данные</title>
</head>
<body>
<%@ page import="classes.Person" %>
<%@ page import="classes.StorageOfUsers" %>
<% Person person = (Person) session.getAttribute("currentUser"); %>
<% if(StorageOfUsers.findPersonInStorageByLogin(person.getLogin()) == null){
    session.setAttribute("currentUser", null);
    application.getRequestDispatcher("/Error").forward(request,response);
}%>
<form action="/myPage" method="post">
    Имя: <input type="text" name="name" value=<%= person.getName() %>>
    <br/>
    Возраст: <input type="text" name="age" value=<%= person.getAge() %>>
    <br/>
    Логин: <input type="text" name="login" value=<%= person.getLogin() %>>
    <br/>
    Пароль: <input type="text" name="password"/>
    <p><a href="/myPage">Назад</a>
        <input type="submit" value="Сохранить"/> </p>
</form>

<p style="color:#bf3b3b">Примечание: Пустое окно пароля оставит его прежним.</p>
</body>
</html>
