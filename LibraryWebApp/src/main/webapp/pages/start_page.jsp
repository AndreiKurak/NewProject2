<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="lib.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="lib.connectors.DataConnection" %>
<%@ page import="lib.connectors.DataBaseConnector" %>
<%@ page import="lib.connectors.Books" %>
<%@ page import="common.views.ErrorView" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>InputLibrarianRequestPage</title>
    </head>
    <body>
        <form action="<c:url value="/pages/input_options.jsp"/>">
            action: <select name="selection">
                        <option name="selection" value="add author title year" selected>add</option>
                        <option name="selection" value="delete id">delete</option>
                        <option name="selection" value="update id author title year">update</option>
                        <option name="selection" value="list all authors titles years">list</option>
                        <option name="selection" value="search author title year">search</option>
                    </select>
                
                <input type="submit" value="confirm">
        </form>
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
                        %>
                  <%=ex.getMessage()%>
                <%
                    }
                %>
            </tbody>
        </TABLE>
    </body>
</html>
