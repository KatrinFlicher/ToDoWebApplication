<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
</head>
<body>
<table>
    <tr class="section">
        <td><a href="addTask.jsp">Add task</a></td>
        <c:choose>
            <c:when test="${section eq 'RECYCLE_BIN'}"/>
            <c:when test="${section eq 'FIXED'}"/>
            <c:otherwise>
                <td><a href="JavaScript:sendEditFunction('FIX')">Fix task</a></td>
            </c:otherwise>
        </c:choose>
        <c:if test="${section ne 'RECYCLE_BIN'}">
            <td><a href="JavaScript:sendEditFunction('RECYCLE')">Recycle task</a></td>
        </c:if>
        <c:if test="${section eq 'RECYCLE_BIN'}">
            <td><a href="JavaScript:sendEditFunction('REMOVE')">Remove task</a></td>
            <td><a href="JavaScript:sendEditFunction('RESTORE')">Restore task</a></td>
            <td><a href="JavaScript:sendEditFunction('CLEAN')">Clean bin</a></td>
        </c:if>
    </tr>
</table>
</body>
</html>
