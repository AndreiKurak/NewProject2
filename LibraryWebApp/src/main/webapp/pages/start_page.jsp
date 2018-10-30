<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="lib.Book" %>
<%@ page import="java.util.List" %>
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
        <a href="<%=url%>/add.show">add</a>
        <a href="<%=url%>/update.show">update</a>
        <a href="<%=url%>/search.show">search</a>
        <form name="form" action="${pageContext.request.contextPath}/delete.do" method="post">
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
                        for(Book book : (List<Book>) request.getAttribute("booksList")){
                    %>
                    <tr>
                        <td><%=book.getId() %></td>
                        <td><%=book.getAuthor() %></td>
                        <td><%=book.getTitle() %></td>
                        <td><%=book.getYear() %></td>
                        <td><input type="submit" value="delete" id="<%=book.getId()%>"
                                   onclick="document.getElementById('id').name='id';
                                   document.getElementById('id').value=id">
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </TABLE>
            <input type="hidden" id="id">
        </form>
    </body>
</html>
