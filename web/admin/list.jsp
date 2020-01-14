<%@ page import="java.util.List" %>
<%@ page import="model.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <title>Список пользователей</title>
</head>
<body>

<table border="2">
    <tr>
        <th>ID</th>
        <th>Логин</th>
        <th>Возраст</th>
        <th>Город</th>
        <th>Роль</th>
        <th>Действие</th>
    </tr>

    <%--@elvariable id="users" type="java.util.List"--%>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getLogin()}</td>
            <td>${user.getAge()}</td>
            <td>${user.getCity()}</td>
            <td>${user.getRole()}</td>

            <td>
                <a href="${pageContext.request.contextPath}/admin/edit?id=${user.getId()}" >
                    <input type="submit" value="Измениить">
                </a>

                <form action="${pageContext.request.contextPath}/admin/delete?id=${user.getId()}" method="get">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <input type="submit" value="Удалить" style="float:right">
                </form>
            </td>
        </tr>
    </c:forEach>
    <div>

    </div>
</table>
<form action = "${pageContext.request.contextPath}/admin/add">
    <input type="submit" value="Добавить пользователя">
</form>
</body>
</html>

