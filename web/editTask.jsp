<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Kate
  Date: 28.08.2018
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form form action="/edittask" method="post" name="formEdit">
    <input type="hidden" name="editFunction" value="EDIT">
    <input type="hidden" name="idTask" value="${param.id}">
    <table border="1px">
        <tr>
            <td>
                <input type="text" name="conTask" value="${param.conTask}">
            </td>
            <td>
                <input type="date" name="dataTask" value="${param.data}">
            </td>
            <td>
                <input type="submit" name="Edit task">
            </td>
        </tr>
    </table>
</form>

</body>
</html>
