<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ToDoApplication</title>
    <script type="text/javascript" src="Content/JS/javaScripts.js"></script>
</head>
<body>


    <c:choose>

        <c:when test="${not empty tasks}">
            <form action="/edittask" name="formEdit" method="get">
                <input type="hidden" name="editFunction" value="">
                <input type="hidden" name="markedTask" value="">
            </form>
                <table>
                    <tr class="currSect">
                        <th colspan="4"><c:if test="${not empty section}"> ${section}</c:if></th>
                    </tr>
                    <c:forEach var="task" items="${tasks}" varStatus="theCount" >

                        <tr>
                            <td><input type="checkbox" name="editCheck" value="<c:out value='${task.id}'/>"/></td>
                            <!--Здесь пришлось пойти на уловку-->
                            <td>${task.contentTask}</td>
                        <c:choose>
                            <c:when test="${section eq 'TODAY'}"/>
                            <c:when test="${section eq 'TOMORROW'}"/>
                            <c:otherwise> <td>${task.date}</td>
                            </c:otherwise>
                        </c:choose>
                        <!--test (section ne 'TODAY') || (section ne 'TOMORROW')}"-->

                        <c:choose>
                            <c:when test="${section eq 'RECYCLE_BIN'}"/>
                        <c:when test="${task.fileName eq 'no file'}">
                            <td>
                                <div>
                                    <form name="fileForm" action="/fileservice" method="post" enctype="multipart/form-data">
                                        <input type="file" name="file" id="file">
                                        <input type="submit" name="fileAction" value="UPLOAD">
                                        <input type="hidden" value="<c:out value="${task.id}"/>" name="idTask">
                                        <input type="hidden" value="<c:out value="${task.fileName}"/>" name="nameFile">
                                    </form>
                                </div>
                            </td>

                        </c:when>
                        <c:otherwise>
                            <td>${task.fileName}
                            <form action="/fileservice" method="post" >
                                <input type="submit" name="fileAction" value="DELETE">
                                <input type="submit" name="fileAction" value="DOWNLOAD">
                                <input type="hidden" value="<c:out value="${task.id}"/>" name="idTask">
                                <input type="hidden" value="<c:out value="${task.fileName}"/>" name="nameFile">

                            </form></td>
                        </c:otherwise>
                        </c:choose>
                        </tr>
                    </c:forEach>
            </table>
            <%@include file="menuEditTask.jsp"%>
        </c:when>
        <c:otherwise>
            <center>
                <div class="text">
                    There are not tasks.<br>
                </div>
                <div style="width: 80%" class="add">
                    <a href="addTask.jsp">Add task</a>
                </div>
            </center>


        </c:otherwise>

    </c:choose>

</body>
</html>
