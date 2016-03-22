<%--
  Created by IntelliJ IDEA.
  User: Developer
  Date: 22.03.2016
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Online library</title>
</head>
<body>
<% request.setCharacterEncoding("UTF-8"); %>
<%="Hello"%>

<h3>
    <%=request.getParameter("username")%>
</h3>
<h3>
    ${param["password"]}
</h3>


</body>
</html>
