<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="lib.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="lib.commands.ListCommand" %>
<%@ page import="lib.global_options.GlobalOptions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>InputLibrarianRequestPage</title>
    </head>
    <body>
        <%
            String optionsUrl = request.getContextPath() + "/options_input";
            String url = request.getContextPath();
        %>
            <a href="<%=url%>/showAddCommand">add</a>
            <a href="<%=optionsUrl%>/add_options.jsp?command=add">add</a>
            <a href="<%=optionsUrl%>/delete_options.jsp?command=delete">delete</a>
            <a href="<%=optionsUrl%>/update_options.jsp?command=update">update</a>
            <a href="<%=optionsUrl%>/search_options.jsp?command=search">search</a>
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
