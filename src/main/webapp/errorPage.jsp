<%--
  Created by IntelliJ IDEA.
  User: Жевновы
  Date: 13.11.2020
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ой, ошибка</title>
</head>
<body>
<% Integer code = (Integer) request.getAttribute("javax.servlet.error.status_code"); %>
<h1>Ой...</h1>
Вылетела ошибка <%= code.toString() %>. <br/>
Перейдите по этой <a href="/login">ссылке</a> и авторизуйтесь снова.
</body>
</html>
