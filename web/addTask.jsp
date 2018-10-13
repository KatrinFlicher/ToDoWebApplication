<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Kate
  Date: 28.08.2018
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Task</title>
    <link href="Content/style.css" rel="stylesheet">
</head>
<body>
<c:if test="${ not empty errorMessage}">
    <c:out value="${errorMessage}"/>
    <hr>
</c:if>
<form form action="/addtask" method="POST" name="formEdit">
    <input type="hidden" name="editFunction" value="ADD">
    <table border="1px">
        <div class="text">
            Please, fill next cells:
        </div>
        <hr>
        <tr>
            <td>
                Content: <input type="text" name="conTask" value="Content of task">
            </td>

                <c:if test="${section eq 'SOMEDAY'}">
            <td>
                    Date: <input type="date" name="dataTask" value="Date of task">
            </td>
                </c:if>

            <td>
                <input type="submit" name="Add task" class="enter">
            </td>
        </tr>
    </table>
</form>
<%@include file="footer.jsp"%>
</body>
</html>
