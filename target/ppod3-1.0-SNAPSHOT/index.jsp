
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
    <style type="text/css">
        input {width:100% }
    </style>
</head>
<body>

    <form action="login" method="post">
        <table border = 2 >
            <tr>
                <td> Login: </td><td> <input required type="text" name="login" placeholder="Логин" > </td>
            </tr>
            <tr>
                <td>Password:</td><td> <input required type="password" name="password" placeholder="Пароль"> </td>
            </tr>
            <tr >
                <td colspan="2" align="center">  <input type="submit" value="Log In" > </td>
            </tr>
        </table>
    </form>

</body>
</html>
