<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="spring_xml_library.Book"%>
<html>
    <head>
        <title>Home</title>
    </head>
    <body>
        <%
            String url = request.getContextPath();
        %>

        <a href="<%=url%>/options/add">add</a>
        <a href="<%=url%>/options/delete">delete</a>
        <a href="<%=url%>/options/search">search</a>
        <a href="<%=url%>/options/update">update</a>
        <a href="<%=url%>/test/add">update</a>
        ${message}

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
                for(Book book : (List<Book>) request.getAttribute("books")){
            %>
            <tr>
                <td><%=book.getId() %></td>
                <td><%=book.getAuthor() %></td>
                <td><%=book.getTitle() %></td>
                <td><%=book.getYear() %></td>
                <td><a href='${pageContext.request.contextPath}/command/delete?id=<%=book.getId()%>'>delete</a></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </TABLE>

    </body>
</html>
