<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.gsu.epamlab.constants.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="Content/style.css" rel="stylesheet">
</head>
<body>
<center>
    <div class="login">
        <form name="loginForm" method="POST" action=<c:url value='/login'/>>
            Login:<br>
            <input type="text" name="<%=Constants.KEY_LOGIN%>" value=""><br>
            Password:<br>
            <input type="password" name="<%=Constants.KEY_PASSWORD%>" value=""><br><br>
            <input type="submit" value="ENTER" class="enter">
        </form>
    </div>
</center>
<%@include file="footer.jsp"%>
</body>
</html>
