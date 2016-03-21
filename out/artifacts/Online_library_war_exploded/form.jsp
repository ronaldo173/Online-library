<%--
  Created by IntelliJ IDEA.
  User: Santer
  Date: 21.03.2016
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h1>Hello!</h1>
<br><br>

<form action="CalcServlet" method="get">
    <table border="1">
        <tr>
            <td><b>First number: </b></td>
            <td><input type="text" name="one"></td>
        </tr>
        <tr>
            <td><b>Second number:</b> </td>
            <td><input type="text" name="two"></td>
        </tr>
        <tr>
            <td><b>...operation:</b></td>
            <td><input type="text" name="operation"></td>
        </tr>

        <tr>
            <td colspan="2"><input type="submit" value="calculate"></td>
        </tr>
    </table>
</form>

</body>
</html>
