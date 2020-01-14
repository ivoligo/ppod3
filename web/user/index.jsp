
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <title>Страница пользователя</title>
</head>
<body>
<c:set var="user" value="${sessionScope.user}"/>

    Страница пользователя id = ${user.id}

    <p>здесь могла быть ваша реклама</p>


        <table border="2">
            <td>
                 <input type="text" name="id" value="${user.id}" readonly>
                 <input required type="text" name="login" value="${user.login}" placeholder="Login">
                 <input required type="text" name="age" value="${user.age}" placeholder="Возраст">
                 <input required type="text" name="city" value="${user.city}" placeholder="Город">
                 <input type="text" name="role" value="${user.role}" placeholder="Роль" readonly>
                 <input required type="password" name="password" value="${user.password}" placeholder="Паролька">
            </td>
        </table>



    </body>
</html>
