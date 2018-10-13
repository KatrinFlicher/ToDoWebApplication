<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<form  name="sects" method="get" action="/taskcontrol">
    <input name="section" type="hidden" value="">
<table>
    <tr class="section">
        <td><a href="JavaScript:sendSection('TODAY')" >Today</a></td>
        <td><a href="JavaScript:sendSection('TOMORROW')" >Tomorrow</a></td>
        <td><a href="JavaScript:sendSection('SOMEDAY')" >Someday</a></td>
        <td><a href="JavaScript:sendSection('FIXED')" >Fixed</a></td>
        <td><a href="JavaScript:sendSection('RECYCLE_BIN')" >Recycle Bin</a></td>
    </tr>
</table>
</form>
</body>
</html>
