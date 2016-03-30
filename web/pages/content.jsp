<%@page import="beans.Book" %>
<%@ page import="beans.BookList" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<h1>Read page</h1>
<%--<param name="pdf" value="<%= request.getContextPath()%>/PdfContent?name=<%=request.getParameter("name")%>--%>
<%--&session_id=<%=request.getSession().getId()%>"/>--%>
<%
    request.setCharacterEncoding("UTF-8");
%>
Book: <b><%=request.getParameter("name")%>
</b>
<br>

<%
    String nameBook = request.getParameter("name");
    byte pdfInByteArr[] = null;

    List<Book> bookList = new BookList().getBookList();
    for (Book book : bookList) {
        if (book.getName().equalsIgnoreCase(nameBook)) {
            pdfInByteArr = book.getContent();
        }
    }


%>


</body>
</html>
