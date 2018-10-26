<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="lib.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="lib.commands.ListCommand" %>
<%@ page import="lib.global_options.GlobalOptions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>InputLibrarianRequestPage</title>
        <h2>Library</h2>
    </head>
    <body>
        <%
            String url = request.getContextPath();
        %>
        <%if (request.getAttribute("view") != null)%><%= request.getAttribute("view") %>
        <p>
        <a href="<%=url%>/add.do">add</a>
        <a href="<%=url%>/delete.do">delete</a>
        <a href="<%=url%>/update.do">update</a>
        <a href="<%=url%>/search.do">search</a>
        <%
            List<Book> bookList = (List<Book>) new ListCommand().execute(null, new GlobalOptions()).getModel();
        %>
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
                    for(Book book : bookList){
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
    </body>
</html>
