<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="lib.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="lib.connectors.DataConnection" %>
<%@ page import="lib.connectors.DataBaseConnector" %>
<%@ page import="lib.connectors.Books" %>
<%@ page import="java.util.logging.Logger" %>
<%@ page import="java.util.logging.Level" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>InputLibrarianRequestPage</title>
    </head>
    <body>
        <%
            String optionsUrl = request.getContextPath() + "/options_input";
        %>
            <a href="<%=optionsUrl%>/add_options.jsp?command=add">add</a>
            <a href="<%=optionsUrl%>/delete_options.jsp?command=delete">delete</a>
            <a href="<%=optionsUrl%>/update_options.jsp?command=update">update</a>
            <a href="<%=optionsUrl%>/search_options.jsp?command=search">search</a>
        <%
            List<Book> bookList = null;
            try {
                DataConnection connector = new DataBaseConnector();
                Books books = connector.read();
                bookList = books.list();
                request.setAttribute("bookList", bookList);
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
                    }
                    catch (Exception ex) {
                        Logger.getLogger("start_page.jsp").log(Level.SEVERE, "Exception:", ex);
                        %>
                        <p>
                        <%=ex.getMessage()%>
                        </p>
                <%
                    }
                %>
            </tbody>
        </TABLE>
    </body>
</html>
