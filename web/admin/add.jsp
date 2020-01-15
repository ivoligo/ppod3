<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление нового пользователя</title>
</head>
<body>

<form method="post">
    <input required type="text" name="login" placeholder="Логин">
    <input required type="number" name="age" placeholder="Возраст">
    <input required type="text" name="city" placeholder="Город">
    <select name="userRole" >
        <option>назначь роль </option>
        <option value="user">user</option>
        <option value="admin">admin</option>
    </select>
    <input required type="password" name="password" placeholder="Пароьлька">
    <input type="submit" value="Схоранить">
</form>
<form action="${pageContext.request.contextPath}/admin/list" >
    <input type="submit" value="Список пользователей">
</form>

</body>
</html>
