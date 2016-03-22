<%--
  Created by IntelliJ IDEA.
  User: Santer
  Date: 07.03.2016
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" ; charset="utf-8">
    <title>Library enter</title>
    <link href="css/style_index.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="main">

    <div class="content">
        <p class="title"><span class="text"><img src="images/lib.png" align="middle"> </span></p>
        <p class="title">Online library!</p>
        <p class="text">You are welcome in library....</p>
    </div>

    <div class="login_div">
        <p class="title">Enter form:</p>
        <form class="login_form" name="username" action="pages/main.jsp" method="post">
            Name: <input type="text" name="username" value="" size="20"/>
            <input type="submit" value="Enter"/>
        </form>

    </div>
    <div class="footer">
        Developer: Alexandr, 2016
    </div>

</div>

<p>some tests here now:</p>
<a href="les5_html.html">got Les5 html</a>
<br>
<a href="SecondServlet">go servlet2</a>
<br>
<a href="CalcServlet">Calc</a>
<br><br>
<a href="form.jsp">Go form</a>
</body>
</html>
