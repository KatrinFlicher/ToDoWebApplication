<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<table width="100%">
    <c:choose>
        <c:when test="${not empty user}">
            <tr>
                <td>
                    <c:out value="User: ${user.name}"/>
                </td>
                <td><a href="<c:url value='/logout'/>">Logout</a></td>
            </tr>
        </c:when>
        <c:otherwise>
            <tr>
                <td>User: guest</td>
                <td><a href="login.jsp">Login</a></td>
                <td><a href="<c:url value='register.jsp'/>">Registrate</a></td>
            </tr>
        </c:otherwise>
    </c:choose>
</table>
<hr>
<c:if test="${ not empty errorMessage}">
    <c:out value="${errorMessage}"/>
    <hr>
</c:if>
</body>
</html>
