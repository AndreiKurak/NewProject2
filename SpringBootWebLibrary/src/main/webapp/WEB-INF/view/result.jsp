<%@ page import="spring_boot_library.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.03.2019
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Result</title>
        <h2>Result</h2>
    </head>
    <body>
        <TABLE border="4" class="table table-striped table-bordered" cellspacing="0" width="50%">
            <thead>
            <tr>
                <th>ID</th>
                <th>Author</th>
                <th>Title</th>
                <th>Year</th>
            </tr>
            </thead>
            <tbody>

            <%
                for(Book book : (List<Book>) request.getAttribute("message")){
            %>
            <tr>
                <td><%=book.getId() %></td>
                <td><%=book.getAuthor() %></td>
                <td><%=book.getTitle() %></td>
                <td><%=book.getYear() %></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </TABLE>
        <p>
            <a href="<%=request.getContextPath()%>/home">Home Page</a>
    </body>
</html>
