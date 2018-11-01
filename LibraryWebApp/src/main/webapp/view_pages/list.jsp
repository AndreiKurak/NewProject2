<%@ page import="java.util.List" %>
<%@ page import="lib.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>List</title>
        <H2>List of books</H2>
    </head>
    <body>
        <%
            List<Book> books = (List<Book>) request.getSession().getAttribute("view");
            for (Book book : books) {
        %>
            <p><%=book%></p>
        <%
            }
        %>
        <%request.getSession().removeAttribute("view");%>
        <p>
            <a href="<%=request.getContextPath()%>/home">Home Page</a>
        </p>
    </body>
</html>
