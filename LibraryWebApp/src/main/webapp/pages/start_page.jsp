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
        
        <%if (request.getSession().getAttribute("view") != null)%><%= request.getSession().getAttribute("view") %>
        <%request.getSession().removeAttribute("view");%>
        <p>
        <a href="<%=url%>/showAdd.do">add</a>
        <a href="<%=url%>/showUpdate.do">update</a>
        <a href="<%=url%>/showSearch.do">search</a>
        
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
                        <td><a id="<%=book.getId()%>" href='${pageContext.request.contextPath}/delete.do?id='
                               onclick="this.href+=id">delete</a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </TABLE>

    </body>
</html>
