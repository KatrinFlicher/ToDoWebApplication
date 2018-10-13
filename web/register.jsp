<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.gsu.epamlab.constants.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrate</title>
</head>
<body>
    <form name="registerForm" method="POST" action="<c:url value='/register'/>">
        Login:<br>
        <input type="text" name="<%=Constants.KEY_LOGIN%>" value=""><br>
        Password:<br>
        <input type="password" name="<%=Constants.KEY_PASSWORD%>" value=""><br>
        First name:<br>
        <input type="text" name="<%=Constants.KEY_NAME%>" value=""><br>
        Email:<br>
        <input type="email" name="<%=Constants.KEY_EMAIL%>" value=""><br>
        <input type="submit" value="Registrate">
    </form>
</body>
</html>
